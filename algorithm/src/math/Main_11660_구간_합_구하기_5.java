package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_구간_합_구하기_5 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 누적합을 활용한다.
		// map[i][j]가 map[1][1]부터 map[i][j]의 누적합이 되도록 계산한다.
		for (int i = 1; i <= N; i++) {
			for (int j = 2; j <= N; j++) {
				map[i][j] += map[i][j-1];
			}
		}
		for (int j = 1; j <= N; j++) {
			for (int i = 2; i <= N; i++) {
				map[i][j] += map[i-1][j];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			// map[x1][y1]부터 map[x2][y2]까지의 누적합은 다음과 같은 식으로 계산한다.
			sb.append(map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
