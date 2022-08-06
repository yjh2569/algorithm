package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16985_Maaaaaaaaaze {
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static int[] dr = {-1, 0, 1, 0, 0, 0};
	static int[] dc = {0, 1, 0, -1, 0, 0};
	static int[][][] map;
	static int[][][] resultMap;
	static int[][][][] rotatedMap;
	static int min;
	static int[] p;
	static boolean[] v;
	static int[] p2;
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 처음에 주어지는 미로 판들
		map = new int[5][5][5];
		// 미로 판들을 이용해 만든 미로를 저장하는 배열
		resultMap = new int[5][5][5];
		StringTokenizer st;
		for (int h = 0; h < 5; h++) {
			for (int i = 0; i < 5; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 5; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		// 회전한 미로판들을 미리 구해 저장한다.
		// h번째 미로 판을 회전한 4개의 결과를 rotatedMap[0], rotatedMap[1], rotatedMap[2], rotatedMap[3]에 저장
		rotatedMap = new int[5][4][5][5];
		for (int h = 0; h < 5; h++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					rotatedMap[h][0][i][j] = map[h][i][j];
				}
			}
			// 회전
			for (int d = 1; d < 4; d++) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						rotatedMap[h][d][i][j] = rotatedMap[h][d-1][j][4-i];
					}
				}
			}
		}
		p = new int[5];
		v = new boolean[5];
		p2 = new int[5];
		// 이동 횟수의 최소값
		min = Integer.MAX_VALUE;
		// BFS에 사용할 방문 배열
		visited = new boolean[5][5][5];
		perm(0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}
	// 미로의 각 높이에 어떤 미로 판을 넣을지 순열을 통해 결정
	private static void perm(int cnt) {
		// 어떤 미로 판을 넣을지 다 결정했으면 각 미로 판을 얼마나 회전시킬지를 결정한다.
		if (cnt == 5) {
			perm2(0);
			return;
		}
		for (int i = 0; i < 5; i++) {
			if (v[i]) continue;
			v[i] = true;
			p[cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}
	}
	// 각 미로 판을 얼마나 회전시킬지를 중복순열을 통해 결정한다.
	private static void perm2(int cnt) {
		// 각 미로 판을 얼마나 회전시킬지 결정하면 resultMap에 해당 결과를 기록한 뒤 BFS를 수행한다.
		if (cnt == 5) {
			for (int h = 0; h < 5; h++) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						resultMap[h][i][j] = rotatedMap[p[h]][p2[h]][i][j];
					}
				}
			}
			bfs();
			return;
		}
		for (int i = 0; i < 4; i++) {
			p2[cnt] = i;
			perm2(cnt+1);
		}	
	}
	// (0, 0, 0)에서 (4, 4, 4)까지 이동하기 위한 최소 거리를 구한다.
	// 모든 판을 회전시키기 때문에 다른 모서리에서 출발하는 경우도 모두 포함할 수 있다.
	private static void bfs() {
		// 모서리에서 출발이 불가능한 경우
		if (resultMap[0][0][0] == 0) return;
		Queue<int[]> q = new LinkedList<>();
		for (int h = 0; h < 5; h++) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					visited[h][i][j] = false;
				}
			}
		}
		q.offer(new int[] {0, 0, 0});
		visited[0][0][0] = true;
		int dist = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 6; d++) {
					int nh = u[0] + dh[d];
					int nr = u[1] + dr[d];
					int nc = u[2] + dc[d];
					if (check(nh, nr, nc) && !visited[nh][nr][nc] && resultMap[nh][nr][nc] == 1) {
						if (nh == 4 && nr == 4 && nc == 4) {
							min = Math.min(min, dist);
							return;
						}
						visited[nh][nr][nc] = true;
						q.offer(new int[] {nh, nr, nc});
					}
				}
				qLen--;
			}
			dist++;
		}
	}
	// 인접한 지점이 미로를 벗어나는지 확인
	private static boolean check(int h, int r, int c) {
		return 0<=h && h<5 && 0<=r && r<5 && 0<=c && c<5;
	}

}
