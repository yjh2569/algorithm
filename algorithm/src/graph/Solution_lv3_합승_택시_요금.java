package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_lv3_합승_택시_요금 {
	
	static int N;
	static ArrayList<ArrayList<int[]>> graph;
	static int[][] dps;
	
	public static void main(String[] args) {
		int result = solution(6, 4, 6, 2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
		System.out.println(result);
	}
	
	public static int solution(int n, int s, int a, int b, int[][] fares) {
		// 전역변수 선언
		N = n;
        int answer = Integer.MAX_VALUE;
        // fares를 그래프로 활용하기 좋은 형태로 바꿔준다.
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
        	graph.add(new ArrayList<>());
        }
        // s, a, b를 출발점으로 할 때 각 지점까지 가기 위한 최소 비용을 저장하는 배열
        dps = new int[3][n+1];
        // 초기화
        for (int i = 0; i < 3; i++) {
        	Arrays.fill(dps[i], Integer.MAX_VALUE/10);
        }
        // 그래프 초기화
        for (int[] fare : fares) {
        	graph.get(fare[0]).add(new int[] {fare[1], fare[2]});
        	graph.get(fare[1]).add(new int[] {fare[0], fare[2]});
        }
        // dijkstra's algorithm을 s, a, b를 출발점으로 정해 적용한다.
        dijkstra(s, 0);
        dijkstra(a, 1);
        dijkstra(b, 2);
        // s -> k -> a와 s -> k -> b에 소요되는 비용은 s -> k, a -> k, b -> k에 소요되는 비용의 합과 같다.
        for (int i = 1; i <= n; i++) {
        	answer = Math.min(answer, dps[0][i]+dps[1][i]+dps[2][i]);
        }
        return answer;
    }
	// dijkstra's algorithm을 적용하는 함수
	private static void dijkstra(int k, int idx) {
		int[] dp = dps[idx];
		PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
		boolean[] visited = new boolean[N+1];
		pq.offer(new int[] {k, 0});
		dp[k] = 0;
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
	}

}
