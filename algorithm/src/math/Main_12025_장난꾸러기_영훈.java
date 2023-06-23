package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_12025_장난꾸러기_영훈 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String password = br.readLine();
		long k = Long.parseLong(br.readLine())-1;
		int cnt = 0; // 1, 2, 6, 7의 개수
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == '1' || password.charAt(i) == '2' || password.charAt(i) == '6' || password.charAt(i) == '7') {
				cnt++;
			}
		}
		// 만약 k+1이 2^cnt보다 크다면, k+1번째 수는 절대 만들 수 없다. 
		if ((1l << cnt) < k+1) {
			System.out.println(-1);
			return;
		}
		// 수의 맨 끝자리부터 보면서 1, 2, 6, 7을 발견하면,
		// k의 tempCnt번째 비트가 1이면 1과 6, 2와 7 중 큰 수로 바꾸고,
		// 그렇지 않으면 1과 6, 2와 7 중 작은 수로 바꾼다.
		int cur = password.length()-1; 
		int tempCnt = 0;
		StringBuilder sb = new StringBuilder();
		while (cur >= 0) {
			// cur번째 자리수가 1, 2, 6, 7이 아닌 경우 그냥 넘어간다.
			if (password.charAt(cur) != '1' && password.charAt(cur) != '2' && password.charAt(cur) != '6' && password.charAt(cur) != '7') {
				sb.append(password.charAt(cur--));
				continue;
			}
			// cur번째 자리수가 1, 2, 6, 7인 경우 k의 tempCnt번째 비트값에 따라 다른 수로 바꾼다.
			int appended = 0;
			// k의 tempCnt번째 비트값이 1인 경우
			if ((k & (1l << tempCnt)) != 0) {
				if (password.charAt(cur) == '1') {
					appended = 6;
				} else if (password.charAt(cur) == '2') {
					appended = 7;
				} else {
					appended = password.charAt(cur) - '0';
				}
			// k의 tempCnt번째 비트값이 0인 경우
			} else {
				if (password.charAt(cur) == '6') {
					appended = 1;
				} else if (password.charAt(cur) == '7') {
					appended = 2;
				} else {
					appended = password.charAt(cur) - '0';
				}
			}
			sb.append(appended);
			tempCnt++;
			cur--;
		}
		// 위와 같은 과정 진행 시 결과값의 뒤집힌 형태가 나오게 된다.
		System.out.println(sb.reverse().toString());
	}

}
