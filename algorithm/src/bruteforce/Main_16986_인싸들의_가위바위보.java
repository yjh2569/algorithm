package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16986_인싸들의_가위바위보 {
	static int N, K;
	static int[][] rules;
	static int[][] plays;
	static boolean[] v;
	static int[] p;
	static boolean canWin;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		rules = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				rules[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 각 라운드에 낼 손동작으로, plays[0]은 지우, plays[1]은 경희, plays[2]는 민수를 의미한다.
		// 경희와 민수는 손동작이 입력으로 주어지나, 지우의 경우는 직접 채워넣어야 한다.
		plays = new int[3][20];
		for (int i = 1; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 20; j++) {
				plays[i][j] = Integer.parseInt(st.nextToken())-1;
			}
		}
		// 순열을 통해 가능한 지우의 손동작의 경우를 모두 찾고, 각 경우에 대해 지우가 이길 수 있는지를 조사한다.
		v = new boolean[N];
		p = new int[N];
		perm(0);		
		System.out.println(canWin ? 1 : 0);
	}
	// 가능한 지우의 손동작을 찾기 위해 순열을 이용하는 함수
	private static void perm(int cnt) {
		// 모든 손동작이 다르도록 지우의 손동작을 고른 경우
		if (cnt == N) {
			// 손동작을 정한 결과를 바탕으로 게임에서 이길 수 있는지 확인한다.
			if (tryGame()) canWin = true;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (v[i]) continue;
			v[i] = true;
			plays[0][cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}		
	}
	// 게임을 시뮬레이션해 지우가 이길 수 있는지를 조사하는 함수
	private static boolean tryGame() {
		// 지우, 경희, 민호가 현재 몇 판의 게임에 참여했는지를 나타낸다.
		// 입력으로 주어지는 손동작은 자신이 참여한 게임에서 낼 손동작이기에, 중간에 빼먹는 손동작이 없음에 유의한다.
		int[] turns = new int[3];
		int[] wins = new int[3]; // 지우, 경희, 민호가 이긴 판 수
		// players[0]과 players[1]은 현재 경기에 참여한 사람의 번호고,
		// players[2]는 현재 경기에 참여하지 않은 사람의 번호다.
		int[] players = new int[3];
		// 초기화
		for (int i = 1; i <= 2; i++) {
			players[i] = i;
		}
		while (true) {
			// 지우가 경기에서 이긴 경우
			if (wins[0] == K) return true;
			// 지우가 경기에서 이기지 못하는 경우
			// 모든 손동작을 냈음에도 이기지 못하는 경우 역시 지우가 이기지 못한다.
			if (wins[1] == K || wins[2] == K || turns[0] >= N) return false;
			// 비기는 경우를 좀더 쉽게 고려하기 위해 항상 players[0]보다 players[1]이 크거나 같도록 설정
			if (players[0] > players[1]) swap(players, 0, 1);
			// 각 선수의 번호를 확인
			int first = players[0];
			int second = players[1];
			// 각 선수가 다음에 어떤 손동작을 낼지 확인함과 동시에 turns 값 증가
			int firstPlay = plays[first][turns[first]++];
			int secondPlay = plays[second][turns[second]++];
			// first가 이기는 경우
			if (rules[firstPlay][secondPlay] == 2) {
				wins[first]++;
				// players에서 1번 인덱스와 2번 인덱스의 값을 바꿔서 player[2]번이 players[1]번 대신 참여하도록 한다.
				swap(players, 1, 2);
			// second가 이기는 경우
			} else {
				wins[second]++;
				// players에서 0번 인덱스와 2번 인덱스의 값을 바꿔서 player[2]번이 players[0]번 대신 참여하도록 한다.
				swap(players, 0, 2);
			}
		}
	}
	// players에서 i번 인덱스와 j번 인덱스의 값을 바꾸는 함수
	private static void swap(int[] players, int i, int j) {
		int temp = players[i];
		players[i] = players[j];
		players[j] = temp;		
	}
	

}
