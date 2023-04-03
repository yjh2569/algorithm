package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2186_문자판 {
	static int N, M;
	static class Location {
		int r, c;
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		char[][] board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		String word = br.readLine();
		int L = word.length(); // 영단어의 길이
		// BFS를 이용해 경로의 개수를 찾는다.
		Queue<Location> q = new LinkedList<>();
		// 경로의 수는 매우 많아질 수 있기 때문에, 모든 경로를 하나씩 찾는 것이 아니라 한꺼번에 찾아나간다.
		// 즉, dynamic programming을 이용해 각 지점마다 부분 문자열을 완성하는 경로의 개수를 구하고, 
		// 해당 지점에서 다음 문자를 찾아 경로의 개수를 문자가 있는 지점에 더해준다.
		// cases[r][c][l]은 (r, c) 지점까지 영단어의 (l+1)번째 문자까지 완성할 수 있는 경로의 개수를 의미한다.
		int[][][] cases = new int[N][M][L];
		// 방문 배열도 문자열 길이에 대해서도 추가해 같은 길이일 때 같은 지점인 경우 한 번만 방문하도록 한다.
		boolean[][][] visited = new boolean[N][M][L];
		// 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (word.charAt(0) == board[i][j]) {
					q.offer(new Location(i, j));
					cases[i][j][0] = 1;
					visited[i][j][0] = true;
				}
			}
		}
		// 다음 문자의 인덱스
		int curIdx = 1;
		// 영단어를 완성할 때까지 BFS를 진행한다.
		while (curIdx < L && !q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				Location l = q.poll();
				for (int d = 0; d < 4; d++) {
					// 거리가 1인 지점부터 K인 지점까지 방문할 수 있다.
					for (int k = 1; k <= K; k++) {
						int nr = l.r + dr[d]*k;
						int nc = l.c + dc[d]*k;
						// 만약 중간에 문자판을 벗어나는 경우 k가 더 커져도 문자판을 벗어나므로 그만둔다.
						if (!check(nr, nc)) break;
						// 다음 문자가 문자판에 있는 문자와 동일한지 확인한다.
						if (word.charAt(curIdx) != board[nr][nc]) continue;
						// 다음 문자가 있는 지점에 경로의 개수를 더해준다.
						cases[nr][nc][curIdx] += cases[l.r][l.c][curIdx-1];
						// 해당 지점을 같은 길이일 때 한 번만 방문한다.
						if (!visited[nr][nc][curIdx]) {
							visited[nr][nc][curIdx] = true;
							q.offer(new Location(nr, nc));
						}
					}
				}
				qLen--;
			}
			curIdx++;
		}
		int sum = 0; // 영단어를 완성할 수 있는 경로의 개수
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 영단어의 맨 끝자리 문자가 문자판의 문자와 동일한 경우 영단어가 완성된다.
				if (word.charAt(L-1) == board[i][j]) {
					sum += cases[i][j][L-1];
				}
			}
		}
		System.out.println(sum);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

}
