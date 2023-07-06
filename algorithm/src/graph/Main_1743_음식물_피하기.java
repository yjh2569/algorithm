package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1743_음식물_피하기 {
	static int N, M, K;
	static boolean[][] trash, visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		trash = new boolean[N][M]; // 각 지점에 음식물 쓰레기가 있는지를 나타내는 배열
		visited = new boolean[N][M]; // 방문 여부를 나타내는 배열
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			trash[r][c] = true;
		}
		int max = 0; // 가장 큰 음식물 쓰레기의 크기
		// BFS를 활용해 가장 큰 음식물 쓰레기의 크기를 구한다.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (trash[r][c]) max = Math.max(bfs(r, c), max);
			}
		}
		System.out.println(max);
	}
	// BFS를 이용해 (r, c) 부분의 음식물 쓰레기를 포함한 음식물 쓰레기 덩어리의 크기를 구하는 함수
	private static int bfs(int r, int c) {
		int cnt = 1; // 음식물 쓰레기의 크기
		Queue<int[]> q = new LinkedList<>();
		visited[r][c] = true;
		q.offer(new int[] {r, c});
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && trash[nr][nc]) {
					visited[nr][nc] = true;
					cnt++;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		return cnt;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
