package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_18428_감시_피하기 {
	// 선생님의 위치를 나타내는 클래스
	static class Location {
		int r, c;
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N;
	static int[][] map;
	static ArrayList<Location> teachers;
	static int[] p;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 선생님, 학생, 현재 설치한 장애물의 위치를 나타내는 배열
		map = new int[N][N];
		// 선생님들의 좌표를 기록하는 ArrayList
		teachers = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				char c = st.nextToken().charAt(0);
				// 선생님은 map에 -2, 학생은 -1, 장애물은 1로 표시
				if (c == 'T') {
					teachers.add(new Location(i, j));
					map[i][j] = -2;
				} else if (c == 'S') {
					map[i][j] = -1;
				}
			}
		}
		// 장애물 위치를 고르기 위해 조합을 사용
		p = new int[3];
		boolean res = combi(0, 0);
		System.out.println(res ? "YES" : "NO");
	}
	// 조합을 이용해 장애물 위치를 고르는 함수
	private static boolean combi(int cnt, int start) {
		// 장애물을 3개 설치했을 때 선생님들이 학생을 볼 수 없는지 확인
		if (cnt == 3) {
			if (checkAllBlocked()) return true;
			else return false;
		}
		for (int i = start; i < N*N; i++) {
			int r = i/N;
			int c = i%N;
			// 선생님이나 학생이 있는 경우에는 장애물을 설치할 수 없다.
			if (map[r][c] != 0) continue;
			map[r][c] = 1;
			if (combi(cnt+1, i+1)) return true;
			map[r][c] = 0;
		}
		return false;
	}
	// 선생님들이 학생을 볼 수 없는지 확인하는 함수
	private static boolean checkAllBlocked() {
		for (Location teacher : teachers) {
			// 상하좌우로 학생을 볼 수 있는지 확인
			for (int d = 0; d < 4; d++) {
				int r = teacher.r;
				int c = teacher.c;
				// 경계를 벗어나거나 장애물을 만날 때까지 반복
				while (check(r, c) && map[r][c] != 1) {
					// 학생을 볼 수 있는 경우
					if (map[r][c] == -1) return false;
					r += dr[d];
					c += dc[d];
				}
			}
		}
		return true;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
