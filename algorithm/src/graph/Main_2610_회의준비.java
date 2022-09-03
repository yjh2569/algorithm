package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2610_회의준비 {
	static class Edge {
		int u, v;
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
	static int[] parents;
	static int[] ranks;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		// union-find를 진행하기 위해 필요한 배열들
		parents = new int[N+1];
		ranks = new int[N+1];
		// 초기화
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			ranks[i] = 1;
		}
		// 간선들을 저장하기 위한 큐
		Queue<Edge> q = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			q.offer(new Edge(u, v));
		}
		// 위원회 수
		int cnt = N;
		// 플로이드-와샬 알고리즘을 이용하기 위한 배열
		int[][] dp = new int[N+1][N+1];
		// 초기화
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/10);
		}
		// union-find를 통해 위원회 수를 구한다.
		while (!q.isEmpty()) {
			Edge e = q.poll();
			// dp 배열 초기화
			dp[e.u][e.v] = 1;
			dp[e.v][e.u] = 1;
			if (find(e.u) != find(e.v)) {
				union(e.u, e.v);
				cnt--;
			}
		}
		// 플로이드-와샬 알고리즘을 통해 서로 다른 두 정점 사이의 최단 거리를 모두 구한다.
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) continue;
					if (dp[i][j] > dp[i][k] + dp[k][j]) dp[i][j] = dp[i][k] + dp[k][j];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		// 위원회 내 대표를 정하기 위해 모든 정점과의 최단 거리 중 가장 큰 값이 최소가 되는 정점을 찾는다.
		// 각 위원회에서 찾은 임의의 루트 정점(여기서 구한 정점은 대표가 될 수 없다.)
		int[] roots = new int[cnt];
		// 각 위원회 내 각 정점에서 구한 최소 의사전달거리
		int[] mins = new int[cnt];
		// 각 위원회 내 대표
		int[] minIdxs = new int[cnt];
		// 각 위원회의 루트 정점을 찾는다.
		int idx = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == i) {
				roots[idx++] = i;
			}
		}
		// 초기화
		Arrays.fill(mins, Integer.MAX_VALUE);
		// 각 정점에 대해 해당 정점이 속한 위원회를 찾고, 이를 바탕으로 대표가 될 수 있는지 조사한다.
		for (int i = 1; i <= N; i++) {
			// 해당 정점과 모든 정점 사이의 최단 거리의 최대값을 찾는다.
			int temp = 0;
			for (int j = 1; j <= N; j++) {
				if (i != j && dp[i][j] != Integer.MAX_VALUE/10) temp = Math.max(temp, dp[i][j]);
			}
			// 해당 정점이 속한 위원회를 찾는다.
			int root = find(i);
			int rootIdx = 0;
			for (int j = 0; j < cnt; j++) {
				if (root == roots[j]) {
					rootIdx = j;
					break;
				}
			}
			// 해당 정점이 위원회의 대표가 될 수 있는지 조사한다.
			if (mins[rootIdx] > temp) {
				mins[rootIdx] = temp;
				minIdxs[rootIdx] = i;
			}
		}
		// 대표 번호를 작은 순서대로 출력해야 함에 유의
		Arrays.sort(minIdxs);
		for (int i = 0; i < cnt; i++) {
			sb.append(minIdxs[i]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// union-find에 사용되는, 루트 정점을 찾는 함수
	static int find(int u) {
		if (parents[u] == u) return parents[u];
		else return parents[u] = find(parents[u]);
	}
	// union-find에 사용되는, 루트 정점이 다른 두 트리를 합치는 함수
	static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) {
			if (ranks[u] > ranks[v]) {
				parents[v] = u;
			} else {
				parents[u] = v;
				if (ranks[u] == ranks[v]) ranks[v]++;
			}
		}
	}
}
