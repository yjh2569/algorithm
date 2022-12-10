package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution_lv4_동굴_탐험 {

	public static void main(String[] args) {
		boolean result = solution(9, new int[][] {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}}, new int[][] {{8,5},{6,7},{4,1}});
		System.out.println(result);
	}
	
	public static boolean solution(int n, int[][] path, int[][] order) {
		// path를 통해 주어진 통로를 단방향 그래프로 바꾼다.
        ArrayList<ArrayList<Integer>> graph = changePathToGraph(n, path, order);
        // 모든 방을 탐험하기 위해서는 다음과 같은 조건을 만족해야 한다.
        // path로 주어진 통로를 양방향 그래프가 아닌 부모 노드에서 자식 노드 방향으로 향하는 간선만 존재하는 단방향 그래프로 바꾼다.
        // order의 각 정보 [a, b]에 대해 a -> b 간선을 추가한다.
        // 이때 이렇게 만든 그래프에 사이클이 없어야 한다.
        // 탐험이 불가능한 경우는 order에 [a, b], [c, d] 조건이 있을 때 a를 방문하기 위해서는 d를 방문해야 하고,
        // c를 방문하기 위해 b를 방문해야 하는 상황인데, 이를 위에서 만든 단방향 그래프의 간선으로 추가했을 때
        // 사이클이 존재한다.
        return !checkCycleExists(n, graph);
    }
	// path와 order에 주어진 정보를 바탕으로 문제를 풀기 위한 단방향 그래프를 만드는 함수
	private static ArrayList<ArrayList<Integer>> changePathToGraph(int n, int[][] path, int[][] order) {
		// path를 바탕으로 양방향 그래프를 만든다.
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] p : path) {
			graph.get(p[0]).add(p[1]);
			graph.get(p[1]).add(p[0]);
		}
		// 결과로 반환할 단방향 그래프
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			result.add(new ArrayList<>());
		}
		// BFS를 통해 각 간선에서 부모 자식 관계를 알아내 단방향 그래프에 추가한다.
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		q.offer(0);
		visited[0] = true;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					result.get(u).add(v);
					q.offer(v);
				}
			}
		}
		// order에서 주어진 정보를 result에 간선으로 추가
		for (int[] o : order) {
			result.get(o[0]).add(o[1]);
		}
		return result;
	}
	// graph 내에 사이클이 존재하는지 확인하는 함수
	private static boolean checkCycleExists(int n, ArrayList<ArrayList<Integer>> graph) {
		// 각 노드를 방문했는지, 방문했다면 현재 어떤 상태인지를 저장하는 방문 배열
		// 값이 1인 경우는 해당 노드를 방문했으나 그 노드의 자식 노드들을 모두 탐색하지 않은 경우
		// 값이 2인 경우 해당 노드를 루트 노드로 하는 서브트리를 모두 방문한 경우
		int[] visited = new int[n];
		// 재귀함수 적용 시 stack overflow 에러가 발생한다.
		// 따라서 stack을 사용해 재귀함수를 적용하는 것처럼 진행한다.
		// 실제 재귀함수는 아래 dfs 함수 참고
		Stack<Integer> dfs = new Stack<>();
		dfs.push(0);
		while (!dfs.isEmpty()) {
			// 현재 보고 있는 노드
			int u = dfs.peek();
			// 이미 방문했던 노드라면 이전의 노드를 확인
			if (visited[u] > 0) {
				visited[u] = 2;
				dfs.pop();
				continue;
			}
			// 처음 방문한 노드라면 우선 방문 값을 1로 바꾸고 자식 노드를 탐색한다.
			visited[u] = 1;
			for (int v : graph.get(u)) {
				// 이미 자식 노드까지 다 탐색한 노드인 경우
				if (visited[v] == 2) continue;
				// 사이클이 발생한 경우
				if (visited[v] == 1) return true;
				// 자식 노드로 재귀
				dfs.push(v);
			}
		}
		// 사이클이 없는 경우
		return false;
	}
	// 재귀 함수를 활용해 사이클이 있는지 조사
//	private static boolean dfs(int u, int n, ArrayList<ArrayList<Integer>> graph, int[] visited) {
//		visited[u] = 1;
//		boolean res = false;
//		for (int v : graph.get(u)) {
//			if (visited[v] == 2) continue;
//			if (visited[v] == 1) return true;
//			res = res || dfs(v, n, graph, visited);
//		}
//		visited[u] = 2;
//		return res;
//	}
}
