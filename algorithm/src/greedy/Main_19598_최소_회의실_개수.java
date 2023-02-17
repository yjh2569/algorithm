package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19598_최소_회의실_개수 {
	// 회의의 시작 시간과 끝 시간을 저장하는 클래스
	static class Discussion implements Comparable<Discussion> {
		int start, end;
		public Discussion(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public int compareTo(Discussion d) {
			return Integer.compare(this.start, d.start);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Discussion> discussions = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			discussions.add(new Discussion(start, end));
		}
		// 회의들을 시작 시간이 빠른 순서대로 정렬한다.
		Collections.sort(discussions);
		int cnt = 0; // 최소 회의실 개수
		// 현재 회의들이 끝나는 시간을 저장하는 우선순위 큐
		PriorityQueue<Integer> endTimes = new PriorityQueue<>();
		// 시작 시간이 빠른 회의부터 회의실을 배정한다.
		// 이때 회의가 끝난 회의실을 정리하기 위해 현재 회의의 시작 시간보다 이른, 회의가 끝나는 시간을 endTimes에서 제거한다.
		// 그 후 현재 회의의 끝나는 시간을 endTimes에 넣는다.
		// 그러면 endTimes의 크기는 현재 배정된 회의실의 개수가 되고, 이의 최댓값이 곧 최소 회의실 개수가 된다.
		for (int i = 0; i < N; i++) {
			Discussion d = discussions.get(i);
			while (!endTimes.isEmpty() && endTimes.peek() <= d.start) endTimes.poll();
			endTimes.offer(d.end);
			cnt = Math.max(cnt, endTimes.size());
		}
		System.out.println(cnt);
	}

}
