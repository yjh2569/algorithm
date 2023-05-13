package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_7490_0_만들기 {
	static int N;
	static char[] ops = new char[] {' ', '+', '-'}; // 가능환 연산들을 모아놓은 배열
	static char[] p;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		sb = new StringBuilder(); // 출력 저장용 StringBuilder
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			p = new char[N-1]; // 각 숫자 사이에 +, -, 공백 중 어떤 것을 넣을지 나타내는 배열
			// 부분집합을 활용해 가능한 모든 경우를 살펴본다.
			subset(0);
			sb.append("\n"); // 테스트케이스 간에 한 줄을 띄워 출력
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length()-2);
		System.out.println(sb.toString());
	}
	// 부분집합을 활용해 가능한 모든 연산식을 고려하는 함수
	private static void subset(int cnt) {
		// 모든 연산자를 고려한 경우
		if (cnt == N-1) {
			// 연산 결과가 0이면 식을 만들어 출력용 StringBuilder에 넣는다.
			if (checkIfZero()) {
				sb.append(makeExpression());
			}
			return;
		}
		for (int i = 0; i < 3; i++) {
			p[cnt] = ops[i];
			subset(cnt+1);
		}
	}
	// 식의 결과가 0인지 확인하는 함수
	private static boolean checkIfZero() {
		// 식에서 나오는 모든 숫자들을 저장하는 ArrayList
		// 단, 숫자 앞에 -가 붙는 경우 숫자를 저장할 때 음수로 저장한다.
		ArrayList<Long> nums = new ArrayList<>();
		boolean minus = false; // 앞에서 - 부호가 나왔는지를 나타내는 변수
		for (int i = 0; i < N; i++) {
			// 공백까지 고려해 어떤 숫자가 있는지를 파악
			StringBuilder temp = new StringBuilder();
			temp.append(i+1);
			while (i < N-1 && p[i] == ' ') {
				i++;
				temp.append(i+1);
			}
			// 숫자 앞에 - 부호가 있는지에 따라 nums에 숫자 저장
			if (minus) nums.add(-Long.parseLong(temp.toString()));
			else nums.add(Long.parseLong(temp.toString()));
			// 다음에 - 부호가 나오는지 확인
			if (i < N-1 && p[i] == '-') minus = true;
			else minus = false;
		}
		// nums에 있는 모든 숫자를 합한다.
		long res = 0;
		for (long n : nums) {
			res += n;
		}
		// 모든 숫자의 합이 0인지 확인
		return res == 0l;
	}
	// p 배열을 활용해 식을 만드는 함수
	private static String makeExpression() {
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < N-1; i++) {
			temp.append(i+1).append(p[i]);
		}
		temp.append(N).append("\n");
		return temp.toString();
	}

}
