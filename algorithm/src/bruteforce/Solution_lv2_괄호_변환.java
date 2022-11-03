package bruteforce;

import java.util.Stack;

public class Solution_lv2_괄호_변환 {

	public static void main(String[] args) {
		String result = solution("(()())()");
		System.out.println(result);
	}
	
	public static String solution(String p) {
        return change(p);
    }
	// 재귀적으로 균형잡힌 괄호 문자열을 올바른 괄호 문자열로 바꾸는 함수
	private static String change(String p) {
		// 빈 문자열일 경우
		if (p.length() == 0) return p;
		// 균형잡힌 괄호 문자열 u, v로 나눈다.
		int l = 0;
		int r = 0;
		while (l+r < p.length()) {
			if (p.charAt(l+r) == '(') l++;
			else r++;
			if (l > 0 && l == r) break;
		}
		String u = p.substring(0, l+r);
		String v = p.substring(l+r);
		// u가 올바른 괄호 문자열이면 v를 올바른 괄호 문자열로 바꾸고 이를 u에 더해 반환
		if (check(u)) return u+change(v);
		// u가 올바른 괄호 문자열이 아닌 경우
		StringBuilder newStr = new StringBuilder();
		newStr.append("(").append(change(v)).append(")");
		for (int i = 1; i < u.length()-1; i++) {
			if (u.charAt(i) == '(') newStr.append(")");
			else newStr.append("(");
		}
		return newStr.toString();
	}
	// 올바른 괄호 문자열인지 판단하는 함수
	// stack을 이용해 검사한다.
	private static boolean check(String u) {
		Stack<Character> stack = new Stack<>();
		for (char c : u.toCharArray()) {
			if (c == '(') stack.push(c);
			else if (!stack.isEmpty()) stack.pop();
			else return false;
		}
		return stack.isEmpty();
	}
	

}
