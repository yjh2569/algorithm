package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_14699_관악산_등반 {
	static int N, M;
	static int[] heights;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		heights = new int[N+1];
		for (int i = 1; i <= N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		graph = new ArrayList<>(); // 양방향 그래프
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// dynamic programming을 이용한다.
		// DFS를 이용하되, 구한 값을 memoization해 후에 이미 구한 값이 있다면 해당 값을 바로 이용해 시간을 단축한다. 
		dp = new int[N+1];
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(dfs(i)).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// DFS를 이용하되, memoization과 함께 진행하는 함수
	private static int dfs(int u) {
		// 이미 구한 값인 경우
		if (dp[u] > 0) return dp[u];
		// 구하지 않은 경우 새롭게 구해나간다.
		dp[u] = 1;
		int max = 0;
		// 인접한 지점들 중 경유했을 때 가장 많은 지점을 지날 수 있는 지점을 찾는다.
		for (int v : graph.get(u)) {
			// 기본적으로 인접한 지점이 더 높은 지점이어야 한다.
			if (heights[v] <= heights[u]) continue;
			max = Math.max(max, dfs(v));
		}
		// 가장 많은 지점을 지나는 지점을 향하면 총 지나는 지점은, 다음 지점에서 출발한 경우 지점의 개수에 1을 더한 값이다.
		dp[u] += max;
		return dp[u];
	}

}
