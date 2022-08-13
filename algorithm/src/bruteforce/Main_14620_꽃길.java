package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14620_꽃길 {
	static int N, min, sum;
	static int[][] map;
	static boolean[][] visited;
	static int[] p;
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		// 꽃잎 혹은 꽃술이 있는 칸을 표시하기 위한 배열
		visited = new boolean[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 조합을 사용해 3개의 칸을 고른다.
		p = new int[3];
		// 꽃을 심는데 필요한 최소비용
		min = Integer.MAX_VALUE;
		combi(0, 0);
		System.out.println(min);
	}
	// 조합을 사용해 테두리가 아닌 곳의 3개의 칸을 고른다.
	private static void combi(int cnt, int start) {
		if (cnt == 3) {
			// 3개의 칸을 고른 뒤 꽃을 심는데 필요한 비용을 계산해본다.
			check();
			return;
		}
		// 테두리를 제외하면 (N-2)*(N-2) 크기의 배열에서 3개의 칸을 고르면 된다.
		for (int i = start; i < (N-2)*(N-2); i++) {
			p[cnt] = i;
			combi(cnt+1, i+1);
		}
	}
	// 위에서 선택한 3개의 칸에 꽃을 심어보고 모두 심을 수 있는 경우 비용의 최소값을 갱신한다.
	private static void check() {
		// 비용의 합
		sum = 0;
		// 방문 배열 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
		// 3개의 꽃을 심어본다.
		for (int i = 0; i < 3; i++) {
			int n = p[i];
			// 조합을 통해 선택한 3개의 수를 적절한 행과 열로 바꿔준다.
			int r = n/(N-2)+1, c = n%(N-2)+1;
			// 꽃술과 꽃잎이 다른 꽃에 닿는지 확인하고, 그렇지 않으면 비용을 더해준다.
			for (int d = 0; d < 5; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				// 해당 칸에 꽃술 혹은 꽃잎이 있는 경우 더 이상 비용을 계산할 필요가 없다.
				if (visited[nr][nc]) return;
				visited[nr][nc] = true;
				sum += map[nr][nc];
			}
		}
		// 꽃들이 전혀 겹치치 않으면 비용의 최소값을 갱신한다.
		min = Math.min(min, sum);
	}
}
