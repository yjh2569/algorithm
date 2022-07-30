package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12886_돌_그룹 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		// 돌 개수의 총합
		int total = A+B+C;
		// 돌 개수의 총합이 3의 배수가 아니면 돌을 같은 개수로 만들 수 없다.
		if (total%3 != 0) {
			System.out.println(0);
			System.exit(0);
		}
		// 돌 개수가 이미 모두 같은 경우
		if (A == B && B == C) {
			System.out.println(1);
			System.exit(0);			
		}
		// BFS를 통해 돌을 움직여서 만들 수 있는 모든 경우를 고려한다.
		Queue<int[]> q = new LinkedList<>();
		// 돌 개수의 총합은 항상 일정하므로 앞의 두 돌 그룹에 대해서만 방문 여부를 확인한다.
		boolean[][] visited = new boolean[total+1][total+1];
		// 초기화
		q.offer(new int[] {A, B, C});
		visited[A][B] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			// 두 돌 그룹을 선택해 돌을 이동시키는 작업을 수행한다.
			for (int i = 0; i < 3; i++) {
				for (int j = i+1; j < 3; j++) {
					if (u[i] == u[j]) continue;
					int[] v = Arrays.copyOf(u, 3);
					if (v[i] > v[j]) {
						v[i] -= v[j];
						v[j] *= 2;
					} else {
						v[j] -= v[i];
						v[i] *= 2;
					}
					// 세 돌 그룹의 돌 개수가 모두 같아지는 경우
					if (v[0] == v[1] && v[1] == v[2]) {
						System.out.println(1);
						System.exit(0);
					}
					if (!visited[v[0]][v[1]]) {
						visited[v[0]][v[1]] = true;
						q.offer(v);
					}
				}
			}
		}
		// BFS를 수행했음에도 세 돌 그룹의 돌 개수가 같아지는 경우가 없다.
		System.out.println(0);
	}

}
