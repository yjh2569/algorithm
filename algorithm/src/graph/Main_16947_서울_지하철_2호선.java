package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16947_서울_지하철_2호선 {
	static int N;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] isInCycle;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// 각 지점이 순환선에 속하는지를 나타내는 배열
		isInCycle = new boolean[N+1];
		// 방문 배열
		visited = new boolean[N+1];
		// 각 지점에 대해 DFS를 수행해 순환선에 속하는지를 확인한다.
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited, false);
			dfs(i, i, 0);
		}
		// BFS를 통해 각 지점이 순환선으로부터 떨어진 거리를 구한다.
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(bfs(i)).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// DFS를 통해 순환선에 속하는지를 판단하는 함수
	// start는 시작 지점, u는 현재 방문하는 지점, cnt는 현재까지 방문한 지점의 개수를 의미한다.
	private static void dfs(int start, int u, int cnt) {
		// start가 순환선에 속하는 지점인 경우
		if (start == u && cnt >= 2) {
			isInCycle[u] = true;
			return;
		}
		visited[u] = true;
		for (int v : graph.get(u)) {
			// 방문하지 않은 지점에 대해 방문
			if (!visited[v]) dfs(start, v, cnt+1);
			// start에 도달한 경우
			else if (start == v && cnt >= 2) dfs(start, v, cnt);
		}
	}
	// BFS를 통해 순환선까지의 최단 거리를 구하는 함수
	private static int bfs(int u) {
		if (isInCycle[u]) return 0;
		Queue<Integer> q = new LinkedList<>();
		Arrays.fill(visited, false);
		q.offer(u);
		visited[u] = true;
		int l = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				u = q.poll();
				for (int v : graph.get(u)) {
					if (isInCycle[v]) return l;
					if (!visited[v]) {
						visited[v] = true;
						q.offer(v);
					}
				}
				qLen--;
			}
			l++;
		}
		return 0;
	}
}
