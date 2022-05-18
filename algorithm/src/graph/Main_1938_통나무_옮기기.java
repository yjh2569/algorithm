package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1938_통나무_옮기기 {
	// 그래프 탐색 이동 방향
	// 세로 방향 2개, 가로 방향 2개, 대각선 방향 순서대로 배치
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, 1, -1, 1, -1, 1, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		int[] start = new int[3]; // 통나무의 가운데 부분의 위치
		int[] end = new int[3]; // 목적지의 가운데 부분의 위치
		Arrays.fill(start, -1);
		Arrays.fill(end, -1);
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 통나무와 목적지의 가운데 부분의 위치를 파악한다.
		// 이때 start[2]와 end[2]는 통나무와 목적지가 어떤 방향으로 위치했는지를 나타낸다.
		// 0은 가로, 1은 세로를 의미한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B' && start[0] == -1) {
					if (i < N-1 && map[i+1][j] == 'B') {
						start[0] = i+1; start[1] = j; start[2] = 0;
					} else {
						start[0] = i; start[1] = j+1; start[2] = 1;
					}
				} else if (map[i][j] == 'E' && end[0] == -1) {
					if (i < N-1 && map[i+1][j] == 'E') {
						end[0] = i+1; end[1] = j; end[2] = 0;
					} else {
						end[0] = i; end[1] = j+1; end[2] = 1;
					}
				}
			}
		}
		// BFS를 수행하되, 통나무의 가운데 위치와 놓여진 방향을 가지고 탐색한다.
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][][] visited = new boolean[N][N][2]; // 통나무 위치뿐만 아니라 방향도 고려한다.
		q.offer(start);
		visited[start[0]][start[1]][start[2]] = true;
		// 최소 이동 횟수
		int cnt = 0;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				// 목적지에 도달한 경우
				if (u[0] == end[0] && u[1] == end[1] && u[2] == end[2]) {
					System.out.println(cnt);
					System.exit(0);
				}
				// 상하좌우로 이동할 때
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					// 통나무가 놓여진 방향과 이동 방향이 일치할 때
					// 가운데 위치가 아닌, 가운데 위치에서 이동 방향으로 1칸 앞에 있는 곳이 경계를 넘어가는지, 그리고 나무가 있는지를 확인해야 한다.
					if (d/2 == u[2] && 0<=nr+dr[d] && nr+dr[d]<N && 0<=nc+dc[d] && nc+dc[d]<N && !visited[nr][nc][u[2]] && map[nr+dr[d]][nc+dc[d]] != '1') {
						visited[nr][nc][u[2]] = true;
						q.offer(new int[] {nr, nc, u[2]});
					}
					// 통나무가 놓여진 방향과 이동 방향이 반대일 때
					// 통나무가 놓여진 방향에 따라 통나무의 양끝에 나무가 없는지를 추가로 확인한다.
					if (d/2 != u[2] && 0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc][u[2]] && map[nr][nc] != '1') {
						if ((u[2] == 0 && map[nr-1][nc] != '1' && map[nr+1][nc] != '1')
								|| (u[2] == 1 && map[nr][nc-1] != '1' && map[nr][nc+1] != '1')) {
							visited[nr][nc][u[2]] = true;
							q.offer(new int[] {nr, nc, u[2]});
						}
					}
				}
				// 통나무를 90도 회전시키는 경우
				if (0<u[0] && u[0]<N-1 && 0<u[1] && u[1]<N-1 && !visited[u[0]][u[1]][u[2]^1]) {
					// 우선 통나무의 가운데 위치를 기준으로 3*3 구역에 나무가 하나도 없는지를 확인한다.
					boolean check = true;
					for (int d = 0; d < 8; d++) {
						int nr = u[0] + dr[d];
						int nc = u[1] + dc[d];
						if (map[nr][nc] == '1') {
							check = false;
							break;
						}
					}
					// 나무가 없는 경우
					if (check) {
						visited[u[0]][u[1]][u[2]^1] = true;
						q.offer(new int[] {u[0], u[1], u[2]^1});
					}
				}
				qLen--;
			}
			cnt++;
		}
		// 통나무를 목적지에 도달시킬 수 없는 경우
		System.out.println(0);
	}

}
