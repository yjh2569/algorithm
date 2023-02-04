package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_11437_LCA {
	static int N, maxLevel;
	static int[][] parents;
	static int[] levels;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 트리를 구성하기 위한 그래프
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		maxLevel = 1; // 가능한 가장 큰 깊이 값의 log_2 를 취한 값
		// maxLevel을 N의 값에 대해 구한다.
		int temp = 1;
		while (temp <= N) {
			temp *= 2;
			maxLevel++;
		}
		// parents[i][l]은 노드 i의 2^l번째 조상 노드의 값을 의미한다.
		parents = new int[N+1][maxLevel+1];
		// 각 노드의 깊이
		levels = new int[N+1];
		// graph를 이용해 트리를 만들고 parents와 levels 배열을 완성한다.
		makeTree(1, 0, 1);
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// u와 v의 LCA를 찾는다.
			sb.append(findLCA(u, v)).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 트리를 재귀적으로 찾는 함수
	// u는 현재 노드, parent는 u의 부모 노드, level은 현재 노드의 깊이를 의미한다.
	private static void makeTree(int u, int parent, int level) {
		parents[u][0] = parent;
		levels[u] = level;
		// parents 갱신
		// 노드 u의 2^l번째 조상 노드는 노드 u의 2^(l-1)번쨰 조상 노드의 2^(l-1)번째 조상 노드와 같다.
		for (int l = 1; l < maxLevel; l++) {
			parents[u][l] = parents[parents[u][l-1]][l-1];
		}
		// 노드 u의 자식 노드에 대해 재귀적으로 작업을 수행
		for (int v : graph.get(u)) {
			if (v == parent) continue;
			makeTree(v, u, level + 1);
		}
	}
	// u와 v의 LCA를 찾는 함수
	private static int findLCA(int u, int v) {
		// u의 깊이가 v의 깊이보다 항상 크거나 같도록 조정
		if (levels[u] < levels[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		// u와 v의 깊이가 다를 때, 깊이가 같아지게 조정한다.
		// u의 깊이가 더 깊으므로 u의 깊이가 v가 될 때까지 u의 깊이를 낮춘다.
		// 이떄 깊이를 1씩 감소시키는 게 아니라, 2^l씩 감소시켜보면서 그 결과가 v의 깊이보다 크거나 같은지 확인한다.
		// 만약 v의 깊이보다 더 작아진다면 너무 깊이가 작아진 것이고, 
		// v의 깊이보다 크다면 적절히 줄인 것이다.
		// 이 작업을 수행하고 나면 u와 v의 깊이가 같아진다.
		// 왜냐하면 아래 작업을 통해 2^0 * a0 + 2^1 * a1 + ... + 2^maxLevel * aML + levels[u] = levels[v]
		// 를 만족하는 수열 {an}을 찾은 것과 같기 때문이다.(an은 0 또는 1)
		if (levels[u] != levels[v]) {
			for (int l = maxLevel; l >= 0; l--) {
				if (levels[parents[u][l]] >= levels[v]) {
					u = parents[u][l];
				}
			}
		}
		// 최소 공통 조상
		int LCA = u;
		// 이제 u와 v의 깊이가 같아졌으므로 깊이를 동시에 조절하면서 최소 공통 조상을 찾는다.
		// 가장 깊이가 작은 공통 조상부터 LCA로 갱신한다.
		// 그러다 보면 깊이가 커져서 두 노드의 parents 값이 달라질 수 있는데,
		// 이 경우도 위와 마찬가지로 그 parents 값으로 갱신함에 따라 수열을 찾는 방식처럼 진행한다.
		if (u != v) {
			for (int l = maxLevel; l >= 0; l--) {
				if (parents[u][l] != parents[v][l]) {
					u = parents[u][l];
					v = parents[v][l];
				}
				LCA = parents[u][l];
			}
		}
		return LCA;
	}
}
