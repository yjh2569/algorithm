package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2933_미네랄 {
	static int R, C, N;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			simulate(R-Integer.parseInt(st.nextToken()), i%2 == 0);
		}
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(map[r][c]);
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	private static void simulate(int r, boolean left) {
		for (int c = 0; c < C; c++) {
			if (left && map[r][c] == 'x') {
				map[r][c] = '.';
				simulAfterRemove(r, c);
				break;
			} else if (!left && map[r][C-1-c] == 'x') {
				map[r][C-1-c] = '.';
				simulAfterRemove(r, C-1-c);
				break;
			}
		}		
	}
	private static void simulAfterRemove(int r, int c) {
		int[][] visited = new int[R][C];
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (check(nr, nc) && visited[nr][nc] == 0 && map[nr][nc] == 'x') {
				bfs(nr, nc, visited, ++cnt);
			}
		}
		if (cnt >= 2) {
			for (int i = 1; i <= cnt; i++) {
				gravity(visited, i);
			}
		}
	}
	private static void gravity(int[][] visited, int cnt) {
		int min_dist = Integer.MAX_VALUE;
		for (int c = 0; c < C; c++) {
			int floor = R;
			for (int r = R-1; r >= 0; r--) {
				if (map[r][c] == 'x' && visited[r][c] != cnt) floor = r;
				if (map[r][c] == 'x' && visited[r][c] == cnt) {
					min_dist = Math.min(min_dist, floor - r);
					break;
				}
			}
		}
		if (min_dist <= 1) return;
		for (int c = 0; c < C; c++) {
			for (int r = R-1; r >= 0; r--) {
				if (visited[r][c] == cnt) {
					map[r+min_dist-1][c] = 'x';
					map[r][c] = '.';
				}
			}
		}
	}
	private static void bfs(int r, int c, int[][] visited, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		visited[r][c] = cnt;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && visited[nr][nc] == 0 && map[nr][nc] == 'x') {
					visited[nr][nc] = cnt;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		
	}
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

}
