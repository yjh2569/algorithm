package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1027_고층_건물 {
	static int N;
	static int[] buildings;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		buildings = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
		int max = 0; // 가장 많은 고층 빌딩이 보이는 빌딩에서 볼 수 있는 고층 빌딩의 수
		for (int i = 0; i < N; i++) {
			max = Math.max(max, countBuildings(i));
		}
		System.out.println(max);
	}
	// cur 위치에서 볼 수 있는 고층 빌딩의 수를 구하는 함수
	private static int countBuildings(int cur) {
		int cnt = 0; // 볼 수 있는 고층 빌딩의 수
		// 자기 자신을 제외한 나머지 빌딩을 볼 수 있는지 체크한다.
		for (int i = 0; i < N; i++) {
			if (i == cur) continue;
			if (checkIfCanSee(cur, i)) cnt++;
		}
		return cnt;
	}
	// cur 위치의 빌딩에서 target 위치의 빌딩을 볼 수 있는지를 체크하는 함수
	private static boolean checkIfCanSee(int cur, int target) {
		// target이 cur보다 왼쪽에 있는 경우
		if (cur > target) {
			long base = buildings[cur] - buildings[target];
			// target과 cur 사이에 있는 모든 빌딩이 (base)/(cur-target) < val/(cur-i) 조건을 만족해야 한다.
			// 즉, cur과 target 사이를 잇는 선분의 기울기가 cur과 i 사이를 잇는 선분의 기울기보다 작아야 한다.
			for (int i = target+1; i < cur; i++) {
				long val = buildings[cur] - buildings[i];
				if (base*(cur-i) >= val*(cur-target)) return false;
			}
		// target이 cur보다 오른쪽에 있는 경우
		} else {
			long base = buildings[target] - buildings[cur];
			// target과 cur 사이에 있는 모든 빌딩이 (base)/(cur-target) > val/(cur-i) 조건을 만족해야 한다.
			// 즉, cur과 target 사이를 잇는 선분의 기울기가 cur과 i 사이를 잇는 선분의 기울기보다 커야 한다.
			for (int i = cur+1; i < target; i++) {
				long val = buildings[i] - buildings[cur];
				if (base*(i-cur) <= val*(target-cur)) return false;
			}
		}
		return true;
	}

}
