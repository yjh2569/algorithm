package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6497_전력난 {
	static int M, N;
	static int[] parents;
	// 간선 정보를 저장하는 Edge 클래스
	// 우선순위 큐에 넣기 위해 Comparable 인터페이스를 구현
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
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 여러 개의 테스트케이스가 주어짐에 유의
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			// M과 N이 0일 때 테스트케이스가 끝난다.
			if (M == 0 && N == 0) break;
			// 도로 길이의 총합
			int sum = 0;
			// 도로 정보들을 도로 길이 순서대로 저장하기 위한 우선순위 큐
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				sum += z;
				pq.offer(new Edge(x, y, z));
			}
			// Kruskal's algorithm을 적용
			// 이를 위해 각 노드마다 해당 노드가 속한 트리의 루트 노드를 알려주는 배열을 만든다. 
			parents = new int[M+1];
			// 초기화
			for (int i = 1; i <= M; i++) {
				parents[i] = i;
			}
			// MST의 비용
			int cost = 0;
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				if (find(e.u) != find(e.v)) {
					union(e.u, e.v);
					cost += e.w;
				}
			}
			// 문제에서 묻는 것은 절약할 수 있는 최대 비용이므로 들 수 있는 총 비용에서 최소 비용을 뺀 값이 정답이다.
			sb.append(sum - cost).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
		
	}
	// Kruskal's algorithm에 필요한 union 함수와 find 함수
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}

}
