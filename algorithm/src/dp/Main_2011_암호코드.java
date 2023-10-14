package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2011_암호코드 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = s.length();
		// dynamic programming을 활용한다.
		// dp[i]는 i번째 숫자까지 고려했을 때 가능한 암호의 해석의 경우의 수를 의미한다.
		int[] dp = new int[N+1];
		int CONST = 1_000_000; // 상수
		// 초기화
		dp[0] = 1;
		int prev = -1;
		for (int i = 1; i <= N; i++) {
			int n = s.charAt(i-1) - '0';
			if (n != 0) dp[i] = (dp[i] + dp[i-1]) % CONST; // 한 자리 수를 알파벳으로 바꾸는 경우
			// 두 자리 수를 알파벳으로 바꾸는 경우
			if (i > 1 && (prev == 1 || (prev == 2 && 0 <= n && n <= 6))) {
				dp[i] = (dp[i] + dp[i-2]) % CONST;
			}
			prev = n;
		}
		System.out.println(dp[N]);
	}

}
