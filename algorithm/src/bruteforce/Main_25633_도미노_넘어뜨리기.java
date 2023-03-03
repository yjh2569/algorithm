package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25633_도미노_넘어뜨리기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dominos = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dominos[i] = Integer.parseInt(st.nextToken());
		}
		// 완벽하게 나열할 수 있는 도미노의 최댓값
		int max = 0;
		// 어떤 도미노부터 쓰러뜨릴지를 결정하고, 이후로는 완벽하게 나열할 수 있는 도미노를 찾아나간다.
		// 이후 max를 갱신한다.
		for (int i = 1; i <= N; i++) {
			int weight = dominos[i]; // 쓰러뜨린 도미노의 무게의 총합
			int num = 1; // 쓰러뜨린 도미노의 개수
			for (int j = i+1; j <= N; j++) {
				// 완벽하게 나열할 수 있는 도미노를 찾은 경우
				if (weight >= dominos[j]) {
					weight += dominos[j];
					num++;
				}
			}
			// max 갱신
			max = Math.max(max, num);
		}
		System.out.println(max);
	}
}
