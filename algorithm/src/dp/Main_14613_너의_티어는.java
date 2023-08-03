package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14613_너의_티어는 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		double W = Double.parseDouble(st.nextToken());
		double L = Double.parseDouble(st.nextToken());
		double D = Double.parseDouble(st.nextToken());
		// dynamic programming을 활용
		// dp[i][j][k]는 승리 i회, 패배 j회, 무승부 k회할 확률을 의미한다.
		double[][][] dp = new double[21][21][21];
		dp[0][0][0] = 1.0; // 초기화
		// 진행 판수에 따라 확률을 계산한다.
		for (int N = 1; N <= 20; N++) {
			for (int i = 0; i <= N; i++) { // 이긴 횟수는 0~N번까지 가능
				for (int j = 0; j <= N-i; j++) { // 진 횟수는 0~(N-i)번까지 가능
					int k = N-i-j; // 비긴 횟수는 N-i-j회가 된다.
					if (i > 0) dp[i][j][k] += dp[i-1][j][k]*W; // 현재 판에서 이겼을 때 확률
					if (j > 0) dp[i][j][k] += dp[i][j-1][k]*L; // 현재 판에서 졌을 때 확률
					if (k > 0) dp[i][j][k] += dp[i][j][k-1]*D; // 현재 판에서 비겼을 때 확률
				}
			}
		}
		// 결과 저장용 배열
		double[] result = new double[5];
		for (int i = 0; i <= 20; i++) {
			for (int j = 0; j <= 20-i; j++) {
				int score = 2000+50*i-50*j; // i번 승리, j번 패배 시 점수 계산
				// i번 승리, j번 패배 시 티어를 계산하고, 여기에 i번 승리, j번 패배할 확률을 더해준다.
				result[findTier(score)] += dp[i][j][20-i-j];
			}
		}
		// 소수점 8자리까지 확률 출력
		for (int i = 0; i < 5; i++) {
			System.out.printf("%.8f\n", result[i]);
		}
	}
	// 점수가 score일 때 티어를 계산해주는 함수
	private static int findTier(int score) {
		if (score < 1500) return 0;
		else if (score < 2000) return 1;
		else if (score < 2500) return 2;
		else if (score < 3000) return 3;
		else return 4;
	}
}
