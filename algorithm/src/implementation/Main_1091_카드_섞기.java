package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1091_카드_섞기 {
	static int N;
	static int[] P, S, start;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		P = new int[N];
		S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		// 현재 i번째 위치에 있는 카드가 몇 번째 카드인지를 나타내는 배열
		int[] cur = new int[N];
		// 초기화
		for (int i = 1; i < N; i++) {
			cur[i] = i;
		}
		// 맨 처음 카드 상태
		start = Arrays.copyOf(cur, N);
		int cnt = 0; // 카드를 섞은 횟수
		// 조건을 만족할 때까지 카드를 계속 섞는다.
		while (true) {
			// 조건을 만족하는 경우
			if (checkIfReachedP(cur)) {
				System.out.println(cnt);
				return;
			}
			// 카드를 섞는다.
			cur = swap(cur);
			cnt++;
			// 카드의 개수가 N일 때 가능한 카드 배치의 경우의 수는 N!이므로 
			// 언젠가 다시 초기 카드 상태로 돌아온다.
			// 이후로 다시 카드를 섞어도 조건을 만족시킬 수 없으므로 -1을 출력한다.
			if (checkIfReachedStart(cur)) {
				break;
			}
		}
		System.out.println(-1);
	}
	// 문제의 조건을 만족하는지 확인하는 함수
	private static boolean checkIfReachedP(int[] cur) {
		// i번째로 위에 있는 카드는 플레이어 i%3에게 간다.
		// 이때 이 카드는 초기에 cur[i]번째 카드였고, 조건을 만족하려면 P[cur[i]]가 i%3이어야 한다.
		for (int i = 0; i < N; i++) {
			if (P[cur[i]] != i%3) return false;
		}
		return true;
	}
	// 카드가 초기 상태로 돌아오는지 확인하는 함수
	private static boolean checkIfReachedStart(int[] cur) {
		for (int i = 0; i < N; i++) {
			if (cur[i] != start[i]) return false;
		}
		return true;
	}
	// 카드를 섞는 함수
	private static int[] swap(int[] cur) {
		int[] next = new int[N]; // 카드를 섞은 결과를 저장하는 배열
		// i번째로 위에 있는 카드가 S[i]번째로 이동
		for (int i = 0; i < N; i++) {
			next[S[i]] = cur[i];
		}
		return next;
	}
}
