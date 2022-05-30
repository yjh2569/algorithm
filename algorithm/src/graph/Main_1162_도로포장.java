package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1162_도로포장 {
	// dijkstra's algorithm 적용 중 방문하는 정점에 대한 정보를 저장하는 클래스
	static class Node implements Comparable<Node> {
		int u, cnt; // 정점의 번호, 지금까지 포장한 도로의 개수
		long cost; // 지금까지 걸린 시간
		public Node(int u, long cost, int cnt) {
			this.u = u;
			this.cost = cost;
			this.cnt = cnt;
		}
		// 정점은 항상 걸린 시간을 가지고 비교
		public int compareTo(Node node) {
			return Long.compare(this.cost, node.cost);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 도로 정보를 저장할 그래프
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new int[] {v, w});
			graph.get(v).add(new int[] {u, w});
		}
		// djikstra's algorithm을 적용하되 도로 포장 개수에 따라서도 나눈다.
		// 단순히 1차원 배열로 하는 경우 경로의 앞쪽에서 포장하는 것이 값을 덮어씌워서
		// 도로를 나중에 포장하는 경우가 나오기 어렵기 때문이다.
		long[][] dijk = new long[N+1][K+1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dijk[i], Long.MAX_VALUE);
		}
		// 초기값
		dijk[1][0] = 0;
		// 방문 배열 역시 1차원이 아닌 2차원으로 정의한다.
		boolean[][] visited = new boolean[N+1][K+1];
		// 방문한 정점을 비용 순으로 저장할 우선 순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 초기값
		pq.offer(new Node(1, 0, 0));
		// dijkstra's algorithm 시행
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int u = cur.u;
			int k = cur.cnt;
			if (u == N) break; // N번 도시에 도착하는 경우 이는 비용이 최소인 상태로 N번 도시에 도달하는 것이라 할 수 있다.
			// 방문 처리
			if (visited[u][k]) continue;
			visited[u][k] = true;
			for (int[] next : graph.get(u)) {
				int v = next[0];
				int w = next[1];
				// 도로를 포장하지 않는 경우
				if (!visited[v][k] && dijk[v][k] > dijk[u][k] + w) {
					dijk[v][k] = dijk[u][k] + w;
					pq.offer(new Node(v, dijk[v][k], k));
				}
				// 도로를 포장하는 경우
				if (k < K && !visited[v][k+1] && dijk[v][k+1] > dijk[u][k]) {
					dijk[v][k+1] = dijk[u][k];
					pq.offer(new Node(v, dijk[v][k+1], k+1));
				}
			}
		}
		// 도로를 몇 개 포장하는지에 따라 결과가 다르므로 모든 경우에 대해 조사하고 그 중 최소값을 찾는다.
		long res = Long.MAX_VALUE;
		for (int k = 0; k <= K; k++) {
			res = Math.min(res, dijk[N][k]);
		}
		System.out.println(res);
	}

}
