package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2624_동전_바꿔주기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		// dynamic programming을 사용
		// dp[i][j]는 j번째 동전까지 사용했을 때 i원을 만드는 경우의 수를 의미한다.
		int[][] dp = new int[T+1][k+1];
		// 초기값 설정
		dp[0][0] = 1;
		StringTokenizer st;
		for (int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			// 각 동전에 대해 동전을 0개~n개 쓰는 경우를 고려한다.
			for (int j = 0; j <= n; j++) {
				// 동전을 j개 쓰는 경우 만들 수 있는 금액의 범위는 p*j원~T원이다.
				for (int t = p*j; t <= T; t++) {
					dp[t][i] += dp[t-p*j][i-1];
				}
			}
		}
		// 구하고자 하는 경우의 수는 dp[T][k]와 같다.
		System.out.println(dp[T][k]);
	}

}
