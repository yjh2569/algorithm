package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2698_인접한_비트의_개수 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// (n, k, 0)번째 성분은 크기가 s인 수열 중 비트의 개수가 k이면서 맨 뒤에 있는 수가 0인 수열의 개수
		// (n, k, 0)번째 성분은 크기가 s인 수열 중 비트의 개수가 k이면서 맨 뒤에 있는 수가 1인 수열의 개수
		int[][][] dp = new int[101][101][2];
		// 초기화
		dp[1][0][0] = 1;
		dp[1][0][1] = 1;
		// dynamic programming을 활용해 계산
		for (int n = 2; n <= 100; n++) {
			for (int k = 0; k < n; k++) {
				dp[n][k][0] = dp[n-1][k][0] + dp[n-1][k][1];
				dp[n][k][1] = k > 0 ? dp[n-1][k][0] + dp[n-1][k-1][1] : dp[n-1][k][0];
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			sb.append(dp[n][k][0] + dp[n][k][1]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
