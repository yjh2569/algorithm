package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_7983_내일_할거야 {
	// 과제를 끝내는데 걸리는 시간과 마감 기한을 나타내는 클래스
	static class Homework implements Comparable<Homework> {
		int d, t;
		public Homework(int d, int t) {
			this.d = d;
			this.t = t;
		}
		public int compareTo(Homework h) {
			return Integer.compare(h.t, this.t);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Homework> pq = new PriorityQueue<>(); // 과제를 마감 기한이 늦은 순서대로 저장하는 우선순위 큐
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			pq.offer(new Homework(d, t));
		}
		// 마감 기한이 늦은 과제부터 마감 기한에 딱 맞춰서 끝낸다.
		// 그리고 다음 과제부터는 그렇게 했을 때 과제를 안 하는 마지막 날과, 
		// 다음 과제의 마감 기한 중 더 빠른 날을 실제 마감 기한으로 삼고 그 마감 기한에 딱 맞춰서 끝낸다. 
		int cur = Integer.MAX_VALUE; // 현재까지 아무것도 안 할 수 있는 기간의 마지막 날
		while (!pq.isEmpty()) {
			Homework h = pq.poll();
			cur = Math.min(h.t,  cur);
			cur -= h.d;
		}
		System.out.println(cur);
		
	}

}
