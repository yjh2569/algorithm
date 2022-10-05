package dp;

import java.util.*;

public class Solution_lv3_등산코스_정하기 {

	public static void main(String[] args) {
		int[] result = solution(6, new int[][] {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[] {1, 3}, new int[] {5});
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		// 초기값 설정
        int[] answer = {0, Integer.MAX_VALUE};
        // 산봉우리 번호가 정렬되지 않은 경우도 있어서, 반드시 정상 번호를 오름차순으로 정렬해야 한다.
        Arrays.sort(summits);
        // paths를 이용해 그래프로 나타낸다.
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
        	graph.add(new ArrayList<>());
        }
        // 양방향 간선
        for (int[] path : paths) {
        	graph.get(path[0]).add(new int[] {path[1], path[2]});
        	graph.get(path[1]).add(new int[] {path[0], path[2]});
        }
        // 출입문 여부를 나타내는 배열
        boolean[] isGate = new boolean[n+1];
        // 산봉우리 여부를 나타내는 배열
        boolean[] isSummit = new boolean[n+1];
        for (int gate : gates) {
        	isGate[gate] = true;
        }
        for (int summit : summits) {
        	isSummit[summit] = true;
        }
        // 각 지점까지 갈 때 intensity의 최소값
        int[] dp = new int[n+1];
        // 해당 지점을 방문했는지를 나타내는 배열
        boolean[] visited = new boolean[n+1];
        // 산봉우리 번호 순서대로 탐색하면서 intensity의 최소값을 찾는다.
        // dijkstra's algorithm을 활용
        for (int summit : summits) {
        	// 초기화
        	PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        	Arrays.fill(dp, Integer.MAX_VALUE);
        	Arrays.fill(visited, false);
        	dp[summit] = 0;
        	pq.offer(new int[] {summit, 0});
        	// 가장 처음으로 만나는 출입문
        	// dijkstra's algorithm 특성상, 가장 intensity가 작은 출입문을 제일 처음 만나게 된다.
        	int minIdx = 0;
        	while (!pq.isEmpty()) {
        		int[] u = pq.poll();
        		if (visited[u[0]]) continue;
        		// 가장 intensity가 작은 출입문(첫 출입문)을 만난 경우
        		if (isGate[u[0]]) {
        			minIdx = u[0];
        			break;
        		}
        		visited[u[0]] = true;
        		for (int[] v : graph.get(u[0])) {
        			// 산봉우리가 아닌 지점에 대해서만 탐색
        			if (!isSummit[v[0]] && dp[v[0]] > Math.max(dp[u[0]], v[1])) {
        				dp[v[0]] = Math.max(dp[u[0]], v[1]);
        				pq.offer(new int[] {v[0], dp[v[0]]});
        			}
        		}
        	}
        	// 최소값 갱신
        	if (dp[minIdx] < answer[1]) {
        		answer[0] = summit;
        		answer[1] = dp[minIdx];
        	}        	
        }
        return answer;
    }

}
