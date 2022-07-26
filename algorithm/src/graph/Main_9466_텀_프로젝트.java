package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466_텀_프로젝트 {
	static int res;
	static int[] graph, visited;
	static boolean[] isInCycle, v;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			graph = new int[N+1];
			// DFS 수행 중 사이클을 발견하기 위해, visited 배열을 boolean 배열이 아닌 int 배열로 만든다.
			// 만약 해당 노드와 자식 노드에 대한 탐색을 모두 마친 경우에는 visited의 값을 2로 두고,
			// 해당 노드를 방문하기는 했지만 자식 노드에 대한 탐색을 모두 마치치 않은 경우 visited의 값을 1로 둔다.
			// 따라서 만약 자식 노드 방문 중 해당 노드를 다시 방문하는 경우를 확인할 수 있고, 
			// 이 경우 해당 노드를 시작으로 하는 사이클을 발견할 수 있다.
			visited = new int[N+1];
			// 각 노드가 사이클에 속하는지를 나타내는 배열
			isInCycle = new boolean[N+1];
			// 사이클 탐색 시 사용하는 배열
			v = new boolean[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
			}
			// 팀을 이룰 수 없는 팀원의 수
			// 전체 팀에서 사이클을 이루는 팀원의 수를 빼는 방식으로 진행
			res = N;
			// 사이클 탐색
			for (int i = 1; i <= N; i++) {
				if (visited[i] == 0) dfs(i);
			}
			// 사이클에 속하는 노드의 개수를 res에서 빼준다.
			for (int i = 1; i <= N; i++) {
				if (isInCycle[i] && !v[i]) check(i);
			}
			sb.append(res).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// DFS를 통해 사이클 내 노드의 개수를 센다.
	private static void check(int i) {
		res--;
		v[i] = true;
		if (!v[graph[i]]) check(graph[i]);
	}
	// DFS를 통해 사이클을 발견한다.
	private static void dfs(int i) {
		visited[i] = 1;
		if (visited[graph[i]] == 0) dfs(graph[i]);
		// 이 경우 어떤 노드의 자식 노드 탐색 중 자기 자신을 발견한 경우이므로 사이클을 발견한 경우라 할 수 있다.
		else if (visited[graph[i]] == 1) {
			isInCycle[graph[i]] = true;
		}
		visited[i] = 2;
	}

}
