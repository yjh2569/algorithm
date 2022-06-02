package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1865_동철이의_일_분배 {
	static int N;
	static double[][] arr;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new double[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					// 백분율로 주어지는 확률을 일반 확률로 바꿔 저장
					arr[i][j] = (Integer.parseInt(st.nextToken())*1.0)/100;
				}
			}
			// 비트마스킹을 이용한 완전탐색으로 좀더 효율적으로 해결
			double[][] dp = new double[N+1][1 << N];
			// 초기값
			dp[0][0] = 1.0;
			// 각 직원들에 대해
			for (int i = 1; i <= N; i++) {
				// 각 일에 대해
				for (int j = 0; j < N; j++) {
					// 이전까지 배분한 일에 대한 확률을 고려해 다음 확률을 계산한다.
					for (int k = 0; k < (1 << N); k++) {
						if (((1 << j) & k) != 0) continue; // 이미 배분한 일인 경우
						if (dp[i-1][k] == 0.0) continue; // 이전까지 배분한 결과 확률이 0이거나 배분이 불가능한 경우
						dp[i][k | (1 << j)] = Math.max(dp[i][k | (1 << j)], dp[i-1][k] * arr[i-1][j]);
					}
				}
			}
			System.out.printf("#%d %.6f\n", t, dp[N][(1 << N) - 1]*100);
		}
	}

	

}
