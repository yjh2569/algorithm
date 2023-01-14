package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2410_2의_멱수의_합 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// dynamic programming을 활용
		// dp[i]는 i를 2의 멱수의 합으로 만들 수 있는 경우의 수
		int[] dp = new int[N+1];
		dp[1] = 1;
		int CONST = 1_000_000_000;
		// i는 i-1을 2의 멱수의 합으로 나타낸 식에 1을 더하는 방법과,
		// i가 짝수인 경우 i/2를 2의 멱수의 합으로 나타낸 식에 2를 곱하는 방법이 있다.
		for (int i = 2; i <= N; i++) {
			if (i%2 == 0) dp[i] = (dp[i/2] + dp[i-1])%CONST;
			else dp[i] = dp[i-1];
		}
		System.out.println(dp[N]);
	}
}
