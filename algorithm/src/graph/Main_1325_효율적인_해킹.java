package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1325_효율적인_해킹 {
	static int N, M;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] nums;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		// 그래프에 간선 저장 시 "A가 B를 신뢰한다"는 관계는 B -> A 간선으로 저장해야 한다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
		}
		// 각 컴퓨터를 해킹했을 때 한 번에 해킹할 수 있는 컴퓨터의 수를 저장하는 배열
		nums = new int[N+1];
		// 각 컴퓨터에 대해 BFS를 수행하여 한 번에 해킹할 수 있는 컴퓨터의 수를 센다.
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
		// 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 저장하는 ArrayList
		ArrayList<Integer> max_idxs = new ArrayList<>();
		// 하나의 컴퓨터로 해킹할 수 있는 컴퓨터 수의 최댓값 
		int max_val = 0;
		for (int i = 1; i <= N; i++) {
			if (max_val < nums[i]) {
				max_val = nums[i];
			}
		}
		// 위에서 구한 최댓값을 바탕으로 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 저장
		// 컴퓨터 번호를 1번부터 N번까지 순차적으로 조사하므로 굳이 오름차순으로 정렬할 필요는 없다.
		for (int i = 1; i <= N; i++) {
			if (max_val == nums[i]) {
				max_idxs.add(i);
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int idx : max_idxs) {
			sb.append(idx).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}
	// BFS를 통해 각 컴퓨터로 인해 해킹될 수 있는 컴퓨터 수를 구한다.
	private static void bfs(int i) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		visited[i] = true;
		q.offer(i);
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : graph.get(u)) {
				if (!visited[v]) {
					visited[v] = true;
					nums[i]++;
					q.offer(v);
				}
			}
		}		
	}
	

}
