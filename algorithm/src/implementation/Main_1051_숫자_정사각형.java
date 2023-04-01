package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1051_숫자_정사각형 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] nums = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				nums[i][j] = s.charAt(j) - '0';
			}
		}
		// 꼭짓점에 있는 수가 모두 같은 정사각형의 최대 변 길이
		// 기본적으로 변 길이가 1이면 수가 하나뿐이므로 조건을 만족하는 정사각형을 항상 만들 수 있다.
		int maxSize = 1;
		// 변 길이를 설정
		for (int l = 2; l <= Math.min(N, M); l++) {
			// 정사각형의 왼쪽 위 꼭짓점 좌표 설정
			for (int i = 0; i <= N-l; i++) {
				for (int j = 0; j <= M-l; j++) {
					int num = nums[i][j];
					// 꼭짓점에 있는 수가 모두 같으면 maxSize를 갱신
					if (nums[i+l-1][j] == num && nums[i][j+l-1] == num && nums[i+l-1][j+l-1] == num) {
						maxSize = Math.max(maxSize, l);
					}
				}
			}
		}
		// 정사각형의 최대 크기를 구하므로 최대 변 길이의 제곱을 출력한다.
		System.out.println(maxSize*maxSize);
	}

}
