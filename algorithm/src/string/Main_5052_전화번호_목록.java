package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_5052_전화번호_목록 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			// 모든 문자열에 대해, 접두어로 가능한 문자열의 개수를 세는 HashMap
			Map<String, Integer> cnts = new HashMap<>();
			// 입력으로 주어지는 문자열을 저장하는 배열
			String[] nums = new String[N];
			// 각 문자열에 대해, 접두어로 가능한 문자열의 개수를 하나씩 늘려준다.
			for (int n = 0; n < N; n++) {
				String s = br.readLine();
				nums[n] = s;
				for (int j = 1; j <= s.length(); j++) {
					String l = s.substring(0, j);
					cnts.putIfAbsent(l, 0);
					cnts.put(l, cnts.get(l)+1);
				}	
			}
			boolean res = true; // 목록이 일관성이 있는지를 나타내는 변수
			// 각 문자열 n이 접두어로 하나 이상 나온다면 cnts에 저장된 횟수는 2 이상이다.
			for (String n : nums) {
				if (cnts.get(n) > 1) res = false;
			}
			sb.append(res ? "YES" : "NO").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
