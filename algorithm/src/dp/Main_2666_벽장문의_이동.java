package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2666_벽장문의_이동 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 계산의 편의를 위해 i번 벽장은 (i-1)번 벽장으로 간주한다.
		int a = Integer.parseInt(st.nextToken())-1;
		int b = Integer.parseInt(st.nextToken())-1;
		int M = Integer.parseInt(br.readLine());
		// 브루트포스 알고리즘을 통해 사용할 벽장 번호가 주어질 때마다 벽장을 이동시키는 모든 방법을 고려해야 한다.
		// 만약 i번 벽장과 j번 벽장의 문이 열려있는 상태에서 k번 벽장의 문을 열고 싶다고 할 때,
		// k <= i인 경우에는 i번 벽장으로만 문을 옮겨도 되고, k >= j인 경우에는 j번 벽장으로만 문을 옮겨도 된다.
		// 하지만 i < k < j인 경우 i번 벽장으로 문을 옮길지, 아니면 j번 벽장으로 문을 옮길지에 따라 추후에 열 벽장에 따라 
		// 벽장 이동 횟수가 차이가 날 수 있다. 따라서 i < k < j인 경우에는 두 가지 경우 모두 다 고려해야 한다.
		// 다만 이렇게 하면 나중에 고려할 경우의 수가 매우 많아질 수 있다.
		// 따라서 dynamic programming을 통해 고려해야 할 가짓수를 줄인다.
		// dp[i][j][k]는 i번째로 주어진 열고 싶은 벽장의 번호가 주어졌을 때 벽장 문을 옮겨 j번째 벽장 문과 k번째 벽장 문이
		// 열려 있을 때 벽장 문의 이동 횟수의 최소값이다.
		int[][][] dp = new int[M+1][N][N];
		// 초기화
		for (int i = 0; i <= M; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}
		// 주어진 벽장 번호를 정렬
		int temp_a = Math.min(a, b);
		int temp_b = Math.max(a, b);
		// 초기화
		dp[0][temp_a][temp_b] = 0;
		// 각 열고 싶은 벽장 번호마다 조건을 만족시키도록 벽장 문을 옮기고 벽장 문의 이동 횟수의 최소값을 구한다.
		for (int i = 1; i <= M; i++) {
			// 열고 싶은 벽장 번호
			// a, b를 보정했던 것처럼 to도 보정
			int to = Integer.parseInt(br.readLine())-1;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					// 지금까지 벽장 문을 이동시켜서 나올 수 있는 경우일 때만 고려
					if (dp[i-1][j][k] < Integer.MAX_VALUE) {
						// 아래 경우는 열고자 하는 벽장 문, 현재 열려 있는 벽장 문 2개까지 해서 총 3개의 문만 고려
						// 열고자 하는 벽장 문이 가장 왼쪽에 있을 때
						if (to <= j) dp[i][to][k] = Math.min(dp[i-1][j][k] + j - to, dp[i][to][k]);
						// 열고자 하는 벽장 문이 가장 오른쪽에 있을 때
						else if (to >= k) dp[i][j][to] = Math.min(dp[i-1][j][k] + to - k, dp[i][j][to]);
						// 열고자 하는 벽장 문이 열려 있는 문 사이에 있을 때
						// 두 가지 경우 모두 고려해야 함에 유의
						else {
							dp[i][to][k] = Math.min(dp[i-1][j][k] + to - j, dp[i][to][k]);
							dp[i][j][to] = Math.min(dp[i-1][j][k] + k - to, dp[i][j][to]);
						}
					}
				}
			}
		}
		// 벽장 문의 이동 횟수의 최소값
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				min = Math.min(min, dp[M][i][j]);
			}
		}
		System.out.println(min);
	}

}
