package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int w, h;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Queue<int[]> qF = new LinkedList<>(); // 불이 퍼지는 과정을 BFS로 표현하기 위한 큐
		Queue<int[]> q = new LinkedList<>(); // 상근이가 이동하는 과정을 BFS로 표현하기 위한 큐
		// 테스트케이스
		outer: for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			char[][] map = new char[h][w];
			qF.clear(); 
			q.clear(); 
			// 불과 상근이가 각 지점에 방문했는지를 나타내는 배열
			// 불과 상근이 각각에 대해 방문 배열을 따로 만들어도 되지만, 하나로 합쳤다.
			// 불이 방문한 지점은 상근이가 방문할 수 없고, 상근이가 방문한 지점은 불이 못 가도 
			// 어차피 상근이는 최단거리로 빌딩을 빠져나와야 하기 때문에 상근이가 그 지점을 다시 방문할 일이 없어 괜찮다.
			boolean[][] visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '@') {
						visited[i][j] = true;
						q.offer(new int[] {i, j});
					} else if (map[i][j] == '*') {
						visited[i][j] = true;
						qF.offer(new int[] {i, j});
					}
				}
			}
			int time = 1; // 빌딩을 탈출하는데 걸리는 시간
			// 상근이가 방문할 지점이 없을 때까지 BFS를 진행한다.
			while (!q.isEmpty()) {
				// 먼저 불을 인접한 구역으로 퍼지게 한다.
				int qFLen = qF.size();
				while (qFLen > 0) {
					int[] u = qF.poll();
					int r = u[0]; int c = u[1];
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] != '#') {
							visited[nr][nc] = true;
							qF.offer(new int[] {nr, nc});
						}
					}
					qFLen--;
				}
				// 상근이가 인접한 구역 중 불이 퍼지지 않은 구역으로 이동한다.
				int qLen = q.size();
				while (qLen > 0) {
					int[] u = q.poll();
					int r = u[0]; int c = u[1];
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						// 만약 (nr, nc)가 경계를 넘어가는 경우 탈출에 성공했다는 의미이므로 
						// 탈출 시간을 출력한다.
						if (!check(nr, nc)) {
							sb.append(time).append("\n");
							continue outer;
						}
						if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] != '#') {
							visited[nr][nc] = true;
							q.offer(new int[] {nr, nc});
						}
					}
					qLen--;
				}
				time++;
			}
			// 상근이가 탈출에 실패한 경우
			sb.append("IMPOSSIBLE").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	// 경계 확인 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<h && 0<=c && c<w;
	}

}
