package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2877_4와_7 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 1~2번째로 작은 수는 1자리 수, 3~6번째로 작은 수는 2자리 수, 7~14번째로 작은 수는 3자리 수, ...
		// (2^n-1)~(2^(n+1)-2)번째로 작은 수는 n자리 수가 된다.
		// 우선, N번째로 작은 수의 길이를 구한다.
		int k = 0; // N번째로 작은 수의 길이
		int temp = 1; // 2^k
		while (N >= 2*temp-1) {
			temp *= 2;
			k++;
		}
		// 이제, n자리 수 중 (N-(2^n-1)+1)번째로 작은 수를 구한다.
		// 이는 (N-(2^n-1))을 이진수로 나타냈을 때 0과 1을 각각 4와 7로 바꾸는 방식으로 구할 수 있다.
		// 예를 들어, 3자리 수 중 1번째로 작은 수는 444(000), 2번째로 작은 수는 447(001), 3번째로 작은 수는 474(010)
		// 과 같은 방식으로 나타낼 수 있다.
		N -= temp-1;
		// 일의 자리부터 구한 뒤 출력 결과를 뒤집으면 된다.
		for (int i = 0; i < k; i++) {
			if (N%2 == 0) sb.append(4);
			else sb.append(7);
			N /= 2;
		}
		System.out.println(sb.reverse().toString());
	}

}
