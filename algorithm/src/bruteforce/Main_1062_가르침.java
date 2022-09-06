package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1062_가르침 {
	static int N, K, max;
	static int[] words;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 단어들에 대해 각 글자가 들어있는지를 비트마스킹을 통해 저장한다.
		words = new int[N];
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			for (char c : word.toCharArray()) {
				words[i] |= 1 << (c - 'a');
			}
		}
		// 모든 단어는 "anta"로 시작하고 "tica"로 끝나므로 이를 포함시키고 시작한다.
		int first = 0;
		String basic = "antatica";
		for (char c : basic.toCharArray()) {
			first |= 1 << (c - 'a');
		}
		// K개의 글자로 학습할 수 있는 최대 단어 수
		max = 0;
		// 조합을 통해 basic에 있는 글자 포함 총 K개의 글자를 선택하고 
		// 이를 학습했을 때 읽을 수 있는 단어 개수를 구하면서 최댓값을 찾는다.
		combi(0, 0, first);
		System.out.println(max);
	}
	// 조합을 위한 재귀 함수
	private static void combi(int cnt, int start, int first) {
		// 기본 5글자 포함 총 K개의 글자를 선택한 경우
		if (cnt == K - 5) {
			int count = 0; // 읽을 수 있는 단어 개수
			outer: for (int word : words) {
				for (int i = 0; i < 26; i++) {
					// 단어에는 있지만 현재 익힌 글자들 중에는 없는 글자가 있는 경우 다음 단어로 넘어간다.
					if ((word & (1 << i)) != 0 && (first & (1 << i)) == 0) continue outer;
				}
				// 읽을 수 있는 단어인 경우
				count++;
			}
			// 읽을 수 있는 단어 개수의 최댓값 갱신
			max = Math.max(max, count);
			return;
		}
		// 조합을 통해 각 글자를 배울지 선택한다.
		for (int i = start; i < 26; i++) {
			if ((first & (1 << i)) == 0) {
				combi(cnt+1, i+1, first | (1 << i));
			}
		}
	}
}
