package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20005_보스몬스터_전리품 {
	// 플레이어의 정보를 저장하는 클래스
	static class Player {
		int r, c, id, dps;
		public Player(int r, int c, int id, int dps) {
			this.r = r;
			this.c = c;
			this.id = id;
			this.dps = dps;
		}
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		// 벽이 있는 경우는 1, 그렇지 않은 경우는 0으로 표시한다.
		int[][] map = new int[M][N];
		// 플레이어의 위치를 임시로 저장하는 map
		Map<Integer, int[]> playerLocs = new HashMap<>();
		// 보스의 위치
		int[] boss = new int[2];
		for (int i = 0; i < M; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = s.charAt(j);
				if ('a' <= c && c <= 'z') {
					playerLocs.put(c-'a', new int[] {i, j});
				} else if (c == 'B') {
					boss = new int[] {i, j};
				} else if (c == 'X') {
					map[i][j] = 1;
				}
			}
		}
		// BFS를 각 플레이어에 대해 진행한다.
		Queue<Player> q = new LinkedList<>();
		boolean[][][] visited = new boolean[M][N][26];
		// 이전에 입력받은 내용을 바탕으로 Player 인스턴스를 만들어 q에 집어넣는다.
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int id = st.nextToken().charAt(0)-'a';
			int dps = Integer.parseInt(st.nextToken());
			int[] loc = playerLocs.get(id);
			q.offer(new Player(loc[0], loc[1], id, dps));
			visited[loc[0]][loc[1]][id] = true;
		}
		// 보스몬스터의 현재 체력
		int hp = Integer.parseInt(br.readLine());
		// 1초당 입히는 대미지의 총합
		int damage = 0;
		// 공격 중인 플레이어 수
		int cnt = 0;
		while (!q.isEmpty()) {
			int qLen = q.size();
			// damage만큼 보스몬스터의 체력 감소
			hp -= damage;
			// 보스몬스터를 잡은 경우
			if (hp <= 0) {
				break;
			}
			while (qLen > 0) {
				Player p = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];
					if (check(nr, nc, M, N) && !visited[nr][nc][p.id] && map[nr][nc] == 0) {
						visited[nr][nc][p.id] = true;
						// 현재 플레이어가 보스몬스터를 만난 경우
						if (nr == boss[0] && nc == boss[1]) {
							// 공격 시작
							cnt++;
							damage += p.dps;
							continue;
						}
						q.offer(new Player(nr, nc, p.id, p.dps));
					}
				}
				qLen--;
			}
		}
		System.out.println(cnt);
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c, int M, int N) {
		return 0<=r && r<M && 0<=c && c<N;
	}

}
