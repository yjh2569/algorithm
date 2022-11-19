package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1374_강의실 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 서로 다른 강의 시작 시간과 종료 시간을 모두 저장하는 우선 순위 큐
		PriorityQueue<Integer> time = new PriorityQueue<>();
		// 각 강의 시작 시간에 시작하는 강의의 개수를 map 형태로 저장
		Map<Integer, Integer> startTime = new HashMap<>();
		// 각 강의 종료 시간에 끝나는 강의의 개수를 map 형태로 저장
		Map<Integer, Integer> endTime = new HashMap<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			st.nextToken();
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			// time에는 서로 다른 강의 시작 시간과 종료 시간을 저장
			if (startTime.get(s) == null && endTime.get(s) == null) time.offer(s); 
			if (startTime.get(e) == null && endTime.get(e) == null) time.offer(e);
			// 초기화
			startTime.putIfAbsent(s, 0);
			endTime.putIfAbsent(e, 0);
			// 강의가 동시에 시작 또는 종료하는 경우를 위해 startTime과 endTime에 
			// 동시에 시작 또는 종료하는 강의의 개수를 저장
			startTime.put(s, startTime.get(s)+1);
			endTime.put(e, endTime.get(e)+1);
		}
		// 최소한으로 필요한 강의장의 개수
		int max = 0;
		// 현재 사용하는 강의장의 개수
		int cur = 0;
		while (!time.isEmpty()) {
			int curTime = time.poll();
			// 종료하는 강의가 있는 경우 강의장의 개수를 감소시킨다.
			if (endTime.get(curTime) != null) {
				cur -= endTime.get(curTime);
			}
			// 시작하는 강의가 있는 경우 강의장의 개수를 증가시키고 최댓값을 갱신한다.
			if (startTime.get(curTime) != null) {
				cur += startTime.get(curTime);
				max = Math.max(max, cur);
			}
		}
		System.out.println(max);
	}

}
