package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14284_간선_이어가기_2 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
		}
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		// dijkstra's algorithm을 이용한다.
		int[] dijkstra = new int[n+1];
		boolean[] visited = new boolean[n+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
		Arrays.fill(dijkstra, Integer.MAX_VALUE/10);
		dijkstra[s] = 0;
		pq.offer(new int[] {s, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (visited[u[0]]) continue;
			// dijkstra's algorithm 중 t에 도착하면 곧 최단거리로 도착한다는 의미이다. 
			if (u[0] == t) {
				System.out.println(u[1]);
				System.exit(0);
			}
			visited[u[0]] = true;
			for (int[] v : graph.get(u[0])) {
				if (dijkstra[v[0]] > dijkstra[u[0]] + v[1]) {
					dijkstra[v[0]] = dijkstra[u[0]] + v[1];
					pq.offer(new int[] {v[0], dijkstra[v[0]]});
				}
			}
		}
	}

}
