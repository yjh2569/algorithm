package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1613_역사 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] graph = new int[n+1][n+1];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u][v] = 1;
		}
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
			if (graph[u][v] == 1 && graph[v][u] == 0) sb.append(-1);
			else if (graph[v][u] == 1 && graph[u][v] == 0) sb.append(1);
			else sb.append(0);
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());

	}

}
