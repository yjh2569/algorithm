package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11053_가장_긴_증가하는_부분_수열 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming을 이용한다.
		// dp[i]는 i번째 수가 수열의 끝인, 가장 긴 증가하는 부분수열의 길이를 의미한다.
		int[] dp = new int[N];
		// 자기 자신만 수열에 포함되는 경우 증가하는 부분수열이 될 수 있으므로 기본값은 1로 한다.
		Arrays.fill(dp, 1);
		int max = 1; // 가장 긴 증가하는 부분수열의 길이
		// i번째 수가 수열의 끝인 가장 긴 증가하는 부분수열을 찾기 위해,
		// j(1<j<i-1)번째 수 중 i번째 수보다 작은 수를 발견할 경우
		// j번째 수가 수열의 끝인 가장 긴 증가하는 부분수열에 i번째 수를 추가해 가장 긴 증가하는 부분수열을 만들 수 있다.
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			// 가장 긴 증가하는 부분수열은 반드시 마지막 수를 포함할 필요는 없기 때문에 
			// 지속적으로 가장 긴 증가하는 부분수열의 길이를 갱신한다.
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}
