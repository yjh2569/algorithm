package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20529_가장_가까운_세_사람의_심리적_거리 {
	static int[] mbtis;
	static int min;
	static int[] p;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		// 출력 저장용 StringBuilder
		StringBuilder sb = new StringBuilder();
		outer: for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// 각 MBTI를 가지는 학생의 수를 저장하기 위한 배열
			// 총 16가지의 MBTI가 있으므로 길이는 16으로 한다.
			mbtis = new int[16];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				String s = st.nextToken();
				// MBTI를 정수로 변환해 mbtis에 기록한다.
				mbtiInterpret(s);
			}
			// 가장 가까운 세 학생 사이의 심리적 거리
			min = Integer.MAX_VALUE;
			// 조합 적용을 위해 필요한 배열
			p = new int[3];
			// 만약 MBTI가 같은 학생이 3명 이상이면 그 중에서 3명을 뽑으면 심리적 거리가 0이다.
			for (int i = 0; i < 16; i++) {
				if (mbtis[i] >= 3) {
					sb.append(0).append("\n");
					continue outer;
				}
			}
			// 나머지 경우에 대해서는 조합을 통해 3명의 학생을 뽑는 모든 경우를 고려해
			// 그 중 가장 가까운 세 학생 사이의 심리적 거리를 구한다.
			combi(0, 0, new int[16]);
			sb.append(min).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		
	}
	// MBTI를 정수로 변환해 mbtis 배열에 반영한다.
	private static void mbtiInterpret(String s) {
		int n = 0;
		// 각 척도를 비트로 변환하면 MBTI를 정수로 표현할 수 있다.
		if (s.charAt(0) == 'E') {
			n += 1 << 3;
		}
		if (s.charAt(1) == 'S') {
			n += 1 << 2;
		}
		if (s.charAt(2) == 'T') {
			n += 1 << 1;
		}
		if (s.charAt(3) == 'J') {
			n += 1 << 0;
		}
		mbtis[n]++;
	}
	// 중복조합을 이용해 세 학생을 뽑는다.
	// counts 배열은 지금까지 뽑은 학생의 MBTI를 저장하는 배열로, 각 MBTI를 가진 학생 수보다 많은 수의 학생을 뽑는 것을 방지한다.
	// 즉, 일반적인 조합에서 visited 배열의 역할을 담당한다.
	private static void combi(int cnt, int start, int[] counts) {
		// 3명의 학생을 다 뽑은 경우 심리적 거리를 구해보고 최소값과 비교해 본다.
		if (cnt == 3) {
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = i+1; j < 3; j++) {
					sum += check(p[i], p[j]);
				}
			}
			min = Math.min(min, sum);
			return;
		}
		// 중복조합을 적용해 3명의 학생을 재귀적으로 뽑는다.
		for (int i = start; i < 16; i++) {
			if (counts[i] >= mbtis[i]) continue;
			p[cnt] = i;			
			counts[i]++;
			combi(cnt+1, i, counts);
			counts[i]--;
		}
	}
	// 심리적 거리를 재기 위한 함수
	private static int check(int i, int j) {
		int s = 0; // 심리적 거리
		int diff = i ^ j; // 비트로 표현된 MBTI의 차이를 알아내기 위해 XOR 연산을 사용한다.
		// 각 비트를 조사해 비트가 1인 경우 i와 j에서 해당 비트가 달랐다는 것을 의미하므로 심리적 거리를 증가시킨다.
		for (int k = 0; k < 4; k++) {
			if ((diff & (1 << k)) != 0) s++;
		}
		return s;
	}

	

}
