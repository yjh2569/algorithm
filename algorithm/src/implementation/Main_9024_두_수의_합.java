package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9024_두_수의_합 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[1_000_000];
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			Arrays.fill(arr, Integer.MAX_VALUE);
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 투 포인터를 사용한다.
			// 우선 배열을 정렬한 후, 왼쪽 끝과 오른쪽 끝에서 시작해
			// 두 수의 합과 k와의 차이가 가장 작아질 때마다 순서쌍의 개수를 초기화한다.
			// 두 수의 합과 k와의 차이가 최솟값과 같을 경우 순서쌍의 개수를 1 증가시킨다.
			Arrays.sort(arr);
			int l = 0; // 왼쪽 끝 인덱스
			int r = n-1; // 오른쪽 끝 인덱스
			int cnt = 0; // 순서쌍 개수
			int min = Integer.MAX_VALUE; // 두 수의 합과 k와의 차이의 최솟값
			while (l < r) {
				int sum = arr[l] + arr[r];
				if (min > Math.abs(sum - k)) {
					min = Math.abs(sum - k);
					cnt = 1;
				} else if (min == Math.abs(sum - k)) cnt++;
				if (sum < k) l++;
				else r--;
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
