package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_2716_원숭이_매달기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			// 가장 깊은 지점에 있는 곳에 원숭이를 1마리씩 배치하면,
			// 그 다음 깊이에 있는 곳에는 원숭이를 2마리씩, 그 다음 깊이는 4마리씩 배치해야 하고,
			// 이를 모두 고려하면 총 원숭이의 수의 최솟값은 2^(트리의 깊이)가 된다.
			Stack<Character> stack = new Stack<>(); // 트리의 깊이를 구하기 위한 스택
			int maxDepth = 0; // 트리의 깊이
			// 트리의 깊이를 구한다.
			for (char c : s.toCharArray()) {
				if (c == '[') stack.push(c);
				else stack.pop();
				maxDepth = Math.max(maxDepth, stack.size());
			}
			// 트리의 깊이를 이용해 원숭이의 수의 최솟값을 구한다.
			sb.append(1 << maxDepth).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
