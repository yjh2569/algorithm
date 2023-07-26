package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_22252_정보_상인_호석 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		// 각 고릴라에 대해, 현재 가지고 있는 정보의 비용을 비싼 순서대로 저장하는 우선순위 큐를 배정하는 맵
		Map<String, PriorityQueue<Long>> map = new HashMap<>();
		long sum = 0l; // 쓴 돈의 총합
		for (int q = 0; q < Q; q++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			// 고릴라가 정보를 얻는 경우
			if (type == 1) {
				String name = st.nextToken();
				int K = Integer.parseInt(st.nextToken());
				// 이전에 쿼리에서 나온 고릴라가 아닌 경우
				map.putIfAbsent(name, new PriorityQueue<Long>(Collections.reverseOrder()));
				// k개의 정보 획득
				for (int k = 0; k < K; k++) {
					map.get(name).offer(Long.parseLong(st.nextToken()));
				}
			// 호석이가 고릴라로부터 정보를 구입하는 경우
			} else if (type == 2) {
				String name = st.nextToken();
				// 해당 고릴라가 이전 쿼리에서 나오지 않은 경우
				if (!map.containsKey(name)) continue;
				PriorityQueue<Long> pq = map.get(name);
				int b = Integer.parseInt(st.nextToken());
				// b개의 비싼 정보를 구입하되, 고릴라가 b개보다 적은 정보를 가진 경우 고릴라가 가진 모든 정보를 구입한다.
				while (!pq.isEmpty() && b-- > 0) {
					sum += pq.poll();
				}
			}
		}
		System.out.println(sum);
	}

}
