package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_22865_가장_먼_곳 {
	static int N, M;
	static long[][] dp;
	static ArrayList<ArrayList<int[]>> graph;
	// 각 정점의 번호와 시작 정점으로부터의 최단거리를 저장하기 위한 클래스
	static class Node implements Comparable<Node> {
		int u; // 정점의 번호
		long w; // 시작 정점으로부터의 최단거리
		public Node(int u, long w) {
			this.u = u;
			this.w = w;
		}
		// dijkstra's algorithm을 적용하기 위해 비교가 필요하다.
		public int compareTo(Node n) {
			return Long.compare(this.w, n.w);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] friends = new int[3];
		for (int i = 0; i < 3; i++) {
			friends[i] = Integer.parseInt(st.nextToken());			
		}
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// 양방향 간선
			graph.get(u).add(new int[] {v, w});
			graph.get(v).add(new int[] {u, w});
		}
		// A, B, C에 대해 각 지점을 시작 지점으로 하는 dijkstra's algorithm을 적용한다.
		// M과 L 값에 따라 최단 경로 길이가 int 범위를 넘어갈 수도 있다.
		dp = new long[3][N+1];
		for (int i = 0; i < 3; i++) {
			Arrays.fill(dp[i], Long.MAX_VALUE/10);
		}
		// A, B, C에 대해 dijkstra's algorithm 적용
		for (int i = 0; i < 3; i++) {
			dijkstra(i, friends[i]);
		}
		int maxIdx = -1; // A, B, C 중 가장 가까운 정점과의 거리가 가장 큰 정점
		long max = -1; // A, B, C 중 가장 가까운 정점과의 거리의 최대값
		for (int j = 1; j <= N; j++) {
			// 각 정점에 대해 A, B, C 중 가장 거리가 가까운 정점을 찾는다.
			long minDistance = Long.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				minDistance = Math.min(minDistance, dp[i][j]);
			}
			// 위에서 찾은 정점까지의 거리가 max보다 크면 max와 maxIdx를 갱신한다.
			if (max < minDistance) {
				maxIdx = j;
				max = minDistance;
			}
		}
		System.out.println(maxIdx);
	}
	// dijkstra's algorithm을 적용하는 함수
	// k는 A, B, C 중 현재 몇 번째 정점을 보고 있는지를 나타내는 변수로, dp에 값을 보다 쉽게 저장하기 위해 사용
	// start는 A, B, C의 정점 번호 중 하나
	private static void dijkstra(int k, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		pq.offer(new Node(start, 0));
		dp[k][start] = 0;
		while (!pq.isEmpty()) {
			Node n = pq.poll();
			if (visited[n.u]) continue;
			visited[n.u] = true;
			for (int[] v : graph.get(n.u)) {
				if (dp[k][v[0]] > dp[k][n.u] + v[1]) {
					dp[k][v[0]] = dp[k][n.u] + v[1];
					pq.offer(new Node(v[0], dp[k][v[0]]));
				}
			}
		}
	}
}
