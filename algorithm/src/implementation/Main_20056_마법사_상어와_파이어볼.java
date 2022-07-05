package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20056_마법사_상어와_파이어볼 {
	static int N, M, K;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	// 파이어볼 클래스
	static class Fireball {
		int r, c, m, s, d;
		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static ArrayList<Fireball> fireballs;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 현재 남아있는 파이어볼을 저장하는 ArrayList
		fireballs = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireballs.add(new Fireball(r, c, m, s, d));
		}
		// 이동을 K번 명령
		for (int k = 0; k < K; k++) {
			moveFireballs();
		}
		// 남아 있는 파이어볼의 질량을 합한다.
		int res = 0;
		for (Fireball f : fireballs) {
			res += f.m;
		}
		System.out.println(res);
	}
	// 파이어볼의 이동을 시뮬레이션한 함수
	private static void moveFireballs() {
		int[][] counts = new int[N][N]; // 각 칸에 파이어볼이 몇 개 중첩되었는지를 나타낸다.
		// fireballs에 있는 파이어볼들이 이동한 결과를 저장하는 배열
		// 두 파이어볼이 동시에 같은 지점에 도달하면, 질량과 속도를 기존 파이어볼에 합친다.
		Fireball[][] nextBalls = new Fireball[N][N];
		// 하나의 칸에 파이어볼이 2개 이상 중첩되는 경우 나누어진 파이어볼의 방향을 알아내기 위한 배열
		// 지금까지 도착한 파이어볼들의 방향이 모두 홀수면 1, 짝수면 2, 그렇지 않으면 -1을 저장
		int[][] dirCheck = new int[N][N];
		// 각 파이어볼을 이동시킨다.
		for (Fireball f : fireballs) {
			// 파이어볼을 이동시킬 때, 나머지 연산에 유의해야 한다.
			// 특히 속도가 N보다 큰 경우에는 N으로 나눈 나머지에 해당하는 속도와 이동 결과는 같기 때문에, 
			// 이를 잘 이용하면 직접 이동시키지 않아도 다음 좌표를 쉽게 계산할 수 있다.
			f.r = (f.r+(f.s%N)*dr[f.d]+N)%N;
			f.c = (f.c+(f.s%N)*dc[f.d]+N)%N;
			// 이동한 칸에 아직 다른 파이어볼이 없는 경우
			if (nextBalls[f.r][f.c] == null) {
				nextBalls[f.r][f.c] = f;
				if (f.d%2 == 1) dirCheck[f.r][f.c] = 1;
				else dirCheck[f.r][f.c] = 2;
			} else {
				// 기존에 도착한 파이어볼에 자신의 질량과 속도를 더해준다.
				nextBalls[f.r][f.c].m += f.m;
				nextBalls[f.r][f.c].s += f.s;
				// 방향 확인
				if (dirCheck[f.r][f.c] == 1 && f.d%2 == 1) dirCheck[f.r][f.c] = 1;
				else if (dirCheck[f.r][f.c] == 2 && f.d%2 == 0) dirCheck[f.r][f.c] = 2;
				else dirCheck[f.r][f.c] = -1;
			}
			counts[f.r][f.c]++;
		}
		// 파이어볼을 모두 이동시켰다면 이제 기존 위치의 파이어볼은 필요없다.
		fireballs.clear();
		// 각 지점의 파이어볼을 조사해 새로운 파이어볼을 만들어 fireballs에 넣는다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Fireball nextBall = nextBalls[i][j];
				if (nextBall == null) continue;
				// 해당 지점에 파이어볼이 하나만 있는 경우
				else if (counts[i][j] == 1) fireballs.add(nextBall);
				// 해당 지점에 파이어볼이 여러 개 있는 경우
				else {
					// 조건에 맞게 질량과 속도를 조절해 4개의 파이어볼로 나눈다.
					nextBall.m /= 5;
					// 질량이 0이 되면 소멸된다고 했으므로 새로운 파이어볼을 만들지 않는다.
					if (nextBall.m == 0) continue;
					nextBall.s /= counts[i][j];
					// 0, 2, 4, 6 방향으로 파이어볼을 나누는 경우 0
					// 1, 3, 5, 7 방향으로 파이어볼을 나누는 경우 1
					int temp_dir = 0;
					if (dirCheck[nextBall.r][nextBall.c] == -1) {
						temp_dir++;
					}
					// 4개로 나누어지는 파이어볼을 fireballs에 추가한다.
					for (int d = 0; d < 8; d += 2) {
						fireballs.add(new Fireball(i, j, nextBall.m, nextBall.s, temp_dir+d));
					}
				}
			}
		}
		
	}
}
