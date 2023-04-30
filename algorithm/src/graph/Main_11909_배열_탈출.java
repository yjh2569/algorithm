package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11909_배열_탈출 {
	static int n;
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[][] A = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dijkstra's algorithm을 이용한다.
		int[][] dijk = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(dijk[i], Integer.MAX_VALUE/10);
		}
		dijk[0][0] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
		boolean[][] visited = new boolean[n][n];
		pq.offer(new int[] {0, 0, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (u[0] == n-1 && u[1] == n-1) break; // 목적지에 최단 비용으로 도착한 경우
			if (visited[u[0]][u[1]]) continue;
			visited[u[0]][u[1]] = true;
			for (int d = 0; d < 2; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				// max 함수를 이용해 버튼을 누를 경우를 고려한다.
				if (check(nr, nc) && !visited[nr][nc] && dijk[nr][nc] > dijk[u[0]][u[1]] + Math.max(0, A[nr][nc] - A[u[0]][u[1]] + 1)) {
					dijk[nr][nc] = dijk[u[0]][u[1]] + Math.max(0, A[nr][nc] - A[u[0]][u[1]] + 1);
					pq.offer(new int[] {nr, nc, dijk[nr][nc]});
				}
			}
		}
		System.out.println(dijk[n-1][n-1]);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<n && 0<=c && c<n;
	}

}
