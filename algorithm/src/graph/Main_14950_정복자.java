package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14950_정복자 {
	// 간선의 양 끝 노드와 비용을 저장하는 클래스
	static class Edge implements Comparable<Edge> {
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
	// Kruskal's algorithm 적용을 위해 부모 노드의 번호를 저장하는 배열
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			pq.offer(new Edge(A, B, C));
		}
		// Kruskal's algorithm을 적용한다.
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		// 모든 도시를 정복하는데 드는 최소 비용
		int cost = 0;
		// 사용한 도로의 수
		int num = 0;
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				// 도로를 사용한 만큼 추가 요금을 지불한다.
				cost += e.w + num * t;
				// 사용한 도로의 수를 증가시킨다.
				num++;
			}
		}
		System.out.println(cost);
	}
	// 노드의 조상 노드를 찾는 함수
	private static int find(int u) {
		if (parents[u] == u)
			return parents[u];
		else
			return parents[u] = find(parents[u]);
	}
	// 두 노드가 속한 트리를 합치는 함수
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v)
			parents[u] = v;
	}

}
