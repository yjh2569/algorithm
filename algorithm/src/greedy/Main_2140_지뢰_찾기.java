package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2140_지뢰_찾기 {
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, count;
	static int[][] map;
	static int[][] mines;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 테두리에 있는 지뢰 개수
		count = 0;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if ('0'<=c && c<='9') {
					map[i][j] = c - '0';
				} else {
					map[i][j] = -1; // 정수 배열로 받기 위해 지뢰 부분은 -1로 표시
				}
			}
		}
		// 지뢰만 표시하는 배열
		// -1이면 모르는 지점, 0이면 지뢰가 없는 지점, 1이면 지뢰가 있는 지점
		mines = new int[N][N];
		// 지뢰 숫자를 알려주는 테두리 부분은 제외하고 모두 -1로 바꿔준다.
		for (int i = 1; i < N-1; i++) {
			Arrays.fill(mines[i], -1);
			mines[i][0] = 0; mines[i][N-1] = 0;
		}
		// 위쪽, 아래쪽, 왼쪽, 오른쪽 테두리의 각 숫자를 조사하여 근처에 지뢰가 있는지를 조사한다.
		for (int i = 0; i < N; i++) {
			dig(0, i);
			dig(N-1, i);
			dig(i, 0);
			dig(i, N-1);
		}
		// N이 5 이상일 경우 테두리 부분을 제외한 나머지 부분은 모두 지뢰로 채운다.
		int t = Math.max(N-4, 0);
		System.out.println(count + t*t);
	}
	// (r, c)에 적힌 숫자를 바탕으로 근처 지점에 지뢰가 있는지를 조사한다.
	private static void dig(int r, int c) {
		int num = map[r][c];
		int cnt = 0; // 근처에 있는 지뢰 수
		// (r, c)와 인접한 지점 중 지뢰가 있는지 모르는 지점
		// 끝부분부터 차례대로 조사하기 때문에 지뢰가 있는지 모르는 지점은 항상 한 개 이하다.
		int t_r = -1; int t_c = -1;
		// 8방에 대하여 조사
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (0<=nr && nr<N && 0<=nc && nc<N) {
				if (mines[nr][nc] == 1) cnt++;
				else if (mines[nr][nc] == -1) {
					t_r = nr; t_c = nc;
				}
			}
		}
		// 4개의 테두리에 대하여 동시에 조사하다보니 지뢰가 있는지 모르는 지점이 아예 없을 수도 있다.
		if (t_r == -1) return;
		// 조사 결과 지금까지 발견한 지뢰 개수와 map에 적혀있는 숫자가 같은 경우 지뢰를 추가로 설치하지 않아도 된다.
		if (num == cnt) mines[t_r][t_c] = 0;
		// 조사 결과 지금까지 발견한 지뢰 개수가 map에 적혀있는 숫자와 다른 경우 지뢰를 추가해야 한다.
		// 이 경우는 map에 적힌 숫자가 지금까지 발견한 지뢰 개수보다 1개 많은 경우와 같다.
		if (num != cnt) {
			mines[t_r][t_c] = 1;
			count++;
		}
	}

}
