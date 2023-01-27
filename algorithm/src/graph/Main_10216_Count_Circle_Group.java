package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10216_Count_Circle_Group {
	// 진영의 좌표와 통신 거리를 포함하는 클래스
	static class Base {
		int x, y, R;
		public Base(int x, int y, int R) {
			this.x = x;
			this.y = y;
			this.R = R;
		}
	}
	static int[] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			Base[] bases = new Base[N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int R = Integer.parseInt(st.nextToken());
				bases[i] = new Base(x, y, R);
			}
			// 유니온 파인드를 적용해 연결된 그룹의 개수를 구한다.
			parents = new int[N];
			for (int i = 0; i < N; i++) {
				parents[i] = i;
			}
			// i번째 진영과 j번째 진영이 통신할 수 있는지를 파악한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j) continue;
					if (checkDistance(bases[i], bases[j])) {
						union(i, j);
					}
				}
			}
			// 그룹의 개수
			int cnt = 0;
			// 연결된 그룹의 개수는 유니온 파인드 적용 결과 각 트리의 루트 노드의 개수와 같다.
			for (int i = 0; i < N; i++) {
				if (parents[i] == i) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 유니온 파인드 적용을 위한 파인드, 유니온 함수
	private static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) parents[u] = v;
	}
	// 통신이 가능한지 확인하는 함수
	private static boolean checkDistance(Base base1, Base base2) {
		int x1 = base1.x, y1 = base1.y, R1 = base1.R;
		int x2 = base2.x, y2 = base2.y, R2 = base2.R;
		return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2) <= (R1+R2)*(R1+R2);
	}

}
