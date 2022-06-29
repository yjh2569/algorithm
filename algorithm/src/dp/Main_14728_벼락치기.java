package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14728_벼락치기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		// 각 단원별로 공부 시간과 점수를 기록하는 배열
		int[][] studies = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			studies[i][0] = Integer.parseInt(st.nextToken());
			studies[i][1] = Integer.parseInt(st.nextToken());
		}
		// 베낭 문제 풀이 방법과 동일
		int[][] dp = new int[N+1][T+1];
		for (int i = 1; i <= N; i++) {
			for (int t = 0; t <= T; t++) {
				if (t < studies[i][0]) dp[i][t] = dp[i-1][t];
				else dp[i][t] = Math.max(dp[i-1][t], dp[i-1][t-studies[i][0]] + studies[i][1]);
			}
		}
		System.out.println(dp[N][T]);
	}

}
