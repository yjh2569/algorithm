package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_25918_북극곰은_괄호를_찢어 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 스택을 활용해 해결한다.
		Stack<Character> stack = new Stack<>();
		String s = br.readLine();
		int max = 0; // 원하는 문자열을 만드는데 걸리는 최소 시간
		int depth = 0; // 현재 찢어진 O 또는 X의 깊이
		// 가장 짧은 시간 안에 원하는 문자열을 만드려면, () 안에서는 O를 찢고, )( 안에서는 X를 찢어야 한다.
		// 예를 들어, ()()의 경우 O를 찢은 뒤 그 안에서 X를 찢는 것보다는, OO를 찢는 것이 낫다.
		// 따라서, 스택에 괄호를 순차적으로 넣으면서, 스택이 비었거나 스택에 들어있는 괄호 모양과 같은 모양의 괄호를 찾으면
		// 스택에 계속해서 넣고, 그렇지 않으면 스택에서 제거한다.
		// 이를 모두 진행한 후 스택이 비었으면 max 값을, 그렇지 않으면 -1을 출력한다.
		for (int i = 0; i < N; i++) {
			char c = s.charAt(i);
			if (stack.isEmpty() || c == stack.peek()) {
				stack.push(c);
				depth++;
			} else {
				stack.pop();
				depth--;
			}
			max = Math.max(max, depth);
		}
		System.out.println(stack.isEmpty() ? max : -1);
	}

}
