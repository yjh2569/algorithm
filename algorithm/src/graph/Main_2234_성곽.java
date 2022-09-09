package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2234_성곽 {
	static int N, M;
	static int[][] map;
	// 서쪽, 북쪽, 동쪽, 남쪽을 의미
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int cnt, maxSize, maxSize2;
	static int[][] visited;
	static ArrayList<Integer> sizes;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 이 성의 있는 방의 개수, 가장 넓은 방의 넓이, 하나의 벽을 제거해 얻을 수 있는 가장 넓은 방의 크기
		cnt = 0; maxSize = 0; maxSize2 = 0;
		// 각 칸이 어떤 방에 속하는지를 기록하는 방문 배열
		visited = new int[M][N];
		// 각 방의 크기를 저장하는 ArrayList
		sizes = new ArrayList<>();
		// 방 번호는 1번부터 시작하도록 한다.
		sizes.add(0);
		// 성 내의 모든 칸을 조사해 방문한 적 없는 방을 발견한 경우,
		// 해당 방의 번호와 크기를 기록한다.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j] == 0) bfs(i, j, ++cnt);
			}
		}
		// 하나의 벽을 제거해 얻을 수 있는 가장 넒은 방의 크기를 구한다.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				checkMaxSize2(i, j);
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n")
		.append(maxSize).append("\n")
		.append(maxSize2);
		System.out.println(sb.toString());
	}
	// BFS를 통해 (i, j) 칸을 포함하는 방을 조사하고, 해당 방은 k번째 방으로 한다.
	private static void bfs(int i, int j, int k) {
		// BFS 전 초기화
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j] = k;
		// k번쨰 방의 크기
		int size = 1;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				// 현재 방향 d로 갈 때 벽에 의해 막히는지를 확인한다.
				if ((map[u[0]][u[1]] & (1 << d)) != 0) continue;
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && visited[nr][nc] == 0) {
					size++;
					visited[nr][nc] = k;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		sizes.add(size);
		// 방의 크기의 최대값을 갱신
		maxSize = Math.max(maxSize, size);		
	}
	// (i, j) 칸과 인접한 칸 중 다른 방에 속하는 칸이 있는 경우,
	// 해당 방은 하나의 벽에 의해 가로막혀 있기 때문에 
	// (i, j) 칸을 포함하는 방과 다른 방의 크기의 합을 구함으로써
	// 그 벽을 제거했을 때 얻을 수 있는 방의 크기를 구할 수 있다.
	private static void checkMaxSize2(int i, int j) {
		int k = visited[i][j]; // (i, j) 칸을 포함하는 방 번호
		int size = sizes.get(k); // 현재 방의 크기
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			// 인접한 칸 중 다른 방에 포함되는 칸을 찾은 경우
			if (check(nr, nc) && visited[nr][nc] != k) {
				maxSize2 = Math.max(maxSize2, size + sizes.get(visited[nr][nc]));
			}
		}
	}
	// (r, c) 칸이 경계를 넘어가는지 확인하는 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<M && 0<=c && c<N;
	}
}
