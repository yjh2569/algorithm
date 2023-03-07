package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_25049_뮤직_플레이리스트 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		// 총 5단계로 나누어서 dynamic programming을 진행한다.
		// dp[i][0]은 i번째 곡까지 한 번도 되감기를 안 한 경우
		// dp[i][1]은 i번째 곡까지 되감기를 한 번 하고, 현재 곡은 되감기가 적용된 경우
		// dp[i][2]는 i번째 곡까지 되감기를 한 번 하고, 현재 곡은 되감기가 적용되지 않은 경우
		// dp[i][3]은 i번째 곡까지 되감기를 두 번 하고, 현재 곡은 되감기가 적용된 경우
		// dp[i][4]는 i번째 곡까지 되감기를 두 번 하고, 현재 곡은 되감기가 적용되지 않은 경우
		long[][] dp = new long[N][5];
		// 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Long.MIN_VALUE/10);
		}
		dp[0][0] = arr[0];
		dp[0][1] = arr[0]*2;
		for (int i = 1; i < N; i++) {
			dp[i][0] = dp[i-1][0] + arr[i]; // dp[i][0]은 되감기를 전혀 하지 않으므로 값이 고정된다.
			// 되감기를 한 번 하고, 현재 곡에서 되감기를 진행 중인 경우 
			// 이전에 진행 중인 되감기에 참여하거나 되감기를 현재 곡부터 시작하는 경우가 있다.
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]) + arr[i]*2;
			// 되감기를 한 번 하고, 현재 곡에서 되감기를 그만둔 경우 
			// 이전에 진행 중인 되감기를 중지하거나 이전에 되감기를 그만둔 상태에 편승하는 경우가 있다.
			dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]) + arr[i];
			dp[i][3] = Math.max(dp[i-1][2], dp[i-1][3]) + arr[i]*2;
			dp[i][4] = Math.max(dp[i-1][3], dp[i-1][4]) + arr[i];
		}
		// 되감기를 최대 두 번 하기 때문에, 되감기를 한 번 할수도 있고, 아예 하지 않을수도 있다.
		// 따라서 각 경우 중 최댓값을 구한다.
		long max = dp[N-1][0];
		for (int i = 1; i < 5; i++) {
			max = Math.max(max, dp[N-1][i]);
		}
		System.out.println(max);
	}
}
