package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17396_백도어 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] canSee = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			canSee[i] = Integer.parseInt(st.nextToken());
		}
		// 넥서스는 상대편이 볼 수는 있지만 도달 가능한 지점이기에 입력을 수정한다.
		canSee[N-1] = 0;
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			// 상대편이 볼 수 없는 곳인 경우에만 경로를 추가한다.
			if (canSee[b] == 0) graph.get(a).add(new int[] {b, t});
			if (canSee[a] == 0) graph.get(b).add(new int[] {a, t});
		}
		// dijkstra's algorithm을 이용한다.
		// 최단 시간이 int 범위를 넘을 수 있음에 유의한다.
		long[] dijk = new long[N];
		boolean[] visited = new boolean[N];
		PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
		pq.offer(new long[] {0, 0});
		Arrays.fill(dijk, Long.MAX_VALUE);
		dijk[0] = 0;
		while (!pq.isEmpty()) {
			long[] u = pq.poll();
			if (visited[(int)u[0]]) continue;
			visited[(int)u[0]] = true;
			if (u[0] == N-1) {
				System.out.println(u[1]);
				System.exit(0);
			}
			for (int[] v : graph.get((int)u[0])) {
				if (!visited[v[0]] && dijk[v[0]] > dijk[(int)u[0]] + v[1]) {
					dijk[v[0]] = dijk[(int)u[0]] + v[1];
					pq.offer(new long[] {v[0], dijk[v[0]]});
				}
			}
		}
		System.out.println(-1);
	}

}
