package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2143_두_배열의_합 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] A = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int m = Integer.parseInt(br.readLine());
		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		// A와 B 배열에 대하여 1번째 항부터 i번째 항까지의 합을 i번째 값으로 갖는 배열을 만든다.
		int[] cumA = new int[n];
		int[] cumB = new int[m];
		cumA[0] = A[0]; cumB[0] = B[0];
		// A와 B 배열의 모든 부 배열의 합을 저장할 ArrayList
		ArrayList<Integer> sumA = new ArrayList<>();
		ArrayList<Integer> sumB = new ArrayList<>();
		sumA.add(cumA[0]); sumB.add(cumB[0]);
		// 누적합 구하기
		for (int i = 1; i < n; i++) {
			cumA[i] = cumA[i-1] + A[i];
			sumA.add(cumA[i]);
		}
		for (int i = 1; i < m; i++) {
			cumB[i] = cumB[i-1] + B[i];
			sumB.add(cumB[i]);
		}
		// 부 배열의 합 구하기
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				sumA.add(cumA[i] - cumA[j]);
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 0; j < i; j++) {
				sumB.add(cumB[i] - cumB[j]);
			}
		}
		// 이분 탐색을 위한 정렬
		Collections.sort(sumA);
		Collections.sort(sumB);
		// A, B의 부 배열의 합을 더해서 T가 되는 모든 부 배열 쌍의 개수
		long res = 0;
		// A의 부 배열의 합 각각에 대해 B의 부 배열의 합 중 두 합을 더했을 때 T가 나오는 경우를 센다.
		// 이때, 주어진 A의 부 배열의 합을 a라고 할 때, B의 부 배열의 합 중 T-a가 되는 경우의 수를 찾을 때
		// sumB에서 T-a의 가장 왼쪽 위치와, T-a보다 큰 가장 작은 정수의 위치을 찾아 두 위치의 차이를 통해 T-a의 개수를 센다.
		// 만약 단순히 sumB를 모두 탐색한다면 시간 초과가 발생하므로, 이를 효율적으로 찾기 위해 이분 탐색을 사용한다.
		for (int a : sumA) {
			int temp = T - a;
			int l = binSearch(temp, sumB);
			int r = binSearch(temp+1, sumB);
			res += r - l;
		}
		System.out.println(res);
	}
	// sumB에서 temp의 가장 왼쪽 위치를 찾는다. 없으면 temp보다 큰 가장 작은 정수의 위치를 찾는다.
	private static int binSearch(int temp, ArrayList<Integer> sumB) {
		int l = 0;
		int r = sumB.size();
		while (l < r) {
			int mid = (l+r)/2;
			if (sumB.get(mid) < temp) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return l;
	}

}
