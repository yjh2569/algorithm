package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1138_한_줄로_서기 {
	static int N;
	static int[] counts;
	static int[] p;
	static boolean[] v;
	static int[] answer;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		counts = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			counts[i] = Integer.parseInt(st.nextToken());
		}
		// 순열을 이용해 줄을 설 수 있는 모든 경우를 고려하고, 그 중 조건을 만족하는 줄 서기 방법을 찾는다.
		p = new int[N];
		v = new boolean[N+1];
		answer = new int[N];
		perm(0);
		// 풀이 2
		// 키가 큰 사람부터 조건을 만족하도록 줄에 넣는다.
		ArrayList<Integer> answerList = new ArrayList<>();
		for (int i = N; i >= 1; i--) {
			int count = counts[i];
			answerList.add(count, i);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(answer[i]).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 순열을 이용해 줄을 설 수 있는 모든 경우를 고려하는 함수
	private static void perm(int cnt) {
		if (answer[0] != 0) return; // 이미 답을 찾은 경우
		// 답을 찾은 경우
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				answer[i] = p[i];
			}
			return;
		}
		for (int i = 1; i <= N; i++) {
			// cnt번째에 키가 i인 사람을 줄 세웠을 때 조건을 만족하지 않으면 넘어간다.
			if (v[i] || !check(cnt, i)) continue;
			v[i] = true;
			p[cnt] = i;
			perm(cnt+1);
			v[i] = false;
		}
	}
	// n번째에 키가 i인 사람을 줄 세웠을 때 키가 i인 사람보다 키가 큰 사람이 왼쪽에 counts[i]만큼 있는지 확인하는 함수
	private static boolean check(int n, int i) {
		int cnt = 0;
		for (int j = 0; j < n; j++) {
			if (p[j] > i) cnt++;
		}
		return cnt == counts[i];
	}

}
