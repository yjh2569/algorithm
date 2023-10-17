package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18233_러버덕을_사랑하는_모임 {
	static int N, P, E;
	static int[] mins, maxs;
	static boolean[] v;
	static int[] res;
	static boolean possible;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		mins = new int[N];
		maxs = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			mins[i] = Integer.parseInt(st.nextToken());
			maxs[i] = Integer.parseInt(st.nextToken());
		}
		// 브루트포스 알고리즘을 활용해 각 회원에게 러버덕 인형을 나눠주는 경우와 그렇지 않은 경우로 나눠 전체 경우의 수를 고려한다.
		// 이때, 나눠줄 수 있는 인형의 개수의 최솟값과 최댓값을 갱신해 나가면서, 인형을 나눠준 사람이 P명일 때 E가 최솟값과 최댓값 사이에 있으면
		// 인형을 나눠줄 수 있다.
		v = new boolean[N]; // 각 회원의 인형 분배 여부를 나타내는 배열 
		res = new int[N]; // 각 회원에게 나눠준 인형의 수를 나타내는 배열
		possible = false; // 인형을 나눠줄 수 있는지를 나타내는 변수
		subset(0, 0, 0, 0);
		// 인형을 나눠줄 수 없는 경우
		if (!possible || N < P) System.out.println(-1);
		// 인형을 나눠줄 수 있는 경우
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb.append(res[i]).append(" ");
			}
			if (sb.length() > 0) sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}
	// 부분집합을 활용해 회원들에게 인형을 나눠줄 수 있는지를 구하는 함수
	// 현재 cnt명에게 인형을 나눠주었고, 이때 나눠준 인형의 개수의 최솟값은 min, 최댓값은 max다.
	private static void subset(int M, int cnt, int min, int max) {
		// 인형을 P명에게 나눠줄 수 있는 경우
		if (cnt == P) {
			if (min <= E && E <= max) {
				possible = true;
				// 우선 인형을 나눠줄 사람들에게 줄 수 있는 인형의 개수의 최솟값으로 인형을 나눠주고, 
				// E와 최솟값의 합의 차이만큼을 배분한다.
				int diff = E - min; // E와 최솟값의 합의 차이
				for (int i = 0; i < N; i++) {
					res[i] = 0;
					if (!v[i]) continue;
					res[i] = mins[i];
				}
				// E와 최솟값의 합의 차이만큼을 차례대로 최댓값으로 배분한다.
				for (int i = 0; i < N; i++) {
					if (!v[i]) continue;
					res[i] = Math.min(maxs[i], mins[i] + diff);
					diff -= res[i] - mins[i];
				}
			}
			return;
		}
		// 더 이상 진행할 수 없는 경우
		if (M == N) return;
		// M번째 사람에게 인형을 나눠주지 않는 경우
		subset(M+1, cnt, min, max);
		// M번째 사람에게 인형을 나눠주는 경우
		v[M] = true;
		subset(M+1, cnt+1, min + mins[M], max + maxs[M]);
		v[M] = false;
	}

}
