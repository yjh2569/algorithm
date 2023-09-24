package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1263_시간_관리 {
	// 할 일의 걸리는 시간과 마감 시간을 저장하는 클래스
	static class Todo implements Comparable<Todo> {
		int t, s;
		public Todo(int t, int s) {
			this.t = t;
			this.s = s;
		}
		public int compareTo(Todo t) {
			return this.s == t.s ? Integer.compare(t.t, this.t) : Integer.compare(t.s, this.s);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// greedy algorithm을 이용한다.
		// 마감 시간이 가장 늦은 일부터, 마감 시간에 일이 끝나도록 일을 처리한다.
		// 이를 PriorityQueue를 활용해 마감 시간이 가장 늦은 일부터 고려한다.
		// 현재 고려하고 있는 일의 마감 시간이 마감 시간이 더 늦은 일을 시작한 시간보다 늦으면, 
		// 현재 고려하고 있는 일의 마감 시간을 마감 시간이 더 늦은 일을 시작한 시간으로 간주한다.  
		PriorityQueue<Todo> pq = new PriorityQueue<>();
		int endTime = 0; // 현재 고려하고 있는 일의 실질적 마감 시간
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			endTime = Math.max(endTime, s);
			pq.offer(new Todo(t, s));
		}
		while (!pq.isEmpty()) {
			if (endTime < 0) break; // 0시부터 일을 해도 모든 일을 끝내지 못하는 경우 
			Todo t = pq.poll();
			// 현재 고려하고 있는 일의 실질적 마감 시간을 구한다.
			endTime = Math.min(endTime, t.s);
			// 현재 고려하고 있는 일이 걸리는 시간만큼 실질적 마감 시간을 앞당긴다.
			endTime -= t.t;
		}
		System.out.println(endTime < 0 ? -1 : endTime);
	}

}
