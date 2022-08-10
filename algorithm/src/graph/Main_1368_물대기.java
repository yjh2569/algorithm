package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1368_물대기 {
	// Prim's algorithm에 사용할 정점 정보 저장 클래스
	static class Node implements Comparable<Node>{
		int u, w;
		public Node(int u, int w) {
			this.u = u;
			this.w = w;
		}
		public int compareTo(Node n) {
			return Integer.compare(this.w, n.w);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 시작점과 해당 시작점에서 우물을 파는데 드는 비용
		int minIdx = 0;
		int min = Integer.MAX_VALUE;
		// 각 논에서 우물을 파는데 드는 비용
		int[] self = new int[N];
		for (int i = 0; i < N; i++) {
			self[i] = Integer.parseInt(br.readLine());
			// 시작점은 우물을 파는데 가장 적은 지점으로 한다.
			if (min > self[i]) {
				min = self[i];
				minIdx = i;
			}
		}
		// 논들 사이에 물을 끌어오는 비용
		int[][] graph = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dense한 그래프가 주어지기 때문에 Prim's algorithm을 사용한다.
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 초기화
		pq.offer(new Node(minIdx, min));
		boolean[] visited = new boolean[N];
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[minIdx] = min;
		// 모든 논에 물을 대기 위한 최소 비용
		int cost = 0;
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visited[n.u]) continue;
			visited[n.u] = true;
			cost += n.w;
			for (int v = 0; v < N; v++) {
				if (v == n.u || visited[v]) continue;
				// 인접한 논에 대해 두 가지 선택지 중 하나를 고른다.
				// 인접한 논에서 물을 끌어오거나, 인접한 논에 우물을 파는 것 중 비용이 덜 드는 것을 선택한다.
				if (dp[v] > graph[n.u][v] || dp[v] > self[v]) {
					if (graph[n.u][v] < self[v]) {
						dp[v] = graph[n.u][v];
					} else {
						dp[v] = self[v];
					}
					pq.offer(new Node(v, dp[v]));
				}
			}
		}
		System.out.println(cost);
	}
}
