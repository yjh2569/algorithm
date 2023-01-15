package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061_모노미노도미노_2 {
	static boolean[][] board;
	static int score;
	public static void main(String[] args) throws IOException {
		// 보드 내 각 좌표에 블록이 있는지를 표시하는 배열
		board = new boolean[10][10];
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 입력된 블록을 보드에 놓는다.
			putBlock(t, x, y);
		}
		// 초륵색 보드와 파란색 보드에 블록이 있는 칸의 개수를 센다.
		int cnt = countBlock();
		System.out.println(score);
		System.out.println(cnt);
	}
	// 블록을 보드에 놓는 함수
	// t = 1인 경우, 1*1 블록을 (x, y) 위에 놓는다.
	// t = 2인 경우, 1*2 블록을 (x, y), (x, y+1) 위에 놓는다.
	// t = 3인 경우, 2*1 블록을 (x, y), (x+1, y) 위에 놓는다.
	private static void putBlock(int t, int x, int y) {
		if (t == 1) {
			moveBlock(x, y, x, y);
		} else if (t == 2) {
			moveBlock(x, y, x, y+1);
		} else if (t == 3) {
			moveBlock(x, y, x+1, y);
		}
		getScore();
		removeBlock();
	}
	// (x1, y1), (x2, y2) 칸을 차지하고 있는 블록을 초록색 보드와 파란색 보드로 옮기는 함수
	private static void moveBlock(int x1, int y1, int x2, int y2) {
		moveBlockToGreen(x1, y1, x2, y2);
		moveBlockToBlue(x1, y1, x2, y2);
	}	
	// (x1, y1), (x2, y2) 칸을 차지하고 있는 블록을 초록색 보드로 옮기는 함수
	private static void moveBlockToGreen(int x1, int y1, int x2, int y2) {
		while (check(x1, y1) && check(x2, y2) && !board[x1][y1] && !board[x2][y2]) {
			x1++; x2++;
		}
		board[--x1][y1] = board[--x2][y2] = true;
		
	}
	// (x1, y1), (x2, y2) 칸을 차지하고 있는 블록을 파란색 보드로 옮기는 함수
	private static void moveBlockToBlue(int x1, int y1, int x2, int y2) {
		while (check(x1, y1) && check(x2, y2) && !board[x1][y1] && !board[x2][y2]) {
			y1++; y2++;
		}
		board[x1][--y1] = board[x2][--y2] = true;
	}
	// 경계 확인을 위한 함수
	private static boolean check(int x, int y) {
		return 0<=x && x<10 && 0<=y && y<10;
	}
	// 초록색 보드와 파란색 보드에서 행 또는 열에 있는 블록을 제거해 점수를 얻는 함수
	private static void getScore() {
		outer: for (int x = 9; x >= 6; x--) {
			for (int y = 0; y < 4; y++) {
				if (!board[x][y]) continue outer;
			}
			// 초록색 보드 내 (x-4)번째 행에 블록이 모두 있는 경우 
			score++;
			for (int y = 0; y < 4; y++) {
				board[x][y] = false;
			}
			// (x-4)번째 행 위에 있는 초록색 보드 내 모든 블록을 한 칸씩 아래로 이동시킨다.
			// 이때 제거한 (x-4)번째 행 바로 위에 있는 행이 (x-4)번째 행으로 내려왔기 때문에 해당 행에 대해 다시 작업을 수행해야 한다.
			// 따라서 x를 1 증가시켜준다.
			moveBlockInGreen(x++);
		}
		outer: for (int y = 9; y >= 6; y--) {
			for (int x = 0; x < 4; x++) {
				if (!board[x][y]) continue outer;
			}
			// 초록색 보드 내 (y-4)번째 행에 블록이 모두 있는 경우 
			score++;
			for (int x = 0; x < 4; x++) {
				board[x][y] = false;
			}
			moveBlockInBlue(y++);
		}
	}
	// 연한 초록색 혹은 파란색 보드에 블록이 있는 경우 초록색 혹은 파란색 보드의 맨 아래 행 또는 맨 오른쪽 열의 블록을 제거하는 함수
	private static void removeBlock() {
		int count = 0; // 제거할 행 또는 열의 개수
		// 연한 초록색 보드에 블록이 있는지 조사
		outer: for (int x = 4; x < 6; x++) {
			for (int y = 0; y < 4; y++) {
				if (board[x][y]) {
					count++;
					continue outer;
				}
			}
		}
		// 제거할 행이 있는 경우 맨 아래 행을 지운다.
		for (int i = 0; i < count; i++) {
			moveBlockInGreen(9);
		}
		count = 0;
		// 연한 파란색 보드에 블록이 있는지 조사
		outer: for (int y = 4; y < 6; y++) {
			for (int x = 0; x < 4; x++) {
				if (board[x][y]) {
					count++;
					continue outer;
				}
			}
		}
		// 제거할 열이 있는 경우 맨 오른쪽 열을 지운다.
		for (int i = 0; i < count; i++) {
			moveBlockInBlue(9);
		}
	}
	// 초록색 보드에서 start_x 행 위에 있는 모든 블록을 한 행씩 아래로 내리는 함수
	private static void moveBlockInGreen(int start_x) {
		for (int x = start_x; x > 4; x--) {
			for (int y = 0; y < 4; y++) {
				board[x][y] = board[x-1][y];
				board[x-1][y] = false;
			}
		}
	}
	// 파란색 보드에서 start_y 열 왼쪽에 있는 모든 블록을 한 열씩 오른쪽으로 옮기는 함수
	private static void moveBlockInBlue(int start_y) {
		for (int y = start_y; y > 4; y--) {
			for (int x = 0; x < 4; x++) {
				board[x][y] = board[x][y-1];
				board[x][y-1] = false;
			}
		}
	}
	// 초록색 보드와 파란색 보드 내 블록이 있는 칸의 개수를 세는 함수
	private static int countBlock() {
		int cnt = 0;
		for (int i = 6; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j]) cnt++;
				if (board[j][i]) cnt++;
			}
		}
		return cnt;
	}
}
