package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_2232_지뢰 {
	// 지뢰의 인덱스와 힘을 나타내는 클래스
	static class Mine implements Comparable<Mine> {
		int idx, power;
		public Mine(int idx, int power) {
			this.idx = idx;
			this.power = power;
		}
		public int compareTo(Mine m) {
			return Integer.compare(m.power, this.power);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] mines = new int[N];
		// 지뢰를 힘이 큰 순서대로 정렬하는 우선순위 큐
		PriorityQueue<Mine> sortedMines = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			mines[i] = Integer.parseInt(br.readLine());
			sortedMines.offer(new Mine(i, mines[i]));
		}
		// 지뢰가 현재 터졌는지를 나타내는 배열
		boolean[] minesBoomed = new boolean[N];
		// 터뜨린 지뢰를 인덱스가 작은 순서대로 정렬하는 우선순위 큐
		PriorityQueue<Mine> answerMines = new PriorityQueue<>((x, y) -> Integer.compare(x.idx, y.idx));
		// greedy algorithm을 사용한다.
		// 힘이 큰 지뢰 순서대로 터뜨린다.
		// 남아있는 지뢰 중 힘이 가장 큰 지뢰는 다른 지뢰를 터뜨린다고 해서 연쇄적으로 터질 수 없기 때문이다.
		while (!sortedMines.isEmpty()) {
			Mine m = sortedMines.poll();
			if (minesBoomed[m.idx]) continue; // 이미 터진 지뢰인 경우
			// 현재 지뢰를 터뜨린다.
			minesBoomed[m.idx] = true;
			answerMines.add(m);
			// 현재 지뢰 기준 왼쪽 지뢰들을 연쇄적으로 터뜨린다.
			int prev = m.idx;
			int cur = m.idx-1;
			while (cur >= 0 && !minesBoomed[cur] && mines[prev] > mines[cur]) {
				minesBoomed[cur] = true;
				prev--; cur--;
			}
			// 현재 지뢰 기준 오른쪽 지뢰들을 연쇄적으로 터뜨린다.
			prev = m.idx;
			cur = m.idx+1;
			while (cur < N && !minesBoomed[cur] && mines[prev] > mines[cur]) {
				minesBoomed[cur] = true;
				prev++; cur++;
			}
		}
		// 터뜨릴 지뢰들을 인덱스가 작은 순서대로 출력
		StringBuilder sb = new StringBuilder();
		while (!answerMines.isEmpty()) {
			sb.append(answerMines.poll().idx+1).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
