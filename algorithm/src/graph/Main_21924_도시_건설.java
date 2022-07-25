package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21924_도시_건설 {
	// 간선 클래스
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
	// Kruskal's algorithm에서 쓰일 union-find를 위한 배열
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 모든 도로의 설치 비용의 총합
		long total = 0;
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.add(new Edge(u, v, w));
			total += w;
		}
		// Kruskal's algorithm을 사용한다.
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		// 최소 비용으로 연결했을 때 연결된 도로의 개수 
		int cnt = 0;
		// MST의 비용
		long cost = 0;
		while (!edges.isEmpty() && cnt < N-1) {
			Edge e = edges.poll();
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cost += e.w;
				cnt++;
			}
		}
		// 연결된 도로의 개수가 N-1개이면 MST가 완성되었다는 뜻이므로 절약한 비용을 출력
		if (cnt == N-1) System.out.println(total - cost);
		// 연결된 도로의 개수가 N-1개 미만이면 MST를 완성하지 못했다는 뜻이므로 -1을 출력
		else System.out.println(-1);
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
