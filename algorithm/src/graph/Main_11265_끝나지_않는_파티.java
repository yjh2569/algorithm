package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11265_끝나지_않는_파티 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 플로이드-와샬 알고리즘을 활용해 각 파티장까지의 최단 거리를 계산한다.
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) continue;
				for (int j = 0; j < N; j++) {
					if (j == k || j == i) continue;
					if (dp[i][j] > dp[i][k] + dp[k][j]) dp[i][j] = dp[i][k] + dp[k][j];
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			int C = Integer.parseInt(st.nextToken());
			// A에서 B까지 C 시간 이내로 도착할 수 있는 경우
			if (dp[A][B] <= C) sb.append("Enjoy other party").append("\n");
			// 그렇지 않은 경우
			else sb.append("Stay here").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
