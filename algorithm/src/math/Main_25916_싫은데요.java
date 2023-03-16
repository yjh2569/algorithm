package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25916_싫은데요 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long M = Long.parseLong(st.nextToken());
		long[] arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		// 투 포인터를 이용해 모든 연속된 부분수열의 합 중 M보다 작거나 같은 값들 중 최댓값을 구한다.
		int l = 0; // 왼쪽 경계로 sum에서 해당 인덱스의 값을 포함
		int r = 0; // 오른쪽 경계로 sum에서 해당 인덱스의 바로 이전값까지 포함
		long sum = 0; // 현재 연속된 부분수열의 합
		long max = 0; // 부분수열의 합 중 M보다 작거나 같은 값들 중 최댓값
		while (l < N && l <= r) {
			// sum이 M보다 작거나 같으면서 r이 N보다 작을 때는 
			// r번째 값을 더함과 동시에 r을 오른쪽으로 한 칸 이동시킨다.
			if (sum <= M && r < N) {
				sum += arr[r++];
			// 그렇지 않은 경우에는 l번째 값을 뺌과 동시에 l을 오른쪽으로 한 칸 이동시킨다.
			} else {
				sum -= arr[l++];
			}
			// sum이 M보다 작거나 같을 때만 max를 갱신한다.
			if (sum <= M) max = Math.max(sum, max);
		}
		System.out.println(max);
	}

}
