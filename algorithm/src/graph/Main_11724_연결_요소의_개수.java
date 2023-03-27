package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11724_연결_요소의_개수 {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
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
		visited = new boolean[N+1];
		int cnt = 0; // 연결 요소의 개수
		// 각 노드에 대해 BFS를 통해 해당 노드를 포함한 연결 요소에 있는 노드들을 방문 처리한다.
		// 이후 방문하지 않는 노드들은 방문하지 않은 연결 노드에 있는 것이므로 새로운 연결 요소를 찾은 것으로 간주한다.
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				cnt++;
				bfs(i);
			}
		}
		System.out.println(cnt);
	}
	// BFS를 통해 start 노드를 포함하는 연결 요소 내 모든 노드들을 방문하는 함수
	private static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					q.offer(v);
				}
			}
		}
	}

}
