package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3067_Coins {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			// dynamic programming을 이용한다.
			// dp[i]는 i원을 만들 수 있는 경우의 수를 의미한다.
			int[] dp = new int[M+1];
			dp[0] = 1; // 초기화
			// 금액이 적은 동전부터 사용한다.
			// 그래야 동전 사용 순서에 관계없이 동전 사용 갯수가 같으면 같은 경우로 간주한다.
			for (int i = 0; i < N; i++) {
				for (int j = coins[i]; j <= M; j++) {
					dp[j] += dp[j-coins[i]];
				}
			}
			sb.append(dp[M]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
