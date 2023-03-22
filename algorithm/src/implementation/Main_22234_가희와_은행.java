package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_22234_가희와_은행 {
	// 고객의 번호, 업무 처리 시간, 은행에 도착한 시간을 포함하는 고객 클래스
	static class Customer implements Comparable<Customer> {
		int p, t, c;
		public Customer(int p, int t, int c) {
			this.p = p;
			this.t = t;
			this.c = c;
		}
		// 현재 시간이 cur일 때 은행에 도착했는지를 파악하는 함수
		public boolean isArrived(int cur) {
			return this.c <= cur;
		}
		// 아직 은행에 도착하지 않은 고객을 도착 시간이 빠른 순서대로 정렬하기 위해 Comparable 구현
		public int compareTo(Customer c) {
			return Integer.compare(this.c, c.c);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		// 현재 은행에 있는 고객을 저장하는 큐
		Queue<Customer> customers = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			customers.offer(new Customer(p, t, 0));
		}
		int M = Integer.parseInt(br.readLine());
		// 아직 은행에 도착하지 않은 고객을 저장하는 우선순위 큐
		PriorityQueue<Customer> notArrivedCustomers = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			notArrivedCustomers.offer(new Customer(p, t, c));
		}
		int curTime = 0; // 현재 시간
		StringBuilder sb = new StringBuilder(); // 출력 저장용 StringBuilder
		// 현재 시간이 W가 되기 전까지 업무 수행
		while (curTime < W) {
			// 큐의 가장 앞에 있는 고객이 업무 처리
			Customer c = customers.poll();
			int workTime = Math.min(c.t, T); // 실제 업무 처리 시간
			c.t -= workTime; // 업무를 처리하고 남은 업무 처리 시간을 구한다.
			// workTime 동안 고객 c가 창구에서 업무를 처리하므로 이를 sb에 저장
			while (curTime < W && workTime > 0) {
				curTime++;
				workTime--;
				sb.append(c.p).append("\n");
			}
			// 은행에 오지 않은 고객 중 curTime 때 은행에 도착하는 고객을 큐에 추가한다.
			while (!notArrivedCustomers.isEmpty() && notArrivedCustomers.peek().isArrived(curTime)) {
				customers.offer(notArrivedCustomers.poll());
			}
			// 고객 c가 아직 처리할 업무가 남았다면 다시 큐에 넣는다.
			if (c.t > 0) customers.offer(c);
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
