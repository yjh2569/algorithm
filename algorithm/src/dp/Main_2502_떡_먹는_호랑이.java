package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2502_떡_먹는_호랑이 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// f(1) = 1, f(2) = 1인 피보나치 수열에 대해 f(n)을 구한 결과를 저장하는 배열
		int[] fibonacci = new int[D+1];
		fibonacci[1] = 1;
		for (int i = 2; i <= D; i++) {
			fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
		}
		// n째 날에 준 떡의 개수를 F(n)이라 하면,
		// F(n) = f(n-1)*F(2) + f(n-2)*F(1) (n >= 3)이 성립한다.
		// 1. n = 3일 때 F(3) = 1*F(2) + 1*F(1) = f(2)*F(2) + f(1)*F(1)
		// 2. 위 식이 n <= k일 때 성립한다고 가정하면
		// F(k+1) = F(k) + F(k-1) = f(k-1)*F(2) + f(k-2)*F(1) + f(k-2)*F(2) + f(k-3)*F(1)
		// = (f(k-1) + f(k-2))*F(2) + (f(k-2) + f(k-3))*F(1)
		// = f(k)*F(2) + f(k-1)*F(1)
		// 1과 2에 의해 수학적 귀납법에 따라 위 식이 성립한다.
		// 따라서 위 식에서 F(1)과 F(2)를 구하기 위해 위 식을 부정방정식으로 간주해 해결한다.
		// 아래 n은 F(2)의 값을 1부터 f(D-1)*n이 K보다 작을 때까지의 자연수 값을 하나씩 뽑은 것을 의미한다.
		for (int n = 1; n*fibonacci[D-1] < K; n++) {
			// F(D) - f(D-1)*n으로 위 식에서 f(D-2)*F(1)이 될 값이다.
			int res = K - n*fibonacci[D-1];
			// F(1)이 자연수면서 F(1) <= F(2)를 만족해야 한다.
			if (res%fibonacci[D-2] == 0 && res/fibonacci[D-2] <= n) {
				System.out.println(res/fibonacci[D-2]);
				System.out.println(n);
				System.exit(0);
			}
		}
	}

}
