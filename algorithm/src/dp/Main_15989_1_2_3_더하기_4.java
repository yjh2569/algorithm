package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15989_1_2_3_더하기_4 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// 1, 2, 3의 합으로 나타내는 경우의 수를 구하기 위해 dynamic programming을 사용한다.
		// dp[i][j]는 i를 1, 2, 3의 합으로 나타내면서 수들을 내림차순으로 정렬했을 때 마지막에 오는 숫자가 (j+1)인 경우의 수를 의미한다.
		long[][] dp = new long[10001][3];
		// 초기값
		dp[1][0] = 1;
		dp[2][0] = 1;
		dp[2][1] = 1;
		dp[3][0] = 2;
		dp[3][2] = 1;
		// 테스트케이스 진행 중 지금까지 경우의 수를 구한 가장 큰 수
		int max = 3;
		StringBuilder sb = new StringBuilder();
		// 각 테스트케이스에 대한 경우의 수를 구한다.
		// 이때, 숫자의 순서만 다른 경우는 같은 경우로 간주하므로 1, 2, 3의 합으로 나타낼 때 항상 내림차순으로 더하도록 한다.
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// 아직 경우의 수를 구하지 않은 수라면 지금까지 경우의 수를 구한 가장 큰 수 이후부터 경우의 수를 차례대로 구한다.
			if (dp[N][0] + dp[N][1] + dp[N][2] == 0){
				for (int i = max+1; i <= N; i++) {
					// 더하는 숫자가 1인 경우
					dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2];
					// 더하는 숫자가 2인 경우
					dp[i][1] = dp[i-2][1] + dp[i-2][2];
					// 더하는 숫자가 3인 경우
					dp[i][2] = dp[i-3][2];
				}
			}
			// max 최신화
			max = Math.max(max, N);
			// 경우의 수 출력에 추가
			sb.append(dp[N][0] + dp[N][1] + dp[N][2]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
