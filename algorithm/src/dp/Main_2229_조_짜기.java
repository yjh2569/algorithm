package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2229_조_짜기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming을 활용
		// i번째 원소는 i번째 학생까지를 가지고 조 구성을 했을 때 조가 잘 짜여진 정도의 최댓값
		int[] dp = new int[N];
		// 초기값
		dp[1] = Math.abs(scores[1] - scores[0]);
		for (int i = 2; i < N; i++) {
			int max = scores[i];
			int min = scores[i];
			// j번째 학생까지 조 구성을 했을 때의 조가 잘 짜여진 정도의 최댓값과 
			// j+1번째 학생부터 i번째 학생까지 조 구성을 했을 때의 조가 잘 짜여진 정도의 합을
			// 각각 구해 최댓값을 구한다.
			for (int j = i-1; j >= 0; j--) {
				dp[i] = Math.max(dp[i], dp[j] + max - min);
				max = Math.max(max, scores[j]);
				min = Math.min(min, scores[j]);
			}
		}
		System.out.println(dp[N-1]);
	}

}
