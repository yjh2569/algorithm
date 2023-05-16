package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16509_장군 {
	// 상이 이동할 때 상, 하, 좌, 우로 한 칸 이동하는데, 이를 나타내는 델타
	static int[] dr1 = {-1, 0, 1, 0};
	static int[] dc1 = {0, -1, 0, 1};
	// 상이 이동할 때 대각선 방향으로 두 칸 이동하는데, 이를 나타내는 델타
	static int[] dr2 = {-1, -1, -1, 1, 1, 1, 1, -1};
	static int[] dc2 = {1, -1, -1, -1, -1, 1, 1, 1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R1 = Integer.parseInt(st.nextToken());
		int C1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int R2 = Integer.parseInt(st.nextToken());
		int C2 = Integer.parseInt(st.nextToken());
		// BFS를 이용해 왕에게 도달할 수 있는 최소 이동 횟수를 구한다.
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[10][9];
		q.offer(new int[] {R1, C1});
		visited[R1][C1] = true;
		int cnt = 1; // 왕에게 도달하기 위한 최소 이동 횟수
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int[] u = q.poll();
				// 8가지의 루트로 진행
				for (int d = 0; d < 8; d++) {
					// 상하좌우로 한 칸 이동
					int nr1 = u[0] + dr1[d/2];
					int nc1 = u[1] + dc1[d/2];
					// 이동 중 왕을 만나는 경우 목표하는 지점까지 갈 수 없다.
					if (nr1 == R2 && nc1 == C2) continue;
					// 대각선 방향으로 한 칸 이동
					int nr2 = nr1 + dr2[d];
					int nc2 = nc1 + dc2[d];
					// 이동 중 왕을 만나는 경우 목표하는 지점까지 갈 수 없다.
					if (nr2 == R2 && nc2 == C2) continue;
					// 위 대각선 방향과 같은 방향으로 한 칸 이동
					int nr = nr2 + dr2[d];
					int nc = nc2 + dc2[d];
					// 목표하는 지점에 갔을 때 왕을 만나면 끝난다.
					if (nr == R2 && nc == C2) {
						System.out.println(cnt);
						return;
					}
					if (check(nr, nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new int[] {nr, nc});
					}
				}
				qLen--;
			}
			cnt++;
		}
		// 왕을 절대 만나지 못하는 경우
		System.out.println(-1);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<10 && 0<=c && c<9;
	}

}
