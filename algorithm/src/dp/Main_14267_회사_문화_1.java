package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14267_회사_문화_1 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		// 직속 상사의 번호 저장 배열
		int[] superiors = new int[N+1];
		for (int i = 1; i <= N; i++) {
			superiors[i] = Integer.parseInt(st.nextToken());
		}
		// 각 직원이 받은 칭찬 수치 저장 배열
		int[] compliments = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int junior = Integer.parseInt(st.nextToken());
			int compliment = Integer.parseInt(st.nextToken());
			compliments[junior] += compliment;
		}
		// 상사의 번호가 항상 부하의 번호보다 작으므로, 상사 쪽을 우선으로 칭찬 수치의 합을 구하고,
		// 이후 직속 부하가 받은 칭찬 수치의 합을 구하는 식으로 진행한다.
		// 즉, dynamic programming을 이용한다.
		int[] res = new int[N+1]; // 각 직원이 받은 칭찬 수치의 합
		for (int i = 2; i <= N; i++) {
			res[i] += res[superiors[i]] + compliments[i];
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(res[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
