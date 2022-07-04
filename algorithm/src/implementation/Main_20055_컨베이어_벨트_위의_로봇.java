package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20055_컨베이어_벨트_위의_로봇 {
	static int N, cnt;
	static int[] conveyer;
	static boolean[] robots;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 컨베이어 벨트의 각 칸의 내구도를 저장하는 배열(앞으로 1번째 칸은 0번째, ... , 2N번째 칸은 2N-1번째로 간주)
		conveyer = new int[2*N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++) {
			conveyer[i] = Integer.parseInt(st.nextToken());
		}
		// 각 칸에 로봇이 있는지를 나타내는 배열
		// 로봇은 N-1번째 칸에 가면 무조건 내려서 N번째 칸 이후로는 갈 수가 없기 때문에 배열 크기를 N으로 해도 충분하다.
		robots = new boolean[N];
		// 현재 수행 중인 단계
		int phase = 1;
		// 내구도가 0이 된 칸의 개수
		cnt = 0;
		// 내구도가 0이 된 칸의 개수가 K개 이상이 될 때까지 반복
		while (true) {
			// 컨베이어 벨트 회전
			rotate();
			// 조건을 만족하는 로봇 이동
			moveRobot();
			// 올리는 위치에 로봇 올리기
			addRobot(phase);
			if (cnt >= K) break;
			phase++; // 다음 단계로 이동
		}
		System.out.println(phase);
	}
	// 컨베이어 벨트를 로봇과 함께 이동시키는 함수
	private static void rotate() {
		// 컨베이어 벨트 회전
		int temp = conveyer[2*N-1];
		for (int i = 2*N-1; i >= 1; i--) {
			conveyer[i] = conveyer[i-1];
		}
		conveyer[0] = temp;
		// 로봇을 컨베이어 벨트 회전에 맞게 이동
		for (int i = N-1; i >= 1; i--) {
			robots[i] = robots[i-1];
		}
		// N-1번째 칸에 도달한 로봇은 즉시 내린다.
		robots[N-1] = false;
		// 로봇이 컨베이어 벨트와 이동하면 0번째 칸은 항상 비게 된다.
		robots[0] = false;
	}
	private static void moveRobot() {
		// 먼저 탄 로봇일수록 컨베이어 벨트 뒤쪽에 있으므로 컨베이어 뒤쪽부터 조사한다.
		// 단, N-1번째 칸에서는 로봇이 즉시 내리므로 해당 칸은 조사할 필요가 없다.
		for (int i = N-2; i >= 0; i--) {
			// 해당 칸에 로봇이 있고, 다음 칸에 로봇이 없으며, 다음 칸의 내구도가 1 이상일 때
			if (robots[i] && !robots[i+1] && conveyer[i+1] >= 1) {
				// 로봇 이동
				robots[i] = false;
				if (i < N-2) robots[i+1] = true; // 로봇이 N-1번째 칸에 도달하면 즉시 내린다.
				// 다음 칸 내구도 감소
				conveyer[i+1]--;
				// 위 작업으로 인해 내구도가 0이 되면 cnt를 증가시킨다.
				if (conveyer[i+1] == 0) cnt++;
			}
		}	
	}
	// 올리는 칸에 로봇 추가
	private static void addRobot(int phase) {
		// 올리는 칸의 내구도가 1 이상일 때만 로봇을 올릴 수 있다.
		if (conveyer[0] >= 1) {
			robots[0] = true;
			// 다음 칸 내구도 감소
			conveyer[0]--;
			// 위 작업으로 인해 내구도가 0이 되면 cnt를 증가시킨다.
			if (conveyer[0] == 0) cnt++;
		}
		
	}

}
