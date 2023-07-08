package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5464_주차장 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] fees = new int[N+1]; // 각 주차 공간의 단위 무게 당 요금
		for (int i = 1; i <= N; i++) {
			fees[i] = Integer.parseInt(br.readLine());
		}
		int[] weights = new int[M+1]; // 각 차량의 무게
		for (int i = 1; i <= M; i++) {
			weights[i] = Integer.parseInt(br.readLine());
		}
		int[] locations = new int[M+1]; // 각 차량의 현재 위치한 주차 공간
		PriorityQueue<Integer> parkables = new PriorityQueue<>(); // 주차 가능한 공간을 저장하는 우선순위 큐
		// 초기화
		for (int i = 1; i <= N; i++) {
			parkables.offer(i);
		}
		Queue<Integer> q = new LinkedList<>(); // 현재 대기하고 있는 차량들이 있는 큐
		int sum = 0; // 주차장이 벌어들일 수 있는 총수입
		for (int i = 0; i < 2*M; i++) {
			int n = Integer.parseInt(br.readLine());
			// 차량이 주차장에 들어오는 경우
			if (n > 0) {
				// 우선 대기열에 넣는다.
				q.offer(n);
				// 주차장에 빈 자리가 있으면 먼저 온 차량부터 가장 번호가 작은 주차 공간에 주차한다.
				if (!parkables.isEmpty()) {					
					int parkable = parkables.poll();
					int car = q.poll();
					locations[car] = parkable;
					sum += weights[car] * fees[parkable];
				}
			// 차량이 주차장에서 나가는 경우
			} else {
				n = -n; // 양수로 바꿔준다.
				// 해당 차량을 주차 공간에서 제거한다.
				parkables.offer(locations[n]);
				locations[n] = 0;
				// 대기열에 차량이 있는 경우 먼저 온 차량부터 가장 번호가 작은 주차 공간에 주차한다.
				if (!q.isEmpty()) {
					int car = q.poll();
					int parkable = parkables.poll();
					locations[car] = parkable;
					sum += weights[car] * fees[parkable];
				}
			}
		}
		System.out.println(sum);
	}

}
