package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1327_소트_게임 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int s = 0; // 시작 순열을 정수로 나타낸 값
		int target = 0; // 오름차순으로 만든 순열을 정수로 나타낸 값({1, 2, 3, 4, 5} -> 12345)
		st = new StringTokenizer(br.readLine());
		int temp = 1;
		for (int i = 0; i < N-1; i++) temp *= 10;
		// 입력을 정수로 표현
		for (int i = 1; i <= N; i++) {
			s += Integer.parseInt(st.nextToken())*temp;
			target += i*temp;
			temp /= 10;
		}
		// BFS를 활용해 시작 순열에서 오름차순의 순열을 만들기까지 뒤집어야 하는 최소 횟수를 구한다.
		Queue<Integer> q = new LinkedList<>();
		Map<Integer, Boolean> visited = new HashMap<>();
		q.offer(s);
		visited.put(s, true);
		int time = 0; // 뒤집어야 하는 최소 횟수
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll(); 
				// 순열을 오름차순으로 만든 경우
				if (u == target) {
					System.out.println(time);
					return;
				}
				// i번째 숫자부터 K개를 뒤집는 것을 시도
				for (int i = 0; i <= N-K; i++) {
					int newInt = 0;
					temp = 1;
					for (int j = 0; j < N-1; j++) temp *= 10;
					for (int j = 0; j < i; j++) {
						newInt += ((u/temp)%10)*temp;
						temp /= 10;
					}
					int temp2 = temp;
					for (int j = 0; j < K-1; j++) temp2 /= 10;
					for (int j = 0; j < K; j++) {
						newInt += ((u/temp2)%10)*temp;
						temp /= 10;
						temp2 *= 10;
					}
					for (int j = K+i; j < N; j++) {
						newInt += ((u/temp)%10)*temp;
						temp /= 10;
					}
					if (visited.containsKey(newInt)) continue;
					visited.put(newInt, true);
					q.offer(newInt);
				}
				qLen--;
			}
			time++;
		}
		// 아무리 뒤집어도 오름차순으로 못 만드는 경우
		System.out.println(-1);
	}

}
