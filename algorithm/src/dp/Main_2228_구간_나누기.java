package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2228_구간_나누기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N+2];
		// 2번째 인덱스부터 채운다.
		for (int i = 2; i <= N+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		// dynamic programming을 활용
		// dp[i+1][j]는 i번째 수까지 고려한다고 했을 때 j개의 구간을 선택하면 나올 수 있는 구간합의 총합의 최대값
		int[][] dp = new int[N+2][M+1];
		// 초기화
		// 구간이 0개이면 합이 0
		for (int i = 0; i <= N+1; i++) {
			Arrays.fill(dp[i], Integer.MIN_VALUE/100);
			dp[i][0] = 0;
		}
		// 구간을 하나씩 만들어나간다.
		for (int j = 1; j <= M; j++) {
			// 부분 배열의 끝을 지정
			for (int i = 0; i < N+1; i++) {
				// 구간의 시작 지점
				// 구간이 겹치거나 인접하면 안 되므로 구간의 끝에서 2칸 떨어진 곳에서 시작해야 한다.
				// 2번째 인덱스부터 배열을 채운 이유이기도 하다.
				for (int k = i+2; k <= N+1; k++) {
					// 구간합을 누적합으로 쉽게 계산하기 위한 변수
					int sum = 0;
					// 구간의 끝 지점
					for (int l = k; l <= N+1; l++) {
						sum += arr[l];
						// 기존 값과 현재 배열보다 1만큼 짧은 배열에서의 값, 그리고 j-1개의 구간에서 현재 만든 구간합을 더한 값 중 최대값을 고른다.
						dp[l][j] = Math.max(dp[l][j], Math.max(dp[l-1][j], dp[i][j-1] + sum));
					}
				}
			}
		}
		System.out.println(dp[N+1][M]);
	}

}
