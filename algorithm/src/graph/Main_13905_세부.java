package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13905_세부 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
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
		// Prim's algorithm을 통해 s에서 시작하는 트리를 만들어 나간다.
		// 이때, 각 노드까지 들고 갈 수 있는 빼빼로의 개수가 많은 순서대로 노드를 방문하면서 경로를 만들어낸다.
		int max = Integer.MAX_VALUE;
		int[] prim = new int[N+1]; // 각 노드까지 들고 갈 수 있는 빼빼로 개수의 최대값을 저장하는 배열
		// 초기화
		prim[s] = Integer.MAX_VALUE;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[1], x[1]));
		boolean[] visited = new boolean[N+1];
		pq.offer(new int[] {s, Integer.MAX_VALUE});
		// Prim's algorithm
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (u[0] == e) {
				System.out.println(prim[u[0]]);
				System.exit(0);
			}
			if (visited[u[0]]) continue;
			visited[u[0]] = true;
			for (int[] v : graph.get(u[0])) {
				if (prim[v[0]] < Math.min(prim[u[0]], v[1])) {
					prim[v[0]] = Math.min(prim[u[0]], v[1]);
					pq.offer(new int[] {v[0], prim[v[0]]});
				}
			}
		}
		System.out.println(prim[e]);
	}
}
