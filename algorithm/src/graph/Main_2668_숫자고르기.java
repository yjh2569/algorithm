package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main_2668_숫자고르기 {
	static int[] graph;
	static boolean[] visited;
	static ArrayList<Integer> nums;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		graph = new int[N+1];
		for (int i = 1; i <= N; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		// DFS 도중 해당 수를 방문했는지를 나타내는 배열 
		visited = new boolean[N+1];
		// 뽑힌 정수들의 집합
		nums = new ArrayList<>();
		// 각 수에 대해 DFS를 통해 그 수를 포함하는 cycle을 만들 수 있는지 찾는다.
		// 만약 cycle을 만들 수 있으면 nums에 cycle에 있는 수들을 넣는다.
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			visited[i] = true;
			// cycle 내 수들을 저장하기 위한 set
			Set<Integer> set = new HashSet<>();
			set.add(i);
			dfs(graph[i], i, set);
		}
		// 뽑은 수들을 정렬
		Collections.sort(nums);
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(nums.size()).append("\n");
		for (int num : nums) {
			sb.append(num).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// DFS를 통해 cycle이 있는지 탐색한다.
	// i는 현재 정수, start는 탐색을 시작한 정수, set은 지금까지 DFS를 돌면서 발견한 수들의 집합 
	private static void dfs(int i, int start, Set<Integer> set) {
		// start와 i가 같다는 것은 처음 시작한 정수로 돌아왔다는 의미이다.
		if (start == i) {
			for (int num : set) {
				if (!nums.contains(num)) nums.add(num);
			}
			return;
		}
		// 이미 방문한 정점이면 cycle을 발견하지 못한 경우이므로 빠져나온다.
		if (visited[i]) return;
		visited[i] = true;
		set.add(i);
		// 다음 정점을 재귀적으로 방문해 나간다.
		dfs(graph[i], start, set);
	}
}
