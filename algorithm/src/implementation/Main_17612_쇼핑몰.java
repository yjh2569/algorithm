package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17612_쇼핑몰 {
	// 카운터 정보를 나타내는 클래스
	static class Counter implements Comparable<Counter>{
		int num, time; // 카운터 번호와 카운터 계산 시간을 나타낸다.
		public Counter(int num, int time) {
			this.num = num;
			this.time = time;
		}
		public int compareTo(Counter c) {
			return this.time == c.time ? Integer.compare(this.num, c.num) : Integer.compare(this.time, c.time);
		}
	}
	// 고객 정보를 나타내는 클래스
	static class Customer implements Comparable<Customer>{
		int id, time, num; // 고객의 id, 고객이 계산을 마친 뒤의 시간, 고객이 계산한 카운터 번호를 나타낸다.
		public Customer(int id, int time, int num) {
			this.id = id;
			this.time = time;
			this.num = num;
		}
		public int compareTo(Customer c) {
			return this.time == c.time ? Integer.compare(c.num, this.num) : Integer.compare(this.time, c.time);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 카운터 정보들을 저장해두는 우선순위 큐
		// 현재까지 계산한 시간이 짧고, 계산 시간이 같은 경우 카운터 번호가 작은 카운터가 우선순위가 높다.
		PriorityQueue<Counter> counters = new PriorityQueue<>();
		// 초기화
		for (int i = 1; i <= K; i++) {
			counters.add(new Counter(i, 0));
		}
		// 고객 정보들을 저장해두는 우선순위 큐
		// 고객이 계산을 끝낸 시간이 빠르고, 계산이 끝난 시간이 같다면 출구까지의 거리가 짧은, 즉 계산한 카운터 번호가 큰 고객이 우선순위가 높다. 
		PriorityQueue<Customer> customers = new PriorityQueue<>();
		// 고객을 한 명씩 받으면서 적절한 카운터에 배치한다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// 우선순위가 가장 높은 카운터를 선택
			Counter c = counters.poll();
			// 해당 카운터에 고객 할당
			counters.offer(new Counter(c.num, c.time+w));
			customers.offer(new Customer(id, c.time+w, c.num));
		}
		// 결과값
		long res = 0;
		// 결과값을 계산할 때 int 범위를 넘어갈 수 있음에 유의한다.
		for (int i = 1; i <= N; i++) {
			res += (long)i*(long)customers.poll().id;
		}
		System.out.println(res);
	}

}
