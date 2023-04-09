package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21611_마법사_상어와_블리자드 {
	static int N, M;
	static int[][] map;
	// map에서 상어의 위치부터 (0, 0)까지 벽을 따라 이동할 때 바뀌는 방향을 배열로 표현
	static int[] drM = {0, 1, 0, -1};
	static int[] dcM = {-1, 0, 1, 0};
	// 상어가 블리자드 마법을 시전하는 방향을 배열로 표현
	static int[] drB = {0, -1, 1, 0, 0};
	static int[] dcB = {0, 0, 0, -1, 1};
	// answer[i]는 폭발한 i번 구슬의 개수를 의미한다.
	static int[] answer;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		answer = new int[4];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			spellBlizzard(d, s); // d 방향과 거리 s인 블리자드 마법을 시전한다. 
			Queue<Integer> marbles = collectMarbles(); // map에 남아있는 구슬들을 모아 Queue에 저장
			marbles = burstMarbles(marbles); // 조건을 만족하는 구슬들을 터뜨린다.
			marbles = changeMarbles(marbles); // 남은 구슬들을 그룹화해 구슬을 변화시킨다.
			putMarblesToMap(marbles); // 새로운 구슬들을 map에 재배치한다.
		}
		System.out.println(answer[1]+answer[2]*2+answer[3]*3);
	}
	// 방향 d, 거리 s의 블리자드 마법을 시전하는 함수
	private static void spellBlizzard(int d, int s) {
		for (int t = 1; t <= s; t++) {
			int nr = N/2 + drB[d]*t;
			int nc = N/2 + dcB[d]*t;
			if (check(nr, nc)) {
				map[nr][nc] = 0;
			}
		}
	}
	// map에 남은 구슬들을 Queue에 저장하는 함수
	private static Queue<Integer> collectMarbles() {
		Queue<Integer> marbles = new LinkedList<>(); // 남은 구슬들을 모아두는 Queue
		// 벽을 따라 이동하면서 구슬들을 찾아 marbles에 넣는다.
		// 방향은 왼쪽, 아래쪽, 오른쪽, 위쪽으로 바뀌면서, 
		// 이동하는 거리는 1, 1, 2, 2, ..., N-2, N-2, N-1, N-1, N-1이 된다.
		// 따라서 위 규칙성을 활용해 이동하면서 map[r][c]가 0이 아닌 경우에만 marbles에 map[r][c]를 넣는다.
		// (r, c)는 현재 위치, l은 이동 거리 경계값, d는 방향, curL은 현재 d 방향으로 이동한 거리, 
		// cnt는 이동하는 거리가 l인 경로를 몇 번 지났는지를 나타낸다.
		int r = N/2, c = N/2, l = 1, d = 0, curL = 0, cnt = 0;
		while (l < N) {
			r += drM[d];
			c += dcM[d];
			if (map[r][c] != 0) marbles.offer(map[r][c]); // 구슬을 발견한 경우
			// d 방향으로 l만큼 이동을 완료한 경우
			if (++curL == l) {
				d = (d+1)%4;
				curL = 0;
				// l만큼 이동한 횟수가 2번인 경우
				if (++cnt == 2) {
					l++;
					cnt = 0;
				}
			}
		}
		// 위 과정을 거치면 N-1만큼의 거리를 2번 이동하게 되는데, 실제로는 N-1만큼의 거리를 1번 더 이동해야 한다.
		for (int i = 0; i < N-1; i++) {
			r += drM[d];
			c += dcM[d];
			if (map[r][c] != 0) marbles.offer(map[r][c]);
		}
		return marbles;
	}
	// marbles 내에 있는 구슬 중 4개 이상이 연속해서 모여있는 구슬들을 터뜨리는 함수
	private static Queue<Integer> burstMarbles(Queue<Integer> marbles) {
		// 구슬들의 폭발은 연쇄 작용을 일으킬 수 있으므로, 폭발을 감지하면 다시 marbles를 조사한다.
		boolean bursted = true;
		while (bursted) {
			bursted = false;
			int size = marbles.size();
			// 현재 누적된 구슬의 번호와 개수
			int curN = 0, curCnt = 0;			
			for (int i = 0; i < size; i++) {
				int marble = marbles.poll();
				// 현재 누적된 구슬의 번호와 다른 번호의 구슬을 만난 경우
				if (marble != curN) {
					// 누적된 구슬의 개수가 4개 이상이면 해당 구슬들을 모두 터뜨린다.
					if (curCnt >= 4) {
						bursted = true;
						answer[curN] += curCnt;
					// 그렇지 않으면 그 구슬들을 다시 Queue에 집어넣는다.
					} else if (curN != 0) {
						for (int j = 0; j < curCnt; j++) {
							marbles.offer(curN);
						}
					}
					// 새로운 구슬로 누적된 구슬을 갱신한다.
					curN = marble;
					curCnt = 1;
				// 현재 누적된 구슬의 번호와 같은 번호의 구슬을 만난 경우
				} else {
					++curCnt;					
				}
			}
			// 위 작업을 수행하면, 마지막에 미처 처리하지 못한 구슬들이 curN와 curCnt가 남게 된다.
			// 따라서 curCnt에 따라 구슬들을 터뜨릴지, 아니면 marbles에 넣을지를 결정한다.
			if (curCnt >= 4) {
				bursted = true;
				answer[curN] += curCnt;
			} else {
				for (int i = 0; i < curCnt; i++) {
					marbles.offer(curN);
				}
			}
		}
		return marbles;		
	}
	// marbles에 있는 구슬들을 그룹화해 구슬들을 변화시키는 함수
	private static Queue<Integer> changeMarbles(Queue<Integer> marbles) {
		int size = marbles.size();
		// 현재 누적된 구슬의 번호와 개수
		int curN = 0, curCnt = 0;
		for (int i = 0; i < size; i++) {
			int marble = marbles.poll();
			// 현재 누적된 구슬의 번호와 다른 번호의 구슬을 만난 경우
			if (marble != curN) {
				// 누적된 구슬 그룹의 구슬 개수와 번호를 marbles에 추가한다.
				if (curN != 0) {
					marbles.offer(curCnt);
					marbles.offer(curN);
				}
				// 새로운 구슬로 누적된 구슬을 갱신한다.
				curN = marble;
				curCnt = 1;
			// 현재 누적된 구슬의 번호와 같은 번호의 구슬을 만난 경우
			} else {
				curCnt++;
			}
		}
		// burstMarbles 함수의 경우와 마찬가지로, 마지막에 미처 처리하지 못한 구슬들이 curN와 curCnt가 남게 된다.
		// 따라서 이 구슬 그룹의 정보를 marbles에 넣는다.
		marbles.offer(curCnt);
		marbles.offer(curN);
		return marbles;
	}
	// marbles에 있는 구슬들을 map에 재배치하는 함수
	private static void putMarblesToMap(Queue<Integer> marbles) {
		// map 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], 0);
		}
		// 벽을 따라 이동하면서 map에 marbles에 있는 구슬들을 차례대로 배치한다.
		// 이때 marbles에 있는 구슬을 다 쓴 경우 작업을 그만둬야 한다.
		int r = N/2, c = N/2, l = 1, d = 0, curL = 0, cnt = 0;
		while (l < N && !marbles.isEmpty()) {
			r += drM[d];
			c += dcM[d];
			map[r][c] = marbles.poll();
			if (++curL == l) {
				d = (d+1)%4;
				curL = 0;
				if (++cnt == 2) {
					l++;
					cnt = 0;
				}
			}
		}
		for (int i = 0; i < N-1; i++) {
			if (marbles.isEmpty()) return;
			r += drM[d];
			c += dcM[d];
			map[r][c] = marbles.poll();
		}		
	}
	// 경계 확인용 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
}
