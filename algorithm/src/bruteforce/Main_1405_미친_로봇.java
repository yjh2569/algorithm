package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1405_미친_로봇 {
	static int N;
	// 동서남북으로 이동할 확률
	static double[] pos;
	// 방문 배열
	static boolean[][] visited;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	// 이동 경로가 단순할 확률
	static double res;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		pos = new double[4];
		for (int i = 0; i < 4; i++) {
			pos[i] = 0.01*Integer.parseInt(st.nextToken());
		}
		// N이 14 이하의 자연수이므로 (14, 14)에서 출발하면 29*29 크기의 배열에서 절대 벗어날 수 없다.
		visited = new boolean[29][29];
		visited[14][14] = true;
		// 모든 경로를 고려해 로봇의 이동 경로가 단순할 확률을 구한다.
		perm(0, 1, 14, 14);
		System.out.printf("%.10f", res);
	}
	// 중복순열을 이용해 로봇이 갈 수 있는 모든 경로를 고려하고, 그 경로로 갈 확률을 계산해 모두 더한다.
	private static void perm(int cnt, double p, int r, int c) {
		// 로봇의 이동 경로가 단순한 상태로 N번 이동한 경우
		if (cnt == N) {
			res += p;
			return;
		}
		// 4방으로 이동 경로가 단순하도록 유지하면서 이동
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (0<=nr && nr<29 && 0<=nc && nc<29 && !visited[nr][nc]) {
				visited[nr][nc] = true;
				perm(cnt+1, p*pos[d], nr, nc);
				// 백트래킹에 유의
				visited[nr][nc] = false;
			}
		}
		
	}

}
