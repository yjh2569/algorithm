package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14442_벽_부수고_이동하기_2 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		// BFS를 통해 최단 거리를 구한다.
		// 이때 부술 수 있는 벽의 개수가 한정적이므로 방문 처리 시 지금까지 부순 벽의 개수를 추가한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][K+1];
		q.offer(new int[] {0, 0, 0});
		visited[0][0][0] = true;
		int l = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				qLen--;
				int[] u = q.poll();
				// 목적지에 도달
				if (u[0] == N-1 && u[1] == M-1) {
					System.out.println(l);
					System.exit(0);
				}
				// 인접지역 탐색
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (0<=nr && nr<N && 0<=nc && nc<M) {
						// 벽이 없는 경우
						if (!visited[nr][nc][u[2]] && map[nr][nc] == 0) {
							visited[nr][nc][u[2]] = true;
							q.offer(new int[] {nr, nc, u[2]});
						}
						// 벽이 있는 경우
						// 부술 수 있는 벽의 개수가 최대 K개라는 것에 주의
						if (u[2] < K && !visited[nr][nc][u[2]+1] && map[nr][nc] == 1) {
							visited[nr][nc][u[2]+1] = true;
							q.offer(new int[] {nr, nc, u[2]+1});
						}
					}
				}
			}
			l++;
		}
		System.out.println(-1);
	}

}
