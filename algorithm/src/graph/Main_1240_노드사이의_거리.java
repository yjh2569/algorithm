package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1240_노드사이의_거리 {

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
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
		}
		// 각 노드 사이의 거리를 구하기 위해 BFS를 사용한다.
		// 트리 특성 상 각 노드 사이의 경로는 단 하나뿐이기 때문에 dijkstra's algorithm을 사용하지 않아도 된다.
		StringBuilder sb = new StringBuilder();
		outer: for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			Queue<int[]> q = new LinkedList<>();
			boolean[] visited = new boolean[N+1];
			q.offer(new int[] {start, 0});
			visited[start] = true;
			while (!q.isEmpty()) {
				int[] u = q.poll();
				for (int[] v : graph.get(u[0])) {
					if (!visited[v[0]]) {
						if (v[0] == end) {
							sb.append(u[1] + v[1]).append("\n");
							continue outer;
						}
						visited[v[0]] = true;
						q.offer(new int[] {v[0], u[1] + v[1]});
					}
				}
			}
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
