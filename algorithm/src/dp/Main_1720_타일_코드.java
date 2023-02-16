package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1720_타일_코드 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 좌우대칭을 고려하지 않았을 때 2*i 크기의 판을 채우는 경우의 수
		long[] dp = new long[N+1];
		dp[1] = 1l;
		if (N >= 2) dp[2] = 3l;
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i-1] + 2l*dp[i-2];
		}
		// base case
		if (N == 1 || N == 2) {
			System.out.println(dp[N]);
		// N이 짝수인 경우
		// 좌우대칭이 발생할 수 있는 모든 경우를 더해 2로 나눈다.
		// dp[N]에는 2*(N/2) 크기의 판이 데칼코마니 형태로 이루어진 판과, 
		// 2*(N/2-1) 크기의 판이 2*1 크기의 타일 2개 혹은 2*2 크기의 타일 1개를 중앙에 두고 데칼코마니 형태로 이루어진 판이
		// 1번만 고려된다.
		// 따라서 이 경우를 모두 중복되게 더해준 뒤 2로 나눈다.
		} else if (N%2 == 0) {
			System.out.println((dp[N]+dp[N/2]+dp[N/2-1]*2)/2);
		// N이 홀수인 경우
		// dp[N]에는 2*((N-1)/2) 크기의 판이 1*2 크기의 타일을 사이에 두고 데칼코마니 형태로 이루어진 판이 1번만 고려된다.
		// 따라서 이 경우를 중복되게 더해준 뒤 2로 나눈다.
		} else {
			System.out.println((dp[N]+dp[(N-1)/2])/2);
		}
	}

}
