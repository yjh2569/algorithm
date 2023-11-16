package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_7507_올림픽_게임 {
	// 경기 일자, 시작 및 종료 시간을 나타내는 클래스
	static class Game implements Comparable<Game> {
		int day, startTime, endTime;

		public Game(int day, int startTime, int endTime) {
			this.day = day;
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public int compareTo(Game g) {
			return this.day == g.day
					? this.endTime == g.endTime ? Integer.compare(this.startTime, g.startTime)
							: Integer.compare(this.endTime, g.endTime)
					: Integer.compare(this.day, g.day);
		}
	}

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int m = Integer.parseInt(br.readLine());
			// 그리디 알고리즘을 활용
			// 종료시간이 빠른 경기 순으로 정렬한 뒤 현재 뽑은 경기를 볼 수 있을 때마다 해당 경기를 관람한다.
			PriorityQueue<Game> pq = new PriorityQueue<>();
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int day = Integer.parseInt(st.nextToken());
				String start = st.nextToken();
				String end = st.nextToken();
				// 편의를 위해 hhmm 형식의 시간을 분 단위로 바꾼다.
				int startTime = Integer.parseInt(start.substring(0, 2)) * 60 + Integer.parseInt(start.substring(2));
				int endTime = Integer.parseInt(end.substring(0, 2)) * 60 + Integer.parseInt(end.substring(2));
				pq.offer(new Game(day, startTime, endTime));
			}
			int curDay = 0; // 현재 일자
			int curTime = 0; // 현재 시간
			int cnt = 0; // 관람 횟수
			while (!pq.isEmpty()) {
				Game g = pq.poll();
				// 경기를 볼 수 있을 때마다 관람한다.
				if (curDay != g.day || curTime <= g.startTime) {
					curDay = g.day;
					curTime = g.endTime;
					cnt++;
				}
			}
			sb.append("Scenario #" + t + ":").append("\n").append(cnt).append("\n").append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-2);
		System.out.println(sb.toString());
	}

}
