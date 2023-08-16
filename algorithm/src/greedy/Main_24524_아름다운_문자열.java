package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_24524_아름다운_문자열 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		// T 문자열에서 각 문자가 몇 번째 인덱스에 있는지를 나타내는 map
		Map<Character, Integer> idxs = new HashMap<>();
		for (int i = 0; i < T.length(); i++) {
			idxs.put(T.charAt(i), i);
		}
		// greedy algorithm을 이용한다.
		// S 내 i번째 문자 c1에서 대해, c1이 지금까지 몇 번 나왔는지를 센다.
		// 그리고 T에서 c1 바로 앞에 있는 문자 c2가 S에서 지금까지 몇 번 나왔는지를 센다.
		// 이때, T는 최대 c2가 나온 횟수만큼밖에 만들 수 없다.
		// c1이 아무리 많이 나와도, c2가 c1 앞에 있는 문자열은 최대 c2가 나온 횟수밖에 못 만들기 때문이다.
		int[] cnts = new int[T.length()]; // 각 문자가 지금까지 몇 번 나왔는지를 기록하는 배열
		for (char c : S.toCharArray()) {
			if (!idxs.containsKey(c)) continue; // S에 T에는 없는 문자가 나오는 경우
			int idx = idxs.get(c);
			// idx번째 문자가 idx-1번째 문자보다 더 적거나 같게 나온 경우
			if (idx > 0 && cnts[idx] >= cnts[idx-1]) continue;
			cnts[idx]++;
		}
		// 위 과정을 거치면, T는 최대 cnts[T.length()-1]개만 만들 수 있다.
		System.out.println(cnts[T.length()-1]);
	}

}
