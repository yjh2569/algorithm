package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2116_주사위_쌓기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 주사위들을 저장하는 ArrayList
		ArrayList<int[]> dices = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int[] dice = new int[6];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				dice[j] = Integer.parseInt(st.nextToken());
			}
			dices.add(dice);
		}
		// 한 옆면의 숫자의 합의 최댓값
		int max = 0;
		// 1번 주사위의 밑면을 몇번째 면으로 할지 결정하고, 
		// 이후로는 각 주사위를 조건에 맞게 놓았을 때 각 주사위 옆면에 있는 숫자 중 가장 큰 숫자만 골라낸다.
		for (int i = 0; i < 6; i++) {
			int sum = 0; // 각 주사위 옆면에 있는 숫자 중 가장 큰 숫자들의 합
			int cur = i; // 현재 주사위 밑면이 몇 번째 면인지를 나타내는 숫자
			for (int j = 0; j < N; j++) {
				sum += getMaxVal(cur, dices.get(j));
				if (j < N-1) cur = getOppositeSide(cur, dices.get(j), dices.get(j+1));
			}
			// 최댓값 갱신
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
	// dice가 cur번째 면이 밑면일 때 옆면에 있는 숫자들 중 가장 큰 숫자를 반환하는 함수
	private static int getMaxVal(int cur, int[] dice) {
		if (cur == 0 || cur == 5) {
			return Math.max(dice[1], Math.max(dice[2], Math.max(dice[3], dice[4])));
		} else if (cur == 1 || cur == 3) {
			return Math.max(dice[0], Math.max(dice[2], Math.max(dice[4], dice[5])));
		} else if (cur == 2 || cur == 4) {
			return Math.max(dice[0], Math.max(dice[1], Math.max(dice[3], dice[5])));
		}
		return 0;
	}
	// curDice의 cur번째 면이 밑면일 때 nextDice의 밑면은 몇 번째 밑면인지를 구하는 함수 
	private static int getOppositeSide(int cur, int[] curDice, int[] nextDice) {
		int next = 0;
		if (cur == 0) next = curDice[5];
		if (cur == 1) next = curDice[3];
		if (cur == 2) next = curDice[4];
		if (cur == 3) next = curDice[1];
		if (cur == 4) next = curDice[2];
		if (cur == 5) next = curDice[0];
		for (int i = 0; i < 6; i++) {
			if (next == nextDice[i]) return i;
		}
		return 0;
	}
}
