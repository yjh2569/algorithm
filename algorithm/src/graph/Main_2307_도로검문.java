package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2307_도로검문 {
	// dijkstra's algorithm 진행 중 현재 방문한 노드, 노드까지의 최단 거리, 
	// 해당 노드 바로 이전에 방문한 간선 정보를 저장하는 클래스
	static class Node implements Comparable<Node> {
		int u, w;
		Edge prevEdge;
		public Node(int u, int w, Edge prevEdge) {
			this.u = u;
			this.w = w;
			this.prevEdge = prevEdge;
		}
		public int compareTo(Node n) {
			return Integer.compare(this.w, n.w);
		}
	}
	// 간선의 양끝점, 거리 정보를 저장하는 클래스
	static class Edge {
		int u, v, w;
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
	static int N, M;
	static ArrayList<ArrayList<Edge>> graph; // 그래프
	static int[] parents; // 1에서 N까지 최단 거리로 이동할 때 i 이전에 방문한 노드를 parents[i]로 하는 배열
	static boolean[][] canPass; // i에서 j로 가는 간선을 지나갈 수 있는지를 (i, j) 성분에 저장하는 배열
	static int[] dijk; // dijkstra's algorithm에서 각 노드까지의 최단 거리를 저장하는 배열
	// dijkstra's algorithm을 위한 변수
	static PriorityQueue<Node> pq;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		parents = new int[N+1];
		canPass = new boolean[N+1][N+1];
		// 초기화
		for (int i = 0; i <= N; i++) {			
			Arrays.fill(canPass[i], true);
		}
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Edge(u, v, w));
			graph.get(v).add(new Edge(v, u, w));
		}
		dijk = new int[N+1];
		pq = new PriorityQueue<>();
		visited = new boolean[N+1];
		parents[1] = 1;
		// 어떤 도로도 막히지 않았을 때 최단 거리를 구한다.
		int initial = dijkstra(false);
		int max = 0; // 한 도로를 막음으로써 지연시킬 수 있는 최대 시간
		// 1부터 N까지의 최단 경로 중 하나를 막아야 최단 경로에 영향이 있으므로 해당 도로만 막는다.
		for (int i = N; i != parents[i]; i = parents[i]) {
			canPass[parents[i]][i] = false; // 도로를 막는다.
			// 도로를 막았을 때 최단 거리를 구한다.
			int afterRemove = dijkstra(true);
			// 해당 도로를 막음으로 인해 N에 도달하지 못하는 경우 -1을 출력한다.
			if (afterRemove == Integer.MAX_VALUE/10) {
				max = -1;
				break;
			}
			// 최댓값 갱신
			max = Math.max(max, afterRemove - initial);
			canPass[parents[i]][i] = true; // 막은 도로를 다시 풀어준다.
		}
		System.out.println(max);
	}
	// dijkstra's algorithm을 통해 1부터 N까지의 최단 거리를 구하는 함수
	private static int dijkstra(boolean roadRemoved) {
		// 초기화
		Arrays.fill(dijk, Integer.MAX_VALUE/10);
		dijk[1] = 0;
		pq.clear();
		pq.offer(new Node(1, 0, new Edge(0, 1, 0)));
		Arrays.fill(visited, false);
		// djikstra's algorithm 진행
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visited[n.u]) continue;
			visited[n.u] = true;
			// 최단 경로 고려에 쓰기 위해 parents 배열을 갱신한다.
			// 단, 도로를 막지 않은 경우에만 갱신한다.
			if (n.prevEdge.u != 0 && !roadRemoved) {
				parents[n.u] = n.prevEdge.u;
			}
			// 목적지에 도달한 경우
			if (n.u == N) break;
			for (Edge e : graph.get(n.u)) {
				// 해당 도로가 지나갈 수 없는 도로인 경우
				if (!canPass[e.u][e.v]) continue;
				if (!visited[e.v] && dijk[e.v] > dijk[e.u] + e.w) {
					dijk[e.v] = dijk[e.u] + e.w;
					pq.offer(new Node(e.v, dijk[e.v], e));
				}
			}
		}
		return dijk[N];
	}

}
