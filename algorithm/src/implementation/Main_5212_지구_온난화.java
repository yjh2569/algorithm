package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_5212_지구_온난화 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R+2][C+2]; // 주변이 바다임을 고려
		for (int i = 0; i <= R+1; i++) {
			Arrays.fill(map[i], '.');
		}
		for (int i = 1; i <= R; i++) {
			String s = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = s.charAt(j-1);
			}
		}
		char[][] newMap = new char[R+2][C+2]; // 50년 후 지도
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				// 현재 지역이 바다인 경우
				if (map[i][j] == '.') {
					newMap[i][j] = '.';
					continue;
				}
				// 현재 지역이 땅인 경우
				// 인접한 지역 중 3곳 이상이 바다인지 확인해 그렇다면 바다로, 그렇지 않으면 땅으로 표시
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					if (map[nr][nc] == '.') cnt++;
				}
				if (cnt >= 3) newMap[i][j] = '.';
				else newMap[i][j] = 'X';
			}
		}
		// 모서리부터 시작해, 모든 행 또는 열이 바다인 부분을 제외한다.
		int startR = 0, endR = 0, startC = 0, endC = 0;
		outer: for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (newMap[i][j] == 'X') {
					startR = i;
					break outer;
				}
			}
		}
		outer: for (int i = R; i > 0; i--) {
			for (int j = 1; j <= C; j++) {
				if (newMap[i][j] == 'X') {
					endR = i;
					break outer;
				}
			}
		}
		outer: for (int j = 1; j <= C; j++) {
			for (int i = 1; i <= R; i++) {
				if (newMap[i][j] == 'X') {
					startC = j;
					break outer;
				}
			}
		}
		outer: for (int j = C; j > 0; j--) {
			for (int i = 1; i <= R; i++) {
				if (newMap[i][j] == 'X') {
					endC = j;
					break outer;
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = startR; i <= endR; i++) {
			for (int j = startC; j <= endC; j++) {
				sb.append(newMap[i][j]);
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
