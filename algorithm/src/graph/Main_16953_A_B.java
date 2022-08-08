package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16953_A_B {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		// BFS를 통해 A에서 B까지의 최단 거리를 구한다.
		// A와 B가 10^9까지 가능하므로 방문 배열을 단순 배열로 만들기보다는 Map을 활용하면 메모리를 절약할 수 있다. 
		Map<Long, Boolean> visited = new HashMap<>();
		visited.put(A, true);
		Queue<Long> q = new LinkedList<>();
		q.offer(A);
		// 필요한 연산의 최소 횟수(BFS에서 최단 거리)
		int cnt = 2;
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				long u = q.poll();
				// 다음 연산을 통해 B에 도달 가능한 경우
				if (2*u == B || 10*u+1 == B) {
					System.out.println(cnt);
					System.exit(0);
				}
				// 2를 곱하거나 오른쪽에 1을 붙이는 연산을 진행
				// 오른쪽에 1을 붙이는 것은 10을 곱한 후 1을 더하는 것과 같다.
				if ((visited.get(2*u) == null || !visited.get(2*u)) && 2*u < B) {
					visited.put(2*u, true);
					q.offer(2*u);
				}
				if ((visited.get(10*u+1) == null || !visited.get(10*u+1)) && 10*u+1 < B) {
					visited.put(10*u+1, true);
					q.offer(10*u+1);
				}
				qLen--;
			}
			cnt++;
		}
		System.out.println(-1);
	}

}
