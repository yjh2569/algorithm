package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2240_자두나무 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		// dynamic programming을 활용
		// dp[i][j][0]은 자두가 i초까지 j번 움직였을 때 1번 나무에 위치한 경우 먹을 수 있는 자두의 최대 갯수
		// dp[i][j][1]은 자두가 i초까지 j번 움직였을 때 2번 나무에 위치한 경우 먹을 수 있는 자두의 최대 갯수
		int[][][] dp = new int[T+1][W+1][2];
		// 자두는 처음에 1번 나무에 위치하므로 이동하지 않고 2번 나무에 위치하는 것은 불가능
		for (int i = 0; i <= T; i++) {
			dp[i][0][1] = Integer.MIN_VALUE;
		}
		for (int i = 1; i <= T; i++) {
			int t = Integer.parseInt(br.readLine());
			// 자두가 1번 나무에서 떨어질 때
			if (t == 1) {
				for (int j = 0; j <= W; j++) {
					// 자두가 i초에 1번 나무에 위치했을 때
					dp[i][j][0] = j == 0 ? dp[i-1][j][0]+1 : Math.max(dp[i-1][j][0], dp[i-1][j-1][1])+1;
					// 자두가 i초에 2번 나무에 위치했을 때
					dp[i][j][1] = j == 0 ? dp[i-1][j][1] : Math.max(dp[i-1][j-1][0], dp[i-1][j][1]);
				}
			// 자두가 2번 나무에서 떨어질 때
			} else {
				for (int j = 0; j <= W; j++) {
					// 자두가 i초에 1번 나무에 위치할 때
					dp[i][j][0] = j == 0 ? dp[i-1][j][0] : Math.max(dp[i-1][j][0], dp[i-1][j-1][1]);
					// 자두가 i초에 2번 나무에 위치할 때
					dp[i][j][1] = j == 0 ? dp[i-1][j][1]+1 : Math.max(dp[i-1][j-1][0], dp[i-1][j][1])+1;
				}
			}
		}
		// 최댓값을 구하고 출력한다.
		int answer = 0;
		for (int w = 0; w <= W; w++) {
			answer = Math.max(answer, Math.max(dp[T][w][0], dp[T][w][1]));
		}
		System.out.println(answer);
	}

}
