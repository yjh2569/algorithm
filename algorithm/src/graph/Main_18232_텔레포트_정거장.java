package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18232_텔레포트_정거장 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		// 각 지점에서 이동할 수 있는 지점을 저장하는 그래프
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// 텔레포트 지점
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// 이동 지점
		for (int i = 1; i <= N; i++) {
			if (i > 1) graph.get(i).add(i-1);
			if (i < N) graph.get(i).add(i+1);
		}
		// BFS를 활용해 S에서 출발해 E에 도달하는 최단 시간을 구한다.
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(S);
		visited[S] = true;
		int cnt = 1; // E에 도달하는 데 걸리는 최단 시간
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				for (int v : graph.get(u)) {
					if (v == E) {
						System.out.println(cnt);
						System.exit(0);
					}
					if (!visited[v]) {
						visited[v] = true;
						q.offer(v);
					}
				}
				qLen--;
			}
			cnt++;
		}
	}

}
