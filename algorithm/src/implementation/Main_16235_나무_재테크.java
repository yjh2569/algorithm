package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16235_나무_재테크 {
	static int N, M, K;
	// 나무의 정보를 저장하는 클래스
	static class Tree implements Comparable<Tree>{
		int r, c, age;
		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
		public int compareTo(Tree t) {
			return Integer.compare(this.age, t.age);
		}
	}
	static int[][] map;
	static int[][] supplier;
	static PriorityQueue<Tree>[][] trees;
	static Queue<Tree> updatedTrees;
	static Queue<Tree> deads;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 각 칸에 남아있는 양분의 양
		map = new int[N][N];
		// 겨울에 각 칸에 공급하는 양분의 양
		supplier = new int[N][N];
		// 각 칸에 있는 나무들을 우선순위 큐에 담아 저장
		trees = new PriorityQueue[N][N];
		// 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				supplier[i][j] = Integer.parseInt(st.nextToken());	
				trees[i][j] = new PriorityQueue<>();
			}
		}
		// 나무 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			trees[r][c].offer(new Tree(r, c, age));
		}
		// 우선순위 큐로 나무들을 저장하면 
		// 우선순위 큐에서 나무를 꺼낼 때 넣는 순서에 상관없이 우선순위에 따라 나무를 꺼내기 때문에
		// 이를 보조하기 위한 Queue를 하나 만들어둔다.
		updatedTrees = new LinkedList<>();
		// 죽은 나무를 저장하는 Queue
		deads = new LinkedList<>();
		// K년 동안 시뮬레이션
		for (int k = 0; k < K; k++) {
			spring();
			summer();
			fall();
			winter();
		}
		// 남아있는 나무의 총 개수
		int res = 0;
		// 남아있는 나무의 개수를 센다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				res += trees[i][j].size();
			}
		}
		System.out.println(res);
	}
	// 봄에는 각 칸의 나무들이 어린 나이 순서대로 양분을 흡수하고 나이가 증가하는 과정을 거친다.
	// 만약 양분을 흡수하지 못한다면 죽은 나무가 된다.
	private static void spring() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				while (!trees[r][c].isEmpty()) {
					Tree t = trees[r][c].poll();
					// 양분 흡수가 가능한 경우
					if (map[r][c] >= t.age) {
						map[r][c] -= t.age;
						updatedTrees.offer(new Tree(t.r, t.c, ++t.age));
					// 양분 흡수가 불가능한 경우
					} else {
						deads.offer(t);
					}
				}
				while (!updatedTrees.isEmpty()) {
					trees[r][c].offer(updatedTrees.poll());
				}
			}
		}
	}
	// 여름에는 죽은 나무가 양분으로 변한다.
	private static void summer() {
		while (!deads.isEmpty()) {
			Tree t = deads.poll();
			map[t.r][t.c] += t.age/2;
		}
	}
	// 가을에는 나이가 5의 배수인 나무들이 번식을 한다.
	private static void fall() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				while (!trees[r][c].isEmpty()) {
					Tree t = trees[r][c].poll();
					if (t.age%5 == 0) {
						breeding(t);
					}
					updatedTrees.offer(t);
				}
			}
		}
		while (!updatedTrees.isEmpty()) {
			Tree t = updatedTrees.poll();
			trees[t.r][t.c].offer(t);
		}	
	}
	// 번식 과정
	private static void breeding(Tree t) {
		for (int d = 0; d < 8; d++) {
			int nr = t.r + dr[d];
			int nc = t.c + dc[d];
			if (0<=nr && nr<N && 0<=nc && nc<N) {
				updatedTrees.offer(new Tree(nr, nc, 1));
			}
		}
	}
	// 겨울에는 S2D2가 땅에 양분을 추가한다.
	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += supplier[i][j];
			}
		}
		
	}

}
