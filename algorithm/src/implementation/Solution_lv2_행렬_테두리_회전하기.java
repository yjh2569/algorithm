package implementation;

import java.util.Arrays;

public class Solution_lv2_행렬_테두리_회전하기 {
	static int[][] matrix;
	static int[] mins;
	static int idx;
	public static void main(String[] args) {
		int[] result = solution(6, 6, new int[][] {{2,2,5,4},{3,3,6,6},{5,1,6,3}});
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(int rows, int columns, int[][] queries) {
		// 행렬
        matrix = new int[rows][columns];
        // 초기화
        for (int i = 0; i < rows; i++) {
        	for (int j = 0; j < columns; j++) {
        		matrix[i][j] = i*columns+j+1;
        	}
        }
        // 각 회전 시행 시 회전에 의해 위치가 바뀌는 숫자들 중 가장 작은 숫자들
        mins = new int[queries.length];
        // mins 기록을 위한 보조 변수
        idx = 0;
        for (int[] query : queries) {
        	rotate(query[0]-1, query[1]-1, query[2]-1, query[3]-1);
        }
        return mins;
    }
	// (x1, y1)과 (x2, y2)를 꼭짓점으로 하는 직사각형 테두리를 시계 방향으로 회전시키는 함수
	private static void rotate(int x1, int y1, int x2, int y2) {
		// (x1, y1)은 임시로 저장
		int temp = matrix[x1][y1];
		// 회전에 의해 자리가 바뀌는 수들 중 가장 작은 값
		int min = temp;
		// 왼쪽 열의 숫자들을 위로 옮긴다.
		for (int r = x1; r < x2; r++) {
			matrix[r][y1] = matrix[r+1][y1];
			min = Math.min(min, matrix[r][y1]);
		}
		// 아래쪽 행의 숫자들을 왼쪽으로 옮긴다.
		for (int c = y1; c < y2; c++) {
			matrix[x2][c] = matrix[x2][c+1];
			min = Math.min(min, matrix[x2][c]);
		}
		// 오른쪽 열의 숫자들을 아래로 옮긴다.
		for (int r = x2; r > x1; r--) {
			matrix[r][y2] = matrix[r-1][y2];
			min = Math.min(min, matrix[r][y2]);
		}
		// 위쪽 행의 숫자들을 오른쪽으로 옮긴다.
		for (int c = y2; c > y1; c--) {
			matrix[x1][c] = matrix[x1][c-1];
			min = Math.min(min, matrix[x1][c]);
		}
		matrix[x1][y1+1] = temp;
		mins[idx++] = min;
	}

}
