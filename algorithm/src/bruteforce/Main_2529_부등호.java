package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2529_부등호 {
	static int k;
	static int[] p;
	static boolean[] v;
	static char[] ineqs;
	static StringBuilder answer;
	static String max;
	static String min;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		// 순열에 사용할 배열
		p = new int[k+1];
		v = new boolean[10];
		// 부등호들을 저장하는 배열
		ineqs = new char[k];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			ineqs[i] = st.nextToken().charAt(0);
		}
		// 최댓값 저장용 변수
		max = "0";
		// 최솟값 저장용 변수
		min = String.valueOf(Long.MAX_VALUE);
		answer = new StringBuilder();
		// 순열을 통해 조건을 만족하는 정수 중 최댓값과 최솟값을 찾는다.
		perm(0);
		// 출력
		answer.append(max).append("\n");
		answer.append(min);
		System.out.println(answer.toString());
	}
	// 순열을 통해 부등호 조건을 만족하는 수들을 찾는 함수
	private static void perm(int cnt) {
		if (cnt == k+1) {
			findMaxAndMin();
			return;
		}
		// 각 자리수마다 조건을 만족하는 수를 찾아나간다.
		for (int i = 0; i < 10; i++) {
			if (v[i]) continue;
			// 조건을 만족하지 않는 경우
			if (cnt > 0 && !compare(p[cnt-1], i, ineqs[cnt-1])) continue;
			v[i] = true;
			p[cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}
	}
	// 순열을 통해 찾은 수를 최댓값 혹은 최솟값으로 갱신하는 함수
	private static void findMaxAndMin() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= k; i++) sb.append(p[i]);
		if (Long.parseLong(max) < Long.parseLong(sb.toString())) max = sb.toString();
		if (Long.parseLong(min) > Long.parseLong(sb.toString())) min = sb.toString();
	}
	// n과 m이 부등호 c가 주어졌을 때 'n c m'이라는 식을 만족하는지 확인하는 함수
	private static boolean compare(int n, int m, char c) {
		if (c == '>') return n > m;
		else return n < m;
	}

}
