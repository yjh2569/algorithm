package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14852_타일_채우기_3 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// dynamic programming을 이용한다.
		// dp[i]는 2*i 크기의 벽을 타일로 채우는 경우의 수를 의미한다. 
		long[] dp = new long[N+1];
		// sum[i]는 dp[0] + dp[1] + ... + dp[i]를 의미한다.
		long[] sum = new long[N+1];
		dp[0] = 1l; sum[0] = 1l;
		dp[1] = 2l; sum[1] = 3l;
		// 상수
		long CONST = 1_000_000_007;
		// 점화식은 다음과 같다.
		// dp[i] = 2*dp[i-1] + 3*dp[i-2] + 2*(dp[i-3] + dp[i-4] + ... + dp[1] + dp[0])
		// 뒤에 붙은 합을 매번 계산하지 않고, sum 배열을 통해 미리 누적합을 구해놓는다.
		for (int i = 2; i <= N; i++) {
			if (i == 2) dp[i] = (dp[i-1]*2+dp[i-2]*3)%CONST;
			else dp[i] = (dp[i-1]*2 + dp[i-2]*3+sum[i-3]*2)%CONST;
			sum[i] = (dp[i]+sum[i-1])%CONST;
		}
		System.out.println(dp[N]);
	}

}
