package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_15558_점프_게임 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		// lines[0]는 왼쪽 줄, lines[1]은 오른쪽 줄
		int[][] lines = new int[2][N];
		for (int i = 0; i < 2; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				lines[i][j] = s.charAt(j) - '0';
			}
		}
		// BFS를 활용해 게임을 클리어할 수 있는지 확인한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[2][N];
		// 초기화
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		int cnt = 0; // 현재 사라질 발판의 번호
		while (!q.isEmpty()) {
			int qLen = q.size();
			lines[0][cnt] = lines[1][cnt] = 0; // 발판이 사라진다.
			while (qLen > 0) {
				int[] u = q.poll();
				// (N-k)번째 칸보다 앞에 도착했을 때 반대편 줄로 점프하면 게임을 클리어할 수 있다.
				if (u[1] >= N-k) {
					System.out.println(1);
					return;
				}
				// 한 칸 앞으로 이동하는 경우
				if (u[1] < N-1 && !visited[u[0]][u[1]+1] && lines[u[0]][u[1]+1] == 1) {
					visited[u[0]][u[1]+1] = true;
					q.offer(new int[] {u[0], u[1]+1});
				}
				// 한 칸 뒤로 이동하는 경우
				if (u[1] > 0 && !visited[u[0]][u[1]-1] && lines[u[0]][u[1]-1] == 1) {
					visited[u[0]][u[1]-1] = true;
					q.offer(new int[] {u[0], u[1]-1});
				}
				// 반대편 줄로 이동하는 경우
				if (u[1] < N-k && !visited[(u[0]+1)%2][u[1]+k] && lines[(u[0]+1)%2][u[1]+k] == 1) {
					visited[(u[0]+1)%2][u[1]+k] = true;
					q.offer(new int[] {(u[0]+1)%2, u[1]+k});
				}
				qLen--;
			}
			cnt++;
		}
		// 게임을 클리어하지 못하는 경우
		System.out.println(0);
	}
}
