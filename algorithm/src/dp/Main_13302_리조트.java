package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13302_리조트 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 리조트에 갈 수 없는 날을 기록하는 배열
		boolean[] cannotGo = new boolean[N+1];
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int day = Integer.parseInt(st.nextToken());
				cannotGo[day] = true;
			}
		}
		// 가능한 쿠폰의 개수의 최댓값
		int maxNumOfCoupon = Math.max((N+3)/3, ((N+5)/5)*2);
		// dp[day][coupon]은 day번째 날에 coupon개만큼의 쿠폰을 가지고 있을 때 지불해야 하는 최소 금액을 의미한다.
		int[][] dp = new int[N+1][maxNumOfCoupon+1];
		// 초기화
		for (int d = 0; d <= N; d++) {
			Arrays.fill(dp[d], Integer.MAX_VALUE/10);
		}
		dp[0][0] = 0;
		int priceForOneDay = 10000; // 하루 이용권의 금액
		int priceForThreeDay = 25000; // 3일 이용권의 금액
		int priceForFiveDay = 37000; // 5일 이용권의 금액
		// 날짜별, 쿠폰 개수별로 갱신
		for (int d = 0; d < N; d++) {
			for (int c = 0; c <= maxNumOfCoupon; c++) {
				// (d+1)일에 가지 않는 경우로 해당 일자에는 이용권을 살 수 없다.
				if (cannotGo[d+1]) dp[d+1][c] = Math.min(dp[d+1][c], dp[d][c]);
				// (d+1)일에 가는 경우로 해당 일자에 이용권을 산다. 
				else {
					// 하루 이용권을 사는 경우
					dp[d+1][c] = Math.min(dp[d+1][c], dp[d][c]+priceForOneDay);
					// 쿠폰 3장을 이용해 하루 이용권으로 교환하는 경우
					if (c <= maxNumOfCoupon-3) dp[d+1][c] = Math.min(dp[d+1][c], dp[d][c+3]);
					// 3일 이용권을 사는 경우
					if (c >= 1 && d <= N-3) dp[d+3][c] = Math.min(dp[d+3][c], dp[d][c-1]+priceForThreeDay);
					// 5일 이용권을 사는 경우
					if (c >= 2 && d <= N-5) dp[d+5][c] = Math.min(dp[d+5][c], dp[d][c-2]+priceForFiveDay);
				}
			}
		}
		// 리조트를 이용하는데 드는 최소 비용
		int minPrice = Integer.MAX_VALUE;
		for (int c = 0; c <= maxNumOfCoupon; c++) {
			minPrice = Math.min(minPrice, dp[N][c]);
		}
		System.out.println(minPrice);
	}

}
