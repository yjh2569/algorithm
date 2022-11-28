package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution_lv2_뉴스_클러스터링 {
	static Set<String> strs;

	public static void main(String[] args) {
		int result = solution("E=M*C^2", "e=m*c^2");
		System.out.println(result);
	}

	public static int solution(String str1, String str2) {
		// 대소문자 구별이 없으므로 소문자로 변환
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		// str1, str2에서 나올 수 있는 두 글자의 문자열들의 집합
		strs = new HashSet<>();
		// str1, str2 각각에 대해 두 글자의 문자열이 각각 몇 번 나오는지 센다.
		Map<String, Integer> cntsForStr1 = makeSet(str1);
		Map<String, Integer> cntsForStr2 = makeSet(str2);
		// 교집합의 원소의 개수
		int intersect = 0;
		// 합집합의 원소의 개수
		int union = 0;
		// strs 내의 각 두 글자의 문자열에 대해 경우에 따라 교집합 또는 합집합의 원소의 개수를 센다.
		for (String str : strs) {
			if (cntsForStr1.containsKey(str) && cntsForStr2.containsKey(str)) {
				intersect += Math.min(cntsForStr1.get(str), cntsForStr2.get(str));
				union += Math.max(cntsForStr1.get(str), cntsForStr2.get(str));
			} else if (cntsForStr1.containsKey(str)) {
				union += cntsForStr1.get(str);
			} else {
				union += cntsForStr2.get(str);
			}
		}
		// 합집합이 공집합이 되는 경우를 고려한다.
		return union == 0 ? 65536 : intersect * 65536 / union;
	}
	// 문자열 s에 대해 나올 수 있는 두 글자의 문자열 각각이 나오는 횟수를 저장하는 Map을 만든다.
	private static Map<String, Integer> makeSet(String s) {
		Map<String, Integer> cnts = new HashMap<>();
		for (int i = 0; i < s.length() - 1; i++) {
			// 두 글자 모두 알파벳이어야 한다.
			if (check(s.charAt(i)) && check(s.charAt(i + 1))) {
				String substr = s.substring(i, i + 2);
				strs.add(substr);
				cnts.putIfAbsent(substr, 0);
				cnts.put(substr, cnts.get(substr) + 1);
			}
		}
		return cnts;
	}
	// c가 알파벳인지 확인하는 함수
	private static boolean check(char c) {
		return 'a' <= c && c <= 'z';
	}

}
