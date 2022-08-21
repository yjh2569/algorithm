package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19640_화장실의_규칙 {
	// 사원 클래스
	static class Employee implements Comparable<Employee>{
		// 사원의 줄 번호, 근무 일수, 화장실이 급한 정도
		int m, d, h;
		// 해당 사원이 데카가 맞는지를 나타내는 변수
		boolean me;
		public Employee(int m, int d, int h, boolean me) {
			this.m = m;
			this.d = d;
			this.h = h;
			this.me = me;
		}
		// 비교 조건
		public int compareTo(Employee e) {
			return this.d == e.d ? (
					this.h == e.h ? Integer.compare(this.m, e.m) : Integer.compare(e.h, this.h)
				) : Integer.compare(e.d, this.d);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 입력으로 받은 사원들에게 부여할 줄 번호
		int m = 0;
		// 입력을 M개의 줄로 나누어 받는다.
		ArrayList<Queue<Employee>> qs = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			qs.add(new LinkedList<>());
		}
		// 각 줄의 선두에 선 사원들의 우선 순위 큐
		PriorityQueue<Employee> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			if (m == M) m = 0;
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if (i == K) qs.get(m).offer(new Employee(m++, d, h, true));
			else qs.get(m).offer(new Employee(m++, d, h, false));
		}
		// 데카가 화장실을 이용하기 전에 화장실을 사용한 사원의 수
		int cnt = 0;
		// 각 줄에서 선두를 뽑아 pq에 넣는다.
		for (int i = 0; i < M; i++) {
			if (!qs.get(i).isEmpty()) pq.offer(qs.get(i).poll());
		}
		// 데카 차례가 올 때까지 각 줄의 선두 중 화장실을 이용할 사람을 뽑아나간다.
		while (true) {
			Employee e = pq.poll();
			// 데카 차례가 온 경우
			if (e.me) break;
			// 선두가 빠져나간 줄에서 다음 선두를 뽑아 pq에 넣는다.
			if (!qs.get(e.m).isEmpty()) pq.offer(qs.get(e.m).poll());
			cnt++;
		}
		System.out.println(cnt);
	}
}
