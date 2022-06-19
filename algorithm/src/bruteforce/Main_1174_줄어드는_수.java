package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_1174_줄어드는_수 {
	static ArrayList<Long> arr;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new ArrayList<>();
		// 0~9까지의 숫자를 선택해 윗 자리에 차례대로 붙인다.
		subset(0, 0, 1);
		// 1번째 숫자가 0이 나오도록 하기 위해 0을 하나 더 추가
		arr.add(0L);
		// 정렬
		Collections.sort(arr);
		if (N < arr.size()) System.out.println(arr.get(N));
		else System.out.println(-1);
	}
	private static void subset(int cnt, long sum, long t) {
		if (cnt == 10) {
			if (t > 1) arr.add(sum);
			return;
		}
		// 윗 자리에 cnt를 붙이지 않는 경우
		subset(cnt+1, sum, t);
		// 윗 자리에 cnt를 붙이는 경우
		subset(cnt+1, sum+cnt*t, t*10);
	}

}
