package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1261_알고스팟 {
	// 현재 위치의 좌표, 벽을 부순 횟수를 저장하는 클래스
	static class Location implements Comparable<Location> {
		int r, c, cnt;
		public Location(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		public int compareTo(Location l) {
			return Integer.compare(this.cnt, l.cnt);
		}
	}
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		// djikstra's algorithm을 활용한다.
		int[][] dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
		dp[0][0] = 0;
		PriorityQueue<Location> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][M];
		pq.offer(new Location(0, 0, 0));
		while (!pq.isEmpty()) {
			Location l = pq.poll();
			if (visited[l.r][l.c]) continue;
			visited[l.r][l.c] = true;
			for (int d = 0; d < 4; d++) {
				int nr = l.r + dr[d];
				int nc = l.c + dc[d];
				if (check(nr, nc) && !visited[nr][nc]) {
					if (dp[nr][nc] > dp[l.r][l.c] + map[nr][nc]) {
						dp[nr][nc] = dp[l.r][l.c] + map[nr][nc];
						pq.offer(new Location(nr, nc, dp[nr][nc]));
					}
				}
			}
		}
		System.out.println(dp[N-1][M-1]);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
