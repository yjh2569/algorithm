package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20040_사이클_게임 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// union-find 알고리즘을 활용한다.
		// 각 정점을 하나의 트리로 간주하고, 선분이 주어질 때마다 두 트리를 연결한다.
		// 이때, 만약 두 정점이 하나의 트리에 속하는 경우 해당 선분을 추가하는 순간 그래프 안에 사이클이 생기므로
		// 그때까지 추가한 선분의 개수를 출력한다.
		parents = new int[n]; // 각 정점이 속한 트리에서 부모 정점의 번호 
		for (int i = 1; i < n; i++) {
			parents[i] = i;
		}
		int cnt = 0; // 사이클이 생길 때까지 추가한 선분의 개수
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (cnt != 0) continue; // 이미 사이클이 생긴 경우
			// 선분을 추가하는 순간 사이클이 생기는 경우
			if (find(u) == find(v)) {
				cnt = i;
			}
			union(u, v);
		}
		System.out.println(cnt);
	}
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
}
