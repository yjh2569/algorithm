package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_21315_카드_섞기 {
	static int N;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] result = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		// 브루트포스 알고리즘을 활용해, 가능한 모든 두 개의 k에 대해
		// 역으로 복원해 원래 카드뭉치로 돌아오는지 확인한다.
		for (int k1 = 1; Math.pow(2, k1) < N; k1++) {
			for (int k2 = 1; Math.pow(2, k2) < N; k2++) {
				// (2, k1) - 섞기, (2, k2) - 섞기 순으로 카드를 섞으므로
				// 복원할 때는 먼저 (2, k2)에 대한 복원을, 그 다음으로 (2, k1)에 대한 복원을 수행한다.
				int[] cur = tryRestore(result, k2);
				cur = tryRestore(cur, k1);
				// 원래 카드뭉치로 돌아오면 k1과 k2를 출력하고 끝낸다.
				if (check(cur)) {
					System.out.println(k1+" "+k2);
					System.exit(0);
				}
			}
		}
	}
	// start 카드뭉치에서 (2, K) - 섞기를 역으로 복원하는 함수
	private static int[] tryRestore(int[] start, int K) {
		ArrayList<Integer> curList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			curList.add(start[i]);
		}
		// (2, K) - 섞기를 역으로 복원하는 경우 2^0, 2^1, ... , 2^K장의 카드를 복원한다.
		// 이때 지수의 값을 k로 한다.
		int k = 0; 
		int t = 1; // 2^k
		// 2^K장의 카드를 복원할 때를 제외하고는 복원 과정이 유사하다.
		while (k < K) {
			// 1~t번째 카드를 2*t번째 인덱스로 옮긴다.
			for (int i = 0; i < t; i++) {
				curList.add(t*2, curList.get(0));
				curList.remove(0);
			}
			// k를 1 증가시킨다.
			k++;
			t *= 2;
		}
		// 1~t번째 카드를 카드뭉치의 맨 아래로 옮긴다.
		for (int i = 0; i < t; i++) {
			curList.add(curList.size(), curList.get(0));
			curList.remove(0);
		}
		// curList를 배열로 바꾼다.
		int[] cur = new int[N];
		for (int i = 0; i < N; i++) {
			cur[i] = curList.get(i);
		}
		return cur;
	}
	// cur 배열이 초기 카드뭉치가 되었는지 확인한다.
	// 즉, 맨 위 카드부터 적힌 숫자가 1, 2, ... , N이 되었는지 확인한다.
	private static boolean check(int[] cur) {
		for (int i = 1; i < N; i++) {
			if (cur[i] - cur[i-1] != 1) return false;
		}
		return true;
	}

}
