package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_16202_MST_게임 {
	// 간선 클래스
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
	// 간선들을 저장하는 ArrayList
	static ArrayList<Edge> edges;
	// 각 노드의 부모 노드를 저장하는 배열
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			edges.add(new Edge(x, y, m));
		}
		// 남은 턴 수
		int restCnt = K;
		parents = new int[N+1];
		StringBuilder sb = new StringBuilder();
		// 출력 내 빈 칸을 좀더 효율적으로 넣기 위해 미리 정의
		String blank = " ";
		// 각 턴에 대해 Kruskal's algorithm을 통해 최소 스패닝 트리를 찾는다.
		for (int k = 0; k < K; k++) {
			// 초기화
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			// Kruskal's algorithm에 따라 찾아낸 트리에 있는 현재 간선 개수
			int edgeCnt = 0;
			// 현재 트리의 비용
			int cost = 0;
			// Kruskal's algorithm 진행
			for (int i = k; i < M; i++) {
				Edge e = edges.get(i);
				if (find(e.u) != find(e.v)) {
					edgeCnt++;
					union(e.u, e.v);
					cost += e.w;
				}
			}
			// 최소 스패닝 트리를 완성하지 못한 경우
			if (edgeCnt < N-1) break;
			sb.append(cost).append(blank);
			restCnt--;
		}
		// 최소 스패닝 트리를 완성하지 못한 경우 이후는 항상 최소 스패닝 트리를 만들 수 없다.
		for (int i = 0; i < restCnt; i++) {
			sb.append(0).append(blank);
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 노드 u가 속한 트리의 루트 노드를 찾는 함수
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
