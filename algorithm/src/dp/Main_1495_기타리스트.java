package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1495_기타리스트 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] diff = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			diff[i] = Integer.parseInt(st.nextToken());
		}
		// dynamic programming을 활용
		// (i, j) 성분은 i번째 곡을 연주할 때 볼륨 j로 변경할 수 있는지를 의미한다.
		boolean[][] possible = new boolean[N+1][M+1];
		// 초기화
		possible[0][S] = true;
		// 각 i번째 곡의 현재 가능한 볼륨에 대해 diff[i]만큼의 볼륨을 증감시킨 후
		// 볼륨 범위를 넘어서지 않는다면 다음 곡의 해당 볼륨에 대한 possible 값을 true로 한다. 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= M; j++) {
				if (!possible[i][j]) continue;
				if (j >= diff[i]) possible[i+1][j-diff[i]] = true;
				if (j <= M-diff[i]) possible[i+1][j+diff[i]] = true;
			}
		}
		// N번째 곡에 대해 가능한 볼륨의 최댓값
		int max = -1;
		// 모든 볼륨에 대해 N번째 곡의 볼륨으로 가능한지 조사한다.
		// 만약 마지막 곡을 연주할 수 없다면 max는 -1이 될 것이다.
		for (int j = 0; j <= M; j++) {
			if (possible[N][j]) max = j;
		}
		System.out.println(max);
	}	

}
