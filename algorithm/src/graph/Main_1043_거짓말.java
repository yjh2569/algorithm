package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1043_거짓말 {
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// union-find를 통해 진실을 말해야 하는 사람과 같은 파티에 속하는지를 확인한다.
		// parents 배열은 각 사람이 어떤 사람과 연결되어 있는지를 확인하기 위한 배열이다.
		parents = new int[N+1];
		// 각 사람이 진실을 알고 있는지를 나타내는 배열
		boolean[] known = new boolean[N+1];
		// parents 배열 초기화
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(st.nextToken());
			known[k] = true;
		}
		// 각 파티의 첫 번째 사람을 저장하는 배열
		int[] oneOfParties = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			oneOfParties[i] = first;
			// 파티에 2명 이상 있는 경우 첫 번째 사람과 나머지 사람을 union-find를 통해 연결한다.
			for (int j = 0; j < n-1; j++) {
				int next = Integer.parseInt(st.nextToken());
				if (find(first) != find(next)) union(first, next);
			}
		}
		// 가능한 모든 두 사람과의 관계에서 두 사람이 연결되어 있으면서 두 사람 중 한 명이라도 진실을 알고 있으면
		// 나머지 사람도 진실을 알게 된다.
		for (int i = 1; i <= N; i++) {
			for (int j = i+1; j <= N; j++) {
				if (find(i) == find(j) && (known[i] || known[j])) {
					known[i] = known[j] = true;
				}
			}
		}
		// 과장해서 말해도 되는 파티의 수
		int cnt = 0;
		// 위 과정을 통해 파티 내에 진실을 말해야 하는 사람이 속한 경우 해당 파티 전체 인원에게 진실을 말해야 함을 정해줬기 때문에,
		// 파티 내 아무 한 명을 뽑아서 해당 인원이 진실을 모르는지만 확인하면 된다.
		for (int i = 0; i < M; i++) {
			if (!known[oneOfParties[i]]) cnt++;
		}
		System.out.println(cnt);
	}
	// u의 root를 찾는 find 함수
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		return parents[u] = find(parents[u]);
	}
	// u와 v를 연결하는 union 함수
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[v] = u;
	}
}
