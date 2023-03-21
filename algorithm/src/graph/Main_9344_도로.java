package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_9344_도로 {
	// 간선의 양끝 지점과 간선 비용
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
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			// 간선들을 비용이 적은 순서대로 저장하는 우선순위 큐
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				pq.offer(new Edge(u, v, w));
			}
			// Kruskal's algorithm을 이용해 최소 스패닝 트리를 구한다.
			parents = new int[N+1];
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			int cnt = 0; // 현재 최소 스패닝 트리를 만들기 위해 선택한 간선 개수
			boolean contains = false; // 간선 (p, q)를 최소 스패닝 트리에 포함하는지를 나타내는 변수
			outer: while (!pq.isEmpty()) {
				if (cnt == N-1) break; // 최소 스패닝 트리를 만든 경우
				Edge e = pq.poll();
				if (find(e.u) != find(e.v)) {
					union(e.u, e.v);
					cnt++;
					// 간선을 추가할 때 해당 간선이 간선 (p, q)인지 확인
					if (Math.min(p, q) == Math.min(e.u, e.v) && Math.max(p, q) == Math.max(e.u, e.v)) {
						contains = true;
						break outer;
					}
				}
			}
			sb.append(contains ? "YES" : "NO").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 노드 u의 루트 노드를 찾는 함수
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	// 노드 u와 노드 v가 각각 속한 트리를 합치는 함수
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}

}
