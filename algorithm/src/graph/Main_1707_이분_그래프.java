package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1707_이분_그래프 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		outer: for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			for (int i = 0; i <= V; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				graph.get(u).add(v);
				graph.get(v).add(u);
			}
			// 너비 우선 탐색을 통해 이분 그래프인지 확인한다.
			Queue<Integer> q = new LinkedList<>();
			int[] visited = new int[V+1]; // 이분 그래프로 나누었을 때, 1, 2번 집합 중에 어느 집합에 포함되는지를 나타내는 배열
			// 주어지는 그래프가 여러 그래프의 집합일 수도 있기에, 모든 정점에 대해 BFS를 수행한다.
			for (int i = 1; i <= V; i++) {
				// 다른 어떤 정점과도 인접하지 않은 정점이거나, 이미 어떤 집합에 넣을지 결정한 정점인 경우
				if (graph.get(i).size() == 0 || visited[i] != 0) continue;
				q.offer(i);
				visited[i] = 1; // i번 정점은 우선 1번 집합에 넣는다.
				int next = 2; // 다음에 넣을 집합의 번호
				while (!q.isEmpty()) {
					int qLen = q.size();
					while (qLen > 0) {
						int u = q.poll();
						for (int v : graph.get(u)) {
							// 이미 v번 정점을 어떤 집합에 넣을지 결정한 경우
							if (visited[v] != 0) {
								// v번 정점이 u번 정점과는 다른 집합에 속하면 괜찮으나, 같은 집합에 속하면 이분 그래프를 만들 수 없다.
								if (visited[v] == next) continue;
								sb.append("NO").append("\n");
								continue outer;
							}
							visited[v] = next;
							q.offer(v);
						}
						qLen--;
					}
					next = next == 1 ? 2 : 1; // next 갱신
				}
			}
			// 이분 그래프인 경우
			sb.append("YES").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
