package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6593_상범_빌딩 {
	// 3차원 BFS를 위해 필요한 배열
	static int[] dl = {0, 0, 0, 0, -1, 1};
	static int[] dr = {-1, 0, 1, 0, 0, 0};
	static int[] dc = {0, 1, 0, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		String input;
		outer: while (true) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			// 입력의 끝
			if (L == 0 && R == 0 && C == 0) break;
			char[][][] map = new char[L][R][C];
			// 시작 위치
			int[] start = new int[3];
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
					input = br.readLine();
					for (int c = 0; c < C; c++) {
						map[l][r][c] = input.charAt(c);
						if (map[l][r][c] == 'S') {
							start = new int[] {l, r, c};
						}
					}
				}
				br.readLine();
			}
			// 3차원 BFS를 수행한다.
			Queue<int[]> q = new LinkedList<int[]>();
			boolean[][][] visited = new boolean[L][R][C];
			q.offer(start);
			visited[start[0]][start[1]][start[2]] = true;
			// 탈출하는데 걸린 시간
			int time = 1;
			while (!q.isEmpty()) {
				int qLen = q.size();
				while (qLen > 0) {
					int[] u = q.poll();
					for (int d = 0; d < 6; d++) {
						int nl = u[0] + dl[d];
						int nr = u[1] + dr[d];
						int nc = u[2] + dc[d];
						if (0<=nl && nl<L && 0<=nr && nr<R && 0<=nc && nc<C && !visited[nl][nr][nc] && map[nl][nr][nc] != '#') {
							// 탈출에 성공한 경우
							if (map[nl][nr][nc] == 'E') {
								sb.append("Escaped in ").append(time).append(" minute(s).").append("\n");
								continue outer;
							}
							visited[nl][nr][nc] = true;
							q.offer(new int[] {nl, nr, nc});
						}
					}
					qLen--;
				}
				time++;
			}
			// 탈출에 실패한 경우
			sb.append("Trapped!").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
