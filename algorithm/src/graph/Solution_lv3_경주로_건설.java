package graph;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_lv3_경주로_건설 {

	public static void main(String[] args) {
		int result = solution(new int[][] {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 1, 0, 1}, {1, 0, 0, 0}});
		System.out.println(result);
	}
	// dijkstra's algorithm 진행 중 노드의 좌표, 방향, 해당 지점까지의 도로 건설 비용을 저장하는 클래스
	static class Car implements Comparable<Car> {
		int r, c, d, cost;
		public Car(int r, int c, int d, int cost) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.cost = cost;
		}
		public int compareTo(Car c) {
			return Integer.compare(this.cost, c.cost);
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        int N = board.length;
        // dijkstra's algorithm 적용
        PriorityQueue<Car> pq = new PriorityQueue<>();
        // 커브길도 고려해야 하므로 방향도 고려한 방문배열을 만든다.
        boolean[][][] visited = new boolean[N][N][4];
        // 좌표와 방향마다 그 지점까지 도로를 건설하는데 드는 최소 비용을 저장하는 배열
        int[][][] dijk = new int[N][N][4];
        // 초기화
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {        		
        		Arrays.fill(dijk[i][j], Integer.MAX_VALUE);
        	}
        }
        for (int d = 0; d < 4; d++) {
        	dijk[0][0][d] = 0;        	
        }
        pq.offer(new Car(0, 0, 1, 0));
        pq.offer(new Car(0, 0, 2, 0));
        // dijkstra's algorithm 진행
        while (!pq.isEmpty()) {
        	Car c = pq.poll();
        	if (visited[c.r][c.c][c.d]) continue;
        	visited[c.r][c.c][c.d] = true;
        	for (int d = 0; d < 4; d++) {
        		// 커브를 추가로 건설해야 하는지 확인
        		int nextCost = c.d == d ? 100 : 600;
        		int nr = c.r + dr[d];
        		int nc = c.c + dc[d];
        		if (check(nr, nc, N, board) && dijk[nr][nc][d] > dijk[c.r][c.c][c.d] + nextCost) {
        			dijk[nr][nc][d] = dijk[c.r][c.c][c.d] + nextCost;
        			pq.offer(new Car(nr, nc, d, dijk[nr][nc][d]));
        		}
        	}
        }
        // 최소 건설 비용 반환
        for (int d = 0; d < 4; d++) {
        	answer = Math.min(answer, dijk[N-1][N-1][d]);
        }
        return answer;
    }
	// 주어진 (r, c) 좌표가 경주로 부지를 벗어나지 않는지, 벽이 없는지를 확인하는 함수
	private static boolean check(int r, int c, int N, int[][] board) {
		return 0<=r && r<N && 0<=c && c<N && board[r][c] == 0;
	}

}
