package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1788_피보나치_수의_확장 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long prev = 0; // F(n-2)
		long prev2 = 1; // F(n-1)
		long temp = 1; // F(n)
		// n이 음수인 경우에는 n이 홀수일 경우 F(-n)과 같고, n이 짝수일 경우 -F(-n)과 같다.
		// 따라서 n이 양수인 경우에 대해서만 구해도 충분하다.
		if (Math.abs(n) >= 2) {
			for (int i = 2; i <= Math.abs(n); i++) {
				temp = (prev + prev2)%1000000000;
				prev = prev2%1000000000;
				prev2 = temp%1000000000;
			}
		}
		if (n == 0) {
			System.out.println(0);
			System.out.println(0);
		} else if ((Math.abs(n))%2 == 1 || n > 0) {
			System.out.println(1);
			System.out.println(temp);
		} else {
			System.out.println(-1);
			System.out.println(temp);
		}
	}

}
