package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1584_게임 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static StringTokenizer st;
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		// 입력
		br = new BufferedReader(new InputStreamReader(System.in));
		// 위험 구역, 안전 구역을 표시하기 위한 배열
		// 값이 0이면 안전 구역, 1 이상이면 위험 구역이다.
		int[][] map = new int[501][501];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			inputMap(map);
		}
		// 죽음의 구역을 표시하기 위한 배열
		// 값이 1 이상이면 죽음의 구역이다.
		int[][] canEnter = new int[501][501];
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			inputMap(canEnter);
		}
		findArea(map);
		findArea(canEnter);
		// 너비 우선 탐색을 하되 위험 구역은 우선 순위가 낮다는 것을 이용해 0-1 너비 우선 탐색을 한다.
		// 0-1 너비 우선 탐색은 ArrayDeque를 사용해 우선 순위가 높은 것은 앞에 넣어 우선적으로 고려하고,
		// 우선 순위가 낮은 것은 뒤에 넣어 나중에 고려하는 탐색 방식을 말한다.
		ArrayDeque<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[501][501];
		q.offer(new int[] {0, 0, 0});
		visited[0][0] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			if (u[0] == 500 && u[1] == 500) {
				System.out.println(u[2]);
				System.exit(0);
			}
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<=500 && 0<=nc && nc<=500 && !visited[nr][nc] && canEnter[nr][nc] == 0) {
					if (map[nr][nc] > 0) {
						visited[nr][nc] = true;
						// 위험 구역은 우선 순위가 낮으므로 나중에 고려한다.
						q.offerLast(new int[] {nr, nc, u[2]+1});
					} else {
						visited[nr][nc] = true;
						// 안전 구역은 우선 순위가 높으므로 먼저 고려한다.
						q.offerFirst(new int[] {nr, nc, u[2]});
					}
				}
			}
		}
		
		System.out.println(-1);
	}
	// inputMap에서 표시해둔 정보를 바탕으로 위험 구역, 안전 구역, 죽음의 구역을 찾는다.
	// 가로 방향으로 누적합을 구하고, 세로 방향으로 누적합을 구하면 된다.
	private static void findArea(int[][] map) {
		for (int i = 0; i <= 500; i++) {
			for (int j = 1; j <= 500; j++) {
				map[i][j] += map[i][j-1];
			}
		}
		for (int i = 1; i <= 500; i++) {
			for (int j = 0; j <= 500; j++) {
				map[i][j] += map[i-1][j];
			}
		}		
	}
	// 입력으로 위험 구역에 대한 정보를 받는다.
	// 효율성을 위해 받자마자 바로 영역을 채우지 않는다.
	private static void inputMap(int[][] map) throws IOException {
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		// 사각형 영역을 채울 수 있도록 적절한 두 꼭짓점을 고른다.
		int min_x = Math.min(x1, x2);
		int min_y = Math.min(y1, y2);
		int max_x = Math.max(x1, x2);
		int max_y = Math.max(y1, y2);
		// 받자마자 바로 영역을 채우지 않고, 꼭짓점 혹은 꼭짓점에 인접한 곳에 해당 영역이 있음을 표시해 둔다.
		map[min_x][min_y] += 1;
		if (max_x < 500) map[max_x+1][min_y] -= 1;
		if (max_y < 500) map[min_x][max_y+1] -= 1;
		if (max_x < 500 && max_y < 500) map[max_x+1][max_y+1] += 1;
	}
	
}
