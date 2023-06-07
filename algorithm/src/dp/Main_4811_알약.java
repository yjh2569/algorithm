package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_4811_알약 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) break; // 입력의 끝
			// dynamic programming을 이용
			// dp[i][j]는 i번째 날 반으로 쪼갠 알약의 개수가 j개일 때 가능한 서로 다른 문자열의 개수를 의미한다.
			long[][] dp = new long[2*N+1][2*N+1];
			dp[0][0] = 1; // 초기값
			for (int d = 1; d <= 2*N; d++) {
				// d번째 날 알약을 쪼개서 반 개를 먹는 경우
				for (int j = 0; j <= 2*N-2; j++) {
					dp[d][j+1] += dp[d-1][j];
				}
				// d번째 날 남은 알약 반 개를 먹는 경우
				for (int j = 1; j <= 2*N; j++) {
					dp[d][j-1] += dp[d-1][j];
				}
			}
			// 2*N번쨰 날에는 항상 알약을 모두 먹게 된다.
			sb.append(dp[2*N][0]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
