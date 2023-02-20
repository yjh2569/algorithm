package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16933_벽_부수고_이동하기_3 {
	static class Location {
		int r, c, k, day;
		public Location(int r, int c, int k, int day) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.day = day;
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}
		// 1*1 맵인 경우
		if (N == 1 && M == 1) {
			System.out.println(1);
			System.exit(0);
		}
		// BFS를 이용한다.
		Queue<Location> q = new LinkedList<>();
		// 좌표, 벽을 부순 횟수, 낮밤 여부에 따라 방문했는지를 나타내는 배열
		boolean[][][][] visited = new boolean[N][M][K+1][2];
		q.offer(new Location(0, 0, 0, 0));
		visited[0][0][0][0] = true;
		int count = 2; // 이동한 칸의 최솟값
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				Location u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u.r + dr[d];
					int nc = u.c + dc[d];
					// 목적지에 도착한 경우
					if (nr == N-1 && nc == M-1) {
						System.out.println(count);
						System.exit(0);
					}
					if (nr<0 || nr>=N || nc<0 || nc>=M) continue;
					// 빈 공간을 마주한 경우
					if (board[nr][nc] == 0) {
						// 다음 공간으로 이동하고 낮밤이 바뀐다.
						if (!visited[nr][nc][u.k][u.day ^ 1]) {
							visited[nr][nc][u.k][u.day ^ 1] = true;
							q.offer(new Location(nr, nc, u.k, u.day ^ 1));
						}
					// 벽을 마주한 경우
					} else {
						// 낮에는 벽을 뚫고 지나갈 수 있다. 단, 현재 벽을 뚫은 횟수가 K보다 작아야지만 가능 
						if (u.day == 0 && u.k < K && !visited[nr][nc][u.k + 1][u.day ^ 1]) {
							visited[nr][nc][u.k + 1][u.day ^ 1] = true;
							q.offer(new Location(nr, nc, u.k + 1, u.day ^ 1));
						// 밤에는 벽을 마주한 경우 낮까지 기다리는 경우도 고려한다.
						} else if (u.day == 1 && u.k < K && !visited[u.r][u.c][u.k][u.day ^ 1]) {
							visited[u.r][u.c][u.k][u.day ^ 1] = true;
							q.offer(new Location(u.r, u.c, u.k, u.day ^ 1));
						}
					}
				}
				qLen--;
			}
			count++;
		}
		// 목적지까지 가는 게 불가능한 경우
		System.out.println(-1);
	}

}
