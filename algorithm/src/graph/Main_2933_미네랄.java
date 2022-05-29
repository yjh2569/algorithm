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
		// 입력
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
		// 막대기를 왼쪽부터 번갈아가면서 던진다.
		for (int i = 0; i < N; i++) {
			simulate(R-Integer.parseInt(st.nextToken()), i%2 == 0);
		}
		// 출력
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
	// r : 막대기를 던진 행, left : 왼쪽에서 던지면 true, 오른쪽에서 던지면 false
	private static void simulate(int r, boolean left) {
		for (int c = 0; c < C; c++) {
			// 왼쪽에서 던지면 r행의 가장 왼쪽에 있는 미네랄 하나를 제거
			if (left && map[r][c] == 'x') {
				map[r][c] = '.';
				simulAfterRemove(r, c);
				break;
			// 오른쪽에서 던지면 r행의 가장 오른쪽에 있는 미네랄 하나를 제거
			} else if (!left && map[r][C-1-c] == 'x') {
				map[r][C-1-c] = '.';
				simulAfterRemove(r, C-1-c);
				break;
			}
		}		
	}
	// 미네랄 제거 후 제거된 미네랄 기준 인접한 사방의 클러스터가 분리되었는지 확인하고,
	// 만약 분리되었다면 분리된 클러스터 각각에 대해 중력을 작용시킨다.
	private static void simulAfterRemove(int r, int c) {
		// 각 미네랄이 어느 클러스터에 속하는지도 기록하기 위해, 방문 여부 배열을 int형 배열로 만든다.
		int[][] visited = new int[R][C];
		// 클러스터 번호를 위한 변수
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 발견하지 못한 클러스터면 BFS를 통해 클러스터 조사
			if (check(nr, nc) && visited[nr][nc] == 0 && map[nr][nc] == 'x') {
				bfs(nr, nc, visited, ++cnt);
			}
		}
		// 클러스터가 분리되었다면 각 클러스터에 대해 중력 작용
		if (cnt >= 2) {
			for (int i = 1; i <= cnt; i++) {
				gravity(visited, i);
			}
		}
	}
	// cnt번 클러스터에 대해 중력을 작용시킨다.
	private static void gravity(int[][] visited, int cnt) {
		// 효율성을 위해 해당 클러스터가 얼마만큼의 높이를 떨어져야 하는지를 알아낸 후,
		// 클러스터 내 각 미네랄을 그 높이만큼 옮겨준다.
		int min_dist = Integer.MAX_VALUE;
		for (int c = 0; c < C; c++) {
			// c열에서 클러스터 내 가장 아래쪽 미네랄 하나가 떨어질 때 도달 가능한 지점 + 1
			int floor = R;
			for (int r = R-1; r >= 0; r--) {
				// 현재 클러스터가 아닌 클러스터
				if (map[r][c] == 'x' && visited[r][c] != cnt) floor = r;
				// 현재 클러스터의 c열에서의 가장 아래쪽 미네랄
				if (map[r][c] == 'x' && visited[r][c] == cnt) {
					min_dist = Math.min(min_dist, floor - r);
					break;
				}
			}
		}
		// 떨어지지 않는 경우
		if (min_dist <= 1) return;
		// 떨어지는 경우
		for (int c = 0; c < C; c++) {
			for (int r = R-1; r >= 0; r--) {
				if (visited[r][c] == cnt) {
					map[r+min_dist-1][c] = 'x';
					map[r][c] = '.';
				}
			}
		}
	}
	// 클러스터 탐색을 위한 BFS
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
	// 경계를 넘어가는지 확인하는 메서드
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

}
