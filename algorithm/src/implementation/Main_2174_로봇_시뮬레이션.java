package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2174_로봇_시뮬레이션 {
	// 로봇을 나타내는 클래스
	static class Robot {
		// 로봇의 번호, x좌표, y좌표, 방향을 나타낸다.
		int idx, x, y, d;
		public Robot(int idx, int x, int y, int d) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.d = d;
		}
		// 왼쪽으로 count번만큼 회전하는 함수
		public void rotateLeft(int count) {
			this.d = (this.d+3*count)%4;
		}
		// 오른쪽으로 count번만큼 회전하는 함수
		public void rotateRight(int count) {
			this.d = (this.d+count)%4;
		}
		// d방향으로 전진하는 함수
		public void go() {
			this.x = this.x+dc[d];
			this.y = this.y+dr[d];
		}
	}
	static int A, B;
	static Robot[][] map;
	static Robot[] robots;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// x, y좌표는 기존의 r, c와 다름에 주의
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		// 로봇을 지도에서 찾기 위한 배열
		map = new Robot[B][A];
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 로봇을 인덱스를 통해 빠르게 찾기 위한 배열
		robots = new Robot[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = B-Integer.parseInt(st.nextToken());
			int d = changeCharToDir(st.nextToken().charAt(0));
			robots[i] = new Robot(i, x, y, d);
			map[y][x] = robots[i];
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);
			int count = Integer.parseInt(st.nextToken());
			// 명령이 왼쪽으로 회전인 경우
			if (command == 'L') {
				robots[n].rotateLeft(count);
			// 명령이 오른쪽으로 회전인 경우
			} else if (command == 'R') {
				robots[n].rotateRight(count);
			// 명령이 한 칸 전진인 경우
			} else {
				// 인덱스를 통해 움직이고자 하는 로봇을 찾는다.
				Robot robot = robots[n];
				// count번만큼 움직인다.
				while (count > 0) {
					// 기존의 자리에 있는 로봇을 제거
					map[robot.y][robot.x] = null;
					robot.go(); // 전진
					// 전진했을 때 로봇이 영역 밖을 벗어나는 경우
					if (!check(robot.x, robot.y)) {
						System.out.println("Robot " + n + " crashes into the wall");
						System.exit(0);
					}
					// 전진했을 때 다른 로봇과 충돌하는 경우
					if (map[robot.y][robot.x] != null) {
						System.out.println("Robot " + n + " crashes into robot " + map[robot.y][robot.x].idx);
						System.exit(0);
					}
					// 전진한 결과를 map에 기록
					map[robot.y][robot.x] = robot;
					count--;
				}
			}
		}
		// 위 과정을 모두 수행한 후 이상이 없는 경우
		System.out.println("OK");
	}
	// 문자로 주어진 방향을 dr, dc에 맞게 정수로 변형
	private static int changeCharToDir(char c) {
		if (c == 'N') return 0;
		else if (c == 'E') return 1;
		else if (c == 'S') return 2;
		else if (c == 'W') return 3;
		return 0;
	}
	// 경계 확인용 함수
	private static boolean check(int x, int y) {
		return 0<=x && x<A && 0<=y && y<B;
	}
}
