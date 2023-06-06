package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13397_구간_나누기_2 {
	static int N, M;
	static int[] arr;
	static int min, max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		// 배열 전체의 수 중 최솟값과 최댓값
		min = Integer.MAX_VALUE; max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		// 이분 탐색, 매개 변수 탐색을 이용해 구간의 점수의 최댓값의 최솟값을 찾는다.
		int result = binarySearch();
		System.out.println(result);
	}
	// 이분 탐색, 매개 변수 탐색을 활용해 구간의 점수의 최댓값의 최솟값을 찾는 함수
	private static int binarySearch() {
		// 이분 탐색에서 구간의 점수의 최댓값을 조정하기 위한 변수
		int l = 0; int r = max - min;
		while (l < r) {
			int mid = (l+r)/2;
			int cnt = 1; // 구간의 점수의 최댓값이 mid를 넘지 않을 때 구간의 최소 개수
			int tMin = arr[0]; int tMax = arr[0]; // 현재 구간에서의 최솟값과 최댓값
			// 구간의 점수의 최댓값이 mid를 넘지 않을 때 구간의 최소 개수를 구한다.
			for (int i = 1; i < N; i++) {
				tMin = Math.min(tMin, arr[i]);
				tMax = Math.max(tMax, arr[i]);
				if (tMax - tMin > mid) {
					cnt++;
					tMin = tMax = arr[i];
				}
			}
			// 구간의 개수가 M개 이하면 구간의 점수의 최댓값의 최솟값이 mid보다 작거나 같다.
			if (cnt <= M) {
				r = mid;
			// 구간의 개수가 M개 초과면 구간의 점수의 최댓값의 최솟값이 mid보다 크다.
			} else {
				l = mid+1;
			}
		}
		return l;
	}
}
