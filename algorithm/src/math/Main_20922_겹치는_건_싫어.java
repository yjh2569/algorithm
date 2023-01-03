package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_20922_겹치는_건_싫어 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		// 각 숫자가 나타난 빈도를 저장하는 HashMap
		Map<Integer, Integer> counts = new HashMap<>();
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			counts.putIfAbsent(arr[i], 0);
		}
		// 최장 연속 부분 수열의 길이
		int maxLen = 1;
		// 투 포인터를 활용
		int l = 0, r = 0;
		while (r < N) {
			// r번째 숫자가 나타난 빈도가 K번이라면, r번째 숫자를 수열에 포함시킬 수 없으므로
			// l을 1씩 증가시켜 r번째 숫자가 나타난 빈도를 K번 미만으로 줄일 때까지 진행
			if (counts.get(arr[r]) == K) {
				counts.put(arr[l], counts.get(arr[l++])-1);
			// r번째 숫자가 나타난 빈도가 K번 미만이면 r번째 숫자를 수열에 포함시키고 r을 1 증가
			} else {
				counts.put(arr[r], counts.get(arr[r++])+1);
			}
			maxLen = Math.max(maxLen, r - l);
		}
		System.out.println(maxLen);
	}

}
