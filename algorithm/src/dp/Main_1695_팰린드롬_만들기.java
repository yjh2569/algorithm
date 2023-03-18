package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1695_팰린드롬_만들기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming을 활용
		// dp[i][j]는 i번째 수부터 j번째 수까지의 부분 수열에 대해 팰린드롬이 되기 위해 추가해야 하는 수의 최소 개수를 의미한다. 
		int[][] dp = new int[N][N];
		// 길이가 짧은 부분수열부터 팰린드롬이 되기 위해 추가해야 하는 수의 최소 개수를 구한다.
		for (int l = 1; l < N; l++) {
			// 부분수열의 시작 지점을 지정한다.
			for (int i = 0; i < N-l; i++) {
				// 부분수열의 끝 지점을 지정한다.
				int j = i+l;
				// 현재 부분수열의 시작 지점의 수와 끝 지점의 수가 같다면 
				// i+1번째 수부터 j-1번째 수까지의 부분수열로 만든 팰린드롬에 i번째 수와 j번째 수를 그대로 추가하면 팰린드롬이 된다.
				if (arr[i] == arr[j]) dp[i][j] = dp[i+1][j-1];
				// 현재 부분수열의 시작 지점의 수와 끝 지점의 수가 다르다면
				// i+1번째 수부터 j-1번째 수까지의 부분수열로 만든 팰린드롬에
				// i번째 수를 부분수열 맨끝에 추가하거나, j번째 수를 부분수열 맨앞에 추가해 팰린드롬을 만들 수 있다.
				// 그중 추가하는 수의 개수가 적은 것을 dp[i][j]로 한다.
				else dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1])+1;
			}
		}
		// 위 과정을 거치면 dp[0][N-1]의 값을 구할 수 있다.
		System.out.println(dp[0][N-1]);
	}

}
