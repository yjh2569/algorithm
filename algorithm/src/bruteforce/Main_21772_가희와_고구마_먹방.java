package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_21772_가희와_고구마_먹방 {
	static int R, C, T, max;
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	static char[][] map;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		int[] start = new int[2]; // 가희의 초기 위치
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'G') {
					start = new int[] {i, j};
				}
			}
		}
		max = 0; // 최대로 먹을 수 있는 고구마 개수
		// DFS를 이용해 최대로 먹을 수 있는 고구마 개수를 구한다.
		dfs(start[0], start[1], 0, 0);
		System.out.println(max);
	}
	// DFS를 이용해 최대로 먹을 수 있는 고구마 개수를 구하는 함수
	// 현재 가희는 (r, c)에 있고, 먹은 고구마 개수는 cnt, 시간은 time임을 의미
	private static void dfs(int r, int c, int cnt, int time) {
		// 시간이 T초가 된 경우
		if (time == T) {
			// 고구마 개수 갱신
			max = Math.max(cnt, max);
			return;
		}
		// 가만히 있거나 인접한 칸으로 움직이는 경우를 모두 고려
		for (int d = 0; d < 5; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 맵 밖으로 벗어나거나 벽으로 막힌 경우
			if (!check(nr, nc) || map[nr][nc] == '#') continue;
			// 고구마를 발견한 경우
			if (map[nr][nc] == 'S') {
				// 고구마를 일단 먹고 다음 과정으로 넘어갔다가, 원상복구를 위해 백트래킹을 한다.
				map[nr][nc] = '.';
				dfs(nr, nc, cnt+1, time+1);
				map[nr][nc] = 'S';
			// 일반적인 칸인 경우
			} else {
				dfs(nr, nc, cnt, time+1);
			}
		}
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<R && 0<=c && c<C;
	}

}
