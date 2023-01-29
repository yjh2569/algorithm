package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2617_구슬_찾기 {
	
	static int N;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 각 구슬에 대해 자신보다 무거운 구슬들을 저장해 그래프 형태로 표현한 ArrayList
		ArrayList<ArrayList<Integer>> heaviers = new ArrayList<>();
		// 각 구슬에 대해 자신보다 가벼운 구슬들을 저장해 그래프 형태로 표현한 ArrayList
		ArrayList<ArrayList<Integer>> lighters = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			heaviers.add(new ArrayList<>());
			lighters.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			heaviers.get(l).add(h);
			lighters.get(h).add(l);
		}
		// 무게가 절대로 중간이 될 수 없는 구슬의 개수
		int cnt = 0;
		// 각 구슬에 대해 두 그래프를 이용해 BFS를 수행하면
		// 그 결과 방문한 노드 개수가 곧 자신보다 확실히 무거운 구슬의 개수와 가벼운 구슬의 개수와 같다.
		// 따라서 두 개수 중 어느 하나라도 (N+1)/2 이상이라면 무게가 절대로 중간이 될 수 없다.
		for (int i = 1; i <= N; i++) {
			int numOfHeaviers = bfs(i, heaviers);
			int numOfLighters = bfs(i, lighters);
			if (numOfHeaviers >= (N+1)/2 || numOfLighters >= (N+1)/2) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	// BFS를 통해 자신보다 확실히 무거운 혹은 가벼운 구슬의 개수를 찾는 함수
	private static int bfs(int i, ArrayList<ArrayList<Integer>> graph) {
		int cnt = 0;
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(i);
		visited[i] = true;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					cnt++;
					q.offer(v);
				}
			}
		}
		return cnt;
	}
	
	

}
