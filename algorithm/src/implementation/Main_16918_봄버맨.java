package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16918_봄버맨 {
	static int R, C, N;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		// 격자판을 int형 배열로 바꾼다.
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				// 폭탄을 표시할 때 막 설치된 폭탄은 1, 그 이후로 1초마다 값이 1씩 증가하도록 설정
				// 따라서 값이 4가 되면 해당 폭탄은 설치한지 3초가 지났다는 의미이므로 폭파
				// 여기서는 이미 1초가 흘렀다고 가정하고 초기화
				if (s.charAt(j) == 'O') map[i][j] = 2;
			}
		}
		// 지금까지 경과한 시간
		// 처음이나 1초 후나 지도에서 달라지는 것은 없으므로 1초 후부터 시작한다.
		int t = 1;
		// 시뮬레이션
		while (t < N) {
			t++; // 시간 경과
			time(); // 시간이 지남에 따라 폭탄이 없는 지역에 폭탄을 설치하고, 폭탄은 시간이 흐르도록 한다.
			bomb(); // 설치한지 3초가 지난 폭탄을 터뜨린다.
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// map에서 값이 0이면 빈 칸, 그렇지 않으면 폭탄이 있다는 의미
				if (map[i][j] == 0) sb.append(".");
				else sb.append("O");
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	// 시간 경과를 위해 map의 모든 원소의 값을 1 증가시킨다.
	// 이렇게 하면 폭탄이 설치되지 않은 칸의 경우 0 -> 1이 되어 폭탄이 설치되고,
	// 폭탄이 설치된 칸의 경우 값을 1 증가해 폭탄이 설치된 후 얼마나 시간이 지났는지를 알 수 있다.
	private static void time() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j]++;
			}
		}
	}
	// 설치된지 3초가 지난 폭탄을 터뜨린다.
	private static void bomb() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 연쇄 작용은 없으므로 설치된지 3초가 지난 폭탄의 인접 칸을 빈 칸으로 만들되,
				// 동시에 터질 폭탄은 그대로 둬서 나중에 해당 폭탄과 인접한 칸도 빈 칸으로 만들 수 있게 해야 함에 유의
				if (map[i][j] == 4) {
					map[i][j] = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (0<=nr && nr<R && 0<=nc && nc<C && map[nr][nc] != 4) {
							map[nr][nc] = 0;
						}
					}
				}
			}
		}
		
	}
}
