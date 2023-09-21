package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16924_십자가_찾기 {
	// 십자가를 표현하는 클래스
	static class Cross {
		int x, y, s;
		public Cross(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static ArrayList<Cross> crosses;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		crosses = new ArrayList<>(); // 격자판 내 십자가들을 저장하는 ArrayList
		visited = new boolean[N][M]; // 방문 배열
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 해당 지점에 *가 있으면 그 지점을 중심으로 하는 십자가가 있는지 찾는다.
				if (map[i][j] == '*') findCross(i, j); 
			}
		}
		// 십자가를 모두 찾았는데도 불구하고 방문하지 않은 * 지점이 있으면 십자가만으로 격자판을 만들 수 없다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '*' && !visited[i][j]) {
					System.out.println(-1);
					return;
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(crosses.size()).append("\n");
		for (Cross cross : crosses) {
			sb.append(cross.x+1).append(" ").append(cross.y+1).append(" ").append(cross.s).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// (r, c)를 중심으로 하는 십자가를 찾는 함수
	private static void findCross(int r, int c) {
		int maxR = 0; // (r, c)를 중심으로 하는 십자가 중 크기가 가장 큰 십자가
		for (int R = 1; R <= Math.min(N, M); R++) {
			// 십자가를 만들 수 없는 경우
			if (!checkCanBeCross(r, c, R)) break;
			visitCross(r, c, R); // 찾은 십자가에 대해 방문 처리
			maxR++;
		}
		// 십자가가 만들어진 경우에만 crosses에 십자가를 넣는다.
		if (maxR > 0) crosses.add(new Cross(r, c, maxR));
	}
	// (r, c)를 중심으로 하고, 크기가 R인 십자가가 격자판에 있는지 확인하는 함수
	private static boolean checkCanBeCross(int r, int c, int R) {
		for (int l = 1; l <= R; l++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d]*l;
				int nc = c + dc[d]*l;
				if (!check(nr, nc)) return false;
			}
		}
		return true;
	}
	// (r, c)를 중심으로 하고, 크기가 R인 십자가를 방문 처리하는 함수
	private static void visitCross(int r, int c, int R) {
		for (int l = 0; l <= R; l++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d]*l;
				int nc = c + dc[d]*l;
				visited[nr][nc] = true;
			}
		}
	}
	// (r, c)가 경계 내에 있고, 격자판에서 *인지 확인하는 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M && map[r][c] == '*';
	}
}
