package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055_탈출 {
	static int R, C;
	static char[][] map;
	static int[] start; // 고슴도치 시작 좌표
	static int[] des; // 비버의 굴 좌표
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					start = new int[] {i, j};
					map[i][j] = '.'; // BFS 처리를 보다 원활하게 하기 위해 빈 공간으로 간주한다.
				}
				else if (map[i][j] == 'D') des = new int[] {i, j};
			}
		}
		bfs();
		// 물이나 돌로 인해 비버의 굴에 도달하지 못하는 경우
		System.out.println("KAKTUS");
	}
	// BFS를 통해 고슴도치가 비버의 굴까지 가기 위한 최단 경로의 길이를 구한다.
	private static void bfs() {
		// 비버의 굴까지의 BFS를 위한 초기 설정들
		Queue<int[]> qb = new LinkedList<>();
		boolean[][] visitedb = new boolean[R][C];
		// 홍수의 확산에 대한 BFS를 위한 초기 설정들
		Queue<int[]> qw = new LinkedList<>();
		boolean[][] visitedw = new boolean[R][C];
		// 초기화
		qb.offer(start);
		visitedb[start[0]][start[1]] = true;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '*') {
					qw.offer(new int[] {i, j});
					visitedw[i][j] = true;
				}
			}
		}
		// 고슴도치가 비버의 굴에 도달하는데 걸리는 최단 시간
		int time = 1;
		// 단위 시간마다 위의 두 큐에 대한 완전 순회를 위해 필요한 변수
		int qLen = 0;
		// 고슴도치가 이동이 불가능할 때까지 진행
		while (!qb.isEmpty()) {
			// 우선 물이 차오르는 과정을 수행
			// 고슴도치는 물이 차오를 칸을 건너지 못하기 때문이다.
			qLen = qw.size();
			while (qLen > 0) {
				int[] u = qw.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					// 여기서 map[nr][nc]가 빈 칸인 경우에 대해서만 물을 채우는데,
					// 고슴도치의 시작 위치 역시 물이 차오를 수 있기 때문에 위에서 시작 지점을 빈 칸으로 간주했던 것이다.
					if (check(nr, nc) && !visitedw[nr][nc] && map[nr][nc] == '.') {
						visitedw[nr][nc] = true;
						map[nr][nc] = '*';
						qw.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
			// 고슴도치의 모든 이동 경로를 고려
			qLen = qb.size();
			while (qLen > 0) {
				int[] u = qb.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc) && !visitedb[nr][nc] && map[nr][nc] != '*' && map[nr][nc] != 'X') {
						// 비버의 굴에 도착한 경우
						if (map[nr][nc] == 'D') {
							System.out.println(time);
							System.exit(0);
						}
						visitedb[nr][nc] = true;
						qb.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
			time++;
		}
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

}
