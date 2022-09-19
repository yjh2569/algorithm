package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2428_표절 {
	static int[] nums;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		// 입력으로 받은 솔루션 파일 크기의 배열을 정렬한다.
		Arrays.sort(nums);
		// 검사해야 하는 쌍의 개수
		long cnt = 0;
		// 첫 번째 파일을 제외한 각 파일의 크기에 대해 해당 파일의 크기의 0.9배보다 
		// 크거나 같은 크기의 파일들 중 가장 작은 크기의 파일을 찾아야 한다.
		// 이는 단순 탐색으로는 O(N^2)의 시간 복잡도가 소요되므로, 이분 탐색을 통해 효율적으로 찾는다.
		for (int i = 1; i < N; i++) {
			// 위에서 언급했던 파일의 위치를 찾는다.
			int l = binarySearch(0.9*nums[i], i);
			// 해당 파일 이후로는 모두 검사 조건을 만족한다.
			cnt += i - l;
		}
		System.out.println(cnt);
	}
	// 이분 탐색을 통해 num보다 크기가 큰 파일 중 가장 크기가 작은 파일의 위치를 찾는다.
	private static int binarySearch(double num, int r) {
		int l = 0;
		while (l < r) {
			int mid = (l+r)/2;
			if (nums[mid] < num) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return l;		
	}

}
