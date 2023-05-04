package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_27737_버섯_농장 {
	static int N, M, K;
	static boolean[][] canGrow;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 각 칸에서 버섯이 자랄 수 있는지를 나타내는 배열
		canGrow = new boolean[N][N];
		// BFS를 수행할 때 각 칸을 방문했는지를 나타내는 배열
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				canGrow[i][j] = Integer.parseInt(st.nextToken()) == 0;
			}
		}
		boolean used = false; // 버섯을 하나라도 사용했는지를 나타내는 변수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 해당 칸에 버섯이 자랄 수 있고, 아직 버섯이 자라지 않은 경우
				if (!visited[i][j] && canGrow[i][j]) {
					// 남은 포자의 개수가 음수가 되더라도 포자를 뿌린다.
					used = true;
					bfs(i, j);
				}
			}
		}
		// 버섯을 한 개도 사용하지 않거나, 가지고 있는 포자를 모두 사용해도 버섯이 모든 칸에서 자랄 수 없는 경우
		if (!used || M < 0) {
			System.out.println("IMPOSSIBLE");
		// 가지고 있는 포자를 이용해 버섯이 모든 칸에서 자랄 수 있는 경우
		} else {
			System.out.println("POSSIBLE");
			System.out.println(M);
		}
	}
	// BFS를 활용해 (i, j)와 인접한 칸의 개수를 구하고, 이를 바탕으로 해당 영역에 포자를 몇 개 뿌려야 할지를 결정하는 함수
	private static void bfs(int i, int j) {
		int count = 1; // (i, j)와 인접한 칸의 개수
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && canGrow[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					count++;
				}
			}
		}
		// (i, j)와 인접한 칸의 개수에 따라 해당 영역에 뿌려야 하는 포자의 개수를 전체 포자의 개수에서 빼준다.
		M -= (count-1)/K+1;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
