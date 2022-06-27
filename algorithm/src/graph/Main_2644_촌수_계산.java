package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2644_촌수_계산 {
	static int n;
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 인원 수
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 촌수 관계를 알고 싶은 두 사람의 번호
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		// 관계 수
		int m = Integer.parseInt(br.readLine());
		// 친척 관계를 그래프 형태로 나타낸다.
		// 방향과 상관없이 항상 관계는 1촌을 의미하므로 양방향 그래프로 만든다.
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		// BFS를 통해 두 사람 사이의 촌수를 계산한다.
		bfs(A, B);
		// 위 BFS를 통해 두 사람 사이의 촌수를 찾지 못한 경우
		System.out.println(-1);
	}
	private static void bfs(int x, int y) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		q.offer(x);
		visited[x] = true;
		// 현재까지의 촌수
		int chon = 0;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				// 그래프 탐색 중 목표하는 사람의 번호를 찾으면 관계가 있는 경우로 촌수를 출력하고 프로그램을 종료
				if (u == y) {
					System.out.println(chon);
					System.exit(0);
				}
				for (int v : graph.get(u)) {
					if (!visited[v]) {
						visited[v] = true;
						q.offer(v);
					}
				}
				qLen--;
			}
			chon++;
		}
	}
}
