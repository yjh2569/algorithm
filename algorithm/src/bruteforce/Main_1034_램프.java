package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1034_램프 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 각 숫자들을 저장하는 배열
		long[] nums = new long[N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == '1') nums[i] += 1l << j;
			}
		}
		int K = Integer.parseInt(br.readLine());
		Map<Long, Integer> counts = new HashMap<>(); // 각 행의 숫자가 몇 번 나오는지를 나타내는 map
		Map<Long, Integer> cntOfZeros = new HashMap<>(); // 각 행의 숫자에서 0이 몇 번 나오는지를 나타내는 map
		for (int i = 0; i < N; i++) {
			counts.putIfAbsent(nums[i], 0);
			counts.put(nums[i], counts.get(nums[i])+1);
			if (cntOfZeros.containsKey(nums[i])) continue;
			int cnt = 0;
			for (int j = 0; j < M; j++) {
				if ((nums[i] & (1l << j)) == 0) cnt++;
			}
			cntOfZeros.putIfAbsent(nums[i], cnt);
		}
		int max = 0; // 켜져있는 행의 개수의 최댓값
		// 각 행의 숫자에 대해 0을 모두 1로 바꾸면 행이 켜지지만, 이럴려면 K와 0의 개수를 2로 나눈 나머지가 같아야 하고,
		// K가 0의 개수보다 크거나 같아야 한다.
		// 만약 위 조건을 만족하면, 0을 모두 1로 바꿈으로써 해당 행의 숫자와 같은 모든 행을 켤 수 있다.
		// 해당 행과 다른 숫자의 행은 꺼지게 된다.
		for (Long num : counts.keySet()) {
			if (K%2 != cntOfZeros.get(num)%2 || K < cntOfZeros.get(num)) continue;
			max = Math.max(max, counts.get(num));
		}
		System.out.println(max);
	}

}
