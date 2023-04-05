package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12731_열차_시간표_Small {
	// 기차의 출발 시각과 도착 시각을 포함하는 클래스
	static class Train implements Comparable<Train> {
		int startTime, endTime;
		public Train(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
		}
		// 출발 시각 순서대로 정렬할 필요가 있다.
		public int compareTo(Train t) {
			return Integer.compare(this.startTime, t.startTime);
		}
	}
	static BufferedReader br;
	static StringTokenizer st;
	static int T;
	public static void main(String[] args) throws IOException {
		// 입력
		br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int n = 1; n <= N; n++) {
			T = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int NA = Integer.parseInt(st.nextToken());
			int NB = Integer.parseInt(st.nextToken());
			// 기차역 A, B에 계획된 기차의 출발 시각과 도착 시각을 저장하는 우선순위 큐
			// 출발 시각이 빠른 순서대로 저장
			PriorityQueue<Train> plansA = new PriorityQueue<>();
			PriorityQueue<Train> plansB = new PriorityQueue<>();
			// 입력을 받는 과정이 기차역 A, B가 동일해 함수로 처리
			putTimesToPlans(plansA, NA);
			putTimesToPlans(plansB, NB);
			// 출발이 가장 빠른 기차의 출발 시각을 나타낸다.
			// 만약 기차역에 출발할 기차가 남아있지 않으면 Integer.MAX_VALUE를 가지도록 한다.
			int curTimeA = Integer.MAX_VALUE;
			int curTimeB = Integer.MAX_VALUE;
			// 특정 기차역에서 기차를 출발시키면 다른 기차역에 기차가 도착하게 되는데, 
			// 해당 기차는 나중에 다른 운행 계획을 만족시킬 수도 있기 때문에 이를 각 기차역에 대해 저장해둔다.
			// 이때 정렬 기준은 도착 시각이 빠른 순서대로 정렬해 존재하는 기차를 최대한 활용한다.
			PriorityQueue<Train> waitsA = new PriorityQueue<>((x, y) -> Integer.compare(x.endTime, y.endTime));
			PriorityQueue<Train> waitsB = new PriorityQueue<>((x, y) -> Integer.compare(x.endTime, y.endTime));
			// 각 기차역에서 첫차로 출발시켜야 하는 기차 수
			int cntA = 0;
			int cntB = 0;
			// 모든 운행 계획을 성사시킬 때까지 진행
			while (!plansA.isEmpty() || !plansB.isEmpty()) {
				// 각 기차역에서 운행 계획 중 출발이 가장 빠른 기차의 출발 시각을 찾는다.
				if (!plansA.isEmpty()) curTimeA = plansA.peek().startTime;
				else curTimeA = Integer.MAX_VALUE;
				if (!plansB.isEmpty()) curTimeB = plansB.peek().startTime;
				else curTimeB = Integer.MAX_VALUE;
				// 만약 기차역 A에서 더 빠른 운행 계획이 있으면 해당 기차를 B로 운행한다.
				if (curTimeA <= curTimeB) {
					Train t = plansA.poll();
					cntA = moveTrain(t, waitsA, waitsB, cntA);
				// 기차역 B에서 더 빠른 운행 계획이 있으면 해당 기차를 A로 운행한다.
				} else {
					Train t = plansB.poll();
					cntB = moveTrain(t, waitsB, waitsA, cntB);
				}
			}
			// 출력
			sb.append("Case #").append(n).append(": ").append(cntA).append(" ").append(cntB).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 운행 계획 입력을 M개 받아 plans에 저장하는 함수
	private static void putTimesToPlans(PriorityQueue<Train> plans, int M) throws IOException {
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String[] startMS = st.nextToken().split(":");
			String[] endMS = st.nextToken().split(":");
			int startTime = Integer.parseInt(startMS[0])*60+Integer.parseInt(startMS[1]);
			int endTime = Integer.parseInt(endMS[0])*60+Integer.parseInt(endMS[1]);
			plans.offer(new Train(startTime, endTime));
		}		
	}
	// 기차 t를 1번 역에서 2번 역까지 운행하는 함수
	// waits1은 1번 역에서 대기 중인 기차들, waits2는 2번 역에서 대기 중인 기차들, 
	// cnt는 1번 역에서 첫차로 출발해야 하는 기차의 개수를 의미한다.
	private static int moveTrain(Train t, PriorityQueue<Train> waits1, PriorityQueue<Train> waits2, int cnt) {
		// 1번 역에 2번 역으로부터 온 기차가 남아있고, 회차시간을 고려했을 때 현재 출발할 수 있는 경우
		if (!waits1.isEmpty() && waits1.peek().endTime+T <= t.startTime) waits1.poll();
		// 그렇지 않은 경우 새로운 기차를 첫차로 출발시킨다.
		else cnt++;
		// 기차 t는 2번 역에 도착한다.
		waits2.offer(t);
		return cnt;
	}
}
