package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14501_퇴사 {
	// 상담에 걸리는 시간과 받을 수 있는 금액을 저장하는 클래스
	static class Consult {
		int t, p;
		public Consult(int t, int p) {
			this.t = t;
			this.p = p;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Consult[] consults = new Consult[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			consults[i] = new Consult(t, p);
		}
		// dynamic programming을 활용한다.
		// dp[i]는 i번째 날까지 상담을 진행했을 때 받을 수 있는 금액의 최댓값을 의미한다.
		int[] dp = new int[N+1];
		// (i+1)번째 날에 잡혀있는 상담을 고려한다.
		for (int i = 0; i < N; i++) {
			// (i+1)번째 날에 상담을 진행하지 않는 경우도 고려한다.
			dp[i+1] = Math.max(dp[i+1], dp[i]);
			Consult c = consults[i];
			// (i+1)번째 날에 잡혀있는 상담을 고려해 dp를 갱신한다.
			if (i+c.t <= N) dp[i+c.t] = Math.max(dp[i+c.t], dp[i]+c.p);
		}
		System.out.println(dp[N]);
	}

}
