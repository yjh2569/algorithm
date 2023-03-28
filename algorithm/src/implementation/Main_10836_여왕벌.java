package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10836_여왕벌 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] board = new int[M][M];
		for (int i = 0; i < M; i++) {
			Arrays.fill(board[i], 1);
		}
		// 왼쪽 아래 칸부터 오른쪽 위 칸까지, 모서리에 있는 칸들의 애벌레에 대해 각각 얼마만큼의 크기가 자라야 하는지를 나타내는 배열
		int[] sum = new int[2*M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// 자라는 크기가 0, 1, 2인 애벌레의 수를 나타내는 배열
			int[] nums = new int[3];
			for (int j = 0; j < 3; j++) {
				nums[j] = Integer.parseInt(st.nextToken());
			}
			// 자라는 크기가 0인 애벌레의 수가 n일 때 (n+1)번째 애벌레부터 크기 1만큼 자란다.
			// 다만, 배열에서 인덱스는 0부터 시작이므로 sum에서는 n번째부터 크기 1만큼 자란다고 간주한다.
			sum[nums[0]]++;
			// 자라는 크기가 0인 애벌레의 수가 n, 크기가 1인 애벌레의 수가 m일 때 (n+m+1)번째 애벌레부터 크기 2만큼 자란다.
			// 다만, 배열에서 인덱스는 0부터 시작이므로 sum에서는 (n+m)번째부터 크기 2만큼 자란다고 간주한다.
			sum[nums[0]+nums[1]]++;
		}
		// 누적합 방식을 이용해 한 번에 각 애벌레의 성장 크기를 구한다.
		for (int i = 1; i < 2*M; i++) {
			sum[i] += sum[i-1];
		}
		int r = M-1; // 현재 고려 중인 칸의 행
		int c = 0; // 현재 고려 중인 칸의 열
		int idx = 0; // 현재 sum에서 몇 번째 칸을 고려 중인지를 나타내는 인덱스
		// 왼쪽 아래부터 오른쪽 위까지 모서리를 통해 지나가면서 sum을 통해 애벌레의 성장 크기를 파악해 이를 board에 반영한다.
		while (c < M) {
			if (r == 0) board[r][c++] += sum[idx++];
			else board[r--][c] += sum[idx++];
		}
		// 나머지 칸의 애벌레의 경우 매일 애벌레의 크기를 고려하지 않아도 된다.
		// 결국 왼쪽, 왼쪽 위, 위쪽 애벌레 중 가장 큰 애벌레의 크기를 따라가기 때문에,
		// 세 애벌레가 모두 성장한 후에 고려해도 된다.
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < M; j++) {
				board[i][j] = Math.max(board[i-1][j], Math.max(board[i-1][j-1], board[i][j-1]));
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
