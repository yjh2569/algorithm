package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11501_주식 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] stocks = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				stocks[i] = Integer.parseInt(st.nextToken());
			}
			// greedy algorithm 적용
			// 역으로 탐색하면서 가장 높은 주가를 기록한다.
			// 만약 지금까지의 가장 높은 주가가 현재 보고 있는 주가보다 높으면 현재 주식을 사서 지금까지의 가장 높은 주가에 판다.
			// 만약 현재 보고 있는 주가가 더 높으면 가장 높은 주가를 갱신해 다음에는 그 주가에 팔 수 있도록 한다.
			int max = stocks[N-1]; // 역으로 탐색할 때 가장 높은 주가
			long sum = 0; // 이익의 최대값
			for (int i = N-2; i >= 0; i--) {
				if (max >= stocks[i]) {
					sum += max - stocks[i];
				} else {
					max = stocks[i];
				}
			}
			sb.append(sum).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
