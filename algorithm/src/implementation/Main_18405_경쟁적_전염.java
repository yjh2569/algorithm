package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18405_경쟁적_전염 {
	static int N, K, S, X, Y;
	// 바이러스를 표현하는 클래스
	static class Virus implements Comparable<Virus> {
		// 바이러스의 위치의 행과 열 번호, 바이러스 번호
		int r, c, k;
		public Virus(int r, int c, int k) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
		// 바이러스 번호가 낮은 것부터 고려하기 위해 Comparable 인터페이스 상속
		public int compareTo(Virus v) {
			return Integer.compare(this.k, v.k);
		}
	}
	static int[][] map;
	static PriorityQueue<Virus> pq;
	static Queue<Virus> q;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		// 매 초마다 번호가 낮은 바이러스부터 증식하도록 하기 위해 우선순위 큐를 사용
		pq = new PriorityQueue<>();
		// 새롭게 증식된 바이러스를 저장하는 큐
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 바이러스가 있는 경우 pq에 바이러스 정보 저장
				if (map[i][j] > 0) pq.offer(new Virus(i, j, map[i][j]));
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		// map에 저장하는 바이러스의 위치의 행과 열 번호는 실제 바이러스 위치의 행과 열 번호보다 1만큼 작다.
		X = Integer.parseInt(st.nextToken())-1;
		Y = Integer.parseInt(st.nextToken())-1;
		// S초 동안 시뮬레이션 진행
		for (int s = 0; s < S; s++) {
			// 증식 가능한 바이러스들이 증식한다.
			while (!pq.isEmpty()) {
				Virus v = pq.poll();
				for (int d = 0; d < 4; d++) {
					int nr = v.r + dr[d];
					int nc = v.c + dc[d];
					if (check(nr, nc) && map[nr][nc] == 0) {
						map[nr][nc] = v.k;
						q.offer(new Virus(nr, nc, v.k));
					}
				}
			}
			// q에 저장해둔 바이러스를 번호 순서대로 정렬한다.
			while (!q.isEmpty()) {
				pq.offer(q.poll());
			}
		}
		// 출력
		System.out.println(map[X][Y]);
	}
	// (r, c)가 경계를 넘어가는지 확인하는 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
