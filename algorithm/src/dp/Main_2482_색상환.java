package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2482_색상환 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int CONST = 1_000_000_003;
		// dynamic programming을 사용해 해결한다.
		// dp[0]은 가장 첫 번째로 오는 색을 선택하는 경우, dp[1]은 가장 첫 번째로 오는 색을 절대 선택하지 않는 경우
		// dp[i][k][n]은 n개의 색으로 이루어진 '배열'에서 k개의 색을 선택하는 경우의 수를 의미한다.
		long[][][] dp = new long[2][K+1][N];
		// 초기값
		// 1개의 색만 선택하는 경우, 첫 번째로 오는 색을 선택하는 경우는 1가지밖에 없다.
		// 반면, 첫 번째로 오는 색을 선택하지 않므면 두 번째~i번째 색까지 선택할 수 있다.
		dp[0][1][0] = 1;
		for (int i = 1; i < N; i++) {
			dp[0][1][i] = 1;
			dp[1][1][i] = i;
		}
		// 색을 하나씩 선택한다.
		for (int k = 2; k <= K; k++) {
			// 색을 순서대로 k개 선택하면 k번째 색은 적어도 2*(k-1)번째보다 뒤에 나오는 색이다.(가장 먼저 나오는 색이 0번째)
			// 0번째 색을 선택하면 2번째보다 뒤에 있는 색, 2번째 색을 선택하면 4번째보다 뒤에 있는 색...
			// 색상환을 구성하려면 마지막 색을 선택할 때 첫 번째 색을 선택했는지가 중요해진다. 따라서 앞에서 경우를 나눈 것이다.
			for (int n = 2*(k-1); n < N-1; n++) {
				// 첫 번째 색을 항상 선택한다면 최대 N-2번째 색까지만 선택할 수 있다.
				dp[0][k][n] = (dp[0][k][n-1] + dp[0][k-1][n-2])%CONST;
				// 첫 번째 색을 선택하지 않는다면 N-1번째 색도 선택할 수 있다.
				// 이때 적어도 1번째 색부터 선택 가능하므로 색을 순서대로 k개 선택하면 k번째 색은 적어도 2*(k-1)+1번째보다 뒤에 나오는 색이다.
				dp[1][k][n+1] = (dp[1][k][n] + dp[1][k-1][n-1])%CONST;
			}
		}
		long res = (dp[0][K][N-2] + dp[1][K][N-1])%CONST;		
		System.out.println(res);
	}

}
