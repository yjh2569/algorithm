package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_10826_피보나치_수_4 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// dynamic programming을 통해 피보나치 수열을 구한다.
		// N이 10000까지 가능하므로 BigInteger를 통해 연산해야 한다.
		BigInteger[] arr = new BigInteger[N+1];
		// 초기값
		arr[0] = BigInteger.ZERO;
		if (N >= 1) arr[1] = BigInteger.ONE;
		for (int i = 2; i <= N; i++) {
			arr[i] = arr[i-1].add(arr[i-2]);
		}
		System.out.println(arr[N].toString());
	}

}
