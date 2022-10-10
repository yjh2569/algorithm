package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17141_연구소_2 {
	static int N, M, K, min;
	static int[][] map;
	static ArrayList<int[]> canPut;
	static boolean[] v;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] temp;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 모든 빈 칸에 바이러스가 퍼지는 최소 시간
		min = Integer.MAX_VALUE;
		map = new int[N][N];
		// 바이러스를 놓을 수 있는 칸을 모아둔 ArrayList
		canPut = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 바이러스를 놓을 수 있는 칸이면 빈 칸으로 바꾼 후 canPut에 저장
				if (map[i][j] == 2) {
					map[i][j] = 0;
					canPut.add(new int[] {i, j});
				}
			}
		}
		// 바이러스를 놓을 수 있는 칸의 개수
		K = canPut.size();
		// 조합을 위해 사용하는 방문 배열
		v = new boolean[K];
		// 각 칸에 바이러스가 퍼지기까지 걸리는 시간을 기록하는 배열
		temp = new int[N][N];
		combi(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	// K개의 칸 중 바이러스를 놓을 칸을 결정하기 위해 조합을 사용한다.
	private static void combi(int cnt, int start) {
		if (cnt == M) {
			simulate();
			return;
		}
		for (int i = start; i < K; i++) {
			v[i] = true;
			combi(cnt+1, i+1);
			v[i] = false;
		}
	}
	// BFS를 통해 바이러스를 지정된 칸에 넣었을 때 모든 칸에 퍼지기 위해 걸리는 시간을 구한다.
	private static void simulate() {
		// 각 칸에 퍼지는데 걸리는 시간
		int time = 1;
		for (int i = 0; i < N; i++) {
			Arrays.fill(temp[i], 0);
		}
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		// 바이러스를 조합을 통해 고른 칸에 집어넣는다.
		for (int i = 0; i < K; i++) {
			if (v[i]) {
				int[] loc = canPut.get(i);
				q.offer(new int[] {loc[0], loc[1]});
				visited[loc[0]][loc[1]] = true;
			}
		}
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && map[nr][nc] == 0) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
						temp[nr][nc] = time;
					}
				}
				qLen--;
			}
			time++;
		}
		// 모든 칸에 퍼지기까지 걸린 시간을 구한다.
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 만약 바이러스가 도달하지 못한 칸이 있으면 그만둔다.
				if (!visited[i][j] && map[i][j] == 0) return;
				max = Math.max(max, temp[i][j]);
			}
		}
		min = Math.min(min, max);
	}

}
