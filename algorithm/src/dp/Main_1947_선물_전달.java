package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1947_선물_전달 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[N+1];
		long CONST = 1_000_000_000;
		// 풀이 1 : 포함배제의 원리*를 이용해 일반항을 구한 뒤 이를 이용해 점화식을 유도하는 방법
		// * 포함배체의 원리는 유한 집합의 합집합의 원소의 개수를 세는 기법을 말한다.
		// N명의 인원이 선물을 서로 나누는 경우의 수는, 자신에게 주는 경우를 모두 포함하면 N!이 되고,
		// 여기서 한 명만 자기 자신에게 주는 경우의 수 (N-1)! * NC1을 빼고, 
		// 두 명만 자기 자신에게 주는 경우의 수 (N-2)! * NC2를 더하고, ...
		// N명이 자기 자신에게 주는 경우의 수 1! * NCN을 더함으로써(N이 홀수면 뺌으로써) 구할 수 있다.
		// 따라서 일반항은 N! - (N-1)!*NC1 + (N-2)!*NC2 - ... 이 되고,
		// 이를 정리하면 N!/2! - N!/3! + ... +(-) N!/N!이 된다.
		// 이때 (N-1)번째 항 (N-1)!/2! - (N-1)!/3! + ... -(+) (N-1)!/(N-1)!과 비교하면, 
		// N번째 항은 (N-1)번째 항에 N을 곱한 후 N의 짝수 여부에 따라 1 또는 -1을 더해주면 된다는 것을 알 수 있다.
		// 이때 N이 짝수인 경우에는 1을 더해주고, N이 홀수인 경우에는 -1을 더해준다.
		for (int i = 2; i <= N; i++) {
			int even = i%2 == 0 ? 1 : -1;
			dp[i] = dp[i-1]*i%CONST+even;
		}
		// 풀이 2 : 점화식을 직접 세우는 경우
		// N명의 인원이 선물을 서로 나누는 경우의 수는 크게 두 가지가 있다.
		// 1. N번째 인원이 나머지 인원 중 한 명과 선물을 교환하는 경우
		// 이 경우 N번째 인원과, N번째 인원과 선물을 교환하는 M번째 인원을 제외한 나머지 경우만 고려하면 된다.
		// 따라서 경우의 수는 (N-2)명일 때 경우의 수에, 가능한 M번째 인원의 수인 (N-1)을 곱하면 된다.
		// 2. N번째 인원이 선물을 준 인원이, N번째 인원이 아닌 인원에게 선물을 준 경우
		// 이 경우 N번째 인원의 선물을 받은 M번째 인원은 N번째 인원을 제외한 나머지 인원 중 한 명에게 주면 되고,
		// M번째 인원에게 선물을 줬던 인원만 N번째 인원에게 선물을 주는 것으로 변경하면 된다.
		// 따라서 (N-1)명일 때 경우의 수에, 가능한 M번째 인원의 수인 (N-1)을 곱하면 된다.
//		for (int i = 2; i <= N; i++) {
//			dp[i] = (dp[i-1]+dp[i-2])*i%CONST;
//		}
		System.out.println(dp[N]%CONST);
	}

}
