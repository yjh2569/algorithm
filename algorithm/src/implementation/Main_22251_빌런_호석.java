package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_22251_빌런_호석 {
	static int N, K, P, X;
	static boolean[][] LedOfNumbers;
	static int[][] ReqNumOfLed;
	static int res;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		// 각 숫자를 LED로 나타냈을 때 위, 왼쪽위, 왼쪽아래, 오른쪽아래, 오른쪽위, 가운데 LED에 불이 들어오는지를 나타내는 배열
		LedOfNumbers = new boolean[10][7];
		LedOfNumbers[0] = new boolean[] {true, true, true, true, true, true, false};
		LedOfNumbers[1] = new boolean[] {false, false, false, false, true, true, false};
		LedOfNumbers[2] = new boolean[] {true, false, true, true, false, true, true};
		LedOfNumbers[3] = new boolean[] {true, false, false, true, true, true, true};
		LedOfNumbers[4] = new boolean[] {false, true, false, false, true, true, true};
		LedOfNumbers[5] = new boolean[] {true, true, false, true, true, false, true};
		LedOfNumbers[6] = new boolean[] {true, true, true, true, true, false, true};
		LedOfNumbers[7] = new boolean[] {true, false, false, false, true, true, false};
		LedOfNumbers[8] = new boolean[] {true, true, true, true, true, true, true};
		LedOfNumbers[9] = new boolean[] {true, true, false, true, true, true, true};
		// i를 j로 바꾸기 위해 반전시켜야 하는 LED의 개수를 (i, j)번째 원소로 가지는 배열
		ReqNumOfLed = findReqNumOfLed();
		// 일의 자리부터 10^K의 자리까지 숫자를 변경해보면서 가능한 경우의 수를 센다.
		recur(0, X, 0, 1);
		System.out.println(res);
	}
	// 반전시켜야 하는 LED의 개수를 구하는 함수
	private static int[][] findReqNumOfLed() {
		int[][] result = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = i+1; j < 10; j++) {
				int cnt = 0;
				// 숫자 i와 j를 LED로 표현했을 때 한쪽만 켜져있는 LED를 찾는다.
				for (int k = 0; k < 7; k++) {
					if (LedOfNumbers[i][k] ^ LedOfNumbers[j][k]) {
						cnt++;
					}
				}
				// 숫자 i를 j로 바꾸나, 숫자 j를 i로 바꾸나 반전이 필요한 LED의 개수는 동일하다.
				result[i][j] = result[j][i] = cnt;
			}
		}
		return result;
	}
	// 재귀적으로 일의 자리부터 10^K의 자리까지 숫자를 바꾸는 함수
	private static void recur(int k, int cur, int cnt, int temp) {
		// 모든 자리의 숫자를 바꾸기 시도한 경우
		if (k == K) {
			// 최소 1개의 LED를 바꿔야 하고, 현재 층의 위치가 1층 이상 N층 이하여야 한다.
			if (cnt > 0 && cur > 0 && cur <= N) res++;
			return;
		}
		// 10^k의 자리의 숫자를 i로 바꾼다.
		for (int i = 0; i < 10; i++) {
			// 만약 반전시킨 LED의 개수가 P 초과면 해당 숫자로 바꿀 수 없다.
			if (cnt+ReqNumOfLed[(cur/temp)%10][i] > P) continue;
			recur(k+1, cur-((cur/temp)%10)*temp+i*temp, cnt+ReqNumOfLed[(cur/temp)%10][i], temp*10);
		}
	}
}
