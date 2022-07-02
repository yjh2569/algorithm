package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_23843_콘센트 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] charge = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			charge[i] = Integer.parseInt(st.nextToken());
		}
		// greedy algorithm을 활용한다.
		// 충전에 필요한 시간이 긴 전자기기부터 콘센트 중 충전 시간이 가장 적은 콘센트에 충전시킨다.
		// 충전에 필요한 시간 정렬
		Arrays.sort(charge);
		// 각 콘센트의 충전 시간을 우선순위 큐에 저장
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		// M개의 콘센트 초기화
		for (int i = 0; i < M; i++) {
			pq.offer(0);
		}
		// 가장 긴 충전 시간을 기록하기 위한 변수
		int max = 0;
		// 충전 시간이 가장 긴 전자기기부터 콘센트에 충전시킨다.
		for (int i = N-1; i >= 0; i--) {
			int u = pq.poll();
			pq.offer(u + charge[i]);
			max = Math.max(max, u + charge[i]);
		}
		System.out.println(max);
		
	}

}
