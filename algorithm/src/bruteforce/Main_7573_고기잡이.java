package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7573_고기잡이 {
	static int N, l, M;
	static int[][] points;
	static int max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 물고기 좌표
		points = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			points[i][0] = Integer.parseInt(st.nextToken());
			points[i][1] = Integer.parseInt(st.nextToken());
		}
		// 잡을 수 있는 물고기 수의 최대값
		max = 0;
		// 두 마리의 물고기를 골라 각 물고기가 이웃한 모서리에 위치할 때 잡을 수 있는 물고기 수를 센다.
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				int[] f1 = points[i];
				int[] f2 = points[j];
				// 그물 모양을 잡는다.
				for (int r = 1; r < l/2; r++) {
					int c = l/2 - r;
					count(f1[0], f2[1], r, c);
				}
			}
		}
		System.out.println(max);
	}
	// (x, y)에서 (r, c) 크기로 그물을 설치했을 때 잡을 수 있는 물고기 수를 센다. 
	private static void count(int x, int y, int r, int c) {
		int nr = x + r;
		int nc = y + c;
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			int[] point = points[i];
			if (point[0] >= Math.min(nr, x) && point[0] <= Math.max(nr, x)
					&& point[1] >= Math.min(nc, y) && point[1] <= Math.max(nc, y)) {
				cnt++;
			}
		}
		max = Math.max(max, cnt);
	}
}
