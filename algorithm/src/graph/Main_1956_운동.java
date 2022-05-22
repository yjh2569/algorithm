package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1956_운동 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] graph = new int[V][V];
		// 경로가 없는 경우 간선의 비용(문제의 조건으로 도달 불가능한 값)
		final int MAX = Integer.MAX_VALUE/100;
		for (int i = 0; i < V; i++) {
			Arrays.fill(graph[i], MAX);
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken())-1;
			int v = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			graph[u][v] = w;
		}
		// 플로이드 와샬 알고리즘
		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				// 시간 단축을 위해 경로가 없는 경우 continue
				if (graph[i][k] == MAX) continue;
				for (int j = 0; j < V; j++) {
					if (graph[k][j] == MAX) continue;
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		int min = MAX;
		for (int i = 0; i < V; i++) {
			min = Math.min(min, graph[i][i]);
		}
		System.out.println(min == MAX ? -1 : min);
	}

}
