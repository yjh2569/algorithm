package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_27162_Yacht_Dice {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 각 족보를 선택할 수 있는지를 나타내는 배열
		char[] canSelect = br.readLine().toCharArray();
		// 고정된 세 개의 숫자를 포함하는 배열
		int[] threeNums = new int[3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			threeNums[i] = Integer.parseInt(st.nextToken());
		}
		int maxScore = 0; // 얻을 수 있는 점수의 최댓값
		// Ones ~ Sixes 점수 계산
		for (int i = 1; i <= 6; i++) {
			if (canSelect[i-1] == 'N') continue;
			// 3개의 고정된 숫자 중 i의 개수를 세어 점수에 반영. 
			int score = 0;
			for (int j = 0; j < 3; j++) {
				if (threeNums[j] == i) score += i;
			}
			// 나머지 숫자는 i로 채워 Ones ~ Sixes로 얻을 수 있는 점수의 최댓값을 구한다.
			score += i*2;
			maxScore = Math.max(maxScore, score);
		}
		// Four of a Kind 점수 계산
		if (canSelect[6] == 'Y') {
			// 각 숫자 i에 대해 3개의 고정된 숫자에 i가 2개 이상 있어야 Four of a Kind를 만들 수 있다.
			for (int i = 1; i <= 6; i++) {
				int cnt = 0; // 3개의 고정된 숫자 중 i의 개수
				for (int j = 0; j < 3; j++) {
					if (threeNums[j] == i) cnt++;
				}
				if (cnt < 2) continue;
				// i가 2개 이상 있다면 최대 i*4의 점수를 얻을 수 있다.
				maxScore = Math.max(maxScore, i*4);
			}
		}
		// Full House 점수 계산
		if (canSelect[7] == 'Y') {
			// Full House를 만들 수 있는 경우는 세 가지뿐이다.
			// 3개의 고정된 숫자 중 i와 j의 개수가 (3, 0), (2, 1), (1, 2)인 경우
			// 즉, i와 j의 개수의 합이 3이면서 i의 개수는 0이 아니어야 한다.
			// 따라서 i와 j를 각각 1~6까지 순회하면서(단, i와 j는 다른 값이어야 한다.)
			// 위 조건을 만족하는 경우에만 Full House를 만들 수 있다.
			for (int i = 1; i <= 6; i++) {
				for (int j = 1; j <= 6; j++) {
					if (i == j) continue;
					int cnt1 = 0, cnt2 = 0; // 각각 i와 j의 개수
					for (int k = 0; k < 3; k++) {
						if (threeNums[k] == i) cnt1++;
						if (threeNums[k] == j) cnt2++;
					}
					// 위 조건을 만족하면 i가 3개, j가 2개인 Full House를 만들 수 있고, i*3+j*2의 점수를 획득할 수 있다.
					if (cnt1 + cnt2 < 3 || cnt1 == 0) continue;
					maxScore = Math.max(maxScore, i*3+j*2);
				}
			}
		}
		// Little Straight 점수 계산
		if (canSelect[8] == 'Y') {
			// 3개의 고정된 숫자 내에 6이 포함되어 있지 않아야 하고, 세 개의 숫자 모두 달라야 한다.
			boolean includeSix = false;
			for (int i = 0; i < 3; i++) {
				if (threeNums[i] == 6) includeSix = true;
			}
			// 위 조건을 만족하면 Little Straight를 만들 수 있고, 30점을 얻을 수 있다.
			if (!includeSix && threeNums[0] != threeNums[1] && threeNums[1] != threeNums[2] && threeNums[2] != threeNums[0]) {
				maxScore = Math.max(maxScore, 30);
			}
		}
		// Big Straight 점수 계산
		if (canSelect[9] == 'Y') {
			// 3개의 고정된 숫자 내에 1이 포함되어 있지 않아야 하고, 세 개의 숫자 모두 달라야 한다.
			boolean includeOne = false;
			for (int i = 0; i < 3; i++) {
				if (threeNums[i] == 1) includeOne = true;
			}
			// 위 조건을 만족하면 Big Straight를 만들 수 있고, 30점을 얻을 수 있다.
			if (!includeOne && threeNums[0] != threeNums[1] && threeNums[1] != threeNums[2] && threeNums[2] != threeNums[0]) {
				maxScore = Math.max(maxScore, 30);
			}
		}
		// Yacht 점수 계산
		if (canSelect[10] == 'Y') {
			// 3개의 고정된 숫자 모두 같은 숫자여야 한다.
			// 1~6까지의 숫자 각각에 대해 3개의 고정된 숫자가 모두 그 숫자인지를 확인한다.
			outer: for (int i = 1; i <= 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (threeNums[j] != i) continue outer;
				}
				// 조건을 만족하면 Yacht를 만들 수 있고, 50점을 얻을 수 있다.
				maxScore = Math.max(maxScore, 50);
			}
		}
		// Choice 점수 계산
		if (canSelect[11] == 'Y') {
			// Choice는 단순히 모든 주사위 눈의 총합이므로, 3개의 고정된 숫자의 합을 구한 뒤,
			// 최댓값으로 만들기 위해 나머지 두 숫자는 6으로 한다.
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += threeNums[i];
			}
			maxScore = Math.max(maxScore, sum+6*2);
		}
		System.out.println(maxScore);
	}

}
