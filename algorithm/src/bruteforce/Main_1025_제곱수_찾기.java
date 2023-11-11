package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1025_제곱수_찾기 {
	static int N, M;
	static int[][] map;
	static int max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		max = -1; // 제곱수의 최댓값
		// 시작 위치 (r, c), 행의 변화량 dr, 열의 변화량 dc를 정하고 이를 바탕으로 수를 만든다.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				for (int dr = -r; dr <= N-r; dr++) {
					for (int dc = -c; dc <= M-c; dc++) {
						check(r, c, dr, dc); // r, c, dr, dc를 정했을 때 제곱수가 나오는지 확인한다.
					}
				}
			}
		}
		System.out.println(max);
	}
	// 시작 위치 (r, c), 행의 변화량 dr, 열의 변화량 dc에 대해 제곱수가 나올 수 있는지 확인하는 함수
	private static void check(int r, int c, int dr, int dc) {
		String s = "";
		int nr = r;
		int nc = c;
		// 수를 만든다.
		// dr = 0, dc = 0인 경우 첫번째 조건을 만족하지 못한다.
		while (((nr + dr) != r || (nc + dc) != c) && 0<=nr && nr<N && 0<=nc && nc<M) {
			// 새로운 수를 만들 때마다 제곱수인지 확인
			if (s.length() > 0) {
				int res = Integer.parseInt(s);
				int sqrt = (int)Math.sqrt(res);
				if (res == sqrt*sqrt) max = Math.max(max, res);
			}
			s += map[nr][nc]+"";
			nr += dr;
			nc += dc;
		}
		// 마지막으로 만든 수의 경우 제곱수인지 확인하지 않으므로 추가 확인 작업 필요
		if (s.length() == 0) return;
		int res = Integer.parseInt(s);
		int sqrt = (int)Math.sqrt(res);
		if (res == sqrt*sqrt) max = Math.max(max, res);
	}

}
