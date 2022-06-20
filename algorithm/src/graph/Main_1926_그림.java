package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1926_그림 {
	static int n, m, cnt, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cnt = 0; // 그림의 개수
		max = 0; // 그림의 넓이의 최댓값
		visited = new boolean[n][m]; // BFS를 위한 방문 처리 배열
		// 각 좌표에 대해 방문하지 않은 그림을 찾으면 BFS를 수행해 그림을 탐색한다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
		System.out.println(max);
	}
	// 그림을 탐색해 넓이를 구한다.
	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		int area = 1;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<n && 0<=nc && nc<m && !visited[nr][nc] && map[nr][nc] == 1) {
					area++;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		// 넓이의 최댓값을 구한다.
		max = Math.max(max, area);
	}

}
