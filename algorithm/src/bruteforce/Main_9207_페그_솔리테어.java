package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main_9207_페그_솔리테어 {
	static int K;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int min, movMin;
	// 핀의 좌표를 포함하는 핀 클래스
	static class Pin {
		int r, c;
		public Pin(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static ArrayList<Pin> pins;
	static boolean[] isExist;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s;
		// 게임판 상태를 나타내는 배열
		map = new char[5][9];
		// 출력 저장용
		StringBuilder sb = new StringBuilder();
		// 각 테스트케이스에 대해 수행
		for (int t = 1; t <= N; t++) {
			// 게임판에 남아있는 핀 개수의 최소값
			min = Integer.MAX_VALUE;
			// 핀 개수가 최소가 될 때 이동 횟수의 최소값
			movMin = Integer.MAX_VALUE;
			// 핀들을 저장하기 위한 ArrayList
			pins = new ArrayList<>();
			// 테스트케이스 구별을 위해 빈 줄을 건너뛴다.
			if (t > 1) s = br.readLine();
			for (int i = 0; i < 5; i++) {
				s = br.readLine();
				for (int j = 0; j < 9; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == 'o') pins.add(new Pin(i, j));
				}
			}
			// 핀 개수
			K = pins.size();
			// 각 핀이 현재 게임판에 존재하는지를 나타내는 배열
			isExist = new boolean[K];
			Arrays.fill(isExist, true);
			// DFS를 통해 핀을 움직이면서 핀 개수의 최소값과 최소 이동 횟수를 구한다.
			dfs(pins.size(), 0);
			// 출력
			sb.append(min).append(" ").append(movMin).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	private static void dfs(int cnt, int mov) {
		// base condition
		if (cnt < 0) return;
		// 만약 현재 핀의 개수가 핀의 개수의 최소값보다 작으면
		// 핀의 개수의 최소값과 최소 이동 횟수를 수정한다.
		if (min > cnt) {
			min = cnt;
			movMin = mov;
		// 만약 현재 핀의 개수가 핀의 개수의 최소값과 같다면
		// 최소 이동 횟수를 수정한다.
		} else if (min == cnt) {
			movMin = Math.min(movMin, mov);
		}
		// 각 핀에 대해 순회
		for (int i = 0; i < K; i++) {
			// 현재 존재하지 않는 핀이면 건너뛴다.
			if (!isExist[i]) continue;
			Pin pin = pins.get(i);
			int r = pin.r; int c = pin.c;
			// 현재 핀이 있어야 하는 칸에 핀이 없으면 건너뛴다.
			if (map[r][c] != 'o') continue;
			// 사방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 인접한 곳에 핀이 있는 경우만 고려한다.
				if (0<=nr && nr<5 && 0<=nc && nc<9 && map[nr][nc] == 'o') {
					int nr_2 = nr + dr[d];
					int nc_2 = nc + dc[d];
					// 인접한 곳을 뛰어넘어 구멍이 있는 칸으로 이동할 수 있는지 검사한다.
					if (0<=nr_2 && nr_2<5 && 0<=nc_2 && nc_2<9 && map[nr_2][nc_2] == '.') {
						// 제거할 핀의 pins에서의 인덱스
						int removeIdx = -1;
						for (int k = 0; k < K; k++) {
							if (pins.get(k).r == nr && pins.get(k).c == nc) {
								removeIdx = k;
								break;								
							}
						}
						// 현재 핀의 위치를 수정하고, 인접한 핀을 제거한다.
						pin.r = nr_2; pin.c = nc_2;
						isExist[removeIdx] = false;
						map[r][c] = '.';
						map[nr][nc] = '.';
						map[nr_2][nc_2] = 'o';
						// 재귀 호출해 다음 핀을 옮긴다.
						dfs(cnt-1, mov+1);
						// 백트래킹
						pin.r = r; pin.c = c;
						isExist[removeIdx] = true;
						map[pin.r][pin.c] = 'o';
						map[nr][nc] = 'o';
						map[nr_2][nc_2] = '.';
					}
				}
			}
		}
		
	}

}
