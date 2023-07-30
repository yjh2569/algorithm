package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16938_캠프_준비 {
	static int N, L, R, X;
	static int[] difficulties;
	static boolean[] v;
	static int count;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		difficulties = new int[N];
		count = 0; // 가능한 방법의 수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			difficulties[i] = Integer.parseInt(st.nextToken());
		}
		// 부분집합을 활용해 가능한 방법의 수를 구한다.
		subset(0, 0, Integer.MAX_VALUE, 0, 0);
		System.out.println(count);
	}

	// 부분집합을 활용해 가능한 방법의 수를 구하는 함수
	private static void subset(int cnt, int sum, int min, int max, int k) {
		if (cnt == N) {
			// 난이도의 합이 L보다 크거나 같아야 하고, 문제 간 난이도 차이의 최댓값이 X보다 크거나 같아야 한다.
			if (sum < L || max - min < X) return;
			count++;
			return;
		}
		// cnt번째 문제를 포함시키는 경우
		// 난이도의 합이 R보다 작거나 같아야 한다.
		if (sum + difficulties[cnt] <= R) {
			subset(cnt + 1, sum + difficulties[cnt], Math.min(min, difficulties[cnt]), Math.max(max, difficulties[cnt]), k + 1);
		}
		// cnt번째 문제를 포함시키지 않는 경우
		subset(cnt + 1, sum, min, max, k);
	}
}
