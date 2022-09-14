package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17140_이차원_배열과_연산 {
	// 각 수와 해당 수가 나온 횟수를 저장하는 클래스
	static class Number implements Comparable<Number> {
		int n, count;
		public Number(int n, int count) {
			this.n = n;
			this.count = count;
		}
		public int compareTo(Number num) {
			return this.count == num.count ? Integer.compare(this.n, num.n) : Integer.compare(this.count, num.count);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		int k = Integer.parseInt(st.nextToken());
		// 현재 배열의 상태
		int[][] cur = new int[3][3];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cur[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 100번 연산
		for (int i = 0; i <= 100; i++) {
			// 만약 (r, c)가 k가 되면 i를 출력하고 끝낸다.
			if (r < cur.length && c < cur[0].length && cur[r][c] == k) {
				System.out.println(i);
				System.exit(0);
			}
			// 행의 개수 >= 열의 개수인 경우 R 연산 수행
			if (cur.length >= cur[0].length) cur = simulateRow(cur);
			// 그렇지 않으면 C 연산 수행
			else cur = simulateColumn(cur);
		}
		// 100번 연산에도 (r, c)가 k가 되지 않은 경우
		System.out.println(-1);
	}
	// 각 행에 대해 정렬을 수행한다.
	private static int[][] simulateRow(int[][] cur) {
		int max = 0; // 열의 길이의 최대값
		// max를 알기 전까지는 배열의 열의 크기를 정하지 못하므로, ArrayList로 정렬 결과를 저장한다.
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		// 초기화
		for (int i = 0; i < Math.min(cur.length, 100); i++) {
			res.add(new ArrayList<>());
		}
		for (int i = 0; i < Math.min(cur.length, 100); i++) {
			// 각 행의 숫자가 나타나는 횟수를 저장하는 Map
			Map<Integer, Integer> cnts = new HashMap<>();
			for (int j = 0; j < Math.min(cur[0].length, 100); j++) {
				// 0은 고려하지 않는다.
				if (cur[i][j] == 0) continue;
				// 나오는 수에 따라 cnts를 갱신한다.
				cnts.putIfAbsent(cur[i][j], 0);
				cnts.put(cur[i][j], cnts.get(cur[i][j])+1);
			}
			// cnts에 저장된 수와 해당 수가 나온 횟수를 정렬한다.
			PriorityQueue<Number> pq = new PriorityQueue<>();
			for (int k : cnts.keySet()) {
				pq.offer(new Number(k, cnts.get(k)));
			}
			// 정렬된 결과를 res에 저장한다.
			while (!pq.isEmpty()) {
				Number N = pq.poll();
				res.get(i).add(N.n);
				res.get(i).add(N.count);
			}
			// 열의 크기의 최대값을 구한다.
			max = Math.max(max, res.get(i).size());
		}
		// 연산 결과를 만든다.
		int[][] next = new int[cur.length][max];
		for (int i = 0; i < cur.length; i++) {
			for (int j = 0; j < res.get(i).size(); j++) {
				next[i][j] = res.get(i).get(j);
			}
		}
		return next;
	}
	// 각 열에 대해 정렬을 수행한다.
	private static int[][] simulateColumn(int[][] cur) {
		int max = 0;
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		for (int j = 0; j < Math.min(cur[0].length, 100); j++) {
			res.add(new ArrayList<>());
		}
		for (int j = 0; j < Math.min(cur[0].length, 100); j++) {
			Map<Integer, Integer> cnts = new HashMap<>();
			for (int i = 0; i < Math.min(cur.length, 100); i++) {
				if (cur[i][j] == 0) continue;
				cnts.putIfAbsent(cur[i][j], 0);
				cnts.put(cur[i][j], cnts.get(cur[i][j])+1);
			}
			PriorityQueue<Number> pq = new PriorityQueue<>();
			for (int k : cnts.keySet()) {
				pq.offer(new Number(k, cnts.get(k)));
			}
			while (!pq.isEmpty()) {
				Number N = pq.poll();
				res.get(j).add(N.n);
				res.get(j).add(N.count);
			}
			max = Math.max(max, res.get(j).size());
		}
		int[][] next = new int[max][cur[0].length];
		for (int j = 0; j < cur[0].length; j++) {
			for (int i = 0; i < res.get(j).size(); i++) {
				next[i][j] = res.get(j).get(i);
			}
		}
		return next;
	}
}
