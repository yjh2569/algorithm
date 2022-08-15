package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16974_레벨_햄버거 {
	static long[] sizes;
	static long[] patties;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long X = Long.parseLong(st.nextToken());
		// 레벨-I 버거에 있는 햄버거번과 패티의 총 장수를 i번째 값으로 하는 배열
		sizes = new long[N+1];
		// 레벨-I 버거에 있는 패티의 총 장수를 i번째 값으로 하는 배열
		patties = new long[N+1];
		// 초기화
		sizes[0] = 1;
		patties[0] = 1;
		// 아래 X장이라는 제약이 없다면 점화식을 이용해 쉽게 계산할 수 있다.
		for (int i = 1; i <= N; i++) {
			sizes[i] = sizes[i-1]*2+3;
			patties[i] = patties[i-1]*2+1;
		}
		// 레벨-N 버거의 아래 X장에 있는 패티의 장수를 재귀를 통해 계산한다.
		long res = recur(N, X);
		System.out.println(res);
	}
	// 레벨-n 버거의 아래 x장에 있는 패티의 장수를 구하는 재귀함수
	private static long recur(int n, long x) {
		// x가 0 이하인 경우 햄버거를 먹지 않은 것과 같은 경우이므로 0을 반환한다.
		if (x <= 0) return 0;
		// 기본 케이스로 n이 0이고 x가 1 이상인 경우 패티 1장밖에 없으므로 1을 반환한다.
		if (n == 0) {
			if (x >= 1) return 1;
		}
		// 크게 3가지 경우로 나눌 수 있다.
		// 레벨-n 햄버거에서 햄버거번~레벨-(n-1) 버거 일부까지 고려하는 경우
		if (x-1 <= sizes[n-1]) {
			return recur(n-1, x-1);
		// 레벨-n 햄버거에서 햄버거번~레벨-(n-1) 버거~패티까지 고려하는 경우
		} else if (x == sizes[n-1]+2) {
			return patties[n-1]+1l;
			// 레벨-n 햄버거에서 햄버거번~레벨-(n-1) 버거 전체~패티~레벨-(n-1) 버거 일부까지 고려하는 경우
		} else {
			return patties[n-1]+1l+recur(n-1, x-2-sizes[n-1]);
		}
	}

}
