package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14923_미로_탈출 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int Hx = Integer.parseInt(st.nextToken())-1;
		int Hy = Integer.parseInt(st.nextToken())-1;
		st = new StringTokenizer(br.readLine());
		int Ex = Integer.parseInt(st.nextToken())-1;
		int Ey = Integer.parseInt(st.nextToken())-1;
		int[][] board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// BFS를 활용한다.
		Queue<int[]> q = new LinkedList<>();
		// 각 지점에 대해 벽을 아직 부수지 않은 상태에서 방문한 경우 visited[r][c][0]에서, 
		// 벽을 한 번 부순 상태에서 방문한 경우 visited[r][c][1]에서 방문 처리를 하는 방문 배열
		boolean[][][] visited = new boolean[N][M][2];
		q.offer(new int[] {Hx, Hy, 0});
		visited[Hx][Hy][0] = true;
		int distance = 1; // 목적지까지의 최소 거리
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc)) {
						// 벽을 부수는 경우
						if (u[2] == 0 && !visited[nr][nc][1] && board[nr][nc] == 1) {
							// 목적지에 도착한 경우
							if (nr == Ex && nc == Ey) {
								System.out.println(distance);
								System.exit(0);
							}
							visited[nr][nc][1] = true;
							q.offer(new int[] {nr, nc, 1});
						}
						// 벽을 부수지 않는 경우
						if (!visited[nr][nc][u[2]] && board[nr][nc] == 0) {
							// 목적지에 도착한 경우
							if (nr == Ex && nc == Ey) {
								System.out.println(distance);
								System.exit(0);
							}
							visited[nr][nc][u[2]] = true;
							q.offer(new int[] {nr, nc, u[2]});
						}
					}
				}
				qLen--;
			}
			distance++;
		}
		// 목적지에 도달하지 못한 경우
		System.out.println(-1);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
