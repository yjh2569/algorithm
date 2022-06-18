package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_18222_투에_모스_문자열 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long k = Long.parseLong(br.readLine());
		// 분할 정복 및 재귀를 활용해 k번째에 오는 문자를 구한다.
		int res = recur(k);
		System.out.println(res);
	}

	private static int recur(long k) {
		// base condition
		if (k == 1) {
			return 0;
		}
		// 투에 모스 문자열을 만들 때 2^m 길이의 문자열 두 개를 붙여서 2^(m+1) 길이의 문자열을 만든다.
		// k번째에 오는 문자를 구하려면 k번째 문자를 포함하는 문자열이 어떤 문자열로부터 만들어졌는지를 알아내야 한다.
		// 예: 1234 / 5678 -> 6번째 문자를 구하려면 2번째 문자가 무엇인지 알아야 한다.
		// 이를 위해 k보다 작은 2의 거듭제곱 중 가장 큰 수를 구하고, k에서 그 수를 빼면 k번째 문자가 어디서 왔는지를 알 수 있다.
		long n = 1;
		while (k > n) {
			n *= 2;
		}
		n /= 2;
		// XOR 연산을 통해 k-n번째 문자가 1이면 0으로, 0이면 1로 바꾼다.
		return recur(k - n) ^ 1;
	}

}
