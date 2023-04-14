package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1082_방_번호 {
	static int N, M;
	static int[] prices;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		prices = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		// greedy algorithm을 이용한다.
		// 우선, 가장 싼 가격의 숫자만으로 방 번호를 만들어 길이가 가장 긴 방 번호를 만든다.
		// 만약 가장 싼 가격의 숫자가 0이라면 그 다음으로 싼 가격의 숫자를 맨 앞에 붙인다.
		// 그리고 비용이 M이 넘지 않게 앞 숫자부터 큰 숫자로 바꾸는 것을 시도한다.
		int[] result = new int[51]; // 방 번호를 나타내는 배열
		// 가장 싼 가격의 숫자를 찾는다.
		int minIdx = findMin(0);
		int cost = 0; // result라는 방 번호를 만들기 위한 비용
		int idx = 0; // 방 번호의 길이
		// 가장 싼 가격의 숫자가 0인 경우
		if (minIdx == 0) {
			// 1~(N-1) 중 가장 싼 가격의 숫자를 찾는다.
			int minIdx2 = findMin(1);
			// 숫자의 가격이 M 이하일 경우에만 방 번호에 추가할 수 있음에 유의
			if (prices[minIdx2] <= M) {
				result[idx++] = minIdx2;
				cost += prices[minIdx2];
			// 숫자의 가격이 M보다 클 경우 만들 수 있는 방 번호는 0뿐이다.
			} else {
				System.out.println("0");
				System.exit(0);
			}
		}
		// 가장 싼 가격의 숫자를 방 번호에 추가한다.
		while (cost+prices[minIdx] <= M) {
			cost += prices[minIdx];
			result[idx++] = minIdx;
		}
		// 방 번호의 앞자리 숫자부터 큰 숫자로 바꾼다.
		for (int i = 0; i < idx; i++) {
			for (int j = N-1; j > 0; j--) {
				// 방 번호의 i번째 숫자를 j로 교체해도 비용이 M이 넘지 않는 경우 j로 교체한다.
				if (cost - prices[result[i]] + prices[j] <= M) {
					cost = cost - prices[result[i]] + prices[j];
					result[i] = j;
					break; // 가장 큰 j를 찾는 것이 목적이므로 j를 찾았다면 그만둔다.
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < idx; i++) {
			sb.append(result[i]);
		}
		System.out.println(sb.toString());
	}

	private static int findMin(int start) {
		int minIdx = 0;
		int min = Integer.MAX_VALUE;
		for (int i = start; i < N; i++) {
			if (min > prices[i]) {
				min = prices[i];
				minIdx = i;
			}
		}
		return minIdx;
	}

}
