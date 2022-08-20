package divide_and_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7453_합이_0인_네_정수 {
	static int n;
	static int[] sum1, sum2;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] A = new int[n], B = new int[n], C = new int[n], D = new int[n];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}
		// 시간 복잡도를 최대한 줄이기 위해, A와 B 배열에 대해 가능한 모든 합과, C와 D 배열에 대해 가능한 모든 합을 구한다.
		// 그리고 각 결과를 sum1, sum2라는 배열로 저장한 뒤 sum1에서 합을 하나씩 꺼내 
		// sum2의 합과 합쳤을 때 0이 되는 경우를 이분 탐색을 통해 구한다.
		// 그러면 시간복잡도를 O(n^2 log n)까지 줄일 수 있다.
		sum1 = new int[n*n]; // A와 B 배열만 고려했을 때 가능한 모든 합
		sum2 = new int[n*n]; // C와 D 배열만 고려했을 때 가능한 모든 합
		for (int k = 0; k < n*n; k++) {
			int i = k/n, j = k%n;
			sum1[k] = A[i] + B[j];
			sum2[k] = C[i] + D[j];
		}
		// 이분 탐색을 하기 위해 각 배열을 정렬한다.
		Arrays.sort(sum1);
		Arrays.sort(sum2);
		// 가능한 순서쌍의 개수
		// 최대 n^4개까지 가능하므로 int 범위를 넘어갈 수 있음에 유의
		long cnt = 0;
		// sum1의 각 합 N에 대해 sum2에서 -N의 위치와, -N+1의 위치를 찾아 두 위치의 차이를 구한다.
		for (int k = 0; k < n*n; k++) {
			int l = search(-sum1[k]);
			int r = search(-sum1[k]+1);
			// sum2에서 -N의 개수를 cnt에 더해준다.
			cnt += r - l;
		}
		System.out.println(cnt);
	}
	// 이분탐색을 통해 sum2 내 x의 위치를 찾는 함수
	// x가 없는 경우 x보다 큰, sum2에서 가장 작은 수의 위치를 찾는다.(그러한 수가 없는 경우 n*n으로 반환) 
	private static int search(int x) {
		int l = 0, r = n*n, mid = 0;
		while (l < r) {
			mid = (l+r)/2;
			if (sum2[mid] < x) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return l;
	}

}
