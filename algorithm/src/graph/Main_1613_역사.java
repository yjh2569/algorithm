package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1613_역사 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		// 그래프
		int[][] graph = new int[n+1][n+1];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// 사건 u가 사건 v보다 먼저 일어난 경우 u -> v라는 간선이 존재한다고 가정
			graph[u][v] = 1;
		}
		// 플로이드-와샬 알고리즘을 통해 임의의 두 정점(사건)의 시간 순서를 알 수 있는지 조사
		for (int l = 1; l <= n; l++) {
			for (int i = 1; i <= n; i++) {
				if (graph[i][l] == 0) continue;
				for (int j = 1; j <= n; j++) {
					if (graph[l][j] == 0) continue;
					graph[i][j] = 1;
				}
			}
		}
		int s = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// u가 v보다 먼저 일어난 경우
			if (graph[u][v] == 1 && graph[v][u] == 0) sb.append(-1);
			// v가 u보다 먼저 일어난 경우
			else if (graph[v][u] == 1 && graph[u][v] == 0) sb.append(1);
			// 사건의 전후를 알 수 없는 경우
			else sb.append(0);
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());

	}

}
