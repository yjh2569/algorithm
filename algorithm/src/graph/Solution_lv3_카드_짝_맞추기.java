package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_lv3_카드_짝_맞추기 {
	static int R, C, N, first_r, first_c, cur_r, cur_c, count;
	static int[][] _board;
	static int[][] first_board;
	static int[] p;
	static boolean[] v;
	static int[] p2;
	static int[][] locs;
	static int min;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) {
		int result = solution(new int[][] {{3,0,0,2},{0,0,1,0},{0,1,0,0},{2,0,0,3}}, 0, 1);
		System.out.println(result);
	}
	
	public static int solution(int[][] board, int r, int c) {
		// 입력으로 주어지는 board를 전역 변수로 선언
		first_board = new int[4][4];
		// 게임을 시뮬레이션할 때 board의 변화를 기록하기 위한 배열
		_board = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				first_board[i][j] = board[i][j];
			}
		}
		// 조작 횟수의 최소값
        min = Integer.MAX_VALUE;
        // board 내 숫자 중 가장 큰 값
        int maxNum = 0;
        // 초기 커서의 위치를 전역 변수로 선언
        first_r = r; first_c = c;
        R = board.length;
        C = board[0].length;
        for (int i = 0; i < R; i++) {
        	for (int j = 0; j < C; j++) {
        		maxNum = Math.max(board[i][j], maxNum);
        	}
        }
        // 앞으로 쓰일 배열들의 길이를 정의하기 위한 변수
        N = maxNum+1;
        // 뒤집을 카드 순서를 결정하기 위해 순열을 사용한다.
        // 카드 뒤집는 순서를 기록하는 배열
        p = new int[N-1];
        // 순열 작업을 보조하는 방문 배열
        v = new boolean[N];
        // (i+1)번째 j번 카드의 위치를 locs[j*2+i]에 저장 
        locs = new int[N*2][2];
        for (int i = 0; i < N*2; i++) {
        	Arrays.fill(locs[i], -1);
        }
        // 카드 위치를 locs에 저장
        for (int i = 0; i < R; i++) {
        	for (int j = 0; j < C; j++) {
        		if (board[i][j] == 0) continue;
        		if (locs[board[i][j]*2][0] >= 0) {
        			locs[board[i][j]*2+1][0] = i;
        			locs[board[i][j]*2+1][1] = j;
        		} else {
        			locs[board[i][j]*2][0] = i;
        			locs[board[i][j]*2][1] = j;
        		}
        	}
        }
        perm(0);
        return min;
    }
	// 순열을 통해 뒤집을 카드 순서를 결정한다.
	private static void perm(int cnt) {
		if (cnt == N-1) {
			p2 = new int[(N-1)*2];
			decide(0);
			return;
		}
		for (int i = 1; i < N; i++) {
			if (v[i]) continue;
			v[i] = true;
			p[cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}
	}
	// 뒤집을 카드는 각각 2장씩 존재하는데, 둘 중 어느 카드를 먼저 뒤집을지를 결정한다.
	private static void decide(int cnt) {
		if (cnt == N-1) {
			simulate();
			return;
		}
		p2[cnt*2] = p[cnt]*2;
		p2[cnt*2+1] = p[cnt]*2+1;
		decide(cnt+1);
		p2[cnt*2] = p[cnt]*2+1;
		p2[cnt*2+1] = p[cnt]*2;
		decide(cnt+1);
	}
	// 위에서 결정한 카드 뒤집기 순서에 따라 게임을 시뮬레이션한다.
	private static void simulate() {
		// 조작 횟수
		count = 0;
		// 현재 커서의 위치를 처음 커서의 위치로 초기화
		cur_r = first_r; cur_c = first_c;
		// _board 초기화
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				_board[i][j] = first_board[i][j];
			}
		}
		// 현재 커서 위치에서 뒤집을 카드까지의 최소 조작 횟수를 구한다.
		for (int i = 0; i < (N-1)*2; i++) {
			bfs(i);
		}
		// 조작 횟수 최소값 갱신
		min = Math.min(min, count);
	}
	// BFS를 통해 현재 커서 위치에서 뒤집을 카드까지의 최소 조작 횟수를 구한다.
	private static void bfs(int i) {
		// 뒤집을 카드의 위치
		int[] destination = locs[p2[i]];
		// BFS 초기화
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[4][4];
		q.offer(new int[] {cur_r, cur_c});
		visited[cur_r][cur_c] = true;
		// 조작 횟수
		int k = 0;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				// 뒤집을 카드 위치에 도달한 경우
				if (u[0] == destination[0] && u[1] == destination[1]) {
					// 커서 위치를 뒤집을 카드 위치로 이동
					cur_r = u[0]; cur_c = u[1];
					// 해당 카드는 board에서 제거
					// 1번째로 뒤집은 카드인 경우 실제로는 제거되지 않지만, 2번째로 뒤집을 카드를 찾을 때 해당 카드 위치에서
					// 시작해 절대 현재 카드 위치와 관련된 이동을 하지 않기 때문에 괜찮다.
					_board[u[0]][u[1]] = 0;
					// Enter 키를 포함한 최소 조작 횟수를 구한다.
					count += k+1;
					return;
				}
				for (int d = 0; d < 4; d++) {
					// 단순 방향키로만 조작하는 경우
					int nr = u[0] + dr[d];
					int nc = u[1] + dc[d];
					if (check(nr, nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
					// Ctrl + 방향키로 조작하는 경우
					while (check(nr, nc) && _board[nr][nc] == 0) {
						nr += dr[d];
						nc += dc[d];
					}
					// Ctrl + 방향키로 조작할 때 해당 방향으로 카드가 존재하지 않는 경우
					if (!check(nr, nc)) {
						nr -= dr[d];
						nc -= dc[d];
					}
					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
			k++;
		}
		
	}
	// 경계 확인 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<4 && 0<=c && c<4;
	}
	
	
}
