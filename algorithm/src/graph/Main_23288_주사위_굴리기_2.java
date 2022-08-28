package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23288_주사위_굴리기_2 {
	static int N, M, K, score, d;
	// 입력으로 주어지는 지도
	static int[][] map;
	// 지도의 각 칸에 대해 동서남북으로 연속해서 이동할 수 있는 칸의 개수
	static int[][] possibles;
	// possibles를 구하기 위해 필요한 방문 배열
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	// 주사위 클래스
	static class Dice {
		// 윗면(up), 아랫면(down), 앞면(front), 뒷면(back), 왼쪽면(left), 오른쪽면(right)에 적힌 수
		int u, d, f, b, l, r;
		// 현재 주사위의 지도 상 위치
		int loc_r, loc_c;
		// 초기화
		public Dice() {
			this.u = 1;
			this.d = 6;
			this.f = 5;
			this.b = 2;
			this.l = 4;
			this.r = 3;
			this.loc_r = this.loc_c = 0;
		}
		// 북쪽으로 이동하는 경우
		public void north() {
			loc_r += dr[0]; loc_c += dc[0];
			int temp = u;
			u = f; f = d; d = b; b = temp;			
		}
		// 남쪽으로 이동하는 경우
		public void south() {
			loc_r += dr[2]; loc_c += dc[2];
			int temp = u;
			u = b; b = d; d = f; f = temp;
		}
		// 동쪽으로 이동하는 경우
		public void east() {
			loc_r += dr[1]; loc_c += dc[1];
			int temp = u;
			u = l; l = d; d = r; r = temp;
		}
		// 서쪽으로 이동하는 경우
		public void west() {
			loc_r += dr[3]; loc_c += dc[3];
			int temp = u;
			u = r; r = d; d = l; l = temp;
		}
	}
	static Dice dice;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		possibles = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// possibles를 구하기 위해 각 칸에 BFS를 적용한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (possibles[i][j] == 0) bfs(i, j);
			}
		}
		// 주사위의 이동 방향
		// dr, dc에 맞춤(북, 동, 남, 서)
		d = 1;
		dice = new Dice();
		// K번 이동하면서 점수를 계산
		for (int k = 0; k < K; k++) {
			simulate();
		}
		System.out.println(score);
	}
	// BFS를 통해 동서남북으로 연속해서 이동할 수 있는 칸의 개수를 구한다.
	private static void bfs(int i, int j) {
		Queue<int[]> q = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = false;
			}
		}
		// 동서남북으로 연속해서 이동할 수 있는 칸의 개수
		int cnt = 1;
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				// (i, j)에 적힌 숫자와 (nr, nc)에 적힌 숫자가 같아야지만 연속해서 이동할 수 있는 칸에 반영된다.
				if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[i][j]) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					cnt++;
				}
			}
		}
		// BFS를 통해 구한 cnt를 possibles에 반영한다.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (visited[r][c]) possibles[r][c] = cnt;
			}
		}
	}
	// 주사위의 이동을 시뮬레이션한다.
	private static void simulate() {
		// 방향 d에 따라 한 번 이동해본다.
		int nr = dice.loc_r + dr[d];
		int nc = dice.loc_c + dc[d];
		// 지도를 벗어나면 반대 방향으로 이동
		if (!check(nr, nc)) {
			d = (d+2)%4;
		}
		// 주사위를 방향에 따라 움직인다.
		moveDice();
		// 점수 계산
		score += map[dice.loc_r][dice.loc_c] * possibles[dice.loc_r][dice.loc_c];
		// 주사위의 아랫면의 숫자가 주사위가 있는 칸의 숫자보다 더 큰 경우
		if (dice.d > map[dice.loc_r][dice.loc_c]) {
			d = (d+1)%4; // 시계 방향으로 90도 회전
		// 주사위의 아랫면의 숫자가 주사위가 있는 칸의 숫자보다 더 작은 경우
		} else if (dice.d < map[dice.loc_r][dice.loc_c]) {
			d = (d+3)%4; // 반시계 방향으로 90도 회전
		}
	}
	// 주사위를 방향에 따라 움직이는 함수
	// Dice 클래스에 정의한 메소드를 호출한다.
	private static void moveDice() {
		if (d == 0) dice.north();
		else if (d == 1) dice.east();
		else if (d == 2) dice.south();
		else if (d == 3) dice.west();		
	}
	// 지도를 벗어났는지 확인하는 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}
}
