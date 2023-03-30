package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2313_보석_구매하기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] maxs = new int[n]; // 각 줄에서 보석의 가치의 합의 최댓값
		int[] starts = new int[n]; // 각 줄에서 보석의 가치의 합이 최대일 때 구매를 시작한 보석의 인덱스
		int[] ends = new int[n]; // 각 줄에서 보석의 가치의 합이 최대일 때 구매를 끝내는 보석의 인덱스
		for (int i = 0; i < n; i++) {
			int L = Integer.parseInt(br.readLine());
			int[] jewels = new int[L];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < L; j++) {
				jewels[j] = Integer.parseInt(st.nextToken());
			}
			// incremental algorithm을 이용해 보석 가치의 합의 최댓값을 구한다.
			// 보석의 가치가 음수가 되는 경우도 있어 최댓값을 의미하는 maxSum의 초기화는 Integer.MIN_VALUE로 한다.
			// thisSum은 l+1번째 보석부터 r번째 보석까지의 가치의 합을 의미한다.
			int thisSum = 0;
			int maxSum = Integer.MIN_VALUE;
			int l = 0;
			int r = 0;
			while (r < L) {
				thisSum += jewels[r++];
				// maxSum은 (r-1)번째 보석까지 고려했을 때 가치 합의 최댓값,
				// thisSum은 l+1번째 보석부터 r번째 보석까지의 가치 합을 의미한다.
				// 여기서 (r-1)번째 보석과 r번째 보석을 경계로 해서, 왼쪽 부분의 최댓값, 오른쪽 부분의 최댓값, 
				// 그리고 경계를 포함하는, 즉 (r-1)번째 보석과 r번째 보석을 포함하는 가치 합 중 최댓값을 구한다.
				// 각각은 maxSum, jewels[r], thisSum을 의미한다.
				// 우선 maxSum과 thisSum을 비교하고, thisSum이 더 크거나, thisSum과 같은 값이면서 보석 수가 더 적은 경우 maxSum을 갱신한다.
				// 이를 먼저 하는 것은 보석의 개수가 1개인 경우를 고려하기 위해서다.
				// 보석의 개수가 1개인 경우 thisSum과 jewels[r]이 동일하다.
				if (thisSum > maxSum || (thisSum == maxSum && ends[i] - starts[i] + 1 > r - l)) {
					maxSum = thisSum;
					starts[i] = l+1;
					ends[i] = r;
				}
				// 다음 과정으로 넘어가기 전에 thisSum이 0보다 작거나 같은지를 보는데,
				// 이를 통해 만약 thisSum이 0보다 작거나 같으면 다음 과정에서 jewels[r]을 더했을 때 
				// 무조건 thisSum이 jewels[r]보다 작거나 같게 된다.
				// 따라서 thisSum을 0으로 초기화하고, 시작 지점을 r로 바꿔
				// 다음 과정에서 thisSum이 더 큰 값인 jewels[r]이 되게 한다.
				// 이때 주의해야 할 점이, thisSum이 0과 같은 경우에도 시작 지점을 r로 바꾸는데,
				// 이는 thisSum이 결국 같은 값이지만 보석의 수를 줄일 수 있기 때문이다.
				// 이를 추가함으로써 0 0 0 1 0과 같은 반례를 해결할 수 있다.
				if (thisSum <= 0) {
					thisSum = 0;
					l = r;
				}
			}
			maxs[i] = maxSum;
		}
		// 출력
		int sum = 0; // 모든 줄의 보석 가치의 합의 최댓값의 합
		for (int i = 0; i < n; i++) {
			sum += maxs[i];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(sum).append("\n");
		for (int i = 0; i < n; i++) {
			sb.append(starts[i]).append(" ").append(ends[i]).append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
