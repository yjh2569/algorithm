package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11058_크리보드 {
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N+1];
		// 초기값
		for (int i = 1; i <= N; i++) {
			dp[i] = i;
		}
		// 다이나믹 프로그래밍을 적용하나, dp[i]를 계산할 때 dp[i-1]과 dp[i-3]만 가지고 바로 dp[i]를 계산할 수는 없다.
		// 어떤 문자열을 복사하는지에 따라 당장은 dp[i]가 작을수는 있어도, dp[i+1]만 되어도 확연히 차이가 날 수 있기 때문이다.
		// 따라서 O(N^2) 알고리즘으로 이전에 나왔던 가능한 모든 문자열을 복사하는 것을 시도해 보면서 dp[i]를 계산한다.
		for (int i = 4; i <= N; i++) {
			long temp = dp[i-3]*2;
			for (int j = i; j <= N; j++) {
				dp[j] = Math.max(dp[j], temp);
				temp += dp[i-3];
			}
		}
		System.out.println(dp[N]);
	}

}
