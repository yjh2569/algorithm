package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17281_야구 {
	static int N;
	static int[] p;
	static boolean[] v;
	static int[][] results;
	static int max;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 각 이닝마다 각 선수가 얻을 수 있는 결과
		results = new int[N][9];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				results[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 타순을 결정하기 위해 순열을 사용한다.
		p = new int[9];
		v = new boolean[9];
		v[0] = true;
		// 얻을 수 있는 점수의 최대값
		max = 0;
		perm(0);
		// 얻은 점수의 최대값 출력
		System.out.println(max);
	}
	// 타순을 결정하기 위해 순열을 이용하는 재귀함수
	private static void perm(int cnt) {
		// 4번 주자는 항상 1번 주자이므로 다음으로 넘어간다.
		if (cnt == 3) {
			perm(cnt+1);
			return;
		}
		// 타순을 결정하면 경기를 시뮬레이션한다.
		if (cnt == 9) {
			game();
			return;
		}
		// i = 0인 경우는 1번 선수이고, 1번 선수는 4번 타자로 고정되어 있으므로 제외한다.
		for (int i = 1; i < 9; i++) {
			if (v[i]) continue;
			v[i] = true;
			p[cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}	
	}
	// 경기를 시뮬레이션하는 함수
	private static void game() {
		// 메모리 절약을 위해 경기에서 쓰일 변수를 미리 선언 및 초기화한다.
		int score = 0, out = 0, idx = 0, res = 0, player = 0;
		// 홈, 1루, 2루, 3루에 주자가 있는지를 나타내는 배열
		// 홈에 있는 주자는 현재 타자를 의미한다.
		boolean[] bases = new boolean[4];
		for (int n = 0; n < N; n++) {
			// 이닝 시작 전 out 수, 경기장 내 모든 루를 초기화하고,
			// 현재 이닝의 결과를 results로부터 얻어온다.
			out = 0;
			int[] result = results[n];
			for (int i = 0; i < 4; i++) {
				bases[i] = false;
			}
			// 3아웃이 발생하기 전까지 이닝을 진행한다.
			while (out < 3) {
				// p 배열에 따라 현재 (idx+1)번째 타자를 결정한다.
				player = p[idx];
				// 해당 선수의 결과를 가져온다.
				res = result[player];
				// 홈에 타자가 들어섰음을 표현
				bases[0] = true;
				// 아웃된 경우
				if (res == 0) {
					out++;
				// 공을 쳐서 진루하는 경우
				} else {
					// 3루부터 홈에 있는 모든 주자가 결과에 따라 이동한다.
					for (int i = 3; i >= 0; i--) {
						// 해당 루에 주자가 없는 경우
						if (!bases[i]) continue;
						// 3루보다 더 진루하는 경우, 즉 홈으로 들어왔다는 의미
						// 따라서 점수를 얻는다.
						if (i+res >= 4) {
							score++;
						// 그렇지 않으면 결과에 따라 다른 루로 진루한다.
						} else {
							bases[i+res] = true;
						}
						// 진루한 경우 기존에 있었던 루를 비워야 함에 유의
						bases[i] = false;
					}
				}
				// 다음 타자
				idx = (idx+1)%9;
			}
		}
		// 경기 시뮬레이션이 끝나면 얻은 점수가 최대값이 되는지 확인
		max = Math.max(max, score);
	}

}
