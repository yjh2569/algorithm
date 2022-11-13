package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_3584_가장_가까운_공통_조상 {
	static int N;
	static int[] parents;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] depths;

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			// 각 노드의 부모 노드를 저장하는 배열
			parents = new int[N + 1];
			// 각 노드의 depth를 저장하는 배열
			depths = new int[N + 1];
			// 루트 노드에서부터 트리를 탐색하기 위해 그래프를 생성
			graph = new ArrayList<>();
			// 초기화
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				parents[v] = u;
				graph.get(u).add(v);
			}
			// 트리의 루트 노드를 구한다.
			int root = -1;
			for (int i = 1; i <= N; i++) {
				if (parents[i] == 0) {
					root = i;
					break;
				}
			}
			// 각 노드의 depth를 정한다.
			determineHeight(root, 0);
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 두 노드의 depth가 다른 경우 depth가 더 깊은 노드를 다른 노드의 depth에 맞춰준다. 
			if (depths[a] > depths[b]) {
				a = lowerdepths(a, depths[b]);
			} else if (depths[b] > depths[a]) {
				b = lowerdepths(b, depths[a]);
			}
			// 가장 가까운 공통 조상 노드를 찾는다. 
			int answer = findNearestRoot(a, b);
			sb.append(answer).append("\n");
		}
		if (sb.length() > 0)
			sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	// 각 노드의 depth를 DFS를 통해 결정하는 함수
	private static void determineHeight(int u, int h) {
		depths[u] = h;
		for (int v : graph.get(u)) {
			determineHeight(v, h + 1);
		}
	}
	// u에서 출발해 depth가 h가 될 때까지 올라가는 함수
	private static int lowerdepths(int u, int h) {
		while (depths[u] > h) {
			u = parents[u];
		}
		return u;
	}
	// a와 b의 가장 가까운 공통조상을 찾는 함수
	private static int findNearestRoot(int a, int b) {
		while (a != b) {
			a = parents[a];
			b = parents[b];
		}
		return a;
	}
}
