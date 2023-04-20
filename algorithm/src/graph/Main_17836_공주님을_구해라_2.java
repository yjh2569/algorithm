package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17836_공주님을_구해라_2 {
	// 용사의 위치와 그람을 가지고 있는지를 나타내는 클래스
	static class Location {
		int r, c;
		boolean hasGram;
		public Location(int r, int c, boolean hasGram) {
			this.r = r;
			this.c = c;
			this.hasGram = hasGram;
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
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 공주를 구하는데 걸리는 최소 시간
		int time = 1;
		// BFS를 통해 공주를 구하는데 걸리는 최소 시간을 구한다.
		Queue<Location> q = new LinkedList<>();
		boolean[][][] visited = new boolean[2][N][M];
		q.offer(new Location(0, 0, false));
		visited[0][0][0] = true;
		// T초 내에 공주를 구해야 한다.
		while (time <= T && !q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				Location l = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = l.r + dr[d];
					int nc = l.c + dc[d];
					// T초 내에 공주에게 도달한 경우
					if (nr == N-1 && nc == M-1) {
						System.out.println(time);
						System.exit(0);
					}
					if (0<=nr && nr<N && 0<=nc && nc<M) {
						// 그람을 가지지 않은 경우
						if (!l.hasGram) {
							// 벽은 통과하지 못한다.
							if (map[nr][nc] == 1) continue;
							// 그람을 얻으면 그 지점부터는 그람을 소지한채 이동한다.
							if (map[nr][nc] == 2 && !visited[1][nr][nc]) {
								visited[1][nr][nc] = true;								
								q.offer(new Location(nr, nc, true));
							// 빈 공간인 경우
							} else if (map[nr][nc] == 0 && !visited[0][nr][nc]) {
								visited[0][nr][nc] = true;
								q.offer(new Location(nr, nc, l.hasGram));
							}
						// 그람을 가지고 있는 경우
						} else {
							// 모든 공간을 통과할 수 있다.
							if (!visited[1][nr][nc]) {
								visited[1][nr][nc] = true;
								q.offer(new Location(nr, nc, l.hasGram));
							}
						}
					}
				}
				qLen--;
			}
			time++;
		}
		// T초 내에 통과하지 못하는 경우
		System.out.println("Fail");
	}

}
