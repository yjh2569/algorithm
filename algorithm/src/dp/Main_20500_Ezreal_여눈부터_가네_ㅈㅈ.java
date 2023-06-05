package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20500_Ezreal_여눈부터_가네_ㅈㅈ {
	static int CONST = 1_000_000_007;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// dynamic programming을 활용한다.
		// dp[n][0], dp[n][1], dp[n][2]는 끝자리가 1이고 3으로 나눈 나머지가 0, 1, 2인 경우의 수를 나타낸 것이다.
		// dp[n][3], dp[n][4], dp[n][5]는 끝자리가 5이고 3으로 나눈 나머지가 0, 1, 2인 경우의 수를 나타낸 것이다.
		// 3의 배수는 각 자리수를 모두 합한 수를 3으로 나눈 나머지가 0이어야 하고, 5의 배수는 끝자리가 0 또는 5여야 한다.
		// 따라서 구해야 하는 수는 dp[N][3]이다.
		int[][] dp = new int[N+1][6];
		dp[1][1] = 1;
		dp[1][5] = 1;
		// 부분해를 구할 때, 이전 수를 3으로 나눈 나머지가 k일 때 
		// 끝자리에 1을 붙이면 3으로 나눈 나머지가 (k+1)%3이 되고,
		// 끝자리에 5를 붙이면 3으로 나눈 나머지가 (k+2)%3이 된다.
		for (int n = 2; n <= N; n++) {
			dp[n][0] = (dp[n-1][2] + dp[n-1][5])%CONST;
			dp[n][1] = (dp[n-1][0] + dp[n-1][3])%CONST;
			dp[n][2] = (dp[n-1][1] + dp[n-1][4])%CONST;
			dp[n][3] = (dp[n-1][1] + dp[n-1][4])%CONST;
			dp[n][4] = (dp[n-1][2] + dp[n-1][5])%CONST;
			dp[n][5] = (dp[n-1][0] + dp[n-1][3])%CONST;
		}
		System.out.println(dp[N][3]);
	}

}
