package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16197_두_동전 {
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		// 두 동전의 위치
		int[] coin1 = new int[2];
		int[] coin2 = new int[2];
		Arrays.fill(coin1, -1);
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				map[i][j] = c;
				// 동전을 처음 발견한 경우
				if (c == 'o' && coin1[0] == -1) {
					coin1[0] = i;
					coin1[1] = j;
					map[i][j] = '.';
				// 두 번째 동전을 발견한 경우
				} else if (c == 'o') {
					coin2[0] = i;
					coin2[1] = j;
					map[i][j] = '.';
				}
			}
		}
		// BFS를 통해 한 동전만 떨어지는 최단거리를 찾는다.
		Queue<int[]> q = new LinkedList<>();
		// 두 동전의 위치를 모두 기록할 수 있는 방문 배열을 만든다.
		boolean[][][][] visited = new boolean[N][M][N][M];
		q.offer(new int[] {coin1[0], coin1[1], coin2[0], coin2[1]});
		visited[coin1[0]][coin1[1]][coin2[0]][coin2[1]] = true;
		int time = 1;
		while (!q.isEmpty() && time <= 10) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr1 = u[0] + dr[d];
					int nc1 = u[1] + dc[d];
					int nr2 = u[2] + dr[d];
					int nc2 = u[3] + dc[d];
					if (check(nr1, nc1) && check(nr2, nc2)) {
						// 벽을 만나면 원래 위치로 되돌아간다.
						if (map[nr1][nc1] == '#') {
							nr1 = u[0];
							nc1 = u[1];
						}
						if (map[nr2][nc2] == '#') {
							nr2 = u[2];
							nc2 = u[3];
						}
						if (!visited[nr1][nc1][nr2][nc2]) {
							visited[nr1][nc1][nr2][nc2] = true;
							q.offer(new int[] {nr1, nc1, nr2, nc2});
						}
					// 하나의 동전만 떨어진 경우
					} else if (check(nr1, nc1) ^ check(nr2, nc2)) {
						System.out.println(time);
						System.exit(0);
					}
				}
				qLen--;
			}
			time++;
		}
		// 방문할 수 있는 곳을 모두 방문했거나 10번 움직여도 하나의 동전만 떨어뜨릴 수 없는 경우
		System.out.println(-1);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
