package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14567_선수과목 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 위상 정렬을 적용한다.
		// 각 노드에 대해 해당 노드를 방문하기 전 사전에 방문해야 하는 노드의 개수를 저장하는 배열
		int[] counts = new int[N+1];
		// 그래프 저장
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			counts[b]++;
		}
		// BFS를 적용한다.
		// 우선 사전에 방문해야 하는 노드가 없는 노드를 먼저 방문하고, 
		// 이로 인해 방문할 수 있는 노드가 생기면 추가하는 방식으로 진행
		Queue<Integer> q = new LinkedList<>();
		int[] result = new int[N+1];
		for (int i = 1; i <= N; i++) {
			if (counts[i] == 0) {
				q.offer(i);
			}
		}
		// 진행 중인 학기
		int time = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				result[u] = time;
				for (int v : graph.get(u)) {
					counts[v]--;
					if (counts[v] == 0) {
						q.offer(v);
					}
				}
				qLen--;
			}
			time++;
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(result[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
