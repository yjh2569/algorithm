package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1520_내리막_길 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 각 칸에 도달하는 경우의 수를 나타내는 배열
		long[][] dp = new long[M][N];
		// 초기화
		dp[0][0] = 1L;
		// 도달 가능한 높이가 높은 칸 순서대로 조사한다. 높이가 높은 칸은 나중에 다시 방문할 일이 없기 때문이다.
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[2], x[2]));
		pq.offer(new int[] {0, 0, map[0][0]});
		// 각 칸을 조사했는지를 나타낸다.
		boolean[][] visited = new boolean[M][N];
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (u[0] == M-1 && u[1] == N-1) break;
			if (visited[u[0]][u[1]]) continue;
			visited[u[0]][u[1]] = true;
			// 인접한 지점들을 조사
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				// 만약 내리막이라면 해당 지점에 대해 경우의 수를 update하고 다음 조사를 위해 pq에 집어넣는다.
				if (0<=nr && nr<M && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc] < map[u[0]][u[1]]) {
					dp[nr][nc] += dp[u[0]][u[1]];
					pq.offer(new int[] {nr, nc, map[nr][nc]});
				}
			}
		}
		System.out.println(dp[M-1][N-1]);
	}

}
