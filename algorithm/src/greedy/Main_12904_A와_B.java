package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12904_A와_B {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		StringBuilder t = new StringBuilder(br.readLine());
		// S를 T로 만들 수 있는지 검사하기 위해 역으로 T에서 S를 만들어본다.
		// T에서 S를 만들기 위해 맨 뒤에 있는 알파벳을 확인해 만약 A면 그냥 제거하고, B면 제거한 뒤 뒤집는다.
		while (S.length() < t.length()) {
			char c = t.charAt(t.length()-1);
			if (c == 'A') {
				t.setLength(t.length() - 1);
			} else if (c == 'B') {
				t.setLength(t.length() - 1);
				t.reverse();
			}
		}
		// T의 길이와 S의 길이가 같아지면 S와 T를 비교한다.
		String T = t.toString();
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) != T.charAt(i)) {
				System.out.println(0);
				System.exit(0);
			}
		}
		System.out.println(1);
	}

}
