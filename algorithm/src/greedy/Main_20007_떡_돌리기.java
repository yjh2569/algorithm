package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20007_떡_돌리기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new int[] {v, w});
			graph.get(v).add(new int[] {u, w});
		}
		// dijkstra's algorithm을 활용해 Y와 각 지점까지의 최단 거리를 구한다.
		int[] dp = new int[N];
		// 초기화
		Arrays.fill(dp, Integer.MAX_VALUE/10);
		dp[Y] = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
		boolean[] visited = new boolean[N];
		pq.offer(new int[] {Y, 0});
		while (!pq.isEmpty()) {
			int[] u = pq.poll();
			if (visited[u[0]]) continue;
			visited[u[0]] = true;
			for (int[] v : graph.get(u[0])) {
				if (!visited[v[0]] && dp[v[0]] > dp[u[0]] + v[1]) {
					dp[v[0]] = dp[u[0]] + v[1];
					pq.offer(new int[] {v[0], dp[v[0]]});
				}
			}
		}
		// 거리가 가까운 집부터 방문하기 위해 dp 배열을 정렬한다.
		Arrays.sort(dp);
		boolean canDelivery = true; // 모든 집에 방문할 수 있는지를 나타내는 변수
		int sum = 0; // 하루 동안 이동한 거리
		int cnt = 0; // 현재 일수
		// dp[0]은 Y에서 Y까지의 거리인 0이므로 생략
		for (int i = 1; i < N; i++) {
			// 하루 안에 i까지 왕복할 수 없는 경우
			if (dp[i]*2 > X) {
				canDelivery = false;
				break;
			}
			// (i-1)까지 방문하다가, 더 이상 i까지 갈 수 없는 경우
			if (sum + dp[i]*2 > X) {
				cnt++;
				sum = 0;
			}
			// i까지 왕복
			sum += dp[i]*2;
		}
		cnt++; // 마지막 지점의 경우 for문 내에서 일수를 고려하지 않기에 1을 추가로 더해준다.
		System.out.println(canDelivery ? cnt : -1);
	}

}
