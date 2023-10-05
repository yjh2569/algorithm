package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15925_욱제는_정치쟁이야 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cur = 0; // 현재 전원을 조작한 횟수
		int prev = -1; // 이전에 전원을 조작한 횟수
		boolean[] rows = new boolean[N]; // 각 행의 전원을 조작했는지를 나타내는 배열
		boolean[] cols = new boolean[N]; // 각 열의 전원을 조작했는지를 나타내는 배열
		// 전원을 조작할 수 있는 행 또는 열부터 조작하면서 전체 컴퓨터를 켜거나 끌 수 있는지 확인한다.
		while (prev != cur) {
			prev = cur;
			// 각 행의 컴퓨터를 확인한다.
			for (int i = 0; i < N; i++) {
				if (rows[i]) continue;
				// 현재 상태가 S인 컴퓨터의 수를 조사한다.
				int cnt = 0;
				for (int j = 0; j < N; j++) {
					if (map[i][j] == S) cnt++;
				}
				// 현재 상태가 S인 컴퓨터의 수가 N/2보다 적으면 전원을 조작해도 원하는 상태로 만들 수 없다.
				if (cnt*2 < N) continue;
				// 현재 상태인 S인 컴퓨터의 수가 N/2보다 크면 전원을 조작해 원하는 상태로 만들 수 있다.
				rows[i] = true;
				cur++;
				for (int j = 0; j < N; j++) {
					map[i][j] = S;
				}
			}
			// 각 열에 대해서도 위와 같은 작업을 수행한다.
			for (int i = 0; i < N; i++) {
				if (cols[i]) continue;
				int cnt = 0;
				for (int j = 0; j < N; j++) {
					if (map[j][i] == S) cnt++;
				}
				if (cnt*2 < N) continue;
				cols[i] = true;
				cur++;
				for (int j = 0; j < N; j++) {
					map[j][i] = S;
				}
			}
		}
		// 만약 전원을 조작한 횟수가 2*N이면 모든 행과 열의 전원을 조작해 원하는 상태로 만든 것이다.
		if (cur == 2*N) System.out.println(1);
		else System.out.println(0);
	}

}
