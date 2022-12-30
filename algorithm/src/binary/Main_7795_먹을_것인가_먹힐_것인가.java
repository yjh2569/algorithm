package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7795_먹을_것인가_먹힐_것인가 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] A = new int[N];
			int[] B = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			// 이분 탐색을 적용하기 위해 B를 오름차순으로 정렬
			Arrays.sort(B);
			// A의 크기가 B의 크기보다 큰 쌍의 개수
			int cnt = 0;
			// 각 쌍의 개수를 이분탐색을 통해 계산
			for (int i = 0; i < N; i++) {
				// A[i], 또는 A[i]보다 큰 수 중 가장 작은 수의 위치를 반환하므로 그 이전의 수들은 모두 A[i]보다 작다.
				// 따라서 l의 값이 곧 A[i]보다 작은 수의 개수다.
				int l = binarySearch(A[i], B, N, M);
				cnt += l;
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 이분탐색을 통해 k가 B 내에서 몇 번째 인덱스에 존재하는지를 반환하는 함수
	// 만약 k가 B에 없다면, k보다 큰 수 중 가장 작은 수의 인덱스를 반환
	private static int binarySearch(int k, int[] B, int N, int M) {
		int l = 0, r = M;
		while (l < r) {
			int mid = (l+r)/2;
			if (k > B[mid]) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return l;
	}

}
