package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1106_호텔 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] costs = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[2*C+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp, Integer.MAX_VALUE/10);
		}
		dp[0] = 0;
		for (int i = 1; i <= 2*C; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i] = Math.min(dp[i], dp[Math.max(0, i-costs[j][1])] + costs[j][0]);
			}
		}
		int min = Integer.MAX_VALUE;
		for (int j = C; j <= 2*C; j++) {
			min = Math.min(min, dp[j]);
		}
		System.out.println(min);
	}

}
