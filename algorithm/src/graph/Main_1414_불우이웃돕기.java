package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_1414_불우이웃돕기 {
	// 랜선이 연결하는 컴퓨터와 랜선의 길이를 간선 형태로 나타낸 클래스
	static class Edge implements Comparable<Edge>{
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Edge e) {
			return Integer.compare(this.w, e.w);
		}
	}
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 랜선들을 저장할 우선 순위 큐
		// 랜선 길이가 짧은 순서대로 저장
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = s.charAt(j);
				// 컴퓨터 i와 컴퓨터 j를 연결하는 랜선이 없는 경우
				if (c == '0') continue;
				if ('a' <= c && c <= 'z') {
					pq.offer(new Edge(i, j, c-'a'+1));
				} else if ('A' <= c && c <= 'Z') {
					pq.offer(new Edge(i, j, c-'A'+27));
				}
			}
		}
		// Kruskal's algorithm을 적용해 최소 스패닝 트리를 구하고, 
		// 최소 스패닝 트리에 있는 간선을 제외한 나머지 간선의 비용을 모두 더하면 기부할 수 있는 랜선 길이의 최댓값을 구할 수 있다.
		parents = new int[N+1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		int cnt = 0; // 최소 스패닝 트리를 만들기 위해 선택한 간선의 개수
		int sum = 0; // 트리에 포함되지 않은 간선의 비용의 합, 즉 이 값이 기부할 수 있는 랜선 길이의 최댓값이 된다.
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			// 랜선 e를 선택
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cnt++;
			// 랜선 e를 기부
			} else {
				sum += e.w;
			}
		}
		// 최소 스패닝 트리를 만들기 위해 필요한 간선은 N-1개이므로,
		// 만약 cnt가 N-1이 아니라면 최소 스패닝 트리를 만들 수 없다.
		System.out.println(cnt == N-1 ? sum : -1);
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
