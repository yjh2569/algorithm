package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2156_포도주_시식 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] wines = new int[N];
		for (int i = 0; i < N; i++) {
			wines[i] = Integer.parseInt(br.readLine());
		}
		// dp[i][j]는 (i+1)번째 와인을 j번째로 연속해서 마신 경우 마실 수 있는 와인의 최대 양을 나타낸다.
		// j가 0인 경우는 (i+1)번째 와인을 마시지 않은 경우
		// j가 1인 경우는 i번째 와인을 마시지 않고 (i+1)번째 와인을 마시는 경우
		// j가 2인 경우는 i번째 와인도 마시고 (i+1)번째 와인도 마시는 경우
		int[][] dp = new int[N][3];
		// 초기값 설정
		for (int i = 0; i < N; i++) {
			dp[i][1] = wines[i];
		}
		for (int i = 1; i < N; i++) {
			// 현재 와인을 마시지 않으면 이전 와인에 대한 모든 경우가 가능하다.
			dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
			// 현재 와인을 처음 마시려면 이전 와인을 마시지 않아야 한다.
			dp[i][1] = Math.max(dp[i-1][0] + wines[i], dp[i][1]);
			// 현재 와인을 두 번째로 마시려면 이전 와인을 마셔야 한다. 
			dp[i][2] = dp[i-1][1] + wines[i];
		}
		// N번째 와인에 대한 세 가지 경우 중 최댓값을 구한다.
		int max = 0;
		for (int i = 0; i < 3; i++) {
			max = Math.max(max, dp[N-1][i]);
		}
		System.out.println(max);
	}

}
