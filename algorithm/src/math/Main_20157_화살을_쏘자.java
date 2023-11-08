package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20157_화살을_쏘자 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 특정 방향으로 화살을 발사했을 때 맞출 수 있는 풍선의 개수를 HashMap으로 저장
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// |x|와 |y|의 최대공약수 l을 구하고, x와 y를 l로 나눠 방향벡터를 구한다.
			int l = gcd(x, y);
			String key = x/l+"_"+y/l; // 방향벡터를 문자열로 표현해 key로 한다.
			map.putIfAbsent(key, 0);
			map.put(key, map.get(key)+1);
		}
		int max = 0;
		for (String key : map.keySet()) {
			max = Math.max(map.get(key), max);
		}
		System.out.println(max);
	}
	// |x|와 |y|의 최대공약수를 구하는 함수
	public static int gcd(int x, int y) {
		while(y != 0){
	        int temp = x%y;
	        x = y;
	        y = temp;
	    }
	    return Math.abs(x);
	}
}
