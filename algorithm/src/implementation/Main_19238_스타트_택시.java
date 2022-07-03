package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19238_스타트_택시 {
	static int N, M, fuel, consumedFuel;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	// 승객 우선 순위 판단을 위한 승객 클래스
	static class Passenger implements Comparable<Passenger> {
		int idx, r, c;
		public Passenger(int idx, int r, int c) {
			this.idx = idx;
			this.r = r;
			this.c = c;
		}
		// 승객까지의 거리가 같은 경우 행이 작은 승객을 먼저, 만일 행도 같은 경우 열이 작은 승객을 먼저 태운다. 
		public int compareTo(Passenger p) {
			return this.r == p.r ? Integer.compare(this.c, p.c) : Integer.compare(this.r, p.r);
		}
	}
	// 택시 클래스
	static class Taxi {
		int r, c;
		public Taxi(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Taxi taxi;
	static int[][] destinations;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 남은 연료의 양
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int t_r = Integer.parseInt(st.nextToken())-1;
		int t_c = Integer.parseInt(st.nextToken())-1;
		// 현재 택시 위치
		taxi = new Taxi(t_r, t_c);
		// 각 승객의 목적지를 저장하는 배열
		destinations = new int[M+1][2];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int p_r = Integer.parseInt(st.nextToken())-1;
			int p_c = Integer.parseInt(st.nextToken())-1;
			// 승객을 map에 표현할 때 i번째 승객인 경우 -i로 표현해 벽과 구분되도록 한다.
			map[p_r][p_c] = -i;
			// i번째 승객의 목적지를 저장
			destinations[i][0] = Integer.parseInt(st.nextToken())-1;
			destinations[i][1] = Integer.parseInt(st.nextToken())-1;
		}
		// M명의 승객을 목적지까지 데려다준다.
		for (int i = 1; i <= M; i++) {
			// 소비한 연료 초기화
			consumedFuel = 0;
			// 최단 거리에 있는 승객을 찾는다.
			int p = findPassenger();
			// 벽에 막혀 승객을 못 찾은 경우 
			if (p == 0) {
				System.out.println(-1);
				System.exit(0);
			}
			// 최단 거리에 있는 승객을 목적지까지 데려다준다.
			toDestination(p);
		}
		// 남은 연료 출력
		System.out.println(fuel);
	}
	// 최단 거리에 있는 승객을 찾고 태운다.
	private static int findPassenger() {
		// 택시가 있는 위치에 바로 승객이 있는 경우
		if (map[taxi.r][taxi.c] < 0) {
			int p = -map[taxi.r][taxi.c];
			map[taxi.r][taxi.c] = 0; // 태운 승객을 또 태우는 경우를 방지
			return p;
		}
		// 최단 거리에 있는 승객이 여러 명인 경우 우선 순위에 맞게 승객을 태워야 하기에 
		// 우선 순위 큐를 만들어 최단 거리에 있는 승객을 넣은 뒤 우선 순위가 가장 높은 승객을 찾는다.
		PriorityQueue<Passenger> passengers = new PriorityQueue<>();
		// BFS를 통해 최단 거리에 있는 승객을 찾는다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		visited[taxi.r][taxi.c] = true;
		q.offer(new int[] {taxi.r, taxi.c});
		int distance = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc) && !visited[nr][nc]) {
						// 인접한 위치에 승객이 있으면 우선 순위 큐에 넣는다.
						if (map[nr][nc] < 0) {
							int idx = -map[nr][nc];
							passengers.add(new Passenger(idx, nr, nc));
						}
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
			// 최단 거리에 있는 승객들을 찾은 경우 우선 순위가 가장 높은 승객을 태운다.
			if (passengers.size() > 0) {
				Passenger p = passengers.poll();
				consumedFuel += distance; // 소비한 연료를 저장
				taxi.r = p.r; taxi.c = p.c;
				map[p.r][p.c] = 0;
				// 해당 승객의 번호를 반환
				return p.idx;
			}
			distance++;
		}
		// 태울 승객을 찾지 못한 경우
		return 0;
	}
	// p번째 승객을 목적지에 데려다준다.
	private static void toDestination(int p) {
		// BFS를 통해 목적지까지의 최단 거리를 찾는다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		q.offer(new int[] {taxi.r, taxi.c});
		visited[taxi.r][taxi.c] = true;
		int distance = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc) && !visited[nr][nc]) {
						// 목적지에 도달했을 때
						if (nr == destinations[p][0] && nc == destinations[p][1]) {
							// 만약 승객을 태운 뒤 목적지까지 가는데 필요한 연료가 현재 남은 연료보다 크면
							// -1을 출력한다.
							if (consumedFuel+distance > fuel) {
								System.out.println(-1);
								System.exit(0);
							// 승객을 태우고 목적지까지 갈 수 있으면 
							// 승객을 태운 뒤 목적지까지 가는데 필요한 연료(consumedFuel + distance)를 뺀 뒤
							// 승객을 목적지까지 데리고 가는데 필요한 연료의 2배(distance*2)를 더한다.
							// 이를 종합했을 때 아래와 같이 계산하면 된다.
							} else {
								fuel += distance-consumedFuel;
								taxi.r = nr; taxi.c = nc;
								return;
							}
						}
						q.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
				qLen--;
			}
			distance++;
		}
		// 목적지가 벽에 가로막혀 목적지를 찾지 못한 경우
		System.out.println(-1);
		System.exit(0);
	}
	// 경계 및 벽 유무를 확인하는 함수
	private static boolean check(int nr, int nc) {
		return 0<=nr && nr<N && 0<=nc && nc<N && map[nr][nc] != 1;
	}

}
