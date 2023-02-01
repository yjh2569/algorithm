package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_21773_가희와_프로세스_1 {
	// 프로세스들을 표현하는 클래스
	static class Process implements Comparable<Process> {
		int id, time, priority;
		public Process(int id, int time, int priority) {
			this.id = id;
			this.time = time;
			this.priority = priority;
		}
		// 프로세스를 실행했을 때 다른 프로세스의 우선순위를 높이지 않고 자신의 우선순위를 낮춘다. 
		public void run() {
			this.time--;
			this.priority--;
		}
		// 프로세스를 우선순위가 높은 순서대로 정렬하기 위해 Comparable 구현
		@Override
		public int compareTo(Process p) {
			return this.priority == p.priority ? Integer.compare(this.id, p.id) 
					: Integer.compare(p.priority, this.priority);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		// 프로세스들을 우선순위가 높은 순서대로 정렬하기 위한 우선순위 큐
		PriorityQueue<Process> queue = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			int priority = Integer.parseInt(st.nextToken());
			queue.offer(new Process(id, time, priority));
		}
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			// 더 이상 실행할 프로세스가 없는 경우
			if (queue.isEmpty()) break;
			// 가장 우선순위가 높은 프로세스를 꺼내 실행시킨다.
			Process p = queue.poll();
			sb.append(p.id).append("\n");
			p.run();
			// 프로세스를 아직 실행시킬 수 있다면 우선순위 큐에 다시 넣는다.
			if (p.time > 0) queue.offer(p);
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
