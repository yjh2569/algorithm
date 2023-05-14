package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16397_탈출 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		// 이미 N이 G가 된 경우
		if (N == G) {
			System.out.println(0);
			return;
		}
		// BFS를 통해 N이 G가 되기 위해 눌러야 하는 버튼의 최소 횟수를 구한다.
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[100000];
		q.offer(N);
		visited[N] = true;
		int cnt = 1; // 현재까지, 다음 수를 만들기 위해 눌러야 하는 횟수
		while (!q.isEmpty() && cnt <= T) {
			int qLen = q.size();
			while (qLen > 0) {
				int n = q.poll();
				// A 버튼을 누르는 경우
				// A 버튼을 눌렀을 때 G가 되는 경우
				if (n+1 == G) {
					System.out.println(cnt);
					return;
				}
				// A 버튼을 눌렀을 때 값이 99999를 초과해서는 안 된다.
				if (n < 99999 && !visited[n+1]) {
					visited[n+1] = true;
					q.offer(n+1);
				}
				// B 버튼을 눌렀을 때
				// 우선, 값이 2배가 됐을 때 100000 이상이 되면 안 된다.
				// 그리고, 값이 이미 0인 경우 버튼 B를 눌러도 0이므로 진행할 필요가 없다.
				int next = n*2;
				if (next >= 100000 || next == 0) {
					qLen--;
					continue;
				}
				// 2배가 되었을 때 가장 높은 자릿수를 구한다.
				int temp = 10;
				while (next/temp != 0) {
					temp *= 10;
				}
				temp /= 10;
				// 가장 높은 자릿수의 숫자를 1 감소시킨다.
				next -= temp;
				// B 버튼을 눌렀을 때 숫자가 G가 된 경우
				if (next == G) {
					System.out.println(cnt);
					return;
				}
				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
				qLen--;
			}
			cnt++;
		}
		// 버튼을 T번 눌러도 G에 도달하지 못하는 경우
		System.out.println("ANG");
	}

}
