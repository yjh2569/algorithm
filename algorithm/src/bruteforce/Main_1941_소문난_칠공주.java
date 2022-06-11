package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1941_소문난_칠공주 {
	static char[][] map;
	static int count; // 총 경우의 수
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		count = 0;
		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		combi(0, 0, new ArrayList<>());
		System.out.println(count);
	}
	// 조합을 이용해 25명 중 7명을 선택한 뒤 해당 경우가 조건에 부합하는지를 판단한다.
	private static void combi(int cnt, int start, ArrayList<Integer> arr) {
		if (cnt == 7) {
			// 선택한 7명이 조건을 만족하는지 확인한 뒤 경우의 수를 1 증가시킨다.
			if (check(arr)) count++;
			return;
		}
		for (int i = start; i < 25; i++) {
			arr.add(i);
			combi(cnt+1, i+1, arr);
			arr.remove(arr.size() - 1);
		}
	}
	// BFS를 이용해 7명이 인접해 있는지, 이다솜파 학생이 4명 이상인지 확인한다. 
	private static boolean check(ArrayList<Integer> arr) {
		// arr 내에 포함된 학생들의 위치를 나타내기 위한 배열
		boolean[][] group = new boolean[5][5];
		for (int i : arr) {
			group[i/5][i%5] = true;
		}
		// 인접해 있는 학생들의 수
		int cnt = 1;
		// 그룹 내 이다솜파 학생 수
		int s = 0;
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[5][5];
		// 초기값으로 첫 번째 학생을 넣는다.
		int i = arr.get(0);
		q.offer(new int[] {i/5, i%5});
		visited[i/5][i%5] = true;
		if (map[i/5][i%5] == 'S') s++;
		// BFS
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (0<=nr && nr<5 && 0<=nc && nc<5 && !visited[nr][nc] && group[nr][nc]) {
					visited[nr][nc] = true;
					cnt++;
					if (map[nr][nc] == 'S') s++;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		// 조건들을 모두 만족하는 경우 true 반환
		return cnt == 7 && s >= 4;
	}
	

}
