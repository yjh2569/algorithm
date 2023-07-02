package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17090_미로_탈출하기 {
	static int N, M;
	static int[][] map;
	static int[][] canEscape;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++) {
				char c = s.charAt(j-1);
				// 입력으로 주어진 방향을 dr, dc에 맞게 숫자로 변환한다.
				if (c == 'U') map[i][j] = 0;
				else if (c == 'R') map[i][j] = 1;
				else if (c == 'D') map[i][j] = 2;
				else map[i][j] = 3;
			}
		}
		// 각 지점에서 탈출할 수 있는지를 나타내는 배열
		// 값이 1이면 해당 지점에서 탈출할 수 있음을, 값이 -1이면 그렇지 않음을 의미
		canEscape = new int[N+2][M+2]; 
		// 각 지점을 방문했는지를 나타내는 배열
		visited = new boolean[N+2][M+2];
		// 깊이 우선 탐색과 dynamic programming을 각 지점에 대해 수행한다.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dfs(i, j);
			}
		}
		int cnt = 0; // 탈출할 수 있는 지점의 개수
		// 탈출 가능한 지점의 개수를 센다.
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (canEscape[i][j] == 1) cnt++;
			}
		}
		System.out.println(cnt);
	}
	// 깊이 우선 탐색과 dynamic programming을 활용해 (i, j)에서 탈출할 수 있는지를 구하는 함수
	private static boolean dfs(int i, int j) {
		// 이미 방문한 지점에 도착한 경우, 세 가지 경우가 있다.
		// 1. 해당 지점에서 탈출이 가능한 경우
		// 이 경우 true를 반환함으로써 이전 방문 지점에서 탈출 가능함을 알린다.
		// 2. 탈출 가능한지 알 수 없는 경우
		// 이는 어떤 지점에서 깊이 우선 탐색을 진행한 이후로, 탈출하지 못하고 같은 지점으로 돌아왔음을 의미한다.
		// 따라서 탈출 불가능하므로 false를 반환한다.
		// 3. 해당 지점에서 탈출이 불가능함을 알고 있는 경우
		// false를 반환해 이전 방문 지점에서 탈출 불가능함을 알린다.
		if (visited[i][j]) {
			if (canEscape[i][j] == 1) {
				return true;
			} else {
				return false;
			}
		}
		// 해당 지점을 방문했음을 표시
		visited[i][j] = true;
		// map을 활용해 다음 지점을 파악
		int d = map[i][j];
		int nr = i + dr[d];
		int nc = j + dc[d];
		// 탈출한 경우
		if (check(nr, nc)) {
			canEscape[i][j] = 1;
			return true;
		}
		// 당장 탈출하지 못한 경우
		// 다음 지점으로 재귀적으로 이동한다.
		// 만약 그 결과가 true면 탈출한 것이고, false면 탈출에 실패한 것이다.
		if (dfs(nr, nc)) {
			canEscape[i][j] = 1;
			return true;
		} else {
			canEscape[i][j] = -1;
			return false;
		}
	}
	// (r, c)가 경계를 벗어난 것인지 확인하는 함수
	private static boolean check(int r, int c) {
		return 0 == r || r == N+1 || 0 == c || c == M+1;
	}

}
