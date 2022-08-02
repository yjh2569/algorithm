package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_4056_스_스_스도쿠 {
	static int[][] map;
	static int[][] blanks;
	static int[][] result;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// 출력 저장
		sb = new StringBuilder();
		String s;
		// 처음에 주어지는 스도쿠
		map = new int[9][9];
		// 스도쿠에 채워야 하는 자리의 위치
		blanks = new int[5][2];
		// 스도쿠가 완성된 경우 완성된 스도쿠를 저장
		result = new int[9][9];
		for (int t = 0; t < T; t++) {
			// blanks를 채울 때 필요한 변수
			int idx = 0;
			for (int i = 0; i < 9; i++) {
				s = br.readLine();
				for (int j = 0; j < 9; j++) {
					map[i][j] = s.charAt(j) - '0';
					// 빈 자리를 발견한 경우 blanks에 입력
					if (map[i][j] == 0) {
						blanks[idx][0] = i;
						blanks[idx][1] = j;
						idx++;
					}
				}
			}
			// 5개의 빈 자리에 숫자를 채웠을 때 스도쿠를 완성할 수 있으면 완성된 스도쿠를 출력한다.
			if (perm(0)) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						sb.append(result[i][j]);
					}
					sb.append("\n");
				}
				sb.append("\n");
				continue;
			}
			// 스도쿠를 완성하지 못하는 경우 아래 문구를 출력한다.
			sb.append("Could not complete this grid.").append("\n").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 순열을 통해 스도쿠의 각 빈 자리에 숫자를 채워넣는다.
	private static boolean perm(int cnt) {
		// 스도쿠 조건을 만족시키면서 5개의 빈 자리를 모두 채워넣는 경우
		if (cnt == 5) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					result[i][j] = map[i][j];
				}
			}
			return true;
		}
		// 재귀 과정 중 스도쿠를 완성시켰는지를 확인하는 변수
		boolean res = false;
		for (int i = 1; i <= 9; i++) {
			// 빈 자리에 i라는 숫자를 넣었을 때 스도쿠 조건을 만족하는지 조사한다.
			if (!check(blanks[cnt], i)) continue;
			// 스도쿠 조건을 만족하면 해당 숫자를 넣고 다음 자리로 이동
			map[blanks[cnt][0]][blanks[cnt][1]] = i;
			res = res | perm(cnt+1);
			map[blanks[cnt][0]][blanks[cnt][1]] = 0;
		}
		return res;
	}
	// 빈 자리 loc에 n이라는 숫자를 넣었을 때 스도쿠 조건을 만족하는지 조사한다.
	private static boolean check(int[] loc, int n) {
		int r = loc[0];
		int c = loc[1];
		// 현재 위치를 기준으로 가로, 세로, 3*3 정사각형 각각에 대해 1~9의 숫자가 몇 번 나타나는지 기록
		int[][] counts = new int[3][10];
		// n을 loc에 채워넣었다고 가정
		counts[0][n]++; counts[1][n]++; counts[2][n]++;
		for (int i = 0; i < 9; i++) {
			counts[0][map[r][i]]++; // 가로
			counts[1][map[i][c]]++; // 세로
		}
		// 3*3 정사각형
		for (int i = (r/3)*3; i < (r/3)*3+3; i++) {
			for (int j = (c/3)*3; j < (c/3)*3+3; j++) {
				counts[2][map[i][j]]++;
			}
		}
		// 각 경우에 대해 하나의 숫자라도 2번 이상 나오면 안 된다.
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= 9; j++) {
				if (counts[i][j] > 1) return false;
			}
		}
		return true;
	}

}
