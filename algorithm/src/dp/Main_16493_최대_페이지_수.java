package dp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16493_최대_페이지_수 {
	static class Chapter {
		int day, page;
		public Chapter(int day, int page) {
			this.day = day;
			this.page = page;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Chapter[] chapters = new Chapter[M+1];
		int[][] dp = new int[M+1][N+1];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int page = Integer.parseInt(st.nextToken());
			chapters[i] = new Chapter(day, page);
		}
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = dp[i-1][j];
				if (j < chapters[i].day) continue;
				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-chapters[i].day] + chapters[i].page);
			}
		}
		int max = 0;
		for (int i = 0; i <= N; i++) {
			max = Math.max(max, dp[M][i]);
		}
		System.out.println(max);
	}

}
