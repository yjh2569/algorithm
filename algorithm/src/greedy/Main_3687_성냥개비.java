package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_3687_성냥개비 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// dynamic programming을 활용
		// dp[i]는 성냥개비를 i개 사용했을 때 나오는 수들 중 최소값
		long[] dp = new long[101];
		// 초기화
		Arrays.fill(dp, Long.MAX_VALUE);
		// 각 숫자를 성냥개비로 나타낼 때 필요한 성냥개비의 개수
		int[] nums = new int[] {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
		// 한 자리 수에 대해서는 값을 미리 구해둔다.
		dp[2] = 1; dp[3] = 7; dp[4] = 4; dp[5] = 2; dp[6] = 6; dp[7] = 8;
		// 지금까지 나온 입력값 중 가장 큰 입력값
		// 여기까지는 해를 구했다는 의미
		int maxIdx = 7;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringBuilder max = new StringBuilder();
			// 가장 큰 값을 만들려면 1을 N/2개 붙이고 N이 홀수면 맨 앞에 1 대신 7을 붙이면 된다.
			if (N%2 == 1) {
				max.append("7");
			}
			for (int i = 0; i < N/2; i++) {
				if (N%2 == 1 && i == N/2-1) continue;
				max.append("1");
			}
			// N에 대해 이미 구했던 해가 있다면 그 값을 바로 반환 
			if (maxIdx >= N) {
				sb.append(dp[N]).append(" ").append(max).append("\n");
				continue;
			}
			// 순차적으로 부분해를 구한다.
			for (int n = maxIdx+1; n <= N; n++) {
				// 0~9까지 넣어보면서 n개로 만들 수 있는 수의 최소값을 찾는다.
				for (int i = 0; i < 10; i++) {
					// i를 넣기 위해 필요한 성냥개비의 개수
					int num = nums[i];
					// i를 못 넣는 경우
					if (n-num <= 1) continue;
					// dp[n-num]은 i를 넣기 이전에 n-num개로 만들 수 있는 수의 최소값이다.
					// dp[n-num]에 i를 추가하려고 한다.
					// 우선 dp[n-num]의 길이를 파악한다.
					long temp = 1;
					int L = 0;
					while (temp <= dp[n-num]) {
						temp *= 10l;
						L++;
					}
					// 그 다음 i를 삽입할 위치를 파악하기 위해, 맨 앞자리부터 i보다 크거나 같은 자리수가 나오는지 탐색한다.
					// 그리고 해당 자리에 i를 삽입한다.
					long insert = 1; // i를 삽입할 위치
					temp = 1; // 맨 앞자리부터 십진수 탐색을 위해 만든 임시 변수
					for (int l = 0; l < L; l++) {
						temp *= 10;
					}
					// 맨 앞자리부터 i보다 크거나 같은 자리수가 나오는지 탐색한다.
					for (int l = L-1; l >= 0; l--) {
						temp /= 10;
						// 맨 앞자리에 0을 넣을 수 없음에 유의
						if (i > (dp[n-num]/temp)%10 || (l == L-1 && i == 0)) continue;
						insert = temp*10;
						break;
					}
					// i를 삽입한다.
					long newNum = (dp[n-num]/insert)*insert*10l + i*insert + dp[n-num]%insert;
					// 원래 있던 수와 비교
					dp[n] = Math.min(dp[n], newNum);
				}
			}
			sb.append(dp[N]).append(" ").append(max).append("\n");
			maxIdx = Math.max(maxIdx, N);
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
