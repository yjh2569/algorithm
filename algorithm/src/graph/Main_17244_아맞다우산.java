package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17244_아맞다우산 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[] start = new int[2]; // 시작 지점
		int[] end = new int[2]; // 끝 지점
		int K = 0; // 챙겨야 할 물건의 개수
		int[][] goods = new int[N][M]; // 각 물건이 몇 번쨰 물건인지를 표시하는 배열
		// 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(goods[i], -1);
		}
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					start = new int[] {i, j};
					map[i][j] = '.';
				}
				if (map[i][j] == 'E') end = new int[] {i, j};
				if (map[i][j] == 'X') goods[i][j] = K++;
			}
		}
		// BFS와 비트마스킹을 활용한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][1 << K]; // K개의 물건 중 얼마나 챙겼는지도 방문 배열에 포함시킨다.
		q.offer(new int[] {start[0], start[1], 0});
		visited[start[0]][start[1]][0] = true;
		int cnt = 1; // 이동 횟수
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					// 물건을 모두 챙긴 채 목적지에 도달한 경우
					if (nr == end[0] && nc == end[1] && u[2] == (1 << K) - 1) {
						System.out.println(cnt);
						return;
					}
					// 물건이 없는 곳으로 이동
					if (map[nr][nc] == '.' && !visited[nr][nc][u[2]]) {
						visited[nr][nc][u[2]] = true;
						q.offer(new int[] {nr, nc, u[2]});
					}
					// 물건이 있는 곳으로 이동
					if (map[nr][nc] == 'X' && !visited[nr][nc][u[2] | (1 << goods[nr][nc])]) {
						visited[nr][nc][u[2] | (1 << goods[nr][nc])] = true;
						q.offer(new int[] {nr, nc, u[2] | (1 << goods[nr][nc])});
					}
				}
				qLen--;
			}
			cnt++;
		}
	}

}
