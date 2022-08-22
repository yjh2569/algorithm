package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
	static int N, M;
	static int[][] icebergs;
	static boolean[][] visited;
	static int[][] decrease;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static Queue<int[]> q;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		icebergs = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				icebergs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// BFS를 위한 방문 배열
		visited = new boolean[N][M];
		// 현재 빙산 상태에 따른 올해 감소하는 빙산의 높이
		decrease = new int[N][M];
		// 빙산이 두 덩어리로 분리되는 최초의 시간(년)
		int year = 0;
		while (true) {
			// 빙산이 다 녹았는지, 한 덩어리만 있는지, 그 이상 있는지 확인
			int res = check();
			// 빙산이 다 녹은 경우
			if (res == 0) {
				System.out.println(0);
				System.exit(0);
			// 빙산이 여러 덩어리가 있는 경우
			} else if (res > 1) {
				break;
			}
			// 빙산이 한 덩어리만 있는 경우
			// 빙산이 녹는 과정을 시뮬레이션한다.
			melt();
			year++;
		}
		System.out.println(year);
	}
	// BFS를 통해 현재 빙산이 몇 덩어리 있는지 확인하는 함수
	private static int check() {
		// 현재 있는 빙산 덩어리의 개수
		int cnt = 0;
		// 초기화
		// 경계에는 빙산이 없으므로 그 사이에 있는 값들만 고려한다.
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				visited[i][j] = false;
			}
		}
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				// 아직 방문한 적 없는 빙산인 경우
				if (!visited[i][j] && icebergs[i][j] > 0) {
					// 첫 덩어리를 발견한 경우
					if (cnt == 0) {
						bfs(i, j);
						cnt++;
					}
					// 두 번째 덩어리를 발견한 경우
					else return cnt+1;
				}
			}
		}
		// 만약 빙산 덩어리를 하나도 발견 못 했다면 0, 하나만 발견했다면 1을 반환한다.
		return cnt;
	}
	// 빙산 덩어리를 탐색하는 BFS
	private static void bfs(int i, int j) {
		q = new LinkedList<>();
		visited[i][j] = true;
		q.offer(new int[] {i, j});
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (!visited[nr][nc] && icebergs[nr][nc] > 0) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
	}
	// 빙산이 녹는 과정을 시뮬레이션하는 함수
	private static void melt() {
		// 빙산은 동시에 녹아야 하므로, 각 빙산에 대해 감소할 높이를 미리 구한 뒤 해당 높이만큼 감소시킨다.
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				decrease[i][j] = 0;
				if (icebergs[i][j] > 0) {
					calMelt(i, j);
				}
			}
		}
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < M-1; j++) {
				if (icebergs[i][j] > 0 && decrease[i][j] > 0) {
					icebergs[i][j] = Math.max(0, icebergs[i][j] - decrease[i][j]);
				}
			}
		}
	}
	// 현재 위치에서 감소할 빙산의 높이를 구한다.
	private static void calMelt(int i, int j) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (icebergs[nr][nc] == 0) {
				cnt++;
			}
		}
		decrease[i][j] = cnt;
	}

}
