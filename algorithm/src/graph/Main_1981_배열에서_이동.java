package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1981_배열에서_이동 {
	static int N, min, min_v, max_v;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 주어진 배열 전체에서 최댓값과 최솟값의 차이 
		min = Integer.MAX_VALUE;
		map = new int[N][N];
		StringTokenizer st;
		// 주어진 배열 전체에서 최솟값과 최댓값
		min_v = 200;
		max_v = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min_v = Math.min(map[i][j], min_v);
				max_v = Math.max(map[i][j], max_v);
			}
		}
		min = max_v - min_v;
		System.out.println(binarySearch());
	}
	// 이분 탐색을 통해 최댓값과 최솟값의 차이가 최소가 될 때를 찾는다.
	private static int binarySearch() {
		int l = 0;
		int r = min;
		while (l < r) {
			int mid = (l+r)/2;
			// mid가 최댓값과 최솟값의 차이의 경계값일 때 (1, 1)에서 (n, n)으로 갈 수 있는지를 확인한다.
			if (bfs(mid)) {
				r = mid;
			} else {
				l = mid+1;
			}
		}
		return l;
	}
	// 최댓값과 최솟값의 차이의 경계값이 diff일 때 (1, 1)에서 (n, n)으로 갈 수 있는지 확인하기 위해 BFS 사용 
	private static boolean bfs(int diff) {
		// diff를 통해 배열에서 지나갈 수 있는 수의 범위를 지정하고, 그 범위 내의 수에 대해서만 탐색한다.
		for (int i = min_v; i + diff <= max_v; i++) {
			int l = i;
			int r = i + diff;
			if (map[0][0] < l || map[0][0] > r) continue;
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			q.offer(new int[] {0, 0});
			visited[0][0] = true;
			while (!q.isEmpty()) {
				int[] u = q.poll();
				if (u[0] == N-1 && u[1] == N-1) return true;
				for (int d = 0; d < 4; d++) {
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (0<=nr && nr<N && 0<=nc && nc<N && !visited[nr][nc] && l <= map[nr][nc] && map[nr][nc] <= r) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
			}
		}
		return false;
	}

}
