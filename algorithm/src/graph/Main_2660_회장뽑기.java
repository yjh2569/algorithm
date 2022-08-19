package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2660_회장뽑기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 친구 관계를 나타낼 그래프
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 입력의 끝
			if (a == -1 && b == -1) break;
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		// 회장 후보의 점수
		int minScore = Integer.MAX_VALUE;
		// 각 회원의 점수를 배열로 저장
		int[] scores = new int[N+1];
		// 각 회원의 점수를 BFS를 통해 구한다.
		for (int i = 1; i <= N; i++) {
			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[N+1];
			q.offer(i);
			visited[i] = true;
			// 가장 멀리 떨어져 있는 회원과의 최단 거리
			int dist = 1;
			// 현재까지 친구 관계로 고려한 회원의 수
			int cnt = 1;
			while (!q.isEmpty()) {
				int qLen = q.size();
				while (qLen > 0) {
					int u = q.poll();
					for (int v : graph.get(u)) {
						if (!visited[v]) {
							visited[v] = true;
							cnt++;
							q.offer(v);
						}
					}
					qLen--;
				}
				// 모든 회원을 친구 관계로 고려한 경우
				if (cnt == N) {
					break;
				}
				dist++;
			}
			// 가장 멀리 떨어져 있는 회원과의 최단 거리를 점수로 기록
			scores[i] = dist;
			// 점수의 최소값 구하기
			minScore = Math.min(minScore, scores[i]);
		}
		// 회장 후보의 수
		int count = 0;
		// 회장 후보 번호
		ArrayList<Integer> candidates = new ArrayList<>();
		// 각 회원의 점수가 최소 점수와 같을 경우 회장 후보에 추가
		for (int i = 1; i <= N; i++) {
			if (minScore == scores[i]) {
				count++;
				candidates.add(i);
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(minScore).append(" ").append(count).append("\n");
		for (int candidate : candidates) {
			sb.append(candidate).append(" ");
		}
		if (count > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
