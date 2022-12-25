package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16118_달빛_여우 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[] {b, 2*d});
			graph.get(b).add(new int[] {a, 2*d});
		}
		// 달빛 여우가 각 그루터기에 도착하기까지 걸리는 최단 시간을 저장하는 배열
		long[] dp1 = new long[N+1];
		// 달빛 늑대가 각 그루터기에 도착하기까지 걸리는 최단 시간을 저장하는 배열
		// (u, 0)은 u번째 그루터기에 달릴 수 있는 상태로 도착했을 때, (u, 1)은 u번째 그루터기에 달릴 수 없는 상태로 도착했을 때
		long[][] dp2 = new long[N+1][2];
		// 초기화
		Arrays.fill(dp1, Long.MAX_VALUE/10);
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp2[i], Long.MAX_VALUE/10);
		}
		dp1[1] = 0;
		dp2[1][0] = 0;
		// dijkstra's algorithm 적용
		PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
		boolean[] visited = new boolean[N+1];
		pq.offer(new long[] {1, 0});
		while (!pq.isEmpty()) {
			long[] arr = pq.poll();
			int u = (int)arr[0];
			if (visited[u]) continue;
			visited[u] = true;
			for (int[] v : graph.get(u)) {
				if (dp1[v[0]] > dp1[u] + v[1]) {
					dp1[v[0]] = dp1[u] + v[1];
					pq.offer(new long[] {v[0], dp1[v[0]]});
				}
			}
		}
		// 달빛 늑대의 경우 현재 달릴 수 있는 상태인지를 나타내는 변수를 추가한다.
		pq.offer(new long[] {1, 0, 0});
		boolean[][] v2 = new boolean[N+1][2];
		while (!pq.isEmpty()) {
			long[] arr = pq.poll();
			int u = (int)arr[0];
			int cur = (int)arr[2];
			if (v2[u][cur]) continue;
			v2[u][cur] = true;
			int rest = cur == 0 ? 1 : 0;
			for (int[] v : graph.get(u)) {
				int time = arr[2] == 0 ? v[1]/2 : v[1]*2;
				if (dp2[v[0]][rest] > dp2[u][cur] + time) {
					dp2[v[0]][rest] = dp2[u][cur] + time;
					pq.offer(new long[] {v[0], dp2[v[0]][rest], rest});
				}
			}
		}
		// 달빛 여우가 달빛 늑대보다 더 빠르게 도달할 수 있는 그루터기의 개수를 센다.
		int cnt = 0;
		for (int u = 2; u <= N; u++) {
			if (dp1[u] < Math.min(dp2[u][0], dp2[u][1])) cnt++;
		}
		System.out.println(cnt);
	}

}
