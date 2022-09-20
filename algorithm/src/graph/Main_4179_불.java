package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4179_불 {
	static int R, C;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		// 지훈과 불에 BFS를 적용하기 위해 필요한 초기 큐와 방문 배열
		Queue<int[]> qJ = new LinkedList<>();
		Queue<int[]> qF = new LinkedList<>();
		boolean[][] visitedJ = new boolean[R][C];
		boolean[][] visitedF = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = s.charAt(j);
				map[i][j] = c;
				// 지훈을 발견한 경우
				if (map[i][j] == 'J') {
					// BFS 초기값에 넣고 해당 위치는 빈 칸으로 지정한다.
					visitedJ[i][j] = true;
					qJ.offer(new int[] {i, j});
					map[i][j] = '.';
				// 불을 발견한 경우
				} else if (map[i][j] == 'F') {
					// BFS 초기값에 넣는다.
					visitedF[i][j] = true;
					qF.offer(new int[] {i, j});
				}
			}
		}
		// 가장 빠른 탈출시간
		int time = 1;
		while (!qJ.isEmpty()) {
			// 현재 시간 동안 지훈이 갈 수 있는 인접한 지점을 구한다.
			int qJLen = qJ.size();
			while (qJLen > 0) {
				int[] j = qJ.poll();
				qJLen--;
				// 이전에 불이 지훈이 쪽으로 퍼져서 지훈이가 타버린 경우
				if (visitedF[j[0]][j[1]]) continue;
				for (int d = 0; d < 4; d++) {
					int nr = j[0] + dr[d];
					int nc = j[1] + dc[d];
					// 지훈이가 탈출 성공한 경우
					if (!check(nr, nc)) {
						System.out.println(time);
						System.exit(0);
					}
					if (check(nr, nc) && !visitedJ[nr][nc] && map[nr][nc] == '.') {
						visitedJ[nr][nc] = true;
						qJ.offer(new int[] {nr, nc});
					}
				}
			}
			// 현재 시간 동안 불이 갈 수 있는 인접한 지점을 구한다.
			int qFLen = qF.size();
			while (qFLen > 0) {
				int[] f = qF.poll();
				for (int d = 0; d < 4; d++) {
					int nr = f[0] + dr[d];
					int nc = f[1] + dc[d];
					if (check(nr, nc) && !visitedF[nr][nc] && map[nr][nc] == '.') {
						visitedF[nr][nc] = true;
						qF.offer(new int[] {nr, nc});
					}
				}
				qFLen--;
			}
			time++;
		}
		System.out.println("IMPOSSIBLE");
	}
	// 경계 조사 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

}
