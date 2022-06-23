package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1285_동전_뒤집기 {
	static int N;
	static int[] map;
	static int min;
	static int[] cur_map;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 동전들의 초기 상태
		// 앞면은 0, 뒷면은 1로 표시하고, i번째 원소의 j번째 비트가 (i, j)에 있는 동전의 상태를 의미한다.
		// 예 : 1번째 원소가 010001100 인 경우 (1, 2), (1, 3), (1, 7)에 있는 동전이 뒷면이 위로 향한다는 뜻
		map = new int[N];
		// 구해야 하는 최소값
		min = Integer.MAX_VALUE;
		// 각 행마다 브루트포스 알고리즘을 통해 동전들을 모두 뒤집은 결과를 임시로 저장하는 배열
		cur_map = new int[N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = s.charAt(j);
				// 비트마스킹을 통해 동전들의 상태를 표시
				if (c == 'T') map[i] += (1 << j);
			}
		}
		// 비트마스킹을 통해 각 행에 대해 브루트포스 알고리즘을 시행
		// 즉, 동전을 뒤집을 행을 선택하는 모든 경우의 수를 고려하고 
		// 각 경우에 대해 뒷면이 위로 향하는 동전의 개수가 최소가 되는 경우를 찾는다.
		for (int i = 0; i < (1 << N); i++) {
			simulate(i);
		}
		System.out.println(min);
	}
	private static void simulate(int n) {
		// 초기 상태 복사
		for (int i = 0; i < N; i++) {
			cur_map[i] = map[i];
		}
		// 인자 n의 값에 따라 선택한 행에 있는 동전을 뒤집는다.
		for (int i = 0; i < N; i++) {
			if ((n & (1 << i)) != 0) {
				cur_map[i] = cur_map[i] ^ ((1 << N) - 1);
			}
		}
		// 그리디 알고리즘 적용
		// 열만 고려하는 경우, 열에서 앞면이 위로 향하는 동전의 개수가 더 많은 경우 그대로 두고, 그렇지 않으면 뒤집으면 된다.
		// 이를 간단히 하기 위해, 각 열에서 앞면이 위로 향하는 동전과 뒷면이 위로 향하는 동전의 개수를 센 뒤
		// 더 작은 값을 뒷면이 위로 향하는 동전의 개수로 한다.
		int cnt = 0; // 뒷면이 위로 향하는 동전의 개수
		for (int j = 0; j < N; j++) {
			int cnt_0 = 0; // 뒤집었을 때 뒷면이 위로 향하는 동전의 개수
			int cnt_1 = 0; // 그대로 두었을 때 뒷면이 위로 향하는 동전의 개수
			for (int i = 0; i < N; i++) {
				if ((cur_map[i] & (1 << j)) == 0) cnt_0++;
				else cnt_1++;
			}
			cnt += Math.min(cnt_0, cnt_1);
			// 뒷면이 위로 향하는 동전의 개수가 이미 min을 넘어버린 경우 다음에 나오는 동전을 고려하는 것은 의미가 없다.
			if (cnt >= min) break;
		}
		// 최소값 구하기
		min = Math.min(min, cnt);
	}

}
