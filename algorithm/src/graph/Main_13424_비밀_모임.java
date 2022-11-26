package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13424_비밀_모임 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				graph.get(a).add(new int[] {b, c});
				graph.get(b).add(new int[] {a, c});
			}
			int K = Integer.parseInt(br.readLine());
			ArrayList<Integer> friends = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < K; i++) {
				friends.add(Integer.parseInt(st.nextToken()));
			}
			// dijkstra's algorithm을 친구가 있는 방들에 대해 진행
			int[][] dijk = new int[N+1][N+1];
			for (int i = 0; i <= N; i++) {				
				Arrays.fill(dijk[i], Integer.MAX_VALUE/10);
				dijk[i][i] = 0;
			}
			for (int friend : friends) {
				PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
				pq.offer(new int[] {friend, 0});
				boolean[] visited = new boolean[N+1];
				while (!pq.isEmpty()) {
					int[] u = pq.poll();
					if (visited[u[0]]) continue;
					visited[u[0]] = true;
					for (int[] v : graph.get(u[0])) {
						if (!visited[v[0]] && dijk[friend][v[0]] > dijk[friend][u[0]] + v[1]) {
							dijk[friend][v[0]] = dijk[friend][u[0]] + v[1];
							pq.offer(new int[] {v[0], dijk[friend][v[0]]});
						}
					}
				}
			}
			// 각 방에 대해 해당 방을 모임 장소로 했을 때 친구들의 이동 거리 중 최솟값
			int min = Integer.MAX_VALUE;
			// 이동 거리가 최소가 되는 방 번호
			int minIdx = 0;
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int friend : friends) {
					sum += dijk[friend][i];
				}
				if (min > sum) {
					min = sum;
					minIdx = i;
				}
			}
			sb.append(minIdx).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
