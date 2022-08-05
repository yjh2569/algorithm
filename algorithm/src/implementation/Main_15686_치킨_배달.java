package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15686_치킨_배달 {
	static int N, M, chickenCnt;
	static int[][] map;
	static int[][] chickens;
	static int[] selected;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		chickenCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) chickenCnt++;
			}
		}
		chickens = new int[chickenCnt][2];
		int cur = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) chickens[cur++] = new int[] {i, j};
			}
		}
		selected = new int[M];
		combi(0, 0);
		System.out.println(min);
		
	}
	private static void combi(int cnt, int start) {
		if (cnt == M) {
			int chicken_dist = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						int minDist = Integer.MAX_VALUE;
						for (int s : selected) {
							int[] chicken = chickens[s];
							minDist = Math.min(minDist, Math.abs(i - chicken[0]) + Math.abs(j - chicken[1]));
						}
						chicken_dist += minDist;
					}
				}
			}
			min = Math.min(min, chicken_dist);
			return;
		}
		for (int i = start; i < chickenCnt; i++) {
			selected[cnt] = i;
			combi(cnt+1, i+1);
		}
	}
}
