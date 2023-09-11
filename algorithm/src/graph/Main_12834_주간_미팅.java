package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12834_주간_미팅 {
	static int N, V, E, A, B, sum;
	static int[] H;
	static ArrayList<ArrayList<int[]>> graph;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		H = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}
		graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[] {b, l});
			graph.get(b).add(new int[] {a, l});
		}
		// dijkstra's algorithm을 A와 B를 출발점으로 수행한다.
		// 이후 A와 B 각각 H[i]까지의 거리를 구한다.
		// 양방향 간선이므로 A(B)에서 H[i]까지의 최단 거리는 H[i]에서 A(B)까지의 최단 거리와 같다.
		int[] dp1 = new int[V+1];
		int[] dp2 = new int[V+1];
		sum = 0; // 최단 거리의 합
		dijkstra(A, dp1);
		dijkstra(B, dp2);
		System.out.println(sum);
	}
	// dijkstra's algorithm을 활용해 start에서 각 지점까지의 최단 거리를 구하는 함수
	private static void dijkstra(int start, int[] dp) {
		// 초기화
		Arrays.fill(dp, Integer.MAX_VALUE/10);
		dp[start] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
		boolean[] visited = new boolean[V+1];
		pq.offer(new int[] {start, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (visited[u[0]]) continue;
			visited[u[0]] = true;
			for (int[] v : graph.get(u[0])) {
				if (!visited[v[0]] && dp[v[0]] > dp[u[0]] + v[1]) {
					dp[v[0]] = dp[u[0]] + v[1];
					pq.offer(new int[] {v[0], dp[v[0]]});
				}
			}
		}
		for (int h : H) {
			// 도달할 수 없는 지점이면 최단 거리를 -1로 한다.
			if (dp[h] >= Integer.MAX_VALUE/10) sum += -1;
			else sum += dp[h];
		}
	}

}
