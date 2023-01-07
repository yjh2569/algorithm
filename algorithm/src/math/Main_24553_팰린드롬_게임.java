package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_24553_팰린드롬_게임 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			// 1. N이 1, ... ,9인 경우
			// 한 자리 수는 항상 팰린드롬 수이므로 상윤이가 전체를 가져가면 상윤이가 이긴다.
			// 2. N이 10인 경우
			// 상윤이는 무조건 1~9개의 돌을 가져갈 수밖에 없고 이후 승우가 나머지 돌을 가져가면 상윤이가 진다.
			// 3. N이 11~19인 경우
			// 상윤이가 10개의 돌을 남기고 가져가면 2번 케이스에서 승우가 먼저 시작하는 케이스와 같으므로 승우가 진다. 따라서 상윤이가 이긴다.
			// 4. N이 20인 경우
			// 상윤이는 1~9개의 돌 혹은 11개의 돌을 가져갈 수밖에 없고, 이러면 승우가 이기게 된다.
			// 팰린드롬 수는 일의 자리가 0이 되는 경우는 존재하지 않기 때문에, 팰린드롬 수만큼 돌을 가져가면 일의 자리 수가 바뀌게 된다.
			// 따라서 최선의 플레이를 하는 경우 위와 같은 케이스가 반복될 수밖에 없다.
			// 즉, N이 10의 배수인 경우에는 상윤이가 지고, 나머지 경우에는 상윤이가 이긴다.
			long N = Long.parseLong(br.readLine());
			if (N%10 == 0) sb.append(1).append("\n");
			else sb.append(0).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
