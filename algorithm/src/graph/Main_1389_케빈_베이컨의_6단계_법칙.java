package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1389_케빈_베이컨의_6단계_법칙 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// dp[i][j]는 i와 j 사이의 단계를 의미한다.
		int[][] dp = new int[N+1][N+1];
		// 초기화
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			dp[u][v] = 1;
			dp[v][u] = 1;
		}
		// 플로이드-와샬 알고리즘을 활용해 계산
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {
					if (i == j || j == k) continue;
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}
		// 케빈 베이컨의 수를 계산하고 그 중 최솟값을 가진 사람을 찾는다.
		int min = Integer.MAX_VALUE;
		int minIdx = 1;
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				sum += dp[i][j];
			}
			if (min > sum) {
				min = sum;
				minIdx = i;
			}
		}
		System.out.println(minIdx);
	}

}
