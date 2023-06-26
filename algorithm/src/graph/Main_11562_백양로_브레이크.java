package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11562_백양로_브레이크 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// graph[i][j]는 i에서 j로 갈 때 통행이 가능한지를 정수로 나타낸다.
		// 값이 0인 경우는 통행이 가능한 경우, 1인 경우는 통행이 불가능하나 일방통행 도로가 있어 양방향 도로로 바꾸면 되는 경우,
		// Integer.MAX_VALUE/10인 경우는 애초에 도로가 없어 통행이 불가능한 경우다.
		int[][] graph = new int[n+1][n+1];
		// 초기화
		for (int i = 1; i <= n; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE/10);
			graph[i][i] = 0;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// u에서 v로 가는 일방통행 도로가 있는 경우
			// v에서 u로 가려면 일방통행 도로를 양방향 도로로 바꿔야 한다.
			if (b == 0) {
				graph[u][v] = 0;
				graph[v][u] = 1;
			// u와 v 사이에 양방향 도로가 있는 경우
			} else {
				graph[u][v] = graph[v][u] = 0;
			}
		}
		// 플로이드-와샬 알고리즘을 이용해 출발지에서 도착지까지 가기 위해 최소 몇 개의 도로를 양방향 도로로 바꿔야 하는지를 구한다.
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if (i == k) continue;
				for (int j = 1; j <= n; j++) {
					if (j == i || j == k) continue;
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}
		// 출력
		int k = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			sb.append(graph[s][e]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
