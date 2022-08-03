package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20057_마법사_상어와_토네이도 {
	static int N;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	static int[][] map;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		// 모래밭에 있는 모든 모래의 양
		int t = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				t += map[i][j];
			}
		}
		// 출발 좌표
		int r = N/2;
		int c = N/2;
		// Y 지점과 Alpha 지점의 좌표
		int nr, nc, ar, ac;
		// 토네이도의 방향
		int d = 0;
		// 토네이도의 방향을 결정하기 위해 방문 배열을 이용한다.
		boolean[][] visited = new boolean[N][N];
		// 토네이도가 (0, 0)에 도달할 때까지 진행
		while (r != 0 || c != 0) {
			visited[r][c] = true;
			// Y 지점과 Alpha 지점의 좌표를 구한다.
			nr = r + dr[d];
			nc = c + dc[d];
			ar = r + dr[d]*2;
			ac = c + dc[d]*2;
			// Y 지점에 있는 모래의 양
			int res = map[nr][nc];
			// Alpha 지점으로 이동할 모래의 양
			int total = res;
			// 각 지점의 비율에 맞게 모래를 이동시킨다.
			total -= spread(r+dr[(d+1)%4], c+dc[(d+1)%4], res/100);
			total -= spread(r+dr[(d+3)%4], c+dc[(d+3)%4], res/100);
			total -= spread(nr+dr[(d+1)%4], nc+dc[(d+1)%4], res*7/100);
			total -= spread(nr+dr[(d+3)%4], nc+dc[(d+3)%4], res*7/100);
			total -= spread(nr+dr[(d+1)%4]*2, nc+dc[(d+1)%4]*2, res/50);
			total -= spread(nr+dr[(d+3)%4]*2, nc+dc[(d+3)%4]*2, res/50);
			total -= spread(ar+dr[(d+1)%4], ac+dc[(d+1)%4], res/10);
			total -= spread(ar+dr[(d+3)%4], ac+dc[(d+3)%4], res/10);
			total -= spread(r+dr[d]*3, c+dc[d]*3, res/20);
			// Alpha 지점에 나머지 모래를 이동시킨다.
			spread(ar, ac, total);
			// Y 지점은 빈 공간이 된다.
			map[nr][nc] = 0;
			// 다음 지점으로 토네이도 이동
			r += dr[d];
			c += dc[d];
			// 만약 다음 지점에서 Y 지점이 모래밭 바깥이거나, 현재 방향 기준으로 왼쪽 지점을 방문한 적이 없다면 회전한다.
			nr = r + dr[d];
			nc = c + dc[d];
			if (!check(nr, nc) || !visited[r+dr[(d+1)%4]][c+dc[(d+1)%4]]) d = (d+1)%4;
		}
		// 전체 모래의 양에서 모래밭에 남아있는 모래의 양을 빼면 모래밭 바깥으로 나간 모래의 양을 구할 수 있다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				t -= map[i][j];
			}
		}
		System.out.println(t);
	}
	// 모래를 (r, c) 지점으로 n만큼 이동시킨다.
	private static int spread(int r, int c, int n) {
		if (check(r, c)) {
			map[r][c] += n;
		}
		return n;
	}
	// 모래밭 안의 지점인지 확인
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
