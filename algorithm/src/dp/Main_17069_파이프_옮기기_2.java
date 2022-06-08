package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17069_파이프_옮기기_2 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dynamic programming을 적용한다.
		// dp[i][j][k]는 (i, j)에서 파이프가 놓인 방향이 k일 때까지 이동시키는 경우의 수를 뜻한다.
		// 여기서 k는 가로는 0, 대각선은 1, 세로는 2로 나타낸다.
		long[][][] dp = new long[N][N][3];
		// 초기값 설정
		// 현재 가로로 놓여진 파이프의 오른쪽 끝을 기준으로 한다.
		dp[0][1][0] = 1;
		// 각 좌표에 대해 경우의 수를 bottom-up 방식으로 구한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 인덱스 값과 벽에 막힐 수 있는지는 고려하며 이동시킨다.
				// 가로 방향으로 파이프 이동 : 가로 방향일 때와 대각선 방향일 때 가능
				if (j < N-1 && map[i][j+1] == 0) dp[i][j+1][0] += dp[i][j][0] + dp[i][j][1];
				// 세로 방향으로 파이프 이동 : 세로 방향일 때와 대각선 방향일 때 가능
				if (i < N-1 && map[i+1][j] == 0) dp[i+1][j][2] += dp[i][j][1] + dp[i][j][2];
				// 대각선 방향으로 파이프 이동 : 가로, 세로, 대각선 방향 모두 가능
				if (i < N-1 && j < N-1 && map[i+1][j] == 0 && map[i+1][j+1] == 0 && map[i][j+1] == 0) {
					dp[i+1][j+1][1] += dp[i][j][0] + dp[i][j][1] + dp[i][j][2];
				}
			}
		}
		// (N-1, N-1)에 가로, 세로, 대각선 방향으로 도달할 수 있는 모든 경우를 더한다.
		long res = 0;
		for (int k = 0; k < 3; k++) {
			res += dp[N-1][N-1][k];
		}
		System.out.println(res);
	}

}
