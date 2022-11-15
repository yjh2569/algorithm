package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14395_4연산 {
	// 숫자와 해당 수를 만들기 위해 진행한 연산을 저장하는 클래스
	static class Number {
		long n;
		String operators;
		public Number(long n, String operators) {
			this.n = n;
			this.operators = operators;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long s = Long.parseLong(st.nextToken());
		long t = Long.parseLong(st.nextToken());
		// 두 수가 같은 경우
		if (s == t) {
			System.out.println(0);
			System.exit(0);
		}
		// BFS를 통해 s를 t로 만든다.
		Map<Long, Boolean> visited = new HashMap<>();
		Queue<Number> q = new LinkedList<>();
		// 초기화
		q.offer(new Number(s, ""));
		// t를 만들었을 때, 여러 가지 경우 중 사전 순으로 가장 앞서는 연산 순서를 구하기 위해 
		// 가능한 모든 결과를 저장하는 ArrayList 
		ArrayList<String> arr = new ArrayList<>();
		// t를 만들었는지를 확인하는 변수
		boolean check = false;
		// 아스키 코드를 참조해 가장 사전순으로 앞서는 문자열을 구하기 위해 필요한 맵
		Map<Character, Character> map = new HashMap<>();
		map.put('0', '*'); map.put('1', '+'); map.put('2', '-'); map.put('3', '/');
		while (!q.isEmpty()) {
			int qLen = q.size();
			// 아래 과정 중 방문한 수들을 기록하는 ArrayList
			ArrayList<Long> temp = new ArrayList<>();
			while (qLen > 0) {
				Number u = q.poll();
				// t로 만든 경우
				if (u.n == t) {
					arr.add(u.operators);
					check = true;
					qLen--;
					continue;
				}
				if (visited.get(u.n) != null) {
					qLen--;
					continue;
				}
				temp.add(u.n);
				q.offer(new Number(u.n*2, u.operators+"1"));
				q.offer(new Number(0l, u.operators+"2"));
				q.offer(new Number(u.n*u.n, u.operators+"0"));
				q.offer(new Number(1l, u.operators+"3"));
				qLen--;
			}
			// 방문 처리를 한꺼번에 해준다.
			for (Long l : temp) {
				visited.put(l, true);
			}
			// 위 과정에서 t를 만든 적이 한 번이라도 있는 경우
			if (check) {
				Collections.sort(arr);
				String ss = arr.get(0);
				String result = "";
				for (char c : ss.toCharArray()) {
					result += map.get(c);
				}
				System.out.println(result);
				System.exit(0);
			}
		}
		// t가 한 번도 나오지 않는 경우
		System.out.println(-1);
	}

}
