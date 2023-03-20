package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1445_일요일_아침의_데이트 {
	// 현재 위치와 그 위치에 오기까지 지나간, 쓰레기가 있는 칸의 개수와 쓰레기 옆을 지나가는 칸의 개수를 나타내는 클래스
	static class Location implements Comparable<Location> {
		int r, c, trash, near;
		public Location(int r, int c, int trash, int near) {
			this.r = r;
			this.c = c;
			this.trash = trash;
			this.near = near;
		}
		public int compareTo(Location l) {
			return this.trash == l.trash ? Integer.compare(this.near, l.near) : Integer.compare(this.trash, l.trash);
		}
	}
	static int N, M;
	static int[] start, finish;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = new int[2]; // 출발지
		finish = new int[2]; // 목적지
		// 쓰레기가 있는 칸은 1, 쓰레기 근처에 있는 칸은 -1이라는 값을 가지는 배열
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				if (c == 'g') map[i][j] = 1;
				else if (c == 'S') start = new int[] {i, j};
				else if (c == 'F') finish = new int[] {i, j};
			}
		}
		// 쓰레기가 있는 칸을 전부 확인한 다음 쓰레기 인근에 있는 칸을 찾는다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (checkIfNearTrash(i, j)) map[i][j] = -1;
			}
		}
		// dijkstra's algorithm을 활용한다.
		int[][] dijk = new int[N][M]; // 해당 칸까지의 경로 중 지나온 쓰레기가 있는 칸의 개수의 최솟값을 저장하는 배열
		int[][] dijk2 = new int[N][M]; // 해당 칸까지의 경로 중 지나온 쓰레기 인근에 있는 칸의 개수의 최솟값을 저장하는 배열
		PriorityQueue<Location> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dijk[i], Integer.MAX_VALUE/10);
			Arrays.fill(dijk2[i], Integer.MAX_VALUE/10);
		}
		dijk[start[0]][start[1]] = 0;
		dijk2[start[0]][start[1]] = 0;
		pq.offer(new Location(start[0], start[1], 0, 0));
		while (!pq.isEmpty()) {
			Location l = pq.poll();
			if (visited[l.r][l.c]) continue;
			visited[l.r][l.c] = true;
			// 목적지에 도착한 경우
			if (l.r == finish[0] && l.c == finish[1]) break;
			for (int d = 0; d < 4; d++) {
				int nr = l.r + dr[d];
				int nc = l.c + dc[d];
				if (check(nr, nc)) {
					// 쓰레기가 있는 칸에 도착한 경우
					// 쓰레기가 있는 칸을 더 적게 통과하거나, 
					// 동일한 개수의 쓰레기가 있는 칸을 통과하면서 더 적은 개수의 쓰레기 인근에 있는 칸을 지나는 경우 갱신
					if (map[nr][nc] == 1 && (dijk[nr][nc] > dijk[l.r][l.c]+1 || (dijk[nr][nc] == dijk[l.r][l.c]+1 && dijk2[nr][nc] > dijk2[l.r][l.c]))) {
						dijk[nr][nc] = dijk[l.r][l.c]+1;
						dijk2[nr][nc] = dijk2[l.r][l.c];
						pq.offer(new Location(nr, nc, dijk[nr][nc], dijk2[nr][nc]));
					// 쓰레기 근처에 있는 칸에 도착한 경우
					} else if (map[nr][nc] == -1 && (dijk[nr][nc] > dijk[l.r][l.c] || (dijk[nr][nc] == dijk[l.r][l.c] && dijk2[nr][nc] > dijk2[l.r][l.c]+1))) {
						dijk[nr][nc] = dijk[l.r][l.c];
						dijk2[nr][nc] = dijk2[l.r][l.c]+1;
						pq.offer(new Location(nr, nc, dijk[nr][nc], dijk2[nr][nc]));
					// 쓰레기가 없는 칸에 도착한 경우
					} else if (map[nr][nc] == 0 && (dijk[nr][nc] > dijk[l.r][l.c] || (dijk[nr][nc] == dijk[l.r][l.c] && dijk2[nr][nc] > dijk2[l.r][l.c]))) {
						dijk[nr][nc] = dijk[l.r][l.c];
						dijk2[nr][nc] = dijk2[l.r][l.c];
						pq.offer(new Location(nr, nc, dijk[nr][nc], dijk2[nr][nc]));
					}
				}
			}
		}
		System.out.println(dijk[finish[0]][finish[1]]+" "+dijk2[finish[0]][finish[1]]);
	}
	// (i, j)가 쓰레기 근처에 있는 칸인지 확인하는 함수
	private static boolean checkIfNearTrash(int i, int j) {
		// 쓰레기가 있는 칸이거나 이미 쓰레기 근처에 있는 칸인 경우
		if (map[i][j] != 0) return false;
		// 출발지와 목적지는 쓰레기 근처에 있는 칸으로 고려하지 않는다.
		if ((i == start[0] && j == start[1]) || (i == finish[0] && j == finish[1])) return false;
		// 인접한 칸 중 쓰레기가 있는 칸이 있는지 조사한다.
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (check(nr, nc) && map[nr][nc] == 1) return true;
		}
		return false;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
