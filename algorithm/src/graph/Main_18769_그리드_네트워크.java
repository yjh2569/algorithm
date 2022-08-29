package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_18769_그리드_네트워크 {
	static int R, C;
	// Kruskal's algorithm 적용을 위한 간선 클래스
	static class Edge implements Comparable<Edge> {
		// 정점 u의 행과 열 번호, 정점 v의 행과 열 번호, 그리고 간선의 비용
		int ur, uc, vr, vc, w;
		public Edge(int ur, int uc, int vr, int vc, int w) {
			this.ur = ur;
			this.uc = uc;
			this.vr = vr;
			this.vc = vc;
			this.w = w;
		}
		public int compareTo(Edge e) {
			return Integer.compare(this.w, e.w);
		}
	}
	// Kruskal's algorithm에서 union-find를 위한 부모 정점 저장 배열
	static int[][] parents;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 각 테스트케이스에 대해
		for (int t = 1; t <= T; t++) {
			// MST를 만들어야 하므로 Kruskal's algorithm을 적용한다.
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			// 좌우로 연결하는 통신망
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C-1; j++) {
					int w = Integer.parseInt(st.nextToken());
					pq.offer(new Edge(i, j, i, j+1, w));
				}
			}
			// 상하로 연결하는 통신망
			for (int i = 0; i < R-1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					int w = Integer.parseInt(st.nextToken());
					pq.offer(new Edge(i, j, i+1, j, w));
				}
			}
			// parents 배열 초기화
			parents = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					parents[i][j] = i*C+j;
				}
			}
			// MST의 비용
			int cost = 0;
			// MST를 만들기 위해 선택한 간선의 개수
			int num = 0;
			// MST가 만들어질 때까지 반복한다.(간선의 개수가 (정점의 개수)-1=R*C-1이면 MST가 완성된다.)
			while (!pq.isEmpty() && num < R*C - 1) {
				// 비용이 가장 적은 간선을 하나 선택
				Edge e = pq.poll();
				// 간선의 양끝 정점이 연결되지 않은 경우 연결하고 비용 및 간선 개수 증가
				if (find(e.ur, e.uc) != find(e.vr, e.vc)) {
					cost += e.w;
					num++;
					union(e.ur, e.uc, e.vr, e.vc);
				}
			}
			sb.append(cost).append("\n");
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// (r, c)의 루트 정점를 찾기 위한 함수
	private static int find(int r, int c) {
		if (parents[r][c] == r*C+c) return parents[r][c];
		else return parents[r][c] = find(parents[r][c]/C, parents[r][c]%C);
	}
	// (ur, uc)와 (vr, vc)가 같은 트리에 있는지 확인 및 간선을 통해 연결하는 함수
	private static void union(int ur, int uc, int vr, int vc) {
		int root_u = find(ur, uc);
		int root_v = find(vr, vc);
		if (root_u != root_v) {
			parents[root_u/C][root_u%C] = root_v;
		}
	}

}
