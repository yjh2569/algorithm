package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_12845_모두의_마블 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Integer[] cards = new Integer[N];
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		// 최대값
		long res = 0;
		// 카드들을 레벨이 높은 순서대로 정렬한 후, 가장 레벨이 높은 카드와 순차적으로 합성해나간다.
		Arrays.sort(cards, Collections.reverseOrder());
		for (int i = 1; i < N; i++) {
			res += cards[0] + cards[i];			
		}
		System.out.println(res);
	}

}
