package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10422_괄호 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// dynamic programming을 이용한다.
		// dp[i][0]은 길이가 i인 올바른 괄호 문자열 중 (S) 형태의 괄호 문자열의 개수를,
		// dp[i][1]은 길이가 i인 올바른 괄호 문자열 중 ST 형태의 괄호 문자열의 개수를 나타낸다.
		long[][] dp = new long[5001][2];
		// 초기값 설정
		dp[2][0] = 1;
		// 이미 구했던 값은 다시 처음부터 구하지 않도록 지금까지 나온 길이 중 최대값을 저장한다.
		int max = 2;
		int CONST = 1_000_000_007;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// 길이가 max+2일 때부터 구해나간다.
			if (N > max) {
				for (int i = max+2; i <= N; i += 2) {
					// (S) 형태의 올바른 괄호 문자열의 개수는 S와 길이가 같은 올바른 괄호 문자열의 총 개수와 같다.
					dp[i][0] = (dp[i-2][0] + dp[i-2][1])%CONST;
					// ST 형태의 올바른 괄호 문자열의 개수는 단순하게 생각하면 모든 S와 T에 대한 경우의 수의 곱을
					// S의 길이가 2일 때부터 i-2일 때까지 구하면 될 것 같지만, 중복되는 경우가 발생하기 때문에,
					// S나 T 중 하나는 반드시 (U) 형태를 만족하게 해서 중복되는 경우를 제외한다.
					// 아래는 T가 반드시 (U) 형태를 만족할 때 경우의 수를 구한 것이다.
					for (int j = 2; j <= i-2; j += 2) {
						dp[i][1] = (dp[i][1] + (((dp[j][0]+dp[j][1])%CONST)*dp[i-j][0])%CONST)%CONST;
					}
				}
			}
			// max 값은 반드시 짝수가 되도록 조정한다.
			// 위 순회문에서 max를 사용하기 떄문이다.
			max = Math.max(max, (N/2)*2);
			// 길이가 N인 올바른 괄호 문자열의 개수를 출력에 추가
			sb.append((dp[N][0] + dp[N][1])%CONST).append("\n");
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
