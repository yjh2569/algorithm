package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15685_드래곤_커브 {
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static ArrayList<int[]> points;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		for (int p = 0; p < N; p++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			// 격자 위에 찍히는 점들을 모두 모아놓은 ArrayList
			points = new ArrayList<>();
			// 초기값
			points.add(new int[] {r, c});
			points.add(new int[] {r+dr[d], c+dc[d]});
			// 재귀적으로 커브를 만들어나간다.
			recur(r+dr[d], c+dc[d], 0, g);
		}
		// 격자 내 정사각형 중 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수
		int cnt = 0;
		// 격자를 조사하면서 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형을 찾는다.
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);

	}
	// (g)세대까지 존재하는, (cnt)세대의 드래곤 커브를 (r, c)을 끝점으로 해 만들어나가는 함수
	private static void recur(int r, int c, int cnt, int g) {
		// 드래곤 커브를 다 만든 경우
		// points에 모아둔 점들을 map에 찍는다.
		if (cnt == g) {
			for (int[] point : points) {
				map[point[0]][point[1]] = 1;
			}
			return;
		}
		// 현재까지 만든 드래곤 커브를 이용해 새로운 드래곤 커브를 만든다.
		int[][] temp_points = points.toArray(new int[points.size()][2]);
		// 마지막 점을 제외한 나머지 점들을 시계 방향으로 회전하여 드래곤 커브의 점들로 추가한다.
		for (int i = temp_points.length - 2; i >= 0; i--) {
			int[] temp_point = temp_points[i];
			int diff_r = temp_point[0] - r;
			int diff_c = temp_point[1] - c;
			points.add(new int[] {r+diff_c, c-diff_r});
		}
		// 위 과정을 재귀적으로 반복
		int[] end = points.get(points.size() - 1);
		recur(end[0], end[1], cnt+1, g);
	}

}
