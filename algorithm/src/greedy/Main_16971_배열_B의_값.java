package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16971_배열_B의_값 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[][] A = new long[N][M];
		// 각 행의 합
		long[] rowSum = new long[N];
		// 각 열의 합
		long[] colSum = new long[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				rowSum[i] += A[i][j];
				colSum[j] += A[i][j];
			}
		}
		// 초기 A에 대한 배열 B의 값
		// 배열 B의 값은 배열 A에서 네 꼭짓점에 있는 수의 합과, 1행부터 (N-2)행까지의 각 행의 합의 2배, 
		// 1열부터 (N-2)열까지의 각 열의 합의 2배의 합과 같다.
		long sum = A[0][0] + A[N-1][0] + A[0][M-1] + A[N-1][M-1];
		for (int i = 1; i < N-1; i++) {
			sum += rowSum[i]*2;
		}
		for (int j = 1; j < M-1; j++) {
			sum += colSum[j]*2;
		}
		// 배열 B의 값의 최댓값
		long maxSum = sum;
		// 배열 B의 값이 바뀌는 경우는 0행 또는 (N-1)행이 1행~(N-2)행 중 하나와 바뀌는 경우와,
		// 0열 또는 (M-1)열이 1열~(M-2)열 중 하나와 바뀌는 경우가 있다.
		// 따라서 위 경우 중 배열 B의 값이 최대가 되는 경우를 찾으면 된다.
		for (int i = 1; i < N-1; i++) {
			long tempSum = sum - rowSum[i]*2 + A[i][0] + A[i][M-1];
			maxSum = Math.max(maxSum, tempSum + rowSum[0]*2 - A[0][0] - A[0][M-1]);
			maxSum = Math.max(maxSum, tempSum + rowSum[N-1]*2 - A[N-1][0] - A[N-1][M-1]);
		}
		for (int j = 1; j < M-1; j++) {
			long tempSum = sum - colSum[j]*2 + A[0][j] + A[N-1][j];
			maxSum = Math.max(maxSum, tempSum + colSum[0]*2 - A[0][0] - A[N-1][0]);
			maxSum = Math.max(maxSum, tempSum + colSum[M-1]*2 - A[0][M-1] - A[N-1][M-1]);
		}
		System.out.println(maxSum);
	}

}
