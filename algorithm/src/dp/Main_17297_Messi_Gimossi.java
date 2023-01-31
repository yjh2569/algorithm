package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_17297_Messi_Gimossi {
	static int[] fibonacci;
	static String firstStr = "Messi ";
	static String SecondStr = "Messi Gimossi ";
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine())-1; // 문자열의 인덱스가 0부터 시작함을 고려
		// 첫째항이 firstStr의 길이, 둘째항이 SecondStr의 길이인 피보나치 수열에서
		// 타입이 int인 값만 구한다.
		getFibonacci();
		// M번째 글자를 구한다.
		char result = find(M);
		System.out.println(result == ' ' ? "Messi Messi Gimossi" : result);
	}
	// 첫째항이 firstStr의 길이, 둘째항이 SecondStr의 길이인 피보나치 수열에서
	// 타입이 int인 값만 구하는 함수
	private static void getFibonacci() {
		// 피보나치 수열의 값을 임시로 저장하는 ArrayList
		ArrayList<Integer> fibonacciTemp = new ArrayList<>();
		// 초기값 설정
		fibonacciTemp.add(firstStr.length());
		fibonacciTemp.add(SecondStr.length());
		// 가장 마지막으로 구한 피보나치 수가 몇 번째 피보나치 수인지를 나타내는 인덱스
		int cur = 1;
		// 피보나치 수의 값이 정수 범위를 넘어설 때까지 구한다.
		while (true) {
			long temp = (long)fibonacciTemp.get(cur) + (long)fibonacciTemp.get(cur-1);
			if (temp > Integer.MAX_VALUE) break;
			fibonacciTemp.add((int)temp);
			cur++;
		}
		// fibonacciTemp를 배열로 바꾼다.
		int n = fibonacciTemp.size();
		fibonacci = new int[n];
		for (int i = 0; i < n; i++) {
			fibonacci[i] = fibonacciTemp.get(i);
		}
	}
	// m번째 글자를 구하는 재귀함수
	private static char find(int m) {
		// base condition
		if (m <= SecondStr.length()) return SecondStr.charAt(m);
		for (int k = 1; k < fibonacci.length-1; k++) {
			// 만약 m이 k번째 피보나치 수와 k+1번째 피보나치 수 사이에 있다면
			// (m-fibonacci[k])번째에 있는 글자와 같다.
			if (fibonacci[k] <= m && m < fibonacci[k+1]) {
				return find(m - fibonacci[k]);
			}
		}
		// m의 범위에 의해 여기는 도달할 수 없는 지점
		return '.';
	}

}
