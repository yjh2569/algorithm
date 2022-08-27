package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_18291_비요뜨의_징검다리_건너기 {
	// 10^9+7
	static int CONST = 1_000_000_007;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// N번째 징검다리까지 가는 경우의 수를 a_N이라고 할 때,
			// 자연수 N에 대하여, 수열 {a_N}은 다음과 같은 점화식을 만족한다.
			// a_(N+1) = a_1 + a_2 + ... + a_N ... (1)
			// N >= 2일 때, 다음과 같은 점화식도 만들 수 있다.
			// a_N = a_1 + a_2 + ... + a_(N-1) ... (2)
			// (1) - (2)를 하면
			// a_(N+1) - a_N = a_N
			// 따라서 a_(N+1) = 2*a_N이므로 수열 {a_N}은 N >= 2일 때 첫째항이 a_2 = 1, 공비가 2인 등비수열이 된다.
			// 이때 a_1 = 1임에 유의
			// 따라서 a_N = 2^(N-2) (N >= 2), a_1 = 1이다.
			// 그러므로 N번째 징검다리까지 가는 경우의 수를 계산하기 위해, 2^(N-2)를 계산한다.
			sb.append(pow2(N-2)).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 2^(N-2)를 계산하는 함수
	// divide and conquer를 통해 시간복잡도를 O(log N)까지 낮출 수 있다. 
	private static long pow2(int n) {
		// N = 1, 2인 경우
		if (n <= 0) return 1;
		// N이 짝수인 경우
		if (n%2 == 0) {
			int m = n/2;
			long res = pow2(m);
			return res * res % CONST;
		// N이 홀수인 경우
		} else {
			int m = n/2;
			long res = pow2(m);
			return res * res * 2 % CONST;
		}
	}

}
