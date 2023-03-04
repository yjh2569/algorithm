package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14712_넴모넴모 {
	static int N, M;
	static long answer;
	static boolean[][] filled;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = 0; // 총 가짓수
		// 격자판 내의 각 칸에 넴모가 올려져 있는지를 나타내는 배열
		filled = new boolean[N][M];
		// 격자판 내의 각 칸에 넴모를 올릴지 말지를 결정하고, 이 중 넴모들이 올라간 칸이 2*2 사각형을 이루지 않는 경우의 수를 구한다.
		subset(0);
		System.out.println(answer);
	}
	// 브루트포스 알고리즘을 통해 넴모들이 올라간 칸이 2*2 사각형을 이루지 않는 경우의 수를 구한다.
	// cnt는 현재 고려하고 있는 칸이 (r, c)일 때, r*c를 의미한다.
	private static void subset(int cnt) {
		// 모든 칸을 다 고려한 뒤, 넴모들이 올라간 칸이 2*2 사각형을 이루지 않는 경우
		if (cnt == N*M) {
			answer++;
			return;
		}
		// (r, c)에 넴모를 올리지 않는 경우
		subset(cnt+1);
		// (r, c)에 넴모를 올리는 경우
		int r = cnt/M;
		int c = cnt%M;
		// (r, c)에 넴모를 올리기 전에 해당 칸에 넴모를 올렸을 때 2*2 사각형이 생기는지를 확인한다.
		// 이때 고려하는 칸의 순서가 왼쪽 위에서 오른쪽 아래까지기 때문에
		// 현재 칸 기준 왼쪽 위 부분만 살펴보면 된다.
		if (check(r, c)) {
			filled[r][c] = true;
			subset(cnt+1);
			filled[r][c] = false; // 백트래킹을 통해 넴모를 올렸던 칸을 원래대로 되돌려놓는다.
		}
	}
	// (r, c)에 넴모를 올릴 수 있는지를 확인하는 함수
	private static boolean check(int r, int c) {
		// 왼쪽 변 또는 위쪽 변에 있는 경우 2*2 정사각형을 만들 수 없다.
		if (r == 0 || c == 0) return true;
		else if (filled[r-1][c-1] && filled[r-1][c] && filled[r][c-1]) return false;
		return true;
	}

}
