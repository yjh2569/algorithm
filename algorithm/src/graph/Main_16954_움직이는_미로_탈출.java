package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16954_움직이는_미로_탈출 {
	// 그 자리 그대로와 8방
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[8][8];
		for (int i = 0; i < 8; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		// BFS를 수행한다.
		Queue<int[]> q = new LinkedList<>();
		// 시간이 지날 때마다 map이 변하기 때문에, 방문 배열에도 시간에 따른 각 칸의 방문 여부를 구분한다.
		boolean[][][] visited = new boolean[30][8][8];
		q.offer(new int[] {7, 0});
		visited[0][7][0] = true;
		// 현재 시간
		int time = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				// 현재 위치에 벽이 있는 경우 더 이상 이동 불가
				if (map[u[0]][u[1]] == '#') {
					qLen--;
					continue;
				}
				for (int d = 0; d < 9; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (0<=nr && nr<8 && 0<=nc && nc<8 && !visited[time][nr][nc] && map[nr][nc] == '.') {
						visited[time][nr][nc] = true;
						q.offer(new int[] {nr, nc});
						// 목적지에 도달한 경우
						if (nr == 0 && nc == 7) {
							System.out.println(1);
							System.exit(0);
						}
					}
				}
				qLen--;
			}
			time++;
			// 벽 이동
			for (int i = 7; i > 0; i--) {
				for (int j = 0; j < 8; j++) {
					map[i][j] = map[i-1][j];
				}
			}
			// 맨 윗줄은 모두 빈 칸으로 초기화
			for (int j = 0; j < 8; j++) {
				map[0][j] = '.';
			}
		}
		// 목적지에 도달 불가능한 경우
		System.out.println(0);
	}

}
