package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2195_문자열_복사 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String X = br.readLine();
		String Y = br.readLine();
		int N = X.length();
		int M = Y.length();
		int cnt = 0; // copy 함수의 최소 사용횟수
		int idx = 0; // 현재 인덱스
		// greedy algorithm 활용
		// Y의 idx번째 문자부터, X 내 문자열 중 가장 길게 대응될 수 있는 문자열을 찾는다.
		while (idx < M) {
			int max = 0;
			for (int j = 0; j < N; j++) {
				int temp = 0;
				while (j+temp < N && idx+temp < M && X.charAt(j+temp) == Y.charAt(idx+temp)) temp++;
				if (max < temp) max = temp;
			}
			idx += max;
			cnt++;
		}
		System.out.println(cnt);
	}

}
