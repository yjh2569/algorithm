package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_8901_화학_제품 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 각 테스트케이스에 대해 수행
		for (int t = 1; t <= T; t++) {
			int[] cnts = new int[3];
			int[] costs = new int[3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				cnts[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}
			int max = 0; // 최대 이익
			// 만든 AB의 개수를 x개, BC의 개수를 y개, CA의 개수를 z개라고 하자.
			// 그러면 x+z <= cnts[0], x+y <= cnts[1], y+z <= cnts[2]를 만족할 때, 
			// costs[0]*x + costs[1]*y + costs[2]*z의 최댓값을 구하는 문제와 같다.
			// 이때, x가 만약 고정된다면 y와 z를 결정하기 위해서는 BC와 CA 중 비싼 것을 최대한 많이 생산하면 된다.
			// 그리고 나서 남은 재료로 덜 비싼 것을 최대한 생산하면 최대 금액을 얻을 수 있다.
			// 단, x는 0 ~ min(cnts[0], cnts[1])까지 모든 경우를 고려해야 한다.
			for (int x = 0; x <= Math.min(cnts[0], cnts[1]); x++) {
				int y = 0; int z = 0;
				if (costs[1] >= costs[2]) {
					y = Math.min(cnts[1] - x, cnts[2]);
					z = Math.min(cnts[0] - x, cnts[2] - y);
				} else {
					z = Math.min(cnts[0] - x, cnts[2]);
					y = Math.min(cnts[1] - x, cnts[2] - z);
				}
				max = Math.max(max, costs[0]*x + costs[1]*y + costs[2]*z);
			}
			sb.append(max).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
