package dp;

import java.util.*;

public class Solution_lv3_코딩_테스트_공부 {

	static int maxAlgo = 0;
	static int maxCode = 0;
	static final int ALGO = 1;
	static final int CODE = 2;
	static final int INF = Integer.MAX_VALUE/10;
	static int[][] dp;

	public static void main(String[] args) {
		int result = solution(0, 0,
				new int[][] { { 0, 0, 2, 1, 2 }, { 4, 5, 3, 1, 2 }, { 4, 11, 4, 0, 2 }, { 10, 4, 0, 4, 2 } });
		System.out.println(result);
	}
	
	public static int solution(int alp, int cop, int[][] problems) {
		// 목표로 해야 하는 알고력, 코딩력을 정한다.
		for (int[] problem : problems) {
			maxAlgo = Math.max(maxAlgo, problem[0]);
			maxCode = Math.max(maxCode, problem[1]);
		}
		// dynamic programming을 활용
		// dp[i][j]는 알고력 i, 코딩력 j가 되기 위해 들여야 하는 시간의 최소값을 의미한다.
		dp = new int[Math.max(alp, maxAlgo) + 1][Math.max(cop, maxCode) + 1];
		// 초기화
		for (int i = 0; i <= Math.max(alp, maxAlgo); i++) {
			Arrays.fill(dp[i], INF);
		}
		// DFS를 통해 답을 도출한다.
		return dfs(alp, cop, problems);
	}
	
	static int dfs(int algo, int code, int[][] problems) {
		// 목표에 도달한 경우
		if (maxAlgo <= algo && maxCode <= code) {
			return 0;
		}
		// 이미 해당 알고력과 코딩력에 대한 값을 구한 경우
		if (dp[algo][code] != INF) {
			return dp[algo][code];
		}
		// 값을 구하기 시작하되, 재귀 중 만날 경우 위 조건에 걸리도록 값을 변경
		dp[algo][code] = INF + 1;
		// 알고리즘 공부 혹은 코딩 공부를 할 때 나올 수 있는 최소값을 구한다.
		dp[algo][code] = Math.min(dp[algo][code], 1 + dfs(cal(algo + 1, ALGO), code, problems));
		dp[algo][code] = Math.min(dp[algo][code], 1 + dfs(algo, cal(code + 1, CODE), problems));
		// 각 문제를 풀 수 있을 때, 해결한 경우에 대한 재귀를 수행한 뒤 나온 결과값에 시간을 더해 최소값을 갱신한다.
		for (int[] problem : problems) {
			if (problem[0] <= algo && problem[1] <= code) {
				dp[algo][code] = Math.min(dp[algo][code],
						problem[4] + dfs(cal(algo + problem[2], ALGO), cal(code + problem[3], CODE), problems));
			}
		}

		return dp[algo][code];
	}
	// 값이 maxAlgo 혹은 maxCode를 넘어서지 않도록 하기 위한 함수
	static int cal(int value, int judge) {
		if (judge == ALGO) {
			return Math.min(maxAlgo, value);
		} else {
			return Math.min(maxCode, value);
		}
	}
}
