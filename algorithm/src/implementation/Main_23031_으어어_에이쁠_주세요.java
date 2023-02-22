package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_23031_으어어_에이쁠_주세요 {
	// 아리를 나타내는 클래스
	static class Ari {
		// 아리의 현재 위치와 이동 방향을 나타낸다.
		int r, c, d;
		public Ari(int r, int c) {
			this.r = r;
			this.c = c;
			this.d = 2;
		}
		// 이동 방향대로 이동
		public void move() {
			// 바로 이동하지 않고 경계에 막히는지 확인한다.
			int nr = this.r + dr[this.d];
			int nc = this.c + dc[this.d];
			// 경계에 막히면 제자리에 계속 머문다.
			if (!check(nr, nc)) return;
			// 경계에 막히지 않으면 이동한다.
			this.r = nr;
			this.c = nc;
		}
		// 왼쪽으로 90도 회전
		public void turnLeft() {
			this.d = (this.d+3)%4;
		}
		// 오른쪽으로 90도 회전
		public void turnRight() {
			this.d = (this.d+1)%4;
		}
	}
	// 학생 좀비를 나타내는 클래스
	static class Zombie {
		// 좀비의 현재 위치와 이동 방향을 나타낸다.
		int r, c, d;
		public Zombie(int r, int c) {
			this.r = r;
			this.c = c;
			this.d = 2;
		}
		// 이동 방향대로 이동
		public void move() {
			int nr = this.r + dr[this.d];
			int nc = this.c + dc[this.d];
			// 경계에 막히면 반대 방향으로 돈다.
			if (!check(nr, nc)) {
				this.d = (this.d+2)%4;
				return;
			}
			this.r = nr;
			this.c = nc;
		}
	}
	static int N;
	static Ari ari;
	static int[][] floor; // 4층 내 좀비의 위치를 나타내는 배열
	static ArrayList<Zombie> zombies; // 좀비들을 저장하는 ArrayList
	static boolean[][] isSwitchOn; // 4층 내 각 지점들에 불이 켜져 있는지를 나타내는 배열
	static boolean[][] hasSwitch; // 4층 내 각 지점들에 스위치가 있는 지를 나타내는 배열
	static int[] dr = {-1, 0, 1, 0, -1, 1, 1, -1};
	static int[] dc = {0, 1, 0, -1, 1, 1, -1, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String moves = br.readLine();
		floor = new int[N][N];
		isSwitchOn = new boolean[N][N];
		hasSwitch = new boolean[N][N];
		zombies = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if (c == 'Z' || c == 'z') {
					floor[i][j] = 1;
					zombies.add(new Zombie(i, j));
				} else if (c == 'S' || c == 's') {
					hasSwitch[i][j] = true;
				}
			}
		}
		ari = new Ari(0, 0);
		// 현재 아리가 기절했는지를 나타내는 변수
		boolean ariFaint = false;
		// 입력으로 주어지는 각 움직임에 대해 순회한다.
		for (char move : moves.toCharArray()) {
			ariMove(move); // 이동
			// 이동 후 기절했는지 확인
			if (checkIfAriFaint()) {
				ariFaint = true;
				break;
			}
			zombieMove(); // 좀비 이동
			// 좀비 이동 후 기절했는지 한 번 더 확인
			if (checkIfAriFaint()) {
				ariFaint = true;
				break;
			}
		}
		System.out.println(ariFaint ? "Aaaaaah!" : "Phew...");
	}
	// move에 따라 아리가 이동하는 함수
	// 이동한 직후 스위치를 켤 수 있으면 켠다.
	private static void ariMove(char move) {
		if (move == 'F') ari.move();
		else if (move == 'L') ari.turnLeft();
		else if (move == 'R') ari.turnRight();
		// 스위치를 켤 수 있다면 스위치를 켠다.
		if (hasSwitch[ari.r][ari.c]) turnOnSwitch(ari.r, ari.c);		
	}
	// 스위치를 켰을 때 현재 위치 근방의 불을 켜는 함수
	private static void turnOnSwitch(int r, int c) {
		isSwitchOn[r][c] = true;
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (check(nr, nc)) isSwitchOn[nr][nc] = true;
		}		
	}
	// 좀비들을 이동시키는 함수
	private static void zombieMove() {
		// 현재 좀비가 있는 칸을 비우고 좀비를 이동시킨다.
		for (Zombie z : zombies) {
			floor[z.r][z.c] = 0;
			z.move();
		}
		// 좀비가 한 칸에 여러 명이 겹치는 경우가 있을 수 있어 위 작업을 모두 마친 후 좀비가 있는 칸을 1로 표시
		for (Zombie z : zombies) {
			floor[z.r][z.c] = 1;
		}		
	}
	// 아리가 기절할 수 있는 상태인지를 구하는 함수
	private static boolean checkIfAriFaint() {
		return floor[ari.r][ari.c] == 1 && !isSwitchOn[ari.r][ari.c];
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
