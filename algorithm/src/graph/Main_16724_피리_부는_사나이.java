package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16724_피리_부는_사나이 {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 지도
		map = new int[N][M];
		// 각 지역을 방문했는지를 나타내는 방문 배열
		// 값이 1인 경우는 현재 지도 순회 중 방문했던 지역을, 2인 경우는 이전에 방문횄던 지역을 의마한다.
		visited = new int[N][M];
		// 방향을 나타내는 문자를 숫자로 바꿔서 표현
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				if (c == 'R') {
					map[i][j] = 1;
				} else if (c == 'D') {
					map[i][j] = 2;
				} else if (c == 'L') {
					map[i][j] = 3;
				}
			}
		}
		// SAFE ZONE의 최소 개수
		int cnt = 0;
		// 각 지역에 대해 순회
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// BFS를 통해 SAFE ZONE에 도달 불가능한 지역인지 판단하고 
				// 도달 불가능한 지역인 경우 BFS를 통해 지나온 경로 중 마지막으로 도달한 지점에 SAFE ZONE을 설치
				// 그렇게 하면 BFS를 통해 방문한 모든 지역에서 해당 SAFE ZONE까지 갈 수 있다.
				if (check(i, j)) cnt++;
			}
		}
		System.out.println(cnt);
	}
	// BFS를 통해 (i, j)에서 지도의 경로를 따라 마지막으로 도달할 수 있는 지점을 찾고 이에 SAFE ZONE 지정을 해주는 함수
	private static boolean check(int i, int j) {
		// 이미 이전 방문을 통해 SAFE ZONE에 도달할 수 있는 경우
		if (visited[i][j] == 2) return false;
		// BFS 수행
		Queue<int[]> q = new LinkedList<>();
		// 현재 BFS 수행 중 방문한 지역들을 저장
		Queue<int[]> temp = new LinkedList<>();
		q.offer(new int[] {i, j});
		temp.offer(new int[] {i, j});
		visited[i][j] = 1;
		// SAFE ZONE에 도달할 수 없는지를 나타내는 변수
		boolean result = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			int d = map[u[0]][u[1]];
			int nr = u[0] + dr[d];
			int nc = u[1] + dc[d];
			// SAFE ZONE에 도달할 수 있는 경우
			if (visited[nr][nc] == 2) {
				result = false;
				break;
			}
			if (visited[nr][nc] == 0) {
				visited[nr][nc] = 1;
				q.offer(new int[] {nr, nc});
				temp.offer(new int[] {nr, nc});
			}
		}
		// 다음 step을 위해 방문 처리를 해준다.
		while (!temp.isEmpty()) {
			int[] u = temp.poll();
			visited[u[0]][u[1]] = 2;
		}
		return result;
	}

}
