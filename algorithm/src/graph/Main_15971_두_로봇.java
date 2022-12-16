package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_15971_두_로봇 {
	static int N;
	static ArrayList<ArrayList<int[]>> graph;
	static int[] max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new int[] {v, w});
			graph.get(v).add(new int[] {u, w});
		}
		// dijkstra's algorithm 적용
		long[] dijk = dijkstra(A);
		// B까지의 최단 경로의 길이에서 B까지의 경로 중 가장 긴 통로의 길이를 뺀다.
		System.out.println(dijk[B] - max[B]);
	}
	// dijkstra's algorithm을 적용하는 함수
	private static long[] dijkstra(int start) {
		PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> Long.compare(x[1], y[1]));
		long[] dijk = new long[N+1];
		max = new int[N+1];
		boolean[] visited = new boolean[N+1];
		pq.offer(new long[] {start, 0});
		Arrays.fill(dijk, Long.MAX_VALUE/10);
		dijk[start] = 0;
		while (!pq.isEmpty()) {
			long[] u = pq.poll();
			if (visited[(int)u[0]]) continue;
			visited[(int)u[0]] = true;
			for (int[] v : graph.get((int)u[0])) {
				if (dijk[v[0]] > dijk[(int)u[0]] + v[1]) {
					dijk[v[0]] = dijk[(int)u[0]] + v[1];
					max[v[0]] = Math.max(max[(int)u[0]], v[1]);
					pq.offer(new long[] {v[0], dijk[v[0]]});
				}
			}
		}
		return dijk;
	}

}
