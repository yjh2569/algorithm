package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15817_배수_공사 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int[] lengths = new int[N+1];
		int[] cnts = new int[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			lengths[i] = Integer.parseInt(st.nextToken());
			cnts[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming 활용
		// dp[i][j]는 i번째 파이프까지 고려했을 때 길이가 j인 파이프를 만드는 방법의 수를 의미한다.
		int[][] dp = new int[N+1][x+1];
		dp[0][0] = 1; // 초기화
		for (int i = 1; i <= N; i++) {
			int length = lengths[i];
			// 모든 길이에 대해 가능한 경우의 수를 구한다.
			for (int j = 0; j <= x; j++) {
				// 길이 j인 파이프를 만들기 위해, i번째 파이프를 k개 쓰는 경우의 수를 구한다.
				for (int k = 0; j >= k*length && k <= cnts[i]; k++) {
					dp[i][j] += dp[i-1][j-k*length];
				}
			}
		}
		System.out.println(dp[N][x]);
	}

}
