package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2344_거울 {
	// 빛의 위치와 방향, 출발한 위치를 나타내는 클래스
	static class Light {
		int r, c, d, idx;
		public Light(int r, int c, int d, int idx) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.idx = idx;
		}
		// 빛이 반사될 때 방향을 바꾸는 메소드
		public void reflect() {
			if (this.d%2 == 0) this.d += 1;
			else this.d -= 1;
		}
	}
	static int N, M;
	static int[][] box;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		box = new int[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 각 빛의 경로를 추적해 도착하는 구멍의 번호를 찾는다.
		int[] answer = findLightRoute();
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 2*N+2*M; i++) {
			sb.append(answer[i]).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 각 구멍에서 출발하는 빛의 경로를 추적해 도착하는 구멍의 번호를 찾는 함수
	private static int[] findLightRoute() {
		int[] answer = new int[2*N+2*M];
		// 출발하는 빛의 정보를 모아두는 큐
		Queue<Light> q = new LinkedList<>();
		// 구멍의 정보를 바탕으로 출발하는 빛을 큐에 저장
		putLightsToQueue(q);
		while (!q.isEmpty()) {
			Light l = q.poll();
			l.r += dr[l.d];
			l.c += dc[l.d];
			// 빛이 또 다른 구멍에 도달할 때까지 진행
			while (box[l.r][l.c] >= 0) {
				// 거울을 만나 반사가 이루어지는 경우
				if (box[l.r][l.c] == 1) {
					l.reflect();
				}
				l.r += dr[l.d];
				l.c += dc[l.d];
			}
			// 빛이 구멍에 도달한 경우
			answer[l.idx-1] = -box[l.r][l.c];		
		}
		return answer;
	}
	// 출발하는 빛의 정보를 큐에 저장
	private static void putLightsToQueue(Queue<Light> q) {
		// 구멍의 번호를 음수로 기록
		int idx = -1;
		for (int i = 1; i <= N; i++) {
			q.offer(new Light(i, 0, 1, -idx));
			box[i][0] = idx--;
		}
		for (int j = 1; j <= M; j++) {
			q.offer(new Light(N+1, j, 0, -idx));
			box[N+1][j] = idx--;
		}
		for (int i = N; i >= 1; i--) {
			q.offer(new Light(i, M+1, 3, -idx));
			box[i][M+1] = idx--;
		}
		for (int j = M; j >= 1; j--) {
			q.offer(new Light(0, j, 2, -idx));
			box[0][j] = idx--;
		} 
	}

}
