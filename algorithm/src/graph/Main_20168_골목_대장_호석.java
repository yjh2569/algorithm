package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20168_골목_대장_호석 {
	// 정점 번호와 현재 정점까지 낸 요금의 최댓값, 현재 정점까지 낸 요금의 합을 저장하는 클래스
	static class Vertex implements Comparable<Vertex> {
		int v, w, sum;
		public Vertex(int v, int w, int sum) {
			this.v = v;
			this.w = w;
			this.sum = sum;
		}
		public int compareTo(Vertex v) {
			return Integer.compare(this.w, v.w);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Vertex(v, w, 0));
			graph.get(v).add(new Vertex(u, w, 0));
		}
		// dijkstra's algorithm을 이용하되, 요금의 합이 C 이하인 모든 경우를 살펴봐야 한다.
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1]; // 방문 배열
		// 초기화
		pq.offer(new Vertex(A, 0, 0));
		int min = Integer.MAX_VALUE; // A에서 B까지의 요금의 최댓값의 최솟값
		while (!pq.isEmpty()) {
			Vertex u = pq.poll();
			if (visited[u.v]) continue;
			visited[u.v] = true;
			for (Vertex v : graph.get(u.v)) {
				if (visited[v.v]) continue;
				int nextW = Math.max(u.w, v.w); // 정점 v까지의 요금의 최댓값
				int nextSum = u.sum + v.w; // 정점 v까지의 요금의 합
				// 요금의 합은 C 이하여야 한다.
				if (nextSum > C) continue;
				// B에 도착한 경우
				if (v.v == B) min = Math.min(min, nextW);
				pq.offer(new Vertex(v.v, nextW, nextSum));
			}
		}
		// B에 도착했다면 min이 Integer.MAX_VALUE가 아니고, B에 도착하지 못했다면 min이 Integer.MAX_VALUE다.
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

}
