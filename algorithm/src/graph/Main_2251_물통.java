package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2251_물통 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 문제 풀이를 효율적으로 하기 위해 배열과 변수에 입력을 저장
		int[] buckets = new int[3];
		int A = buckets[0] = Integer.parseInt(st.nextToken());
		int B = buckets[1] = Integer.parseInt(st.nextToken());
		int C = buckets[2] = Integer.parseInt(st.nextToken());
		// C 물통에 있을 수 있는 물의 양을 저장
		ArrayList<Integer> res = new ArrayList<>();
		// 초기값
		res.add(C);
		// res에 같은 값이 중복해서 들어가는 것을 방지하기 위한 방문 처리 배열
		boolean[] v_c = new boolean[C+1];
		v_c[C] = true;
		// BFS를 이용해 세 물통에 물이 담긴 경우를 모두 조사한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] visited = new boolean[A+1][B+1][C+1];
		q.offer(new int[] {0, 0, C});
		visited[0][0][C] = true;
		while (!q.isEmpty()) {
			int[] u = q.poll();
			// 물을 줄 물통과 물을 받을 물통을 선택해 물을 건네준다.
			for (int i = 0; i < 3; i++) {
				if (u[i] == 0) continue;
				for (int j = 0; j < 3; j++) {
					if (i == j || u[j] == buckets[j]) continue;
					int[] v = Arrays.copyOf(u, 3);
					// 물을 받을 물통을 꽉 채울만큼 물을 줄 물통에 물이 많은 경우
					if (v[i] >= buckets[j] - v[j]) {
						v[i] -= buckets[j] - u[j];
						v[j] = buckets[j];
					// 물을 줄 물통에 물이 충분하지 않은 경우
					} else {
						v[j] += v[i];
						v[i] = 0;
					}
					if (!visited[v[0]][v[1]][v[2]]) {
						visited[v[0]][v[1]][v[2]] = true;
						q.offer(v);
						// A 물통이 빈 경우에만 res에 가능한 물의 양으로 추가함에 유의
						if (v[0] == 0 && !v_c[v[2]]) {
							v_c[v[2]] = true;
							res.add(v[2]);
						}
					}
				}
			}
		}
		// 물의 양을 정렬
		Collections.sort(res);
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int c : res) {
			sb.append(c).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}

}
