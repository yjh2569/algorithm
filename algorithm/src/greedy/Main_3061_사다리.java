package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3061_사다리 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 각 도착지점에 도달하는 출발지점을 나타내는 배열
			int[] starts = new int[N+1];
			for (int i = 1; i <= N; i++) {
				starts[i] = Integer.parseInt(st.nextToken());
			}
			int index = 1; // 어떤 출발지에서 출발했을 때 도착 예상 지점
			int cnt = 0; // 필요한 가로줄 개수의 최솟값
			// 왼쪽부터 출발지점으로 지정한다.
			for (int i = 1; i <= N; i++) {
				// 현재 사다리 상태에서 출발지점에서 출발 시 도착 예상 지점을 구한다.
				for (int j = i; j <= N; j++) {
					if (i == starts[j]) index = j;
				}
				// 출발지점에서 출발했을 때 같은 자리로 떨어지도록
				// 도착 예상 지점에 있는 사다리부터 없앤다.
				// 왼쪽부터 처리하기 때문에 처리가 끝난 사다리는 더 이상 처리하지 않아도 된다.
				while (starts[i] != i) {
					int temp = starts[index];
					starts[index] = starts[index-1];
					starts[index-1] = temp;
					index--;
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
