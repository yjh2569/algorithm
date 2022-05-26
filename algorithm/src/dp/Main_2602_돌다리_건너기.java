package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2602_돌다리_건너기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s = br.readLine().toCharArray(); 
		char[] devil = br.readLine().toCharArray();
		char[] angel = br.readLine().toCharArray();
		int N = s.length;
		int M = devil.length;
		// dynamic programming을 활용하면 효율적으로 문제를 해결할 수 있다.
		// dp[i][j][k]는 (i, j)에 있는 돌다리를 밟았을 때 마법의 두루마리에 적힌 문자열의 k번째 문자를 밟은 경우의 수를 의미한다.
		// 초기값으로 첫번쨰 문자를 밟은 경우 값을 1로 해준다.
		int[][][] dp = new int[2][M][N];
		for (int j = 0; j < M; j++) {
			if (devil[j] == s[0]) {
				dp[0][j][0] = 1;
			}
			if (angel[j] == s[0]) {
				dp[1][j][0] = 1;
			}
		}
		// 두번째 문자 이후로는 마법의 두루마리에 적힌 문자와 돌다리의 문자가 같은 경우 이전 값을 토대로 경우의 수를 구한다.
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (devil[j] == s[i]) {
					for (int k = 0; k < j; k++) {
						dp[0][j][i] += dp[1][k][i-1];
					}
				}
				if (angel[j] == s[i]) {
					for (int k = 0; k < j; k++) {
						dp[1][j][i] += dp[0][k][i-1];
					}
				}
			}
		}
		// dp[i][j][N-1]의 값들을 모두 더하면 총 경우의 수를 구할 수 있다.
		int res = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < M; j++) {
				res += dp[i][j][N-1];
			}
		}
		System.out.println(res);
 	}

}
