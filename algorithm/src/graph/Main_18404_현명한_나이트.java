package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18404_현명한_나이트 {
	static int N;
	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(st.nextToken())-1;
		int Y = Integer.parseInt(st.nextToken())-1;
		// 각 지점에 몇 번째 말이 있는지를 나타내는 배열
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], -1);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			map[A][B] = i;
		}
		// BFS를 활용해 각 말까지의 최단 거리를 구한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int[] result = new int[M]; // 각 말까지 최단 거리를 저장하는 배열
		q.offer(new int[] {X, Y});
		visited[X][Y] = true;
		int cnt = 1; // 최단 거리
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 8; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc) && !visited[nr][nc]) {
						q.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
						// 말에 도달한 경우
						if (map[nr][nc] >= 0) {
							result[map[nr][nc]] = cnt;
						}
					}
				}
				qLen--;
			}
			cnt++;
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			sb.append(result[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
