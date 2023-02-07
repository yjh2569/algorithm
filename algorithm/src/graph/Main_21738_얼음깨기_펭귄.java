package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21738_얼음깨기_펭귄 {
	// 그래프 탐색 시 현재 노드의 번호와 초기 펭귄의 위치로부터의 최단 거리를 저장하는 클래스
	static class Node implements Comparable<Node> {
		int u, distance;
		public Node(int u, int distance) {
			this.u = u;
			this.distance = distance;
		}
		public int compareTo(Node n) {
			return Integer.compare(this.distance, n.distance);
		}
	}
	// 얼음 사이의 연결 관계를 나타내는 그래프
	static ArrayList<ArrayList<Integer>> graph;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// 각 지지대 얼음의 정보를 저장하는 배열
		Node[] bases = new Node[S];
		int idx = 0; // bases에 Node를 저장할 때 보조하는 변수
		// BFS를 통해 각 지지대 얼음까지의 최단 거리를 구한 뒤, 가장 거리가 짧은 두 지지대 얼음까지의 거리의 합을 N-1에서 뺀다.
		// 얼음을 최대한 많이 제거하려면, 가장 거리가 짧은 두 지지대 얼음 및 해당 얼음까지의 일반 얼음들을 제외하고 
		// 나머지 얼음들을 제거하면 되기 때문이다.
		// 문제 조건에서 지지대와 연결되어 있다는 것은 지지대로부터 '서로 다른' 일반 얼음들을 통해 연결 관계가 이어져 있다는 것을 의미하고,
		// 연결은 트리 형태로 이루어져 있기 때문에 펭귄이 초기에 서 있는 얼음을 제외하고는 모두 2개 이하의 지지대 얼음과 연결되어 있다.
		// 따라서 지지대 얼음을 제거하면 초기 펭귄 위치의 얼음을 제외한, 해당 지지대 얼음과 연결된 모든 얼음을 제거할 수 있다.
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.offer(new Node(P, 0));
		visited[P] = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int v : graph.get(n.u)) {
				if (!visited[v]) {
					// BFS 중 지지대 얼음을 방문하면 bases 배열에 추가한다.
					// BFS 특성상 최단 거리 얼음부터 먼저 방문하므로 bases 배열은 
					// 자동으로 가장 가까운 지지대 얼음 순으로 정렬된다.
					if (v <= S) {
						bases[idx++] = new Node(v, n.distance+1);
					}
					visited[v] = true;
					q.offer(new Node(v, n.distance+1));
				}
 			}
		}
		System.out.println(N-1-bases[0].distance-bases[1].distance);
	}

}
