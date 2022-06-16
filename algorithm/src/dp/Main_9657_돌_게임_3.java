package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9657_돌_게임_3 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 돌 개수가 i개일 때 상근이가 이기는지 여부를 나타내는 배열
		boolean[] dp = new boolean[n+1];
		// 돌 개수가 1개일 때, 3개일 때, 4개일 때는 무조건 상근이가 이기고, 2개일 때는 무조건 진다.
		dp[1] = true;
		if (n >= 3) dp[3] = true;
		if (n >= 4) dp[4] = true;
		// 돌 개수가 i개일 때 i-1개, i-3개, i-4개일 때 중 한 번이라도 창영이가 이기는 경우 그 다음 턴에 돌을 가져가면 이긴다.
		for (int i = 5; i <= n; i++) {
			dp[i] = !(dp[i-1] && dp[i-3] && dp[i-4]);
		}
		if (dp[n]) System.out.println("SK");
		else System.out.println("CY");
	}

}
