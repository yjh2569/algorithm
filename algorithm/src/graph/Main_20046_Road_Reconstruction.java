package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20046_Road_Reconstruction {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] costs = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				costs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dijkstra's algorithm을 이용한다.
		int[][] dp = new int[m][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
		dp[0][0] = 0;
		pq.offer(new int[] {0, 0, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			// 맨 오른쪽 아래에 도달한 경우
			if (u[0] == m-1 && u[1] == n-1) break;
			if (visited[u[0]][u[1]]) continue;
			visited[u[0]][u[1]] = true;
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				// 해당 지점에 도로를 설치하거나 이미 도로가 설치되어 있어야 한다.
				if (0<=nr && nr<m && 0<=nc && nc<n && costs[u[0]][u[1]] != -1 && dp[nr][nc] > dp[u[0]][u[1]] + costs[u[0]][u[1]]) {
					dp[nr][nc] = dp[u[0]][u[1]] + costs[u[0]][u[1]];
					pq.offer(new int[] {nr, nc, dp[nr][nc]});
				}
			}
		}
		// (m-1, n-1)에 도달해도 해당 지점에 도로를 설치할 수 없는 경우도 고려해야 한다.
		System.out.println(dp[m-1][n-1] >= Integer.MAX_VALUE/10 || costs[m-1][n-1] == -1 ? -1 : dp[m-1][n-1] + costs[m-1][n-1]);
	}

}
