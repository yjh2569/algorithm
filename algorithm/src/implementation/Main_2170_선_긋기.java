package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2170_선_긋기 {
	// 각 선의 왼쪽 끝, 오른쪽 끝 좌표를 나타내는 클래스
	static class Line implements Comparable<Line> {
		int x, y;
		public Line(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Line l) {
			return Integer.compare(this.x, l.x);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Line> pq = new PriorityQueue<>(); // 주어진 선들을 왼쪽 끝 좌표가 작은 순서대로 저장하는 우선순위 큐
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pq.offer(new Line(x, y));
		}
		int sum = 0; // 그려진 선들의 총 길이
		int min = Integer.MAX_VALUE; // 현재까지 가장 오른쪽에 있는, 끊어지지 않은 선의 왼쪽 끝의 좌표
		int max = Integer.MIN_VALUE; // 현재까지 가장 오른쪽에 있는, 끊어지지 않은 선의 오른쪽 끝의 좌표
		// 선을 하나씩 꺼내서, 겹치지 않는 선들의 길이를 더해 나간다.
		for (int i = 0; i < N; i++) {
			Line l = pq.poll();
			min = Math.max(max, l.x);
			sum += Math.max(l.y - min, 0);
			max = Math.max(max, l.y);
		}
		System.out.println(sum);
	}

}
