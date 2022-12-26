package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5214_환승 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N+M; i++) {
			graph.add(new ArrayList<>());
		}
		// N+1번째부터 N+M번째 노드를 하이퍼튜브로 지정한다.
		for (int m = N+1; m <= N+M; m++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				int n = Integer.parseInt(st.nextToken());
				graph.get(m).add(n);
				graph.get(n).add(m);
			}
		}
		System.out.println(bfs(graph, N, M));
	}
	// BFS를 통해 N번째 역에 도달하기 위해 방문해야 하는 최소 역의 개수를 구하는 함수
	private static int bfs(ArrayList<ArrayList<Integer>> graph, int N, int M) {
		// N이 1인 경우는 바로 목적지에 도달한다.
		if (N == 1) return 1;
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[N+M+1];
		// 현재 역의 번호와 그 역에 도달하기까지 방문한 역의 개수를 저장한다.
		q.offer(new int[] {1, 1});
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int v : graph.get(u[0])) {
					if (visited[v]) continue;
					// N번째 역에 도달한 경우
					if (v == N) return u[1] + 1;
					visited[v] = true;
					// 역에 도달한 경우와 하이퍼튜브를 통과하는 경우에 따라 방문한 역의 개수를 조절한다.
					q.offer(new int[] {v, u[1] + (v <= N ? 1 : 0)});
				}
				qLen--;
			}
		}
		// N번째 역에 도달 불가능한 경우
		return -1;
	}
}
