package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1563_개근상 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// dynamic programming을 활용
		// dp[i][j]는 한 학기가 i일일 때, 발생할 수 있는 경우의 수를 특정 상황에 따라 7가지로 나눈 결과다.
		// j = 0 : 지각을 한 번도 하지 않았고, 마지막 날에 출석한 경우
		// j = 1 : 마지막 날에만 지각한 경우
		// j = 2 : 지각을 한 번도 하지 않았고, 마지막 날에 결석했지만 연속해서 결석하지 않은 경우
		// j = 3 : 지각을 한 번도 하지 않았고, 마지막 날 기준 이틀 연속으로 결석한 경우
		// j = 4 : 지각을 한 번 했고, 마지막 날에 출석한 경우
		// j = 5 : 지각을 한 번 했고, 마지막 날에 결석했지만 연속해서 결석하지 않은 경우
		// j = 6 : 지각을 한 번 했고, 마지막 날 기준 이틀 연속으로 결석한 경우
		int[][] dp = new int[N][7];
		int CONST = 1_000_000;
		// 초기화
		for (int i = 0; i < 3; i++) {
			dp[0][i] = 1;
		}
		// 경우에 따라 경우의 수를 적절히 구한다.
		for (int i = 1; i < N; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][2] + dp[i-1][3])%CONST;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2] + dp[i-1][3])%CONST;
			dp[i][2] = dp[i-1][0]%CONST;
			dp[i][3] = dp[i-1][2]%CONST;
			dp[i][4] = (dp[i-1][1] + dp[i-1][4] + dp[i-1][5] + dp[i-1][6])%CONST;
			dp[i][5] = (dp[i-1][1] + dp[i-1][4])%CONST;
			dp[i][6] = dp[i-1][5]%CONST;
		}
		// 모든 경우의 수를 구한다.
		int sum = 0;
		for (int i = 0; i < 7; i++) {
			sum = (sum + dp[N-1][i])%CONST;
		}
		System.out.println(sum);
	}

}
