package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15591_MooTube {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			graph.get(p).add(new int[] {q, r});
			graph.get(q).add(new int[] {p, r});
		}
		// (i, j) 성분은 동영상 i와 동영상 j의 유사도를 의미
		int[][] dp = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			// 같은 동영상 간의 유사도는 최대치라고 가정
			dp[i][i] = Integer.MAX_VALUE;
			// BFS를 통해 경로를 찾고, 경로 탐색 중 유사도를 구한다.
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[N+1];
			q.offer(i);
			visited[i] = true;
			while (!q.isEmpty()) {
				int u = q.poll();
				for (int[] v : graph.get(u)) {
					if (visited[v[0]]) continue;
					visited[v[0]] = true;
					dp[i][v[0]] = Math.min(dp[i][u], v[1]);
					q.offer(v[0]);
				}
			}
		}
		// 각 질문에 대한 답을 출력
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (v != j && dp[v][j] >= k) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
