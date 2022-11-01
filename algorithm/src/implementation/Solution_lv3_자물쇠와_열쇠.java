package implementation;

public class Solution_lv3_자물쇠와_열쇠 {
	
	static int N, M, holeCnt;
	static int[][] board;
	static int[][] _key;
	static int[][] _lock;
	static boolean answer;	
	
	public static void main(String[] args) {
		boolean result = solution(new int[][] {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
		System.out.println(result);
	}
	
	public static boolean solution(int[][] key, int[][] lock) {
		// 전역 변수로 선언
        answer = false;
        N = lock.length;
        M = key.length;
        _key = key;
        _lock = lock;
        // key를 둘 수 있는 모든 경우를 고려하기 위해,
        // lock을 정중앙에 배치한 (N+2*M, N+2*M) 크기의 배열을 선언한다.
        board = new int[N+2*M][N+2*M];
        // lock에 있는 홈 개수
        holeCnt = 0;
        for (int i = M; i < N+M; i++) {
        	for (int j = M; j < N+M; j++) {
        		board[i][j] = lock[i-M][j-M];
        		if (board[i][j] == 0) holeCnt++;
        	}
        }
        // 방향을 시계방향으로 돌려보면서 맞춰본다.
        for (int i = 0; i < 4; i++) {
        	simulate();
        	rotate();
        }
        return answer;
    }
	// key의 왼쪽 위 칸을 board의 어느 칸에 맞출지 결정한다.
	private static void simulate() {
		for (int r = 1; r < N+M; r++) {
			for (int c = 1; c < N+M; c++) {
				check(r, c);
			}
		}		
	}
	// key의 위치를 정한 뒤 key의 돌기가 lock의 홈을 채울 수 있는지 확인한다.
	private static void check(int r, int c) {
		// lock에 있는 홈 중 채운 홈의 개수
		int filledCnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				// lock의 바깥 부분에 존재해 확인할 필요가 없는 경우
				if (r+i<M || N+M<=r+i || c+j<M || N+M<=c+j) continue;
				// key의 돌기가 lock의 홈을 채우는 경우
				if (_key[i][j] == 1 && board[r+i][c+j] == 0) filledCnt++;
				// key의 돌기가 lock의 돌기와 만나는 경우
				if (_key[i][j] == 1 && board[r+i][c+j] == 1) return;
			}
		}
		// lock에 있는 홈을 모두 채운 경우
		if (filledCnt == holeCnt) answer = true;
	}
	// key를 시계방향으로 회전
	private static void rotate() {
		int[][] temp = new int[M][M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				temp[j][M-1-i] = _key[i][j];
			}
		}
		_key = temp;		
	}

}
