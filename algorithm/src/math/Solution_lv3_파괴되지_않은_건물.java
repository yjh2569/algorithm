package math;

public class Solution_lv3_파괴되지_않은_건물 {

	public static void main(String[] args) {
		int result = solution(new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}, new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}});
		System.out.println(result);
	}
	
	public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int N = board.length; // board의 행 길이
        int M = board[0].length; // board의 열 길이
        // 공격 혹은 회복 스킬로 인해 board의 각 지점의 피해 혹은 회복량을 나타내는 이차원 배열
        int[][] affected = new int[N][M];
        // 각 공격 혹은 회복마다 모든 칸을 갱신하는 것이 아니라,
        // (r1, c1), (r1, c2+1), (r2+1, c1), (r2+1, c2+1) 칸만 갱신한다.
        // (r1, c2+1)과 (r2+1, c1)은 부호를 반대로 저장한다.
        for (int[] s : skill) {
        	int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
        	int t = s[0] == 1 ? -1 : 1;
        	affected[r1][c1] += degree * t;
        	if (c2 < M-1) affected[r1][c2+1] -= degree * t;
        	if (r2 < N-1) affected[r2+1][c1] -= degree * t;
        	if (r2 < N-1 && c2 < M-1) affected[r2+1][c2+1] += degree * t;
        }
        // 나머지 칸은 누적합을 통해 계산한다.
        for (int i = 0; i < N; i++) {
        	for (int j = 1; j < M; j++) {
        		affected[i][j] += affected[i][j-1];
        	}
        }
        for (int j = 0; j < M; j++) {
        	for (int i = 1; i < N; i++) {
        		affected[i][j] += affected[i-1][j];
        	}
        }
        // 위에서 계산한 결과를 토대로 board의 각 칸이 파괴되지 않았는지 확인한다.
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < M; j++) {
        		if (board[i][j] + affected[i][j] > 0) answer++;
        	}
        }
        return answer;
    }

}
