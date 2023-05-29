package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1719_택배 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// graph[i][j]는 (i+1)번 집하장에서 (j+1)번 집하장까지의 최단 경로의 비용을 의미한다.
		int[][] graph = new int[n][n];
		// path[i][j]는 (i+1)번 집하장에서 (j+1)번 집하장으로 최단 경로를 통해 가기 위해 몇 번 집하장으로 가야하는지를 나타낸다.
		int[][] path = new int[n][n];
		// 초기화
		for (int i = 0; i < n; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE/10);
			Arrays.fill(path[i], n);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			graph[u][v] = graph[v][u] = w;
			path[u][v] = v;
			path[v][u] = u;
		}
		// 플로이드-와샬 알고리즘을 적용한다.
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (i == k) continue;
				for (int j = 0; j < n; j++) {
					if (j == k || j == i) continue;
					// 최단 경로의 비용을 갱신할 때 path 배열도 함께 갱신한다.
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
						path[i][j] = path[i][k];
					}
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) sb.append("- ");
				else sb.append(path[i][j]+1).append(" ");
			}
			if (sb.length() > 0) sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
