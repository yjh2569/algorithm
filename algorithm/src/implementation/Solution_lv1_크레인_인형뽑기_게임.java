package implementation;

import java.util.Stack;

public class Solution_lv1_크레인_인형뽑기_게임 {

	public static void main(String[] args) {
		int result = solution(new int[][] {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}}, new int[] {1,5,3,5,1,2,1,4});
		System.out.println(result);
	}
	
	public static int solution(int[][] board, int[] moves) {
		// 바구니를 Stack으로 표현
        Stack<Integer> stack = new Stack<>();
        int R = board.length;
        // 사라진 인형 개수
        int cnt = 0;
        for (int move : moves) {
            move--; // 인덱스 보정을 위해 1 감소
            // move열에서 가장 위에 있는 인형을 찾는다.
            int r = 0;
            while (r < R && board[r][move] == 0) r++;
            // move열에 인형이 없는 경우
            if (r == R) continue;
            int doll = board[r][move]; // 인형
            // 인형 제거
            board[r][move] = 0;
            // stack 맨 위에 doll이 있는 경우
            if (!stack.isEmpty() && doll == stack.peek()) {
            	cnt += 2;
                stack.pop();
            // 그렇지 않은 경우
            } else {
                stack.push(doll);
            }
        }        
        return cnt;
    }

}
