package bruteforce;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Solution_lv2_수식_최대화 {

	public static void main(String[] args) {
		long result = solution("100-200*300-500+20");
		System.out.println(result);
	}
	static String[] exp; // expression을 피연산자와 연산자로 분해한 결과를 저장하는 배열
	static long answer; // 연산 결과의 절댓값 중 가장 큰 값 
	static char[] operators; // 연산 우선순위를 정하는 배열
	static boolean[] visited;
	static char[] ops; // +, -, * 연산자 저장 배열
	public static long solution(String expression) {
		// expression을 분석해 exp로 만든다.
        analyzeExp(expression);
        // 초기화
		answer = Long.MIN_VALUE;
        operators = new char[3];
        visited = new boolean[3];
        ops = new char[] {'+', '-', '*'};
        // 순열을 통해 연산자의 우선순위에 따른 모든 연산 결과의 절댓값을 구하고, 이 중 최댓값을 구한다.
        perm(0);
        return answer;
    }
	// expression을 분석해 exp로 만드는 함수
	private static void analyzeExp(String expression) {
		String num = "";
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c < '0' || c > '9') {
				result.add(num);
				num = "";
				result.add(String.valueOf(c));
			} else {
				num += c;
			}
		}
		result.add(num);
		exp = result.toArray(new String[result.size()]);
	}
	// 순열을 통해 +, -, *에 대해 가능한 모든 연산 우선순위를 정해주는 함수
	private static void perm(int cnt) {
		// 연산 우선순위를 정한 경우
		if (cnt == 3) {
			// 정해진 연산 우선순위를 바탕으로 expression 계산
			calculate();
		}
		for (int i = 0; i < 3; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			operators[cnt] = ops[i];
			perm(cnt+1);
			visited[i] = false;
		}	
	}
	// 정해진 연산 우선순위를 바탕으로 expression을 계산하는 함수
	private static void calculate() {
		// 선입선출, 선입후출이 모두 가능하도록 Deque를 사용 
		ArrayDeque<String> q = new ArrayDeque<>();
		for (int i = 0; i < exp.length; i++) {
			q.offer(exp[i]);
		}
		// 우선순위가 높은 순서대로 각 연산에 대해서만 연산을 진행
		for (int i = 0; i < 3; i++) {
			char curOperator = operators[i];
			int qLen = q.size();
			while (qLen > 0) {
				String s = q.poll();
				// 연산할 연산자를 발견한 경우
				if (s.equals(curOperator+"")) {
					// 가장 최근에 넣었던 숫자와 가장 나중에 넣었던 숫자를 꺼내 연산
					long a = Long.parseLong(q.pollLast());
					long b = Long.parseLong(q.poll());
					q.offer(calculateOperation(a, b, s)+"");
					qLen--;
				} else {
					q.offer(s);
				}
				qLen--;
			}
		}
		// 모든 연산을 완료하면 q에는 연산 결과가 남게 된다.
		long result = Long.parseLong(q.poll());
		// 연산 결과의 절댓값의 최댓값을 구한다.
		answer = Math.max(answer, Math.abs(result));
	}
	// a와 b를 연산자 s를 통해 계산
	private static long calculateOperation(long a, long b, String s) {
		if (s.equals("+")) {
			return a+b;
		} else if (s.equals("-")) {
			return a-b;
		} else {
			return a*b;
		}
	}

}
