package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_14426_이모티콘 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		// 너비 우선 탐색을 활용한다.
		// 현재 이모티콘의 개수와, 클립보드에 복사한 이모티콘의 개수를 가지고 너비 우선 탐색을 진행한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[2 * S][2 * S];
		visited[1][0] = true;
		q.offer(new int[] { 1, 0 });
		int cnt = 0; // 지금까지 걸린 시간
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				// S개의 이모티콘을 만든 경우
				if (u[0] == S) {
					System.out.println(cnt);
					return;
				}
				// 이모티콘을 하나 삭제하는 연산
				if (u[0] > 1 && !visited[u[0] - 1][u[1]]) {
					visited[u[0] - 1][u[1]] = true;
					q.offer(new int[] { u[0] - 1, u[1] });
				}
				// 전체 이모티콘을 클립보드에 복사하는 연산
				if (!visited[u[0]][u[0]]) {
					visited[u[0]][u[0]] = true;
					q.offer(new int[] { u[0], u[0] });
				}
				// 클립보드에 복사한 이모티콘을 붙여넣는 연산
				if (u[0] + u[1] < 2 * S && !visited[u[0] + u[1]][u[1]]) {
					visited[u[0] + u[1]][u[1]] = true;
					q.offer(new int[] { u[0] + u[1], u[1] });
				}
				qLen--;
			}
			cnt++;
		}
	}

}
