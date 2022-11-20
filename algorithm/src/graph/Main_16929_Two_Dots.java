package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16929_Two_Dots {
	static int N, M;
	static char[][] map;
	static int[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				map[i][j] = c;
			}
		}
		// 방문 배열
		visited = new int[N][M];
		// DFS를 통해 사이클이 있는지를 판별한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == 0) dfs(i, j, -1, -1);
			}
		}
		// 사이클이 없는 경우
		System.out.println("No");
	}
	// DFS를 통해 사이클을 판별하는 함수
	private static void dfs(int i, int j, int prev_i, int prev_j) {
		// 아직 방문을 완료하지 않은 정점에 방문한 경우
		if (visited[i][j] == 1) {
			System.out.println("Yes");
			System.exit(0);
		}
		// 해당 정점은 방문했지만 모든 인접 정점을 방문하지 않았음을 1로 표현
		visited[i][j] = 1;
		// 인접 정점 조사
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			// 방문하지 않은 정점인 경우
			if (check(nr, nc) && visited[nr][nc] == 0 && map[nr][nc] == map[i][j]) {
				dfs(nr, nc, i, j);		
			}
			// 방문을 완료하지 않은 정점이면서, 직전에 방문한 정점이 아닌 경우
			// 사이클이 발생했다는 의미와 같다.
			if (check(nr, nc) && visited[nr][nc] == 1 && (nr != prev_i || nc != prev_j) && map[nr][nc] == map[i][j]) {
				dfs(nr, nc, i, j);
			}
		}
		// 모든 인접 정점을 방문 완료
		visited[i][j] = 2;
	}
	// 경계 확인을 위한 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
