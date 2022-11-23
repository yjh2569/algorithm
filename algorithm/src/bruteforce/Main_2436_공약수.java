package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2436_공약수 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long gcd = Integer.parseInt(st.nextToken()); // 최대공약수
		long lcm = Integer.parseInt(st.nextToken()); // 최소공배수
		// 두 자연수를 최대공약수와 최소공배수로 하는 두 개의 자연수를 찾기 위해 다음과 같은 과정을 거친다.
		// 1. 우선 최소공배수를 최대공약수로 나눠 두 개의 자연수가 나눠 가져야 할 수를 찾는다.
		// 즉, q = lcm/gcd라 하면 두 개의 자연수는 q의 소인수를 나눠가진다.
		// 단, 이 때 나눠가진 소인수로 이루어진 두 수의 최대공약수는 1, 즉 서로소여야 한다.
		// 2. 1에서 구한 두 수에 최대공약수를 곱한다.
		// 만약 1에서 나눈 두 수의 최대공약수가 1이 아니면, 2로 인해 두 수의 최대공약수가 입력으로 주어진 최대공약수보다 더 커진다.
		long q = lcm/gcd;
		// 1에서 구한 두 수의 합의 최솟값
		long min = Integer.MAX_VALUE;
		// 1에서 구한 두 수 중 작은 수
		long minNum = 0;
		// 브루트포스 알고리즘을 통해 1의 과정을 거쳐 두 수를 구한다.
		for (int i = 1; i <= Math.sqrt(q); i++) {
			if (q%i != 0) continue;
			if (min > i + q/i && check(i, q/i)) {
				min = i + q/i;
				minNum = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		// 2의 과정을 거쳐 원하는 두 수를 얻는다.
		sb.append(minNum*gcd).append(" ").append(gcd*q/minNum);
		System.out.println(sb.toString());
	}
	// n과 m이 서로소인지 확인하는 함수
	private static boolean check(long n, long m) {
		for (int i = 2; i <= Math.sqrt(Math.min(n, m)); i++) {
			if (n%i == 0 && m%i == 0) return false;
		}
		return true;
	}

}
