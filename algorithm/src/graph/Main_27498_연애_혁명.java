package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_27498_연애_혁명 {
	// 간선을 나타내는 클래스
	static class Edge implements Comparable<Edge> {
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		public int compareTo(Edge e) {
			return Integer.compare(e.w, this.w);
		}
	}
	// 각 노드의 부모 노드를 저장하는 배열
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 배열
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		// 초기화
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		// Kruskal's algorithm을 활용
		// 간선을 애정도가 높은 순서대로 저장하는 우선순위 큐
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int sum = 0; // 포기하도록 만든 애정도의 합의 최솟값
		int cnt = 0; // 포기하지 않는 사랑 관계의 수
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			// 이미 이루어진 사랑 관계면 u와 v를 union한다.
			if (d == 1) {
				cnt++;
				union(u, v);
			// 그렇지 않으면 포기하거나 이루어줄 사랑 관계 후보에 포함
			} else {
				sum += w;
				pq.offer(new Edge(u, v, w));
			}
		}
		// Kruskal's algorithm에서 MST를 만드는 방식으로, 
		// 간선의 비용이 큰 것부터 꺼내서 Spanning Tree를 만들어나간다.
		while (!pq.isEmpty() && cnt < N-1) {
			Edge e = pq.poll();
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				sum -= e.w;
				cnt++;
			}
		}
		System.out.println(sum);
	}
	// u의 루트 노드를 찾는 함수
	private static int find(int u) {
		if (u == parents[u]) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	// u와 v가 포함된 트리를 잇는 함수
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
}
