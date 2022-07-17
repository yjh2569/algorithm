package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1105_팔 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 정수로 입력을 받지 않고 문자열로 입력을 받는다.
		String L = st.nextToken();
		String R = st.nextToken();
		// 8이 가장 적게 들어있는 수에 있는 8의 개수
		int cnt = 0;
		// L과 R의 길이가 같은 경우에만 8이 가장 적게 들어있는 수에 8이 들어갈 가능성이 있다.
		// 왜냐하면 L과 R의 길이가 다른 경우에는 L보다 길면서 R과 길이가 같고, 맨 앞 자리수가 1, 나머지 자리수가 0인 수를 항상 포함하기 때문이다.
		// ex) L = 8888 R = 10910인 경우 10000이 L과 R 사이에 존재하므로 cnt는 0
		if (L.length() == R.length()) {
			int N = L.length();
			// 길이가 같은 경우에는 각 자리수를 비교했을 때 자리수가 서로 다르면 그 밑으로는 볼 필요가 없다.
			// 자리수가 서로 다른 경우에는 다음과 같이 수를 만들 수 있다.
			// 1. R에서 나온 자리수가 8인 경우 -> L에서 해당 자리수는 7 이하
			// 해당 자리수에 7, 그 밑의 나머지 자리수를 9로 만들면 된다.
			// 2. L에서 나온 자리수가 8인 경우 -> R에서 해당 자리수는 9만 가능
			// 해당 자리수에 9, 그 밑의 나머지 자리수를 0으로 만들면 된다.
			// 자리수가 서로 같은 경우에는 L과 R 사이의 숫자는 항상 L과 R의 자리수를 따라갈 수밖에 없다.
			// 이때 그 자리수가 8인 경우에만 cnt를 1 증가시킨다.
			for (int i = 0; i < N; i++) {
				if (L.charAt(i) != R.charAt(i)) break;
				if (L.charAt(i) == '8') cnt++;
			}
		}
		System.out.println(cnt);
	}

}
