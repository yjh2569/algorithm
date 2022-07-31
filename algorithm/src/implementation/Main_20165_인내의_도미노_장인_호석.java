package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20165_인내의_도미노_장인_호석 {
	static int N, M, R;
	static int[][] map;
	static boolean[][] states;
	static int score;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		states = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Map<Character, Integer> direction = new HashMap<>();
		direction.put('N', 0); direction.put('E', 1); direction.put('S', 2); direction.put('W', 3);
		int r, c, d;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			d = direction.get(st.nextToken().charAt(0));
			attack(r, c, d);
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			states[r][c] = false;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(score).append("\n");		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (states[i][j]) sb.append("F ");
				else sb.append("S ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	private static void attack(int r, int c, int d) {
		int nr = r;
		int nc = c;
		int l = map[r][c];
		while (0<=nr && nr<N && 0<=nc && nc<M && --l >= 0) {
			if (states[nr][nc]) {
				nr += dr[d];
				nc += dc[d];
				continue;
			}
			l = Math.max(map[nr][nc]-1, l);
			states[nr][nc] = true;
			score++;
			nr += dr[d];
			nc += dc[d];
		}
		
	}

}
