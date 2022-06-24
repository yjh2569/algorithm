package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5972_택배_배송 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// 양방향 그래프
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new int[] {v, w});
			graph.get(v).add(new int[] {u, w});
		}
		// dijkstra's algorithm을 이용하면 해결할 수 있는 문제
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
		boolean[] visited = new boolean[N+1];
		pq.offer(new int[] {1, 0});
		int[] dijkstra = new int[N+1];
		Arrays.fill(dijkstra, Integer.MAX_VALUE);
		dijkstra[1] = 0;
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (u[0] == N) {
				System.out.println(dijkstra[N]);
				System.exit(0);
			}
			if (visited[u[0]]) continue;
			visited[u[0]] = true;
			for (int[] v : graph.get(u[0])) {
				if (!visited[v[0]] && dijkstra[v[0]] > dijkstra[u[0]] + v[1]) {
					dijkstra[v[0]] = dijkstra[u[0]] + v[1];
					pq.offer(new int[] {v[0], dijkstra[v[0]]});
				}
			}
		}
	}

}
