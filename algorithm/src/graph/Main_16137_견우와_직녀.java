package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16137_견우와_직녀 {
	// 현재 위치를 나타내는 클래스
	static class Location {
		int r, c;
		boolean makeBridge, isOnBridge;
		public Location(int r, int c, boolean makeBridge, boolean isOnBridge) {
			this.r = r;
			this.c = c;
			this.makeBridge = makeBridge;
			this.isOnBridge = isOnBridge;
		}
	}
	static int N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 가로 절벽와 세로 절벽이 교차하는 절벽은 접근할 수 없게 한다. 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) continue;
				int cnt = 0;
				if ((i > 0 && map[i-1][j] == 0) || (i+1 < N && map[i+1][j] == 0)) cnt++;
				if ((j > 0 && map[i][j-1] == 0) || (j+1 < N && map[i][j+1] == 0)) cnt++;
				if (cnt == 2) map[i][j] = -1;
			}
		}
		// BFS를 통해 직녀까지 도달하기 위한 최소 시간을 구한다.
		Queue<Location> q = new LinkedList<>();
		// 좌표와 해당 좌표까지 갈 때 오작교를 설치한 적이 있는지를 포함한 방문 배열
		boolean[][][] visited = new boolean[N][N][2]; 
		q.offer(new Location(0, 0, false, false));
		visited[0][0][0] = true;
		int time = 0; // 직녀까지 도달하기 위한 최소 시간
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				Location l = q.poll();
				// 직녀에게 도달한 경우
				if (l.r == N-1 && l.c == N-1) {
					System.out.println(time);
					System.exit(0);
				}
				for (int d = 0; d < 4; d++) {
					int nr = l.r + dr[d];
					int nc = l.c + dc[d];
					int intForMakeBridge = l.makeBridge ? 1 : 0;
					// 경계를 벗어나거나 해당 지역을 이미 방문한 경우
					if (!check(nr, nc) || visited[nr][nc][intForMakeBridge]) continue;
					// 다음 지역이 땅인 경우
					if (map[nr][nc] == 1) {
						visited[nr][nc][intForMakeBridge] = true;
						q.offer(new Location(nr, nc, l.makeBridge, false));
					}
					// 다음 지역이 이미 설치된 오작교인 경우
					// 오작교를 두 번 이상 밟을 수 없으므로 현재 지역은 땅이어야 한다.
					if (map[nr][nc] >= 2 && map[l.r][l.c] == 1) {
						// 주기가 되어 오작교를 건널 수 있으면 건너가고, 그렇지 않으면 그 자리에서 대기한다.
						if ((time+1)%map[nr][nc] == 0) q.offer(new Location(nr, nc, l.makeBridge, true));
						else q.offer(new Location(l.r, l.c, l.makeBridge, false));
					}
					// 다음 지역이 단순 절벽인 경우
					// 해당 절벽에 오작교를 설치해 지나갈 수 있으면 설치를 시도한다.
					if (map[nr][nc] == 0 && !l.makeBridge) {
						// 주기가 되어 오작교를 건널 수 있으면 건너가고, 그렇지 않으면 그 자리에서 대기한다.
						if ((time+1)%M == 0) q.offer(new Location(nr, nc, true, true));
						else q.offer(new Location(l.r, l.c, l.makeBridge, false));
					}
				}
				qLen--;
			}
			time++;
		}
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
