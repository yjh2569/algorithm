package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20058_마법사_상어와_파이어스톰 {
	static int N, Q, max;
	static int[][] map;
	static int[][] temp;
	static boolean[][] isMelt;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = 1 << Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		// 입력으로 받은 얼음판 상태
		map = new int[N][N];
		// 부분 격자를 시계 방향으로 회전시키기 위해 이전 얼음판 상태를 저장해두는 배열
		temp = new int[N][N];
		// 각 칸이 녹을지 나타내는 배열
		isMelt = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] phase = new int[Q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			phase[i] = 1 << Integer.parseInt(st.nextToken());
		}
		// Q번 시뮬레이션하면서 얼음판의 상태를 변화시킨다.
		for (int i = 0; i < Q; i++) {
			// 단계 phase[i]에 따라 모든 부분 격자를 시계 방향으로 회전시킨다.
			rotateAll(phase[i]);
			// 각 얼음이 이웃한 얼음의 개수에 따라 얼음을 녹인다.
			melt();
		}
		// 남아있는 얼음의 합
		int sum = 0;
		// 가장 큰 덩어리가 차지하는 칸의 개수
		max = 0;
		// max를 알아내기 위해 모든 얼음 덩어리에 대해 BFS를 수행한다.
		visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sum += map[r][c];
				if (!visited[r][c] && map[r][c] > 0) bfs(r, c);
			}
		}
		System.out.println(sum + "\n" + max);
	}
	// 단계 q에 대한 모든 부분 격자를 회전시키는 함수
	private static void rotateAll(int q) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		for (int R = 0; R < N; R += q) {
			for (int C = 0; C < N; C += q) {
				rotate(R, C, q);
			}
		}
	}
	// 각 부분 격자를 시계 방향으로 90도 회전하는 함수
	private static void rotate(int R, int C, int q) {
		for (int r = 0; r < q; r++) {
			for (int c = 0; c < q; c++) {
				map[c+R][q-1-r+C] = temp[R+r][C+c];
			}
		}		
	}
	// 인접한 얼음 개수에 따라 각 칸의 얼음을 녹이는 함수
	private static void melt() {
		// 우선 각 칸에 인접한 얼음 개수를 센 뒤, 동시에 얼음을 녹여야 함에 유의
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				isMelt[r][c] = false;
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (check(nr, nc) && map[nr][nc] > 0) {
						cnt++;
					}
				}
				if (cnt < 3) isMelt[r][c] = true;
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (isMelt[r][c]) map[r][c] = Math.max(map[r][c]-1, 0);
			}
		}
	}
	// 가장 큰 얼음 덩어리가 차지하는 칸의 개수를 세기 위해 BFS를 적용한다.
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		int size = 1;
		visited[r][c] = true;
		q.offer(new int[] {r, c});
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] > 0) {
					size++;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		max = Math.max(max, size);		
	}
	// 경계 확인 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
