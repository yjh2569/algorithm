package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_18353_병사_배치하기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming을 이용한다.
		// dp[i]는 i번째 수를 포함하는 감소하는 부분수열 중 가장 긴 부분수열의 길이를 의미한다.
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		int max = 1; // 감소하는 부분수열의 길이의 최댓값
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// j번째 수가 i번째 수보다 크면 j번째 수가 끝인 부분수열에 i번째 수를 붙여 감소하는 부분수열을 연장할 수 있다.
				if (arr[i] < arr[j]) dp[i] = Math.max(dp[i], dp[j]+1);
			}
			// 감소하는 부분수열의 길이가 최대가 될 때 N-1번째 수를 반드시 포함한다는 보장은 없으므로,
			// 모든 i에 대해 i번째 수가 수열의 끝일 때 가장 긴 부분수열의 길이를 지속적으로 갱신한다.
			max = Math.max(max, dp[i]);
		}
		System.out.println(N-max);
	}

}
