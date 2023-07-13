package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_5557_1학년 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// dynamic programming을 이용한다.
		// dp[i][j]는 i번째 수까지의 연산 결과가 j인 경우의 수를 의미한다.
		// 단, j는 0~20 사이의 수만 가능하다.
		long[][] dp = new long[N-1][21];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 초기화
		int first = Integer.parseInt(st.nextToken());
		dp[0][first] = 1l;
		// N-2개의 수를 뽑아 연산을 진행
		for (int i = 1; i < N-1; i++) {
			int next = Integer.parseInt(st.nextToken());
			// 덧셈
			for (int j = next; j <= 20; j++) {
				dp[i][j] += dp[i-1][j-next];
			}
			// 뺄셈
			for (int j = 0; j <= 20-next; j++) {
				dp[i][j] += dp[i-1][j+next];
			}
		}
		// 마지막 수는 최종 연산 결과와 같은 수인지를 판단하기 위해 사용한다.
		System.out.println(dp[N-2][Integer.parseInt(st.nextToken())]);
	}

}
