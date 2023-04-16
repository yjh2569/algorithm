package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10253_헨리 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 계산 과정에 포함된 부등식을 정수들의 부등식으로 바꾼다.
			// 예를 들어, 1/x1 <= a/b는 b <= a*x1, 1/x2 <= a/b - 1/x1은 b*x1 <= x2*(a*x1 - b)
			// 1/x3 <= a/b - 1/x1 - 1/x2는 b*x1*x2 <= x3*(x2*(a*x1-b) - b*x1)과 같이 바꾼다.
			// 그러면 x1, x2, ...를 구하는 방식은 다음과 같다.
			// 1. leftSide는 b, rightSide는 a로 초기화한다.
			// 2. leftSide <= rightSide*x1을 만족시키기 위한 가장 작은 x를 찾는다.
			// 이는 (leftSide-1)/rightSide+1과 같다.
			// 3. 만약 leftSide와 rigthSide가 같으면 모든 단위분수를 다 찾은 것이 되므로 마지막으로 찾은 x를 반환한다.
			// 4. 그렇지 않으면 rightSide에서 leftSide를 빼고, leftSide에 x를 곱한 뒤 2번으로 돌어간다.
			// 이렇게 하면 위의 부등식을 순서대로 표현할 수 있다.
			int leftSide = b; // 좌변
			int rightSide = a; // 우변
			int x = 1; // 마지막 x
			while (true) {
				x = (leftSide-1)/rightSide+1;
				rightSide *= x;
				if (leftSide == rightSide) break;
				rightSide -= leftSide;
				leftSide *= x;
			}
			sb.append(x).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
