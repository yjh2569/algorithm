package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1484_다이어트 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 현재 몸무게로 가능한 값을 저장하는 ArrayList
		ArrayList<Integer> results = new ArrayList<>();
		// 현재 몸무게를 y, 이전 몸무게를 x라 하면
		// G = y^2 - x^2 = (y+x)(y-x)
		// G, x, y는 모두 자연수이므로, 이는 부정방정식이라 할 수 있다.
		// y+x = m, y-x = n이라 하면 m > n이어야 하고,
		// y = (m+n)/2, x = (m-n)/2이므로 m+n과 m-n은 짝수여야 한다.
		// m+n이 짝수면 m-n은 짝수다.
		// 위를 정리하면, G = m*n을 만족하는 자연수 쌍 (m, n)을 찾고, 
		// m+n이 짝수라면 (m+n)/2의 값이 현재 몸무게로 가능한 값이다.
		for (int n = 1; n < Math.sqrt(G); n++) {
			if (G%n != 0 || (n+(G/n))%2 == 1) continue;
			results.add((n+(G/n))/2);
		}
		// 가능한 현재 몸무게가 없는 경우
		if (results.size() == 0) {
			System.out.println(-1);
			System.exit(0);
		}
		// 위 알고리즘을 거치면 results에는 가능한 현재 몸무게들이 내림차순으로 정렬된 상태로 저장된다.
		// 따라서 오름차순으로 출력하기 위해 거꾸로 순회한다.
		for (int i = results.size()-1; i >= 0; i--) {
			sb.append(results.get(i)).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
