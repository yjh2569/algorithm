package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2780_비밀번호 {
	static int CONST = 1_234_567; // 큰 수가 나오는 것을 방지하기 위한 상수
	// 비밀번호 기계를 배열로 표현
	static int[][] lock = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {0, -1, -1}};
	static int[][] dp;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int maxLength = 1;
		// dynamic programming을 이용한다.
		// dp[i][j]는 비밀번호의 길이가 i일 때 끝자리가 j가 되는 경우의 수를 의미한다.
		dp = new int[1001][10];
		// 초기화
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		StringBuilder sb = new StringBuilder(); // 출력 저장용 StringBuilder
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// 이전에 구한 가장 큰 길이에 대한 경우의 수부터 N까지 차례대로 경우의 수를 구해나간다.
			for (int i = maxLength; i < N; i++) {
				calculateNextLength(i);
			}
			// 길이가 N일 때의 총 경우의 수를 구해 sb에 저장
			sb.append(getSum(N)).append("\n");
			// 최대 길이 갱신
			maxLength = Math.max(maxLength, N);
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 길이가 n인 비밀번호로 가능한 모든 경우의 수를 구하는 함수
	private static int getSum(int n) {
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			sum = (sum + dp[n][i])%CONST;
		}
		return sum;
	}
	// 길이가 l인 비밀번호로 가능한 경우의 수로부터 길이가 (l+1)인 비밀번호로 가능한 경우의 수를 구하는 함수
	private static void calculateNextLength(int l) {
		for (int n = 0; n < 10; n++) {
			calculateNextLengthForEachNum(l, n);
		}		
	}
	// 길이가 l이면서 끝자리가 n인 비밀번호의 개수로부터 길이가 (l+1)인 비밀번호의 개수를 구하는 함수
	private static void calculateNextLengthForEachNum(int l, int n) {
		int r = 3, c = 0; // 숫자 0이 비밀번호 기계에 위치한 좌표
		// 나머지 숫자의 경우 순차적으로 위치해 있으므로 규칙을 활용해 좌표를 구한다.
		if (n != 0) {
			r = (n-1)/3;
			c = (n-1)%3;
		}
		// n과 이웃한 숫자를 뒤에 추가함으로써 비밀번호를 만들 수 있으므로 이를 이용해 길이가 (l+1)인 비밀번호의 경우의 수를 구한다.
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			// 실제로 이웃한 숫자인지를 확인한다.
			if (check(nr, nc)) {
				dp[l+1][lock[nr][nc]] = (dp[l+1][lock[nr][nc]] + dp[l][n])%CONST;
			}
		}		
	}
	// 경계 확인 및 해당 좌표에 실제로 숫자가 있는지를 확인하는 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<4 && 0<=c && c<3 && lock[r][c] != -1;
	}

}
