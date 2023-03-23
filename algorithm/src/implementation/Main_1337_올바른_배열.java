package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1337_올바른_배열 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		// 배열 속의 원소 중 연속적인 것이 5개인 것을 찾을 때 원소의 인덱스는 상관이 없으므로 정렬을 한다.
		Arrays.sort(arr);
		// 올바른 배열이 되기 위해 추가해야 하는 원소의 개수
		int min = 4;
		// 연속적인 수들의 시작 지점을 정한다.
		for (int i = 0; i < N; i++) {
			// 고려할 수들의 개수를 정한다.
			for (int l = 1; l < 5; l++) {
				int j = i+l; // i와 l 값을 바탕으로 연속적인 수들의 끝 지점을 정한다.
				if (j >= N) break; // j가 N보다 크거나 같으면 이후의 값들은 고려할 필요가 없다.
				// j번째 수와 i번째 수의 차이가 5보다 크거나 같으면
				// 연속적인 수를 만들기 위해 새로운 수를 추가해도 추가한 수의 개수가 min 값보다 작아질 수 없다.
				if (arr[j] - arr[i] >= 5) continue;
				int cnt = 0; // 연속적인 수를 만들기 위해 추가해야 하는 수의 개수
				// 연속적인 수를 만들기 위해 추가해야 하는 수의 개수를 구한다.
				for (int k = i; k < j; k++) {
					cnt += arr[k+1] - arr[k] - 1;
				}
				cnt += 4 - (arr[j] - arr[i]);
				// 추가해야 하는 수의 개수의 최솟값을 갱신한다.
				min = Math.min(min, cnt);
			}
		}
		System.out.println(min);
	}

}
