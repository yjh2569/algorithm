package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1722_순열의_순서 {
	static int N;
	static long[] factorial;
	static int[] answer;
	static boolean[] used;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int problemNum = Integer.parseInt(st.nextToken());
		answer = new int[N]; // 1번 문제일 때 k번째 수열을 나타내는 배열
		used = new boolean[N+1]; // 각 숫자가 쓰였는지를 나타내는 배열
		factorial = new long[N+1]; // 몇 번째 배열인지를 쉽게 파악하기 위해 팩토리얼 수를 구해놓는다.
		factorial[0] = 1;
		for (int i = 1; i <= N; i++) {
			factorial[i] = factorial[i-1]*i;
		}
		// 문제 번호가 1번인 경우
		if (problemNum == 1) {
			answer = new int[N];
			long k = Long.parseLong(st.nextToken());
			// k번째 수열을 찾는다.
			// 이때 0번째 수열부터 시작한다고 가정한다.
			findArr(0, k-1);
			// 수열 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N; i++) {
				sb.append(answer[i]).append(" ");
			}
			if (sb.length() > 0) sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		// 문제 번호가 2번인 경우
		} else if (problemNum == 2) {
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			// 입력으로 받은 수열이 몇 번째 수열인지 구한다.
			System.out.println(findNum(arr));
		}
	}
	// 1번 문제를 해결하는 함수
	// 자신의 앞에 있는 수열의 개수가 k개인 경우 수열의 cnt번째 수를 찾는 함수
	private static void findArr(int cnt, long k) {
		// 수열을 다 찾은 경우
		if (cnt == N) return;
		// 앞에 있는 수열의 개수가 k개일 때 k / (N-1-cnt)!개만큼의 수가 cnt번째 수에 온 경우가 앞에 있는 수열에 이미 있다.
		// 따라서 그 수들을 제외한 가장 작은 숫자가 cnt번째 수에 오게 된다.
		long q = (long)k / factorial[N-1-cnt];
		long temp = q; // 임시 변수
		// cnt번째 수를 찾는다.
		for (int i = 1; i <= N; i++) {
			// 이미 수열 내에 있는 수라면 건너뛴다.
			if (used[i]) continue;
			// cnt번째 수를 찾은 경우
			if (temp == 0) {
				used[i] = true;
				answer[cnt] = i;
				break;
			}
			temp--;
		}
		// 다음 수를 찾는다.
		findArr(cnt+1, k-q*factorial[N-1-cnt]);
	}
	// arr가 몇 번째 수열인지를 찾는 함수
	private static long findNum(int[] arr) {
		long k = 1; // arr가 몇 번째 수열인지를 나타내는 수
		// findArr의 아이디어를 역으로 이용한다.
		for (int i = 0; i < N; i++) {
			int num = arr[i];
			// num가 i번째 숫자로 오려면 k의 범위가 어떻게 되어야 할지를 고려한다.
			// 이미 사용한 숫자를 제외해야 함에 유의한다.
			for (int j = 1; j <= N; j++) {
				if (used[j]) continue;
				if (num == j) break;
				k += factorial[N-1-i];
			}
			used[num] = true;
		}
		return k;
	}

}
