package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14466_소가_길을_건너간_이유_6 {
	static int N, K, R;
	static boolean[][][] hasPath;
	static boolean[][] cows;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// 각 목초지에서 인접한 목초지로 갈 때 길을 건너야 하는지를 나타내는 배열
		hasPath = new boolean[N][N][4];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int u_r = Integer.parseInt(st.nextToken())-1;
			int u_c = Integer.parseInt(st.nextToken())-1;
			int v_r = Integer.parseInt(st.nextToken())-1;
			int v_c = Integer.parseInt(st.nextToken())-1;
			registerPath(u_r, u_c, v_r, v_c);
		}
		// 각 목초지에 소가 있는지를 나타내는 배열
		cows = new boolean[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			cows[r][c] = true;
		}
		// 길을 건너지 않으면 만날 수 없는 소가 몇 쌍인지를 나타내는 변수
		int cnt = 0;
		// BFS를 위한 방문 배열
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 소가 있으면 해당 소가 길을 건너지 않으면 만날 수 없는 소가 몇 마리인지를 cnt에 더해준다.
				if (cows[i][j]) cnt += bfs(i, j);
			}
		}
		// 위와 같이 진행하면 쌍의 개수가 2배가 되므로 2로 나눠준다.
		System.out.println(cnt/2);
	}
	// (u_r, u_c)와 (v_r, v_c) 사이에 길이 있음을 표시하기 위한 함수
	private static void registerPath(int u_r, int u_c, int v_r, int v_c) {
		if (u_r == v_r && u_c == v_c+1) {
			hasPath[u_r][u_c][3] = true;
			hasPath[v_r][v_c][1] = true;
		} else if (u_r == v_r && u_c == v_c-1) {
			hasPath[u_r][u_c][1] = true;
			hasPath[v_r][v_c][3] = true;
		} else if (u_r+1 == v_r && u_c == v_c) {
			hasPath[u_r][u_c][2] = true;
			hasPath[v_r][v_c][0] = true;
		} else if (u_r-1 == v_r && u_c == v_c) {
			hasPath[u_r][u_c][0] = true;
			hasPath[v_r][v_c][2] = true;
		}		
	}
	// BFS를 통해 (r, c)에 있는 소가 길을 통하지 않고서는 만날 수 없는 소가 몇 마리인지를 구하는 함수
	private static int bfs(int r, int c) {
		int[] cow = new int[] {r, c};
		Queue<int[]> q = new LinkedList<>();
		for (int k = 0; k < N; k++) {
			Arrays.fill(visited[k], false);
		}
		q.offer(cow);
		visited[cow[0]][cow[1]] = true;
		int cnt = K-1; // 만날 수 없는 소의 마릿수
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				// 길을 지나지 않음으로써 길을 통하지 않고서는 만날 수 없는 소의 마릿수를 찾는다.
				if (hasPath[u[0]][u[1]][d]) continue;
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (check(nr, nc) && !visited[nr][nc]) {
					// 다른 소를 찾은 경우
					if (cows[nr][nc]) cnt--;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		return cnt;
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
}
