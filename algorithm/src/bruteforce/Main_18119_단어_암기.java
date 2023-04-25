package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_18119_단어_암기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 각 단어에 포함된 알파벳을 비트로 표현한 결과를 저장하는 배열
		int[] bits = new int[N];
		// 모음은 절대 잊지 않는다고 했기 때문에, 모음을 제외한 문자만 고려하는 map을 만든다.
		// 예를 들어, b의 인덱스가 0, c의 인덱스가 1, d의 인덱스가 2, f의 인덱스가 3, ..., z의 인덱스가 20이 된다.
		Map<Character, Integer> charIdx = getAlphabetMap();
		// 입력으로 주어지는 단어를 쓰인 문자들을 포함하는 비트 표현으로 바꾼다.
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			int bit = 0;
			for (char c : s.toCharArray()) {
				if (charIdx.containsKey(c)) bit |= 1 << charIdx.get(c);
			}
			bits[i] = bit;
		}
		// 현재 어떤 문자를 기억하고 있는지를 나타내는 비트
		// 초기에는 모든 문자를 기억하고 있으므로 (1 << 21) - 1로 표현
		int curBit = (1 << 21) - 1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			char x = st.nextToken().charAt(0);
			int idx = charIdx.get(x);
			// 쿼리에 따라 문자를 기억하거나 잊는다.
			if (o == 1) curBit &= ~(1 << idx);
			else curBit |= 1 << idx;
			// 단어마다 순회하면서 각 단어를 완전히 아는지 확인한다.
			int cnt = 0; // 완전히 아는 단어의 개수
			for (int j = 0; j < N; j++) {
				if ((curBit & bits[j]) == bits[j]) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 모음을 제외한 알파벳만을 모은 map을 만드는 함수
	private static Map<Character, Integer> getAlphabetMap() {
		Map<Character, Integer> charIdx = new HashMap<>();
		int idx = 0;
		for (int i = 0; i < 26; i++) {
			char c = (char)('a'+i);
			if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
				charIdx.put(c, idx++);
			}
		}
		return charIdx;
	}

}
