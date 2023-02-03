package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_5545_최고의_피자 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int priceOfPizza = Integer.parseInt(st.nextToken()); // 현재 피자의 가격
		int priceOfTopping = Integer.parseInt(st.nextToken());
		int kcalOfPizza = Integer.parseInt(br.readLine()); // 현재 피자의 열량
		Integer[] kcalsOfTopping = new Integer[N];
		for (int i = 0; i < N; i++) {
			kcalsOfTopping[i] = Integer.parseInt(br.readLine());
		}
		// 피자의 1원당 열량이 가장 높을 때 1원당 열량
		int max = kcalOfPizza/priceOfPizza;
		// 토핑의 가격은 모두 동일하므로, 열량이 가장 높은 토핑부터 피자에 올리는 게 유리하다.
		Arrays.sort(kcalsOfTopping, Collections.reverseOrder());
		// 열량이 높은 토핑부터 올리면서 1원당 열량이 가장 높은 경우를 구한다.
		for (int i = 0; i < N; i++) {
			priceOfPizza += priceOfTopping;
			kcalOfPizza += kcalsOfTopping[i];
			max = Math.max(max, kcalOfPizza/priceOfPizza);
		}
		System.out.println(max);
	}

}
