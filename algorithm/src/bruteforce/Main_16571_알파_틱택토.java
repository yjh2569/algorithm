package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16571_알파_틱택토 {
	static int[][] board;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[3][3];
		int K = 0; // 지난 턴의 수
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != 0) K++;
			}
		}
		// 지난 턴의 수가 짝수면 X를 놓을 차례고, 그렇지 않으면 O를 놓을 차례다.
		int start = K%2 == 0 ? 1 : 2;
		// 모든 돌을 놓았을 경우 항상 무승부다.
		if (K == 9) System.out.println('D');
		else {
			// 시뮬레이션을 통해 start가 이길지 결정한다.
			int res = simulation(start);
			if (res == 1) System.out.println('W');
			else if (res == 0) System.out.println('D');
			else System.out.println('L');
		}
	}
	// cur의 승부 결과가 어떻게 될지 결정하는 함수
	private static int simulation(int cur) {
		// 이미 승부가 난 경우(이전 수에 의해 승부가 결정난 경우로, 이는 즉 cur가 졌음을 의미한다.)
		if (resultCheck(cur)) return -1;
		int min = 2; // 이후의 돌이 모두 최선의 수를 뒀을 때, 가능한 상대방의 경기 결과의 최소 결과
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// 돌을 두지 못하는 경우
				if (board[i][j] != 0) continue;
				board[i][j] = cur;
				// 돌을 (i, j)에 둔 후, 다음 돌을 놓는 것을 반복한 뒤 경기 결과의 최소 결과를 기록한다.
				// 최소 결과란, 경기 결과를 승 > 무 > 패로 비교할 수 있을 때 최솟값을 의미한다.
				min = Math.min(min, simulation(cur == 1 ? 2 : 1));
				// 백트래킹
				board[i][j] = 0;
			}
		}
		if (min == 1) return -1; // 상대방이 항상 이기는 경우
		else if (min == 2 || min == 0) return 0; // 상대방이 무승부까지만 가능한 경우
		else return 1; // 자신이 이길 수 있는 경우
	}
	// 현재 turn의 차례일 때 승부가 이미 결정났는지 확인하는 함수
	private static boolean resultCheck(int turn) {
		int prevTurn = turn == 1 ? 2 : 1;
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == prevTurn) return true;
			if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == prevTurn) return true;
		}
		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == prevTurn) return true;
		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == prevTurn) return true;
		return false;
	}

}
