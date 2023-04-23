package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1756_피자_굽기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] oven = new int[D+1]; // 각 오븐의 깊이에서 넣을 수 있는 피자의 최대 크기
		oven[1] = Integer.parseInt(st.nextToken());
		// 입력을 받는 과정에서 각 오븐의 깊이까지 피자를 넣을 수 있는지를 판별하기 위해
		// oven[i]는 oven[1] ~ oven[i]의 최솟값으로 한다. 
		for(int i = 2; i <= D; i++) {
			oven[i] = Integer.parseInt(st.nextToken());
			if (oven[i] > oven[i-1]) oven[i] = oven[i-1];
		}
		st = new StringTokenizer(br.readLine());
		int idx = D; // 마지막에 넣는 피자의 깊이
		outer : while (N-- > 0) {
			int pizza = Integer.parseInt(st.nextToken());
			// 피자를 넣을 수 있는 깊이를 찾는다.
			while (pizza > oven[idx]) {
				--idx;
				// 피자를 넣을 수 없는 경우
				if (idx == 0) break outer;
			}
			// 피자를 다 넣은 경우
			if (N == 0) {
				System.out.print(idx);
				return;
			}
			// 피자를 넣은 위치가 깊이가 1인 지점인 경우 더 이상 피자를 넣을 수 없다.
			if (--idx == 0) break;
		}
		// 모든 피자를 넣을 수 없는 경우
		System.out.print(0);
	}
}
