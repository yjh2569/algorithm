package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3709_레이저빔은_어디로 {
	static int N;
	// 레이저의 현재 위치, 방향을 나타내는 클래스
	static class Laser {
		int x, y, d;
		public Laser(int x, int y) {
			this.x = x;
			this.y = y;
			// 초기 위치에 따른 레이저 방향을 정한다.
			if (x == 0) d = 1;
			else if (x == N+1) d = 3;
			else if (y == 0) d = 2;
			else if (y == N+1) d = 0;
		}
		// right-turner를 만났을 때 방향을 바꾸는 함수
		public void turnRight() {
			this.d = (this.d+1)%4;
		}
		// 레이저가 board 밖으로 나갔는지를 확인하는 함수
		public boolean isArrived() {
			return this.x == 0 || this.x == N+1 || this.y == 0 || this.y == N+1;
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			// board 각 지점에 right-turner가 존재하는지를 나타내는 배열
			boolean[][] RightTurnerExist = new boolean[N+2][N+2];
			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				// 배열의 r, c와 좌표평면의 x, y가 다르다는 점에 유의해서 배열에 넣어야 한다.
				int y = N+1-Integer.parseInt(st.nextToken());
				RightTurnerExist[y][x] = true;
			}
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = N+1-Integer.parseInt(st.nextToken());
			Laser laser = new Laser(x, y); // 현재 레이저의 상태
			// 레이저가 갇히는 경우를 고려하기 위해 방문 배열을 만든다.
			boolean[][][] visited = new boolean[N+2][N+2][4];
			visited[laser.y][laser.x][laser.d] = true;
			while (true) {
				// 레이저 이동
				laser.x += dc[laser.d];
				laser.y += dr[laser.d];
				// 이미 방문한 지점이고 방향도 동일한 경우 레이저가 갇힌 상황이다.
				if (visited[laser.y][laser.x][laser.d]) break;
				visited[laser.y][laser.x][laser.d] = true;
				// right-turner가 존재하면 방향을 바꾼다.
				if (RightTurnerExist[laser.y][laser.x]) laser.turnRight();
				// 레이저가 board 밖으로 나가면 끝낸다.
				if (laser.isArrived()) break;
			}
			// 레이저가 board 밖으로 나간 경우 레이저가 도착한 위치를 출력한다.
			if (laser.isArrived()) {
				sb.append(laser.x).append(" ").append(N+1-laser.y);
			// 그렇지 않으면 0 0을 출력한다.
			} else {
				sb.append(0).append(" ").append(0);
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
}
