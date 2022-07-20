package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12869_뮤탈리스크 {
	// dp[i][j][k]는 각 scv의 체력이 i, j, k일 때 공격 횟수의 최소값을 저장한다.
	// 이때 scv가 2마리 이하인 경우도 있는데, 이 경우 존재하지 않는 scv는 체력이 0인 scv로 간주한다.
	static int[][][] dp;
	// 최대 3마리의 SCV를 공격할 때마다 어떤 순서로 공격하는지에 따라 각 SCV가 입는 피해의 모든 경우를 저장 
	static int[][] perms = {{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {3, 9, 1}, {1, 3, 9}, {1, 9, 3}};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// N의 값에 상관없이 항상 scvs 배열의 크기를 3으로 한다.
		int[] scvs = new int[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scvs[i] = Integer.parseInt(st.nextToken());
		}
		// N의 값에 따라 dp 배열의 크기를 다르게 한다.
		if (N == 1) {
			dp = new int[scvs[0]+1][1][1];
		} else if (N == 2) {
			dp = new int[scvs[0]+1][scvs[1]+1][1];			
		} else {
			dp = new int[scvs[0]+1][scvs[1]+1][scvs[2]+1];
		}
		// dp 배열 초기화
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}
		dp[0][0][0] = 0;
		// DFS를 통해 공격 횟수의 최소값을 알아낸다.
		dfs(scvs[0], scvs[1], scvs[2]);
		System.out.println(dp[scvs[0]][scvs[1]][scvs[2]]);
	}
	private static void dfs(int i, int j, int k) {
		// base condition
		if (i == 0 && j == 0 && k == 0) {
			return;
		}
		// 공격 순서에 따른 각 경우에 대해 공격해본 뒤 다음 공격에 대한 DFS를 계속 수행한다.
		for (int d = 0; d < 6; d++) {
			int[] next = perms[d];
			int ni = Math.max(0, i-next[0]);
			int nj = Math.max(0, j-next[1]);
			int nk = Math.max(0, k-next[2]);
			// 아직 체력이 (ni, nj, nk)일 때의 경우를 시도하지 않았다면 DFS를 좀더 수행한다.
			if (dp[ni][nj][nk] == Integer.MAX_VALUE) dfs(ni, nj, nk);
			// 위의 결과를 바탕으로 체력이 (i, j, k)인 경우의 공격 횟수의 최소값을 최신화한다.
			dp[i][j][k] = Math.min(dp[i][j][k], dp[ni][nj][nk]+1);
		}
	}
}
