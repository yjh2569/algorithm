package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16932_모양_만들기 {
	static int N, M, max;
	static int[][] map;
	static int[][] area;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<Integer> sizes;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 각 모양에 번호를 붙인다.
		int idx = 1;
		// 배열의 모든 1이 각각 어떤 모양에 속하는지를 기록한다.
		area = new int[N][M];
		// 각 모양의 크기를 저장한다.
		sizes = new ArrayList<>();
		// 초기화(idx가 1부터 시작하는 것을 고려)
		sizes.add(0);
		// 각 영역의 크기를 BFS를 통해 구한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 방문하지 않은 영역을 만난 경우
				if (map[i][j] == 1 && area[i][j] == 0) bfs(i, j, idx++);
			}
		}
		// 배열 내 0을 1로 바꿨을 때 가능한 모양의 크기의 최대값 
		max = 0;
		// 배열에서 0을 찾아 1로 바꿔본 뒤 모양의 크기를 계산한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) check(i, j);
			}
		}
		System.out.println(max);
	}
	// BFS를 통해 방문하지 않은 모양의 크기를 구하고 이를 sizes에 저장한다.
	private static void bfs(int i, int j, int k) {
		int size = 1;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {i, j});
		area[i][j] = k;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = u[0] + dr[d];
				int nc = u[1] + dc[d];
				if (checkEdge(nr, nc) && map[nr][nc] == 1 && area[nr][nc] == 0) {
					area[nr][nc] = k;
					size++;
					q.offer(new int[] {nr, nc});
				}
			}
		}
		sizes.add(size);
	}
	// 배열 내에 있는 0을 1로 바꿨을 때 BFS를 다시 시행하는 것이 아니라, 
	// 인접한 사방에 있는 서로 다른 모양의 크기의 합을 구해서 전체 모양의 크기를 구한다.
	private static void check(int i, int j) {
		// 고려한 모양의 번호를 저장한다.
		// 사방 중 여러 방향에 같은 번호의 모양이 있을 수도 있기 때문이다.
		Set<Integer> idxs = new HashSet<>();
		// 해당 자리의 0을 1로 바꿨으므로 처음 크기는 1이다.
		int size = 1;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if (checkEdge(nr, nc) && map[nr][nc] == 1 && !idxs.contains(area[nr][nc])) {
				idxs.add(area[nr][nc]);
				size += sizes.get(area[nr][nc]);
			}
		}
		max = Math.max(max, size);		
	}
	// 경계 확인
	private static boolean checkEdge(int i, int j) {
		return 0<=i && i<N && 0<=j && j<M;
	}
}
