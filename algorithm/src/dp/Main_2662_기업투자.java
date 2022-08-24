package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2662_기업투자 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] benefits = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			for (int j = 1; j <= M; j++) {
				benefits[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dynamic programming을 적용해 해결한다.
		// dp[i][j]는 i번째 기업까지 j만원을 가지고 얻을 수 있는 최대 이익을 의미한다.
		int[][] dp = new int[M+1][N+1];
		// divide[i][j][k]는 i번째 기업까지 j만원을 가지고 최대 이익을 얻기 위해 k번째 기업에 얼마나 투자해야 하는지를 의미한다. 
		int[][][] divide = new int[M+1][N+1][M+1];
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 0; k <= j; k++) {
					// 이전 기업까지 고려한 결과에 현재 기업에 k만원을 투자한 결과와 현재 결과를 비교하여
					// 이익이 더 커지는 경우 현재 결과를 수정한다. 
					if (dp[i][j] < dp[i-1][j-k] + benefits[k][i]) {
						dp[i][j] = dp[i-1][j-k] + benefits[k][i];
						for (int m = 1; m <= M; m++) {
							divide[i][j][m] = divide[i-1][j-k][m];
						}
						divide[i][j][i] += k;
					}
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(dp[M][N]).append("\n");
		for (int i = 1; i <= M; i++) {
			sb.append(divide[M][N][i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
