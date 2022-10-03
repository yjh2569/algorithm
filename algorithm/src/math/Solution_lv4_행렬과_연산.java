package math;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution_lv4_행렬과_연산 {

	public static void main(String[] args) {
		// 테스트케이스
		int[][] res = solution(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, new String[] {"Rotate", "ShiftRow"});
		for (int i = 0; i < res.length; i++) {
			System.out.println(Arrays.toString(res[i]));
		}
	}
	
	public static int[][] solution(int[][] rc, String[] operations) {
		int R = rc.length;
		int C = rc[0].length;
		// 배열을 크게 세 개의 구역으로 나눈다.
		// 첫 번째 열, 마지막 열, 두 열 사이에 있는 행들의 집합
		// 두 열을 각각 ArrayDeque로 옮기고, 행들의 집합은 아래에 있는 deques에 옮긴다.
		ArrayDeque<ArrayDeque<Integer>> deques = new ArrayDeque<>();
		int[][] answer = new int[R][C];
		// 첫 번째 열과 마지막 열
		ArrayDeque<Integer> firstCol = new ArrayDeque<>();
		ArrayDeque<Integer> lastCol = new ArrayDeque<>();
		// 배열에 있는 모든 값들을 위에서 정의한 ArrayDeque에 옮긴다.
		for (int r = 0; r < R; r++) {
			ArrayDeque<Integer> deque = new ArrayDeque<>();
			// 첫 번째 열과 마지막 열에 있는 값들은 deques에 옮기지 않음에 유의
			for (int c = 1; c < C-1; c++) {
				deque.addLast(rc[r][c]);
			}
			deques.addLast(deque);
			firstCol.addLast(rc[r][0]);
			lastCol.addLast(rc[r][C-1]);
		}
		// 각 연산에 대한 적절한 ArrayDeque 연산 수행
		// 이때 각 연산의 시간 복잡도는 O(1)이 되어야 시간 초과가 발생하지 않는다.
		for (String operation : operations) {
			// 행을 옮기는 연산의 경우
			// firstCol과 lastCol의 마지막 원소를 맨 앞으로 옮긴다.
			// deques에 있는 행들 중 가장 뒤에 있는 행을 맨 앞으로 옮긴다.
			if (operation.equals("ShiftRow")) {
				firstCol.addFirst(firstCol.pollLast());
				lastCol.addFirst(lastCol.pollLast());
				deques.addFirst(deques.pollLast());
			// 원소들을 회전시키는 연산의 경우
			// firstCol의 맨 앞에 있는 원소를 deques의 맨 앞에 있는 행의 맨 앞에 둔다.
			// deques의 맨 앞에 있는 행의 맨 뒤에 있는 원소를 lastCol의 맨 앞에 둔다.
			// lastCol의 맨 뒤에 있는 원소를 deques의 맨 뒤에 있는 행의 맨 뒤에 둔다.
			// deques의 맨 뒤에 있는 행의 맨 앞에 있는 원소를 lastCol의 맨 뒤에 둔다.
			} else if (operation.equals("Rotate")) {
				deques.peekFirst().addFirst(firstCol.pollFirst());
				lastCol.addFirst(deques.peekFirst().pollLast());
				deques.peekLast().addLast(lastCol.pollLast());
				firstCol.addLast(deques.peekLast().pollFirst());
			}
		}
		// 연산 결과를 배열에 옮겨 담는다.
		for (int r = 0; r < R; r++) {
			ArrayDeque<Integer> deque = deques.pollFirst();
	        for (int c = 1; c < C-1; c++) {
	        	answer[r][c] = deque.pollFirst();
	        }
	        answer[r][0] = firstCol.pollFirst();
	        answer[r][C-1] = lastCol.pollFirst();
		}		
        return answer;
    }
}
