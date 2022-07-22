package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16562_친구비 {
	// 간선을 나타내는 클래스
	static class Edge implements Comparable<Edge>{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		public int compareTo(Edge e) {
			return Integer.compare(this.w, e.w);
		}
	}
	// Kruskal's algorithm을 적용할 때 union-find를 위한 배열
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// Kruskal's algorithm을 적용하여 해결한다.
		// 간선을 비용이 적은 순서대로 우선 순위 큐에 넣는다.
		// 자신을 0번째 정점, 나머지 친구들은 i번째 정점, 친구비를 0번째 정점과 i번째 정점을 잇는 간선의 비용으로 한다.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			pq.offer(new Edge(0, i, Integer.parseInt(st.nextToken())));
		}
		// parents 배열 초기화
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		// 주어진 친구 관계를 활용해 union 작업을 수행한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			union(u, v);
		}
		// Kruskal's algorithm 수행
		int cost = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cost += e.w;
			}
		}
		// 위 알고리즘을 거치면 MST를 만들수는 있지만 비용이 얼마나 드는지에 따라 실제로 만드는 것이 불가능할 수도 있다.
		// 비용이 가지고 있는 돈보다 많으면 모든 사람과 친구가 될 수 없다.
		System.out.println(cost <= K ? cost : "Oh no");
	}
	
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
	

}
