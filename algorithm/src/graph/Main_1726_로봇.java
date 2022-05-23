package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1726_로봇 {
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	// 로봇의 위치와 방향을 나타내기 위해 클래스를 만듦
	static class Robot {
		int r, c, d;
		Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	// 보다 쉽게 문제를 해결하기 위해 방향을 dr과 dc에 맞게 수정
	static int[] dirChange = {-1, 1, 3, 0, 2};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] start = new int[3];
		int[] end = new int[3];
		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken())-1;
		start[1] = Integer.parseInt(st.nextToken())-1;
		start[2] = dirChange[Integer.parseInt(st.nextToken())];
		st = new StringTokenizer(br.readLine());
		end[0] = Integer.parseInt(st.nextToken())-1;
		end[1] = Integer.parseInt(st.nextToken())-1;
		end[2] = dirChange[Integer.parseInt(st.nextToken())];
		// BFS를 수행하되 로봇의 방향도 고려해야 한다.
		Queue<Robot> q = new LinkedList<>();
		boolean[][][] visited = new boolean[M][N][4];
		q.offer(new Robot(start[0], start[1], start[2]));
		visited[start[0]][start[1]][start[2]] = true;
		// 명령 횟수의 최솟값
		int comm = 0;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				Robot u = q.poll();
				if (u.r == end[0] && u.c == end[1] && u.d == end[2]) {
					System.out.println(comm);
					System.exit(0);
				}
				// 1칸부터 3칸까지 이동하는 경우를 고려하되, 중간에 막히는 경우에는 이동이 불가능하므로
				// 1칸부터 차례대로 시도하면서 막히는 경우 바로 for문을 break한다.
				for (int k = 1; k <= 3; k++) {
					int nr = u.r + k*dr[u.d];
					int nc = u.c + k*dc[u.d];
					if (0<=nr && nr<M && 0<=nc && nc<N && !visited[nr][nc][u.d]) {
						if (map[nr][nc] == 1) break;
						visited[nr][nc][u.d] = true;
						q.offer(new Robot(nr, nc, u.d));
					}
				}
				// 시계 방향으로 회전
				if (!visited[u.r][u.c][(u.d+1)%4]) {
					visited[u.r][u.c][(u.d+1)%4] = true;
					q.offer(new Robot(u.r, u.c, (u.d+1)%4));
				}
				// 반시계 방향으로 회전
				if (!visited[u.r][u.c][(u.d+3)%4]) {
					visited[u.r][u.c][(u.d+3)%4] = true;
					q.offer(new Robot(u.r, u.c, (u.d+3)%4));
				}
				qLen--;
			}
			comm++;
		}
	}

}
