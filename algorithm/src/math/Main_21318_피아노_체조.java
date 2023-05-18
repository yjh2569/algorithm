package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_21318_피아노_체조 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] songs = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			songs[i] = Integer.parseInt(st.nextToken());
		}
		// 누적합을 활용해 O(N) 안에 해결할 수 있다.
		// sum[i]는 1번째 곡부터 (i+1)번째 곡까지를 순서대로 연주할 때 실수하는 곡의 개수를 의미한다.
		int[] sum = new int[N+1];
		// 실수하는 곡만 체크
		for (int i = 1; i < N; i++) {
			if (songs[i] > songs[i+1]) sum[i]++;
		}
		// 누적합을 적용하면 O(N)만에 sum을 구할 수 있다.
		for (int i = 1; i <= N; i++) {
			sum[i] += sum[i-1];
		}
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < Q; q++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// x번째 곡부터 y번째 곡까지 연주할 때 실수하는 곡의 개수는 sum[y-1] - sum[x-1]이다.
			sb.append(sum[y-1] - sum[x-1]).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
