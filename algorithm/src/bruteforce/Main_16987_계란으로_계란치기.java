package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16987_계란으로_계란치기 {
	static int N, max;
	static int[] durabilities;
	static int[] weights;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		durabilities = new int[N];
		weights = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			durabilities[i] = Integer.parseInt(st.nextToken());
			weights[i] = Integer.parseInt(st.nextToken());
		}
		max = 0; // 깨진 계란 수의 최댓값
		// 모든 경우를 고려하기 위해 재귀 함수를 활용
		perm(0, 0);
		System.out.println(max);
	}
	// 계란치기를 했을 때 깨진 계란 수의 최댓값을 구하는 함수
	// 현재 cnt번째 계란을 들려고 하고, broken개의 계란이 깨진 경우를 다룬다.
	private static void perm(int cnt, int broken) {
		// 이전에 들려고 했던 계란이 가장 오른쪽 계란인 경우
		if (cnt == N) {
			max = Math.max(max, broken); // 최댓값 갱신
			return;
		}
		// 들려고 하는 계란이 이미 깨진 경우
		if (durabilities[cnt] <= 0) {
			perm(cnt+1, broken);
			return;
		}
		// cnt번째 계란을 들고 i번째 계란을 깨는 것을 시도한다.
		for (int i = 0; i < N; i++) {
			if (i == cnt) continue; // 들고 있는 계란을 보는 경우
			// i번째 계란이 이미 깨진 경우
			if (durabilities[i] <= 0) {
				perm(cnt+1, broken);
				continue;
			}
			// cnt번째 계란으로 i번째 계란을 깨는 것을 시도한다.
			durabilities[i] -= weights[cnt];
			durabilities[cnt] -= weights[i];
			int nextBroken = broken; // 시행 결과 깨진 계란의 총 개수
			if (durabilities[i] <= 0) nextBroken++; // i번째 계란이 깨진 경우
			if (durabilities[cnt] <= 0) nextBroken++; // 들고 있는 계란이 깨진 경우
			// 다음 계란으로 넘어간다.
			perm(cnt+1, nextBroken);
			// 백트래킹
			durabilities[cnt] += weights[i];
			durabilities[i] += weights[cnt];
		}
	}

}
