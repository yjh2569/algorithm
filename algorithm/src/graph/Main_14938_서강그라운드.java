package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14938_서강그라운드 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int[] items = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		// 플로이드-와샬 알고리즘을 적용하기 위해 graph를 배열로 받는다.
		int[][] graph = new int[n+1][n+1];
		// 초기화
		for (int i = 0; i <= n; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE/10);
			graph[i][i] = 0;
		}
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			graph[a][b] = l;
			graph[b][a] = l;
		}
		// 플로이드-와샬 알고리즘을 적용한다.
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if (i == k) continue;
				for (int j = 1; j <= n; j++) {
					if (j == k || j == i) continue;
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}
		// 얻을 수 있는 아이템의 최대 개수
		int max = 0;
		// 각 지점에 착륙했을 때 얻을 수 있는 아이템의 개수
		int cur = 0;
		// 각 지점에 착륙했을 때 얻을 수 있는 아이템의 개수를 구하고 이를 max에 갱신
		for (int i = 1; i <= n; i++) {
			cur = 0;
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] <= m) cur += items[j];
			}
			max = Math.max(max, cur);
		}
		System.out.println(max);
	}

}
