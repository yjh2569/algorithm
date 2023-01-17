package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_9519_졸려 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		StringBuilder s = new StringBuilder(br.readLine());
		// 눈을 깜박일 때마다 바뀌는 문자열은 매번 다른 문자열이 나오지 않고 일정한 주기로 반복되는 패턴을 가진다.
		// 따라서 이러한 패턴을 저장하기 위한 ArrayList를 만든다.
		ArrayList<StringBuilder> patterns = new ArrayList<>();
		// 처음 문자열을 저장
		patterns.add(s);
		// 반복문을 도는 동안 이전의 문자열을 참고하기 위한 변수
		StringBuilder prev = new StringBuilder(s);
		int l = s.length(); // 문자열의 길이
		while (true) {
			// 눈을 깜박였을 때 prev가 바뀐 결과
			StringBuilder next = new StringBuilder();
			// 눈을 깜박였을 때 문자열이 바뀐 결과를 구한다.
			for (int i = 0; i < (l-1)/2; i++) {
				next.append(prev.charAt(i));
				next.append(prev.charAt(l-i-1));
			}
			next.append(prev.charAt((l-1)/2));
			if (l%2 == 0) next.append(prev.charAt(l/2));
			// 처음 문자열로 되돌아간 경우 문자열이 반복되는 주기를 찾았기 때문에 while문을 빠져나간다.
			if (next.toString().equals(patterns.get(0).toString())) {
				break;
			// 그렇지 않으면 새로 발견한 문자열을 patterns에 넣고 prev를 next로 수정해 다음 작업을 이어나갈 수 있도록 한다.
			} else {
				patterns.add(next);
				prev = new StringBuilder(next);
			}
		}
		// 문자열이 반복되는 주기
		int L = patterns.size();
		// X번 깜박이기 전의 문자열을 찾는다.
		System.out.println(patterns.get((L-X%L)%L));
	}

}
