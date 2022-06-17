package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1451_직사각형으로_나누기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		// (0, 0)과 (i, j)를 꼭짓점으로 하는 직사각형에 있는 숫자의 합을 (i, j) 성분으로 하는 누적 합 배열
		int[][] sum = new int[N][M];
		for (int i = 0; i < N; i++) {
			sum[i] = Arrays.copyOf(map[i], M);
		}
		// 누적 합을 구하기 위해 가로로 먼저 누적 합을 구하고 세로로 누적 합을 구한다.
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < M; j++) {
				sum[i][j] += sum[i][j-1];
			}
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sum[i][j] += sum[i-1][j];
			}
		}
		// 세 직사각형의 합의 곱의 최댓값
		long max = 0;
		// 가로로 2번 나눠서 3개의 직사각형을 만드는 경우
		if (N >= 3) {
			for (int i = 0; i < N-2; i++) {
				for (int j = i+1; j < N-1; j++) {
					long rec1 = sum[i][M-1]; // 위쪽 직사각형
					long rec2 = sum[j][M-1]-sum[i][M-1]; // 가운데 직사각형
					long rec3 = sum[N-1][M-1]-sum[j][M-1]; // 아래쪽 직사각형
					max = Math.max(max, rec1*rec2*rec3);
				}
			}
		}
		// 세로로 2번 나눠서 3개의 직사각형을 만드는 경우
		if (M >= 3) {
			for (int i = 0; i < M-2; i++) {
				for (int j = i+1; j < M-1; j++) {
					long rec1 = sum[N-1][i]; // 왼쪽 직사각형
					long rec2 = sum[N-1][j]-sum[N-1][i]; // 가운데 직사각형
					long rec3 = sum[N-1][M-1]-sum[N-1][j]; // 오른쪽 직사각형
					max = Math.max(max, rec1*rec2*rec3);
				}
			}
		}
		// 가로로 1번, 세로로 1번 나눠서 3개의 직사각형을 만드는 경우
		// 경우의 수를 줄이기 위해 4개의 직사각형으로 나누고 2개의 직사각형을 합치는 경우로 간주
		if (N >= 2 && M >= 2) {
			for (int i = 0; i < N-1; i++) {
				for (int j = 0; j < M-1; j++) {
					long rec1 = sum[i][j]; // 2사분면 직사각형
					long rec2 = sum[i][M-1] - sum[i][j]; // 1사분면 직사각형
					long rec3 = sum[N-1][j] - sum[i][j]; // 3사분면 직사각형
					long rec4 = sum[N-1][M-1] - sum[N-1][j] - sum[i][M-1] + sum[i][j]; // 4사분면 직사각형
					max = Math.max(max, (rec1+rec2)*rec3*rec4); // 1사분면과 2사분면을 합치는 경우
					max = Math.max(max, (rec1+rec3)*rec2*rec4); // 2사분면과 3사분면을 합치는 경우
					max = Math.max(max, (rec3+rec4)*rec1*rec2); // 3사분면과 4사분면을 합치는 경우
					max = Math.max(max, (rec2+rec4)*rec1*rec3); // 4사분면과 1사분면을 합치는 경우
				}
			}
		}
		System.out.println(max);		
	}

}
