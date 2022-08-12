package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구_이동 {
	static int N, L, R;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 인구 이동이 발생하는 일수
		int day = 0;
		while (true) {
			// 연합의 수
			int cnt = 0;
			// 각 나라가 현재 속한 연합을 표시하는 배열
			int[][] openedTo = new int[N][N];
			// 초기화
			for (int i = 0; i < N; i++) {
				Arrays.fill(openedTo[i], -1);
			}
			// 각 연합 내 나라의 인구수
			ArrayList<Integer> results = new ArrayList<>();
			// 방문하지 않은 나라에 대해 해당 나라가 속한 연합을 구한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (openedTo[i][j] == -1) {
						int res = bfs(i, j, openedTo, cnt++);
						results.add(res);
					}
				}
			}
			// 연합이 만들어지지 않은 경우
			if (cnt == N*N) break;
			// 각 연합의 인구수의 변화를 연합의 각 나라에 적용한다.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = results.get(openedTo[i][j]);
				}
			}
			day++;
		}
		System.out.println(day);
	}
	// 너비 우선 탐색을 통해 연합을 찾는다.
	private static int bfs(int i, int j, int[][] openedTo, int k) {
		// 연합에 속한 인구수의 합
		int sum = map[i][j];
		// 연합에 속한 나라의 개수
		int num = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {i, j});
		openedTo[i][j] = k;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<N && 0<=nc && nc<N && openedTo[nr][nc] == -1) {
					int diff = Math.abs(map[nr][nc] - map[u[0]][u[1]]);
					if (L<=diff && diff<=R) {
						openedTo[nr][nc] = k;
						sum += map[nr][nc];
						num++;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
		return sum/num;
	}

}
