package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_22352_항체_인식 {
	static int N, M, idx;
	static int[][] before;
	static int[][] after;
	static boolean[][] visited;
	static int[][] areas;
	static int[] areaNums;
	static class Location {
		int r, c;
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		before = new int[N][M];
		after = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				before[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				after[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M]; // BFS를 위한 방문 배열
		areas = new int[N][M]; // before 배열의 영역 구분을 위한 배열
		idx = 1; // 영역 구분을 보조하는 인덱스
		// BFS를 이용해 각 영역을 나눈다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) bfsForBefore(i, j, idx++);
			}
		}
		areaNums = new int[idx]; // before 배열에서 각 영역의 수가 무엇인지를 나타내는 배열
		// after 배열을 조사해 백신을 맞은 결과가 될 수 있는지를 확인한다.
		System.out.println(checkForAfter() ? "YES" : "NO");
	}
	// (r, c)에서 시작해 before 배열에 적힌 인접한 수가 같은 칸을 idx라는 영역으로 묶는 함수
	// BFS를 활용한다.
	private static void bfsForBefore(int r, int c, int idx) {
		Queue<Location> q = new LinkedList<>();
		q.offer(new Location(r, c));
		visited[r][c] = true;
		areas[r][c] = idx;
		int areaNum = before[r][c];
		while (!q.isEmpty()) {
			Location l = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = l.r + dr[d];
				int nc = l.c + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && before[nr][nc] == areaNum) {
					visited[nr][nc] = true;
					areas[nr][nc] = idx;
					q.offer(new Location(nr, nc));
				}
			}
		}
	}
	// after 배열이 before 배열에서 백신을 놓은 결과인지를 확인하는 함수
	private static boolean checkForAfter() {
		int[] afterNums = new int[idx]; // before의 각 영역에 대해 after 배열의 값이 어떻게 되었는지를 기록하는 배열 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				areaNums[areas[i][j]] = before[i][j]; // areaNums 갱신
				// 아직 afterNums를 기록하지 않은 상태인 경우
				if (afterNums[areas[i][j]] == 0) {
					afterNums[areas[i][j]] = after[i][j];
				// 영역 내에 다른 값이 있는 경우
				} else if (afterNums[areas[i][j]] != after[i][j]) {
					return false;
				}
			}
		}
		// 한 조직에만 백신을 놓기 때문에, 변화가 일어난 조직의 개수는 1개 이하여야 한다.
		int changed = 0;
		for (int i = 1; i < idx; i++) {
			if (areaNums[i] != afterNums[i]) changed++;
		}
		return changed <= 1;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
