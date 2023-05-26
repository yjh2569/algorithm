package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14569_시간표_짜기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] classes = new long[N]; // 각 과목의 수업이 진행되는 시간을 비트로 표현한 결과를 저장하는 배열
		// 비트마스킹을 활용해 각 과목의 수업이 진행되는 시간을 기록한다.
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			for (int j = 0; j < k; j++) {
				classes[i] += 1l << (Long.parseLong(st.nextToken()));
			}
		}
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());
		// 각 학생의 강의가 비어있는 시간의 경우 역시 비트마스킹으로 표현하고, (비어있는 시간을 0으로 표현)
		// 각 과목과 각 학생을 비트로 표현한 결과를 AND 연산했을 때
		// 만약 그 결과가 0이라면 학생은 해당 과목을 수강할 수 있다.
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			long student = (1l << 51) - 1;
			for (int j = 0; j < p; j++) {
				student -= 1l << (Long.parseLong(st.nextToken()));
			}
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if ((classes[j] & student) == 0) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
