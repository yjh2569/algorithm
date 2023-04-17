package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_2608_로마_숫자 {
	static Map<Character, Integer> rTA;
	static char[] romas;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String NR = br.readLine();
		String MR = br.readLine();
		// 로마 숫자를 아라비아 숫자에 대응시키는 HashMap
		rTA = new HashMap<>();
		rTA.put('I', 1); rTA.put('V', 5); rTA.put('X', 10);
		rTA.put('L', 50); rTA.put('C', 100); rTA.put('D', 500); rTA.put('M', 1000);
		// 아라비아 숫자를 로마 숫자로 바꿀 때 사용할 배열
		romas = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
		// 주어진 두 로마 숫자를 아라비아 숫자로 바꾼다.
		int N = romaToArab(NR);
		int M = romaToArab(MR);
		int sum = N+M;
		System.out.println(sum);
		// N+M을 로마 숫자로 바꾼다.
		System.out.println(arabToRoma(sum));
	}
	// 로마 숫자 r을 아라비아 숫자로 바꾸는 함수
	private static int romaToArab(String r) {
		int result = 0; // 아라비아 숫자
		for (int i = 0; i < r.length(); i++) {
			char c = r.charAt(i);
			// 작은 숫자가 큰 숫자 왼쪽에 오는 경우
			// (큰 숫자) - (작은 숫자)의 값을 result에 추가하고, 큰 숫자는 더 이상 필요가 없으므로
			// 큰 숫자 다음 숫자로 넘어간다.
			if (i < r.length()-1 && rTA.get(c) < rTA.get(r.charAt(i+1))) {
				result += rTA.get(r.charAt(i+1)) - rTA.get(c);
				i++;
			// 일반적인 경우에는 해당 로마 숫자에 대응되는 아라비아 숫자를 구해 그 값을 result에 더해주면 된다.
			} else {
				result += rTA.get(c);
			}
		}
		return result;
	}
	// 아라비아 숫자를 로마 숫자로 바꾸는 함수
	private static String arabToRoma(int n) {
		StringBuilder sb = new StringBuilder(); // 로마 숫자
		// 자리수를 알아보기 위한 임시 변수
		int temp = 10000;
		// 천의 자리수부터 일의 자리수까지 조사한다.
		for (int i = 3; i >= 0; i--) {
			temp /= 10;
			// 자리수가 0인 경우 다음 자리로 넘어간다.
			if (n/temp == 0) continue;
			// (10^i)의 자리수
			int k = n/temp;
			// 자리수가 4 또는 9인 경우 작은 숫자가 큰 숫자 왼쪽에 오는 방식으로 표현한다.
			if (k == 4) {
				// IV, XL, CD와 같은 식으로 표현
				sb.append(romas[i*2]).append(romas[i*2+1]);
			} else if (k == 9) {
				// IX, XC, CM과 같은 식으로 표현
				sb.append(romas[i*2]).append(romas[(i+1)*2]);
			// 나머지는 큰 숫자 먼저, 그리고 작은 숫자들로 표현
			// 자리수가 5 이상이면 V, L, D를 붙이고,
			// 자리수를 5로 나눈 나미지만큼 I, X, C, M을 붙인다.
			} else {
				if (k >= 5) sb.append(romas[i*2+1]);
				int res = k%5;
				for (int j = 0; j < res; j++) sb.append(romas[i*2]);
			}
			// 자리수에 대한 로마 숫자 처리를 완료했으므로 해당 자리수를 제거
			n -= k*temp;
		}
		return sb.toString();
	}
}
