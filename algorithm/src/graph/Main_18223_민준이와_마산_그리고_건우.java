package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_18223_민준이와_마산_그리고_건우 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		// 양방향 그래프
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
		}
		// dijkstra's algorithm을 적용해 최단 거리를 구한다.
		int[] dijkstra = new int[V+1];
		Arrays.fill(dijkstra, Integer.MAX_VALUE);
		dijkstra[1] = 0;
		// 이때, 건우를 구하는 최단 경로가 있는지를 확인하기 위해 
		// 각 정점까지의 최단 경로 중 건우를 구하는 경로가 있는지를 나타내는 배열을 추가한다.
		boolean[] geonwoo = new boolean[V+1];
		// 초기화
		geonwoo[P] = true;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
		pq.offer(new int[] {1, 0});
		boolean[] visited = new boolean[V+1];
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (visited[u[0]]) continue;
			visited[u[0]] = true;
			for (int[] v : graph.get(u[0])) {
				// 최단 경로가 갱신되는 경우
				if (!visited[v[0]] && dijkstra[v[0]] > dijkstra[u[0]] + v[1]) {
					dijkstra[v[0]] = dijkstra[u[0]] + v[1];
					// 건우가 있는 곳이 아니면 지금까지의 경로에서 건우를 찾았는지를 다음 지점에 갱신한다. 
					if (v[0] != P) geonwoo[v[0]] = geonwoo[u[0]];
					pq.offer(new int[] {v[0], dijkstra[v[0]]});
				// 최단 경로의 비용은 그대로이나, 건우를 구하는 경로일 가능성이 있는 경우
				} else if (!visited[v[0]] && dijkstra[v[0]] == dijkstra[u[0]] + v[1]) {
					// or 연산을 통해 건우를 구하는 경로가 있는지를 확인한다.
					geonwoo[v[0]] = geonwoo[u[0]] | geonwoo[v[0]];
					pq.offer(new int[] {v[0], dijkstra[v[0]]});
				}
			}
		}
		// 목적지까지의 최단 경로 중 건우를 구하는 경로가 있는지를 확인한다.
		System.out.println(geonwoo[V] ? "SAVE HIM" : "GOOD BYE");
		
	}

}
