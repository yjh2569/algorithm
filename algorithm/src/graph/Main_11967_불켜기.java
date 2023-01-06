package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11967_불켜기 {
	static int N, M;
	static Map<Integer, ArrayList<Integer>> switches;
	static boolean[][] turnOn;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 각 방에 있는 스위치 정보를 저장하는 HashMap
		switches = new HashMap<>();
		// 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				switches.put(i*N+j, new ArrayList<>());
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			switches.get(x*N+y).add(a*N+b);
		}
		// 불을 켤 수 있는 방의 개수의 최댓값
		int answer = 0;
		// 불을 켤 수 있는 방을 찾는 배열
		turnOn = new boolean[N][N];
		bfs();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (turnOn[r][c]) answer++;
			}
		}
		System.out.println(answer);
	}
	// BFS를 통해 불을 켤 수 있는 방의 개수를 구한다.
	private static void bfs() {
		// 현재 방문할 방을 저장하는 큐
		Queue<Integer> q = new LinkedList<>();
		// 방문 배열
		visited = new boolean[N][N];
		q.offer(0);
		visited[0][0] = true;
		turnOn[0][0] = true;
		while (!q.isEmpty()) {
			int u = q.poll();
			// 현재 방에서 켤 수 있는 방의 불을 모두 켠다.
			for (int loc : switches.get(u)) {
				int locR = loc/N;
				int locC = loc%N;
				turnOn[locR][locC] = true;
			}
			// 현재 위치를 기준으로 상하좌우로 인접한 방 중 불이 켜져있는 방으로 이동
			for (int d = 0; d < 4; d++) {
				int nr = u/N + dr[d];
				int nc = u%N + dc[d];
				if (check(nr, nc) && !visited[nr][nc] && turnOn[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(nr*N+nc);
				}
			}
			// 현재 위치에서 불을 켠 방에 접근 가능한 경우 해당 방으로 이동
			for (int loc : switches.get(u)) {
				int locR = loc/N;
				int locC = loc%N;
				if (visited[locR][locC]) continue;
				for (int d = 0; d < 4; d++) {
					int nr = locR + dr[d];
					int nc = locC + dc[d];
					if (check(nr, nc) && visited[nr][nc]) {
						visited[locR][locC] = true;
						q.offer(locR*N+locC);
					}
				}
			}
		}
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
