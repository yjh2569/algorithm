package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1826_연료_채우기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		// 주유소 정보 저장 배열
		int[][] gas = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			gas[i][0] = Integer.parseInt(st.nextToken());
			gas[i][1] = Integer.parseInt(st.nextToken());
		}
		// 주유소들을 거리 순서대로 나열
		Arrays.sort(gas, (x, y) -> Integer.compare(x[0], y[0]));
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		// 접근 가능한 주유소들을 주유소 내 남아있는 연료 양이 많은 순서대로 저장하는 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(y[1], x[1]));
		// 주유소 최소 접근 횟수
		int cnt = 0;
		// 현재까지 고려한 주유소
		int idx = 0;
		// 현재 남아있는 연료 P가 L보다 크거나 같을 때까지 접근 가능한 주유소 중 
		// 연료 양이 많은 주유소 순으로 방문할 주유소를 추가한다.
		while (L > P) {
			// 우선 순위 큐에 접근 가능한 주유소들을 추가한다.
			while (idx < N && P >= gas[idx][0]) {
				pq.offer(gas[idx]);
				idx++;
			}
			// 더 이상 방문할 수 있는 주유소가 없는 경우
			if (pq.isEmpty()) {
				System.out.println(-1);
				System.exit(0);
			}
			// 접근 가능한 주유소 중 연료 양이 가장 많은 주유소를 방문할 주유소에 추가한다.
			int[] g = pq.poll();
			P += g[1];
			cnt++;
		}
		System.out.println(cnt);
	}

}
