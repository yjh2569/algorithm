package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1245_농장_관리 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M]; // 방문 배열
		int cnt = 0; // 산봉우리 개수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// (i, j)를 포함하는 산봉우리를 발견하면 cnt를 1 증가시킨다.
				if (!visited[i][j] && bfs(i, j)) cnt++;
			}
		}
		System.out.println(cnt);
	}
	// (i, j)가 정상인 산봉우리가 있는지 너비 우선 탐색을 통해 확인하는 함수
	private static boolean bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		int h = map[i][j];
		boolean flag = true; // (i, j)가 산봉우리의 정상에 속하는지를 나타내는 변수
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 8; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				// 경계 안에 있는지 확인
				if (check(nr, nc)) {
					// (i, j)가 산봉우리에 속하지 않는 경우
					if (map[nr][nc] > h) flag = false;
					// (i, j)와 같은 높이의 지점에 도달한 경우
					if (map[nr][nc] == h && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
		return flag;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
