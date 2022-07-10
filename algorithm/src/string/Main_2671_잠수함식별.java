package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_2671_잠수함식별 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		// 풀이 1. 단순 문자열 탐색을 통한 풀이
		// 소음인지를 나타내는 변수
		boolean isNoise = false;
		// 소리 분석 시 1이 여러 번 나오는지를 확인하는 변수
		boolean isMultipleOne = false;
		// 소리로 주어진 문자열을 탐색하면서 소음인지 잠수함의 엔진소리인지를 구분한다.
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '0') {
				// 0 이전에 1이 여러 번 나오고 다음 문자가 0인 경우
				// 이는 100~1~의 규칙을 가진 소리일 가능성이 있으므로 0 앞의 수로 되돌아가 100~1~의 규칙을 따르는지 확인한다.
				if (isMultipleOne && i < s.length()-1 && s.charAt(i+1) == '0') {
					isMultipleOne = false;
					i -= 2;
					continue;
				}
				// 01의 규칙을 따르는지 확인한다.
				i++;
				if (i == s.length() || s.charAt(i) == '0') {
					isNoise = true;
					break;
				}
			// 100~1~의 규칙을 따르는지 확인한다.
			} else if (c == '1') {
				i++;
				if (i < s.length()-1 && s.charAt(i) == '0' && s.charAt(++i) == '0') {
					while (i < s.length() && s.charAt(i) == '0') i++;
					if (i == s.length()) {
						isNoise = true;
						break;
					}
					// 1이 여러 번 나오는지를 확인하기 위한 변수
					int cnt = 0;
					while (i < s.length() && s.charAt(i) == '1') {
						i++;
						cnt++;
					}
					if (cnt > 1) isMultipleOne = true;
					else isMultipleOne = false;
					i--;					
				} else {
					isNoise = true;
					break;
				}
			}
		}
		System.out.println(isNoise ? "NOISE" : "SUBMARINE");
		
		// 풀이 2. 정규 표현식을 이용한 풀이
//		boolean result = Pattern.matches("^(100+1+|01)+", s);
//		System.out.println(result ? "SUBMARINE" : "NOISE");
	}

}
