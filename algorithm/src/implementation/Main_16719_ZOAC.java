package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_16719_ZOAC {
	static int N;
	static String s;
	static StringBuilder sb;
	static boolean[] check;
	// 가능한 문자열 후보의 문자열과 최근에 추가한 문자의 인덱스를 포함하는 클래스
	static class Candidate implements Comparable<Candidate> {
		String s;
		int idx;
		public Candidate(String s, int idx) {
			this.s = s;
			this.idx = idx;
		}
		public int compareTo(Candidate c) {
			return this.s.compareTo(c.s);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		N = s.length();
		sb = new StringBuilder(); // 결과를 출력하기 위한 StringBuilder
		check = new boolean[N]; // 규칙에 따라 각 문자가 현재 문자열에 포함되었는지를 나타내는 배열
		// 재귀를 통해 규칙에 따라 문자열을 찾아나간다.
		find(0);
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	// 재귀를 통해 규칙에 따라 문자열을 찾아나가는 함수
	// cnt는 현재 문자열에 포함된 문자의 개수를 의미한다.
	private static void find(int cnt) {
		// 모든 문자를 다 고려한 경우
		if (cnt == N) return;
		// 가능한 후보들을 저장하는 ArrayList
		ArrayList<Candidate> list = new ArrayList<>();
		// 아직 고르지 않은 문자들을 하나씩 추가한 문자열을 후보로 추가한다.
		for (int i = 0; i < N; i++) {
			if (check[i]) continue;
			check[i] = true;
			StringBuilder temp = new StringBuilder();
			for (int j = 0; j < N; j++) {
				if (check[j]) temp.append(s.charAt(j));
			}
			list.add(new Candidate(temp.toString(), i));
			check[i] = false; // 백트래킹
		}
		// 후보로 추가된 문자열 중 사전순으로 가장 앞서는 문자열을 고른 뒤 이를 sb에 추가한다.
		Collections.sort(list);
		Candidate result = list.get(0);
		sb.append(result.s).append("\n");
		check[result.idx] = true; // 문자열을 만들기 위해 추가한 문자를 방문 표시한다.
		find(cnt+1); // 다음 문자를 찾는다.
	}

}
