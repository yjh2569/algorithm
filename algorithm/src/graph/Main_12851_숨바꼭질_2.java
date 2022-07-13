package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12851_숨바꼭질_2 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// K가 100,000 이하이므로 굳이 200,000을 넘어가는 지점까지 갈 필요없다.
		int CONST = 200_000;
		// 바로 찾은 경우
		if (N == K) {
			System.out.println(0+"\n"+1);
			System.exit(0);
		}
		// BFS를 통해 동생이 있는 지점까지 걸리는 최단 시간을 찾는다.
		Queue<Integer> q = new LinkedList<>();
		// 경우의 수를 구하기 위해 각 지점마다 최단 시간으로 이동하는 경우의 수를 기록하는 배열을 만든다.
		int[] visited = new int[CONST+1];
		boolean[] v = new boolean[CONST+1];
		// 최단 시간으로 이동하는 경우의 수만 고려하기 위해 보조하는 배열
		int[] dists = new int[CONST+1];
		// 초기화
		Arrays.fill(dists, -1);
		q.offer(N);
		visited[N] = 1;
		dists[N] = 0;
		v[N] = true;
		// BFS 수행 중 지금까지 걸린 시간을 나타내는 변수
		int dist = 1;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				// 이동할 때 이동 후 지점을 처음 방문한 건지, 혹은 최단 시간에 도착했는지를 확인하고,
				// 만약 처음 방문하는 거라면 큐에 넣어 다음 지점을 탐색한다.
				// X-1 지점으로 이동
				if (u > 0 && (dists[u-1] == -1 || dists[u-1] == dist)) {
					dists[u-1] = dist;
					visited[u-1] += visited[u];
					if (!v[u-1]) {
						v[u-1] = true;
						q.offer(u-1);
					}
				}
				// X+1 지점으로 이동
				if (u < CONST && (dists[u+1] == -1 || dists[u+1] == dist)) {
					dists[u+1] = dist;
					visited[u+1] += visited[u];
					if (!v[u+1]) {
						v[u+1] = true;
						q.offer(u+1);
					}
				}
				// 2*X 지점으로 이동
				if (2*u <= CONST && (dists[2*u] == -1 || dists[2*u] == dist)) {
					dists[2*u] = dist;
					visited[2*u] += visited[u];
					if (!v[2*u]) {
						v[2*u] = true;
						q.offer(2*u);
					}
				}
				qLen--;
			}
			// 만약 최단 시간으로 동생에게 도달하는 경우가 발생했다면 지금까지 걸린 시간과 경우의 수를 출력
			if (visited[K] > 0) {
				System.out.println(dist+"\n"+visited[K]);
				System.exit(0);
			}
			dist++;
		}
		
	}

}
