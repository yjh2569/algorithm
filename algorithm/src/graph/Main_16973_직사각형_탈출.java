package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16973_직사각형_탈출 {
	static int N, M, H, W;
	static int[][] board; // 격지판
	static int[] start, end; // 시작점과 끝점
	static int[][] sum; // 격자판에서 (1, 1)에서 (i, j)까지의 누적합을 (i, j) 성분으로 하는 배열
	// 직사각형의 왼쪽 위칸의 좌표가 (i, j)일 때 해당 지점을 방문할 수 있는지를 (i, j) 성분으로 하는 배열
	static boolean[][] canVisit;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		start = new int[2]; end = new int[2];
		start[0] = Integer.parseInt(st.nextToken());
		start[1] = Integer.parseInt(st.nextToken());
		end[0] = Integer.parseInt(st.nextToken());
		end[1] = Integer.parseInt(st.nextToken());
		sum = new int[N+1][M+1];
		// 누적합을 구한다.
		calculateCumSum();
		canVisit = new boolean[N-H+2][M-W+2];
		// 누적합을 활용해 각 지점을 왼쪽 위칸으로 하는 직사각형이 존재할 수 있는지 파악한다. 
		for (int i = 1; i <= N-H+1; i++) {
			for (int j = 1; j <= M-W+1; j++) {				
				checkIfCanVisit(i, j);
			}
		}
		// BFS를 통해 목적지에 도달하기 위한 최소 거리를 구한다.
		System.out.println(bfs());
	}
	// 누적합을 계산하는 함수
	private static void calculateCumSum() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum[i][j] = board[i][j];
				sum[i][j] += sum[i][j-1];
			}
		}
		for (int j = 1; j <= M; j++) {
			for (int i = 1; i <= N; i++) {
				sum[i][j] += sum[i-1][j];
			}
		}
	}
	// 누적합을 활용해 (r, c)를 왼쪽 위칸으로 하는 직사각형이 존재할 수 있는지 확인하는 함수 
	private static void checkIfCanVisit(int r, int c) {
		if (sum[r+H-1][c+W-1] - sum[r+H-1][c-1] - sum[r-1][c+W-1] + sum[r-1][c-1] == 0) {			
			canVisit[r][c] = true;
		}
	}
	// BFS를 활용해 목적지에 도달하기 위한 최소 거리를 구하는 함수
	private static int bfs() {
		// 시작 지점이 곧 도착 지점인 경우
		if (start[0] == end[0] && start[1] == end[1]) return 0;
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N-H+2][M-W+2];
		q.offer(new int[] {start[0], start[1]});
		visited[start[0]][start[1]] = true;
		int l = 1; // 목적지까지의 최소 거리
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc) && !visited[nr][nc] && canVisit[nr][nc]) {
						// 목적지에 도달한 경우
						if (nr == end[0] && nc == end[1]) {
							return l;
						}
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
			l++;
		}
		// 목적지에 도달하지 못한 경우
		return -1;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 1<=r && r<=N-H+1 && 1<=c && c<=M-W+1;
	}
}
