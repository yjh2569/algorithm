package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6118_숨바꼭질 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// BFS를 활용해 각 헛간까지의 최단 거리를 계산한다.
		int[] distances = new int[N+1]; // 1번 헛간에서 각 헛간까지의 거리를 나타내는 배열 
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(1);
		visited[1] = true;
		int distance = 1; // 헛간까지의 거리
		// BFS 수행
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				for (int v : graph.get(u)) {
					if (!visited[v]) {
						distances[v] = distance;
						visited[v] = true;
						q.offer(v);						
					}
				}
				qLen--;
			}
			distance++;
		}
		int maxDistance = 0; // 최단 거리가 가장 먼 헛간까지의 최단 거리
		int maxIdx = -1; // 최단 거리가 가장 먼 헛간 중 가장 작은 번호
		int cnt = 0; // 최단 거리가 가장 먼 헛간과 같은 거리의 헛간 개수
		for (int u = 2; u <= N; u++) {
			// 최단 거리가 지금까지 본 헛간보다 더 먼 헛간을 찾은 경우
			if (maxDistance < distances[u]) {
				maxDistance = distances[u];
				maxIdx = u;
				cnt = 1;
			// 최단 거리가 가장 먼 헛간과 같은 거리인 헛간인 경우
			} else if (maxDistance == distances[u]) {
				cnt++;
			}
		}
		System.out.println(maxIdx + " " + maxDistance + " " + cnt);
	}
 
}
