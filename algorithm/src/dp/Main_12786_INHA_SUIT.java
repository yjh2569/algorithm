package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12786_INHA_SUIT {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int H = 20; // 나무의 높이
		// 나무에 구멍이 있어 지나갈 수 있는지를 나타내는 배열
		boolean[][] canPass = new boolean[N+1][H+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			for (int j = 0; j < M; j++) {
				int hole = Integer.parseInt(st.nextToken());
				canPass[i][hole] = true;
			}
		}
		// 처음 위치
		canPass[0][1] = true;
		// dynamic programming을 활용
		// dp[i][j]는 i번째 나무의 높이 j 부분을 통과할 때 T기능 사용 횟수의 최솟값을 의미한다.
		int[][] dp = new int[N+1][H+1];
		// 초기화
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
		dp[0][1] = 0;
		// 각 지점에서 다음 나무를 지나가면서 필요한 T기능 사용 횟수의 최솟값을 구한다.
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= H; j++) {
				// 해당 지점을 지날 수 없는 경우
				if (!canPass[i][j]) continue;
				// O기능을 사용했을 때
				if (canPass[i+1][j]) dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
				// A기능을 사용했을 때
				if (j < H && canPass[i+1][j+1]) dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
				// B기능을 사용했을 때
				int H_B = Math.min(H, j*2); // B기능 사용 후 예상 도달 위치
				if (canPass[i+1][H_B]) dp[i+1][H_B] = Math.min(dp[i+1][H_B], dp[i][j]);
				// C기능을 사용했을 때
				if (j > 1 && canPass[i+1][j-1]) dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
				// T기능을 사용했을 때
				// T기능을 사용하면 어떤 지점에도 지나갈 수만 있다면 갈 수 있다.
				for (int k = 1; k <= H; k++) {
					if (!canPass[i+1][k]) continue;
					dp[i+1][k] = Math.min(dp[i+1][k], dp[i][j]+1);
				}
			}
		}
		// T기능 사용 횟수의 최솟값
		int min = Integer.MAX_VALUE/10;
		for (int j = 1; j <= H; j++) {
			min = Math.min(min, dp[N][j]);
		}
		// T기능 사용 횟수가 K 이하면 min을, 그렇지 않으면 통과가 불가능하므로 -1을 출력
		System.out.println(min <= K ? min : -1);
	}

}
