package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1757_달려달려 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] distances = new int[N+1];
		for (int i = 1; i <= N; i++) {
			distances[i] = Integer.parseInt(br.readLine());
		}
		// dynamic programming을 활용한다.
		// dp[i][j]는 i분이 지났을 때 지침지수가 j인 경우 갈 수 있는 거리의 최댓값을 의미한다.
		// j가 0보다 큰 경우는 현재 달리는 경우만 고려한다.
		int[][] dp = new int[N+1][M+1];
		// 초기화
		for (int j = 1; j <= M; j++) dp[0][j] = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			// i분일 때 달리는 경우
			// 지침지수가 0이 되지 않는 경우는 이전에 달리는 경우밖에 없다.
			for (int j = 1; j <= M; j++) {
				dp[i][j] = dp[i-1][j-1] + distances[i];
			}
			// 한 번 쉬면 지침지수가 0이 될 때까지 쉬어야 하기 때문에, 
			// 지침지수가 0이 되는 경우는 직전에 지침지수가 0이 된 경우와,
			// (i-j)분 전에 지침지수가 j인 경우(1<=j<=min(i, M))다.
			dp[i][0] = dp[i-1][0];
			for (int j = 1; j <= Math.min(i, M); j++) {
				dp[i][0] = Math.max(dp[i][0], dp[i-j][j]);
			}
		}
		System.out.println(dp[N][0]);
	}
}
