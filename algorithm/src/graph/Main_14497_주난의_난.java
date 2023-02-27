package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14497_주난의_난 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken())-1;
		int y1 = Integer.parseInt(st.nextToken())-1;
		int x2 = Integer.parseInt(st.nextToken())-1;
		int y2 = Integer.parseInt(st.nextToken())-1;
		int[][] classroom = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				classroom[i][j] = s.charAt(j) == '1' ? 1 : 0;
			}
		}
		// dijkstra's algorithm을 이용한다.
		// 초코바를 훔쳐간 범인까지 몇 번 점프해야 하는지는 친구가 있는 칸에 방문하는 비용을 1이라고 했을 때의 배열에서
		// 범인까지의 최소 비용과 같다.
		int[][] dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
		dp[x1][y1] = 1;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
		boolean[][] visited = new boolean[N][M];
		pq.offer(new int[] {x1, y1, 1});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (u[0] == x2 && u[1] == y2) break;
			if (visited[u[0]][u[1]]) continue;
			visited[u[0]][u[1]] = true;
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<N && 0<=nc && nc<M && !visited[nr][nc] && dp[nr][nc] > dp[u[0]][u[1]] + classroom[nr][nc]) {
					dp[nr][nc] = dp[u[0]][u[1]] + classroom[nr][nc];
					pq.offer(new int[] {nr, nc, dp[nr][nc]});
				}
			}
		}
		System.out.println(dp[x2][y2]);
	}

}
