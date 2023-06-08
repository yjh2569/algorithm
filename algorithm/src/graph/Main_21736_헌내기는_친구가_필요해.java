package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21736_헌내기는_친구가_필요해 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[] start = new int[2]; // 시작 지점
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'I') {
					start = new int[] {i, j};
					map[i][j] = 'O';
				}
			}
		}
		// BFS를 통해 도연이가 만날 수 있는 사람의 수를 구한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.offer(start);
		visited[start[0]][start[1]] = true;
		int cnt = 0; // 도연이가 만날 수 있는 사람의 수
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X') {
					// 사람을 만난 경우
					if (map[nr][nc] == 'P') {
						cnt++;
					}
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		// 사람을 한 명도 못 만난 경우 TT를 출력
		System.out.println(cnt == 0 ? "TT" : cnt);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
