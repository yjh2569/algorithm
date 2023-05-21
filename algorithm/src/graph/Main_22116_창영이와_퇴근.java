package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_22116_창영이와_퇴근 {
	static int N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dijkstra's algorithm을 활용한다.
		// (i, j)까지 갔을 때 최대 경사의 최솟값을 (i, j)번째 성분으로 하는 배열
		int[][] dijk = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dijk[i], Integer.MAX_VALUE);
		}
		dijk[0][0] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
		boolean[][] visited = new boolean[N][N];
		pq.offer(new int[] {0, 0, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			// 목적지에 도착한 경우
			if (u[0] == N-1 && u[1] == N-1) {
				System.out.println(dijk[N-1][N-1]);
				return;
			}
			if (visited[u[0]][u[1]]) continue;
			visited[u[0]][u[1]] = true;
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				// 인접한 지점까지의 최대 경사를 구하고 이 값이 기존 값보다 작은지 확인한다. 
				if (check(nr, nc) && !visited[nr][nc] && dijk[nr][nc] > Math.max(dijk[u[0]][u[1]], Math.abs(map[nr][nc] - map[u[0]][u[1]]))) {
					dijk[nr][nc] = Math.max(dijk[u[0]][u[1]], Math.abs(map[nr][nc] - map[u[0]][u[1]]));
					pq.offer(new int[] {nr, nc, dijk[nr][nc]});
				}
			}
		}
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
