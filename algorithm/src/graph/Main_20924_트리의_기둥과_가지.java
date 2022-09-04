package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20924_트리의_기둥과_가지 {
	static int N, R, G;
	// 간선 클래스
	static class Edge {
		int v, w;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static ArrayList<ArrayList<Edge>> graph;
	static int trunk;
	static int maxBranch;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// 트리 저장을 위한 그래프
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Edge(v, w));
			graph.get(v).add(new Edge(u, w));
		}
		G = 0; // 기가 노드
		trunk = 0; // 나무 기둥의 길이
		maxBranch = 0; // 가장 긴 가지의 길이
		// DFS를 위한 방문 배열
		visited = new boolean[N+1];
		// 나무 기둥의 길이를 구하고 기가 노드를 찾는다.
		checkTrunk(R);
		// 가장 긴 가지의 길이를 구한다.
		checkBranch(G, 0);
		System.out.println(trunk+" "+maxBranch);
	}
	// 루트 노드에서 기가 노드까지 DFS를 통해 이동하면서 나무 기둥의 길이를 구한다.
	private static void checkTrunk(int u) {
		int cnt = 0; // 현재 노드의 인접 노드 중 방문하지 않은 노드 개수
		visited[u] = true;
		for (Edge e : graph.get(u)) {
			if (!visited[e.v]) cnt++;
		}
		// 기가 노드를 찾았거나 리프 노드인 경우
		if (cnt != 1) {
			G = u;
			return;
		// 나무 기둥 내 노드인 경우
		} else {
			for (Edge e : graph.get(u)) {
				if (!visited[e.v]) {
					trunk += e.w;
					checkTrunk(e.v);
				}
			}
		}
	}
	// 기가 노드에서 DFS를 통해 가장 긴 가지의 길이를 구한다.
	// u는 현재 노드, l은 현재 노드까지의 가지 길이를 의미한다.
	private static void checkBranch(int u, int l) {
		int cnt = 0; // 현재 노드의 인접 노드 중 방문하지 않은 노드 개수
		visited[u] = true;
		for (Edge e : graph.get(u)) {
			if (!visited[e.v]) {
				cnt++;
				checkBranch(e.v, l+e.w);
			}
		}
		// 리프 노드인 경우
		if (cnt == 0) {
			maxBranch = Math.max(maxBranch, l);
		}
		visited[u] = false;
	}

}
