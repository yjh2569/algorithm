package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3186_소변기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		// 둘째줄에 주어지는 0과 1의 문자열을 StringBuilder로 받고 여기다가 0을 L개만큼 추가한다.
		// 이는 길이가 N인 문자열 이후에도 소변기가 계속 빈 자리이므로 비어있는 시간 동안 플러시 조건을 만족할 수도 있기 때문이다.
		StringBuilder input = new StringBuilder(br.readLine());
		for (int i = 0; i < L; i++) {
			input.append("0");
		}
		// 다시 문자열로 변환
		String s = input.toString();
		// 연속으로 사용한 시간
		int useTime = 0;
		// 소변기가 "사용중"인 상태인지를 나타내는 변수
		boolean isUsing = false;
		// 소변기가 연속으로 비어있는 시간
		int vacantTime = 0;
		// 출력 저장용 변수
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N+L; i++) {
			int x = s.charAt(i) - '0';
			// (i+1)초에 소변기 앞에 사람이 없을 때
			if (x == 0) {
				// 앞에서 소변기를 사용한 사람이 K초 이상 사용했는지를 조사한다.
				if (useTime >= K) {
					isUsing = true;
				}
				// 소변기 연속 사용 시간 초기화
				useTime = 0;
				vacantTime++;
			// (i+1)초에 소변기 앞에 사람이 있을 때
			} else if (x == 1) {
				// 소변기 앞에 사람이 없는 시간 초기화
				vacantTime = 0;
				useTime++;
			}
			// 플러시 조건을 만족하면 현재 시간((i+1)초)를 저장한다.
			if (isUsing && vacantTime == L) {
				sb.append(i+1).append("\n");
				// "사용중" 상태를 초기화한다.
				isUsing = false;
			}
		}
		// 한 번이라도 플러시 조건을 만족한 경우
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
			System.out.println(sb.toString());
		// 한 번도 플러시 조건을 만족하지 못한 경우
		} else {
			System.out.println("NIKAD");
		}
	}

}
