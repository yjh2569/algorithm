package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17144_미세먼지_안녕 {
	static int R, C, T;
	static int[][] map;
	static int[] air_u; // 공기 청정기의 위쪽 부분
	static int[] air_d; // 공기 청정기의 아래쪽 부분
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		boolean check = false;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1 && !check) {
					check = true;
					air_u = new int[] {i, j};
					air_d = new int[] {i+1, j};
				}
			}
		}
		// 시뮬레이션을 크게 확산 부분과 공기 청정기의 바람 부분으로 나눈다.
		for (int t = 0; t < T; t++) {
			map = diffusion();
			cleaning(1); // 공기 청정기 위쪽에서 바람이 불 때
			cleaning(-1); // 공기 청정기 아래쪽에서 바람이 불 때
		}
		// 시뮬레이션이 끝난 후 남아 있는 먼지의 양을 측정한다.
		int res = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] >= 0) res += map[i][j];
			}
		}
		System.out.println(res);
	}
	// 공기 청정기의 바람이 불 때
	private static void cleaning(int k) {
		int i = 0; int j = 0;
		// 어느 쪽에서 바람이 부는지에 따라 시작점을 다르게 잡는다.
		if (k == 1) {
			i = air_u[0];
			j = air_u[1];
		} else {
			i = air_d[0];
			j = air_d[1];
		}
		int d = 0;
		int prev = 0;
		while (true) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			// 경계를 만나면 위쪽에서는 반시계 방향, 아래쪽에서는 시계 방향으로 바람 방향을 전환시킨다.
			if (nr<0 || nr>=R || nc<0 || nc>=C) {
				d = (d+4+k)%4;
			}
			i += dr[d];
			j += dc[d];
			// 공기 청정기를 만나면 while 문을 그만둔다.
			if (map[i][j] == -1) break;
			// swap을 통해 먼지를 이동시킨다.
			int temp = map[i][j];
			map[i][j] = prev;
			prev = temp;
		}
	}
	// 미세먼지가 확산되는 경우
	private static int[][] diffusion() {
		// 새로운 격자판을 만들어 확산된 결과를 기록한다.
		int[][] temp = new int[R][C];
		// 공기청정기 위치에서의 값은 -1로 고정
		temp[air_u[0]][air_u[1]] = -1;
		temp[air_d[0]][air_d[1]] = -1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 미세먼지가 있으면 확산시킨다.
				if (map[i][j] > 0) {
					int cnt = 0; // 확산 가능한 칸 수
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (0<=nr && nr<R && 0<=nc && nc<C && map[nr][nc] != -1) {
							temp[nr][nc] += map[i][j]/5;
							cnt += 1;
						}
					}
					temp[i][j] += map[i][j] - cnt * (map[i][j]/5); 
				}
			}
		}
		return temp;
	}
}
