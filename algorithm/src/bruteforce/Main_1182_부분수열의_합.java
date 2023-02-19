package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1182_부분수열의_합 {
	static int N, S, answer;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 부분집합을 이용해 부분수열 중 합이 S가 되는 경우의 수를 구한다.
		subset(0, 0, 0);
		System.out.println(answer);
	}
	// 부분집합을 이용해 부분수열 중 합이 S가 되는 경우의 수를 구하는 함수
	// cnt는 현재 고려하고 있는 인덱스, sum은 현재까지의 부분수열의 합, k는 고른 숫자의 개수
	private static void subset(int cnt, int sum, int k) {
		// base case
		if (cnt == N) {
			// 고른 숫자의 개수가 양수이면서 부분수열의 합이 S와 같은 경우
			if (sum == S && k > 0) answer++;
			return;
		}
		// cnt번째 숫자를 고르는 경우
		subset(cnt+1, sum+arr[cnt], k+1);
		// cnt번째 숫자를 고르지 않는 경우
		subset(cnt+1, sum, k);
	}

}
