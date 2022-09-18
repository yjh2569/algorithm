package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21610_마법사_상어와_비바라기 {
	static int N, M;
	static int[][] A;
	// 구름의 위치 정보를 담은 클래스
	static class Cloud {
		int r, c;
		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static boolean[][] visited;
	static Queue<Cloud> clouds;
	// 이동 방향에 따른 좌표 변화를 나타낸 배열
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		// 구름이 사라진 칸을 표시하기 위한 배열
		visited = new boolean[N][N];
		// 생성된 구름을 저장하기 위한 queue
		clouds = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// 초기 구름
		for (int r = N-2; r < N; r++) {
			for (int c = 0; c < 2; c++) {
				clouds.offer(new Cloud(r, c));
			}
		}
		// 시뮬레이션
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			// 구름이 사라진 칸 초기화
			for (int r = 0; r < N; r++) {
				Arrays.fill(visited[r], false);
			}
			// 구름이 이동한 뒤 비를 내린 뒤 사라진다.
			moveAndRain(d, s);
			// 물복사버그 마법을 사용한다.
			waterCopyBug();
			// 새로운 구름이 생성된다.
			createCloud();
		}
		// 바구니에 들어있는 물의 양의 합
		int sum = 0;
		// sum 계산
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sum += A[r][c];
			}
		}
		System.out.println(sum);
	}
	// 구름이 방향 d, 속도 s로 이동한 뒤 비를 뿌리고 사라지는 과정을 시뮬레이션한 함수
	private static void moveAndRain(int d, int s) {
		while (!clouds.isEmpty()) {
			Cloud cloud = clouds.poll();
			// 구름을 방향 d, 속도 s로 이동시킨다.
			cloud.r = (cloud.r + dr[d]*(s%N) + N)%N;
			cloud.c = (cloud.c + dc[d]*(s%N) + N)%N;
			// 구름이 이동한 칸에 비를 뿌린다.
			A[cloud.r][cloud.c] += 1;
			// 구름이 해당 칸에서 사라진다.
			visited[cloud.r][cloud.c] = true;
		}
	}
	// 물복사버그 마법을 시뮬레이션한 함수
	private static void waterCopyBug() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 물이 증가한 칸에서 물복사가 이루어진다.
				if (visited[r][c]) copyWater(r, c); 
			}
		}
		
	}
	// 물복사 과정을 시뮬레이션한 함수
	private static void copyWater(int r, int c) {
		int cnt = 0; // 인접한 대각선 방향 칸 중 물이 들어있는 바구니 수
		for (int d = 2; d <= 8; d += 2) { // 대각선 방향에 대해서만 진행
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 경계를 넘지 않고 물이 들어있다면 cnt를 1 증가시킨다.
			if (checkMap(nr, nc) && A[nr][nc] > 0) {
				cnt++;
			}
		}
		// cnt만큼 물의 양을 증가시킨다.
		A[r][c] += cnt;
	}
	// 구름을 생성하는 과정을 시뮬레이션한 함수
	private static void createCloud() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 직전에 구름이 사라진 칸이 아니면서 물의 양이 2 이상인 칸에서 구름이 생성된다.
				if (!visited[r][c] && A[r][c] >= 2) {
					clouds.offer(new Cloud(r, c));
					A[r][c] -= 2; // 해당 칸에서 물의 양 2 감소
				}
			}
		}
	}
	// 경계 확인 함수
	private static boolean checkMap(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
