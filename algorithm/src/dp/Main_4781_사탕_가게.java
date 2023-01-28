package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4781_사탕_가게 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			// 가격을 입력받을 때 "."을 기준으로 두 정수로 나눈 뒤 앞 정수*100 + 뒤 정수를 통해
			// (가격)*100의 값을 입력받는다.
			String[] temp = st.nextToken().split("\\.");
			int m = Integer.parseInt(temp[0])*100 + Integer.parseInt(temp[1]);
			// 입력의 끝
			if (n == 0 && m == 0) break;
			// 각 사탕의 칼로리를 저장하는 배열
			int[] kcals = new int[n];
			// 각 사탕의 가격을 저장하는 배열
			int[] ps = new int[n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				kcals[i] = Integer.parseInt(st.nextToken());
				temp = st.nextToken().split("\\.");
				ps[i] = Integer.parseInt(temp[0])*100 + Integer.parseInt(temp[1]);
			}
			// 원래는 2차원 배열로 dp[i][j]는 (i+1)번째 사탕까지 고려했을 때 j/100원을 가진 상태에서
			// 얻을 수 있는 최대 칼로리로 정의했으나
			// 공간복잡도를 줄임과 동시에 몇몇 연산을 절약할 수 있어 1차원 배열로 정의
			int[] dp = new int[m+1];
			for (int i = 0; i < n; i++) {
				// dp[j]는 (i+1)번째 사탕까지 고려했을 때 j/100원을 가진 상태에서
				// 얻을 수 있는 최대 칼로리
				int kcal = kcals[i];
				int p = ps[i];
				for (int j = p; j <= m; j++) {
					dp[j] = Math.max(dp[j-p] + kcal, dp[j]);
				}
			}
			sb.append(dp[m]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
