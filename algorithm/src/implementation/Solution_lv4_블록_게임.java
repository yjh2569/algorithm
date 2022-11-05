package implementation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv4_블록_게임 {
	
	static int N, cnt;
	static int[][] _board;
	static Map<Integer, Integer> blocks;
	static int[][] locations;
	static boolean[] visited;
	
	public static void main(String[] args) {
		int result = solution(new int[][] {{0, 0, 0, 0, 0}, {1, 0, 0, 2, 0}, {1, 2, 2, 2, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}});
		System.out.println(result);
	}
	
	public static int solution(int[][] board) {
        cnt = 0; // 없앨 수 있는 블록의 최대 갯수
        _board = board; // 전역 변수로 선언
        N = board.length; // board의 크기
        // 각 블록의 타입을 저장하는 HashMap
        // 12개의 블록의 모양이 있는 그림에서 3번째, 4번째, 6번째, 7번째, 9번째 블록을 각각 0, 1, 2, 3, 4번 타입으로 정의
        // 나머지 블록은 5번 타입으로 정의
        // 검은 블록을 쌓았을 때 제거할 수 있는 블록들을 형태별로 나누어 0, 1, 2, 3, 4번 타입으로,
        // 그렇지 않은 것은 5번 타입으로 했다.
        blocks = new HashMap<>();
        // 각 블록을 조사했는지를 나타내는 방문 배열
        visited = new boolean[201];
        // 각 블록의 기준점이 되는 블록
        // 행의 번호가 가장 작은 블록을 기준점으로 한다.
        locations = new int[201][2];
        // 블록 조사
        searchBlocks();
        // 방문 배열 초기화
        Arrays.fill(visited, false);
        // 블록 제거
        removeBlocks();
        return cnt;
    }
	// board 내 블록을 조사한다.
	private static void searchBlocks() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 블록 번호
				int num = _board[r][c];
				// 빈 공간이거나 이미 조사한 블록인 경우
				if (num == 0 || visited[num]) continue;
				visited[num] = true;
				locations[num] = new int[] {r, c};
				// 블록의 타입을 조사
				checkBlockType(r, c, num);
			}
		}
	}
	// 블록의 타입을 조사한다.
	private static void checkBlockType(int r, int c, int num) {
		// (r, c)를 기준점으로 했을 때 인근 블록의 위치를 조사해 블록의 타입을 알아낸다.
		if (r < N-1 && c < N-2 && _board[r+1][c] == num && _board[r+1][c+1] == num && _board[r+1][c+2] == num) {
			blocks.put(num, 0);
		} else if (r < N-2 && c > 0 && _board[r+1][c] == num && _board[r+2][c] == num && _board[r+2][c-1] == num) {
			blocks.put(num, 1);
		} else if (r < N-2 && c < N-1 && _board[r+1][c] == num && _board[r+2][c] == num && _board[r+2][c+1] == num) {
			blocks.put(num, 2);
		} else if (r < N-1 && c > 1 &&  _board[r+1][c] == num && _board[r+1][c-1] == num && _board[r+1][c-2] == num) {
			blocks.put(num, 3);
		} else if (r < N-1 && c > 0 && c < N-1 && _board[r+1][c] == num && _board[r+1][c-1] == num && _board[r+1][c+1] == num) {
			blocks.put(num, 4);
		} else {
			blocks.put(num, 5);
		}		
	}
	// board 내 블록을 위에서부터 제거한다.
	private static void removeBlocks() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int num = _board[r][c];
				// 빈 공간이거나, 이미 조사한 블록이거나, 제거할 수 없는 블록인 경우
				if (num == 0 || visited[num] || blocks.get(num) == 5) continue;
				// 해당 블록을 제거한다.
				tryRemove(r, c, num);
			}
		}
	}
	// (r, c)를 기준점으로 하는 블록을 제거한다.
	private static void tryRemove(int r, int c, int num) {
		visited[num] = true; // 조사 완료 처리
		int type = blocks.get(num); // 타입 파악
		// 다른 블록은 위쪽, 그리고 왼쪽부터 순차적으로 제거해 나가면 문제가 없으나,
		// 2번 타입의 블록은 0번, 3번, 4번 블록이 오른쪽에 쌓여 있으면 그 블록을 (제거할 수 있다면) 제거한 후에
		// 제거 가능한 경우도 있기 때문에 우선 인접한 블록을 제거해본다.
		// 0번 블록이 바로 오른쪽에 쌓여있는지 조사
		if (type == 2 && c < N-1 && _board[r][c+1] > 0 && blocks.get(_board[r][c+1]) == 0 && locations[_board[r][c+1]][0] == r && locations[_board[r][c+1]][1] == c+1) {
        	int[] location = locations[_board[r][c+1]];
        	tryRemove(location[0], location[1], _board[r][c+1]);
		}
		// 3번 블록이 바로 오른쪽에 쌓여있는지 조사
		if (type == 2 && c < N-3 && _board[r][c+3] > 0 && blocks.get(_board[r][c+3]) == 3 && locations[_board[r][c+3]][0] == r && locations[_board[r][c+3]][1] == c+3) {
        	int[] location = locations[_board[r][c+3]];
        	tryRemove(location[0], location[1], _board[r][c+3]);
		}
		// 4번 블록이 바로 오른쪽에 쌓여있는지 조사
		if (type == 2 && c < N-2 && _board[r][c+2] > 0 && blocks.get(_board[r][c+2]) == 4 && locations[_board[r][c+2]][0] == r && locations[_board[r][c+2]][1] == c+2) {
			int[] location = locations[_board[r][c+2]];
			tryRemove(location[0], location[1], _board[r][c+2]);
		}
		// 검은 블록을 통해 현재 블록을 직사각형으로 만들 수 있는지 확인한 후 제거한다.
		if (type == 0 && isEmptyColumn(r, c+1) && isEmptyColumn(r, c+2)) {
			_board[r][c] = _board[r+1][c] = _board[r+1][c+1] = _board[r+1][c+2] = 0;
			cnt++;
		} else if (type == 1 && isEmptyColumn(r+1, c-1)) {
			_board[r][c] = _board[r+1][c] = _board[r+2][c] = _board[r+2][c-1] = 0;
			cnt++;
		} else if (type == 2 && isEmptyColumn(r+1, c+1)) {
			_board[r][c] = _board[r+1][c] = _board[r+2][c] = _board[r+2][c+1] = 0;
			cnt++;
		} else if (type == 3 && isEmptyColumn(r, c-2) && isEmptyColumn(r, c-1)) {
			_board[r][c] = _board[r+1][c] = _board[r+1][c-1] = _board[r+1][c-2] = 0;
			cnt++;
		} else if (type == 4 && isEmptyColumn(r, c-1) && isEmptyColumn(r, c+1)) {
			_board[r][c] = _board[r+1][c] = _board[r+1][c-1] = _board[r+1][c+1] = 0;
			cnt++;
		}
	}
	// 검은 블록은 위에서 떨어지기 때문에, (R, c)에 도달하려면 해당 위치를 포함해 그 위에 있는 모든 칸이 빈 칸이어야 한다.
	// 해당 함수는 이 조건을 조사하는 함수
	private static boolean isEmptyColumn(int R, int c) {
		for (int r = 0; r <= R; r++) {
			if (_board[r][c] > 0) return false;
		}
		return true;
	}

}
