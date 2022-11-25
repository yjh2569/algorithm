package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12931_두_배_더하기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] B = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		// 연산의 횟수가 최소일 때, 1을 더하는 연산의 횟수
		int cnt = 0;
		// 연산의 횟수가 최소일 때, 모든 값을 2배시키는 연산의 횟수
		int maxMul = 0;
		for (int i = 0; i < N; i++) {
			// 해당 값을 2배시키는 연산 횟수
			int temp = 0;
			// 해당 값을 역추적하면서, 짝수인 경우는 2로 나누고, 홀수인 경우는 1을 빼면서 연산 횟수를 센다.
			while (B[i] != 0) {
				if (B[i]%2 == 0) {
					B[i] = B[i]/2;
					temp++;
				} else {
					B[i] -= 1;
					cnt++;
				}
			}
			// maxMul은 모든 값을 2배시키는 연산의 횟수로, 동시에 진행해야 하므로 temp 중 최댓값으로 한다.
			maxMul = Math.max(maxMul, temp);
		}
		System.out.println(cnt+maxMul);
	}

}
