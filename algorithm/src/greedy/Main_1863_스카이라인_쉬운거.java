package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1863_스카이라인_쉬운거 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		// greedy algorithm을 적용하면 효율적으로 해결할 수 있다.
		// 1. 현재 고려 중인 건물들의 높이를 저장할 스택을 만든다.
		// 2. 고도가 바뀌는 좌표를 받았을 떄 스택이 비거나 스택의 가장 끝에 있는 높이(Y)가 y좌표보다 작거나 같을 때까지 
		// pop하고, 건물 개수를 1 증가시킨다.(y가 Y보다 작은 경우 높이가 Y인 건물이 최소 1개는 있어야 한다.)
		// 3. 스택의 가장 끝에 있는 높이가 y좌표와 같은 경우 다음 고도가 바뀌는 좌표로 continue한다.(한 건물로 쭉 이어질 수 있기 떄문이다.)
		// 4. 그렇지 않으면 스택에 현재 좌표를 push한다.
		// 5. 모든 좌표를 다 받은 뒤, 스택이 빌 때까지 pop하고, 건물 개수를 1 증가시킨다.(2번에서 y좌표가 0인 경우를 고려하는 것과 같음)
		Stack<Integer> stack = new Stack<>(); // 건물들의 높이를 저장할 스택
		int cnt = 0; // 건물의 최소 개수
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 2번 과정
			while (!stack.isEmpty() && stack.peek() > y) {
				stack.pop();
				cnt += 1;
			}
			// 3번 과정
			if (!stack.isEmpty() && stack.peek() == y) continue;
			// 4번 과정
			stack.push(y);
		}
		// 5번 과정
		while (!stack.isEmpty() && stack.peek() > 0) {
			stack.pop();
			cnt += 1;
		}
		System.out.println(cnt);
	}

}
