package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1522_문자열_교환 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int cntA = 0; // 문자열 내 a의 개수
		int N = s.length(); // 문자열의 길이
		for (char c : s.toCharArray()) {
			if (c == 'a') cntA++;
		}
		// 교환해야 하는 문자의 최소 개수
		int min = Integer.MAX_VALUE;
		// 문자열 내 a의 개수만큼의 길이의 연속된 부분 문자열을 모두 살펴보면서,
		// 해당 부분 문자열 내 b의 개수의 최솟값을 구한다.
		// 그러면 그 b의 개수가 곧 교환해야 하는 문자의 개수의 최솟값이 된다.
		for (int i = 0; i < N; i++) {
			int cntB = 0;
			for (int k = 0; k < cntA; k++) {
				int j = (i+k)%N;
				if (s.charAt(j) == 'b') cntB++;
			}
			min = Math.min(min, cntB);
		}
		System.out.println(min);
	}

}
