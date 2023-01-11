package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_19940_피자_오븐 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// 출력 시 빈 칸 추가를 위한 문자열
		String blank = " ";
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			// N%60이 35보다 작거나 같은 경우에는 ADDH 버튼을 N/60번 누르는 게 횟수가 더 적다.
			// 그렇지 않다면 ADDH 버튼을 (N/60+1)번 누르는 게 횟수가 더 적거나 사전순으로 더 앞에 온다.
			if (N%60 <= 35) {
				sb.append(N/60).append(blank);
				N -= (N/60)*60;
			} else {
				sb.append(N/60+1).append(blank);
				N -= (N/60+1)*60;
			}
			// ADDH 버튼을 N/60번 누른 경우
			if (N >= 0) {
				// N%10이 5보다 작거나 같은 경우에는 ADDT 버튼을 N/10번 누르고 ADDO 버튼을 N%10번 누르는 게 횟수가 더 적다.
				// 그렇지 않다면 ADDT 버튼을 (N/10+1)번 누르고 MINO 버튼을 10-N%10번 누르는 게 횟수가 더 적다. 
				if (N%10 <= 5) {
					sb.append(N/10).append(blank).append(0).append(blank).append(N%10).append(blank).append(0);
				} else {
					sb.append(N/10+1).append(blank).append(0).append(blank).append(0).append(blank).append(10-N%10);
				}
			// ADDH 버튼을 (N/60+1)번 누른 경우
			} else {
				// (-N)%10이 5보다 작거나 같은 경우에는 MINT 버튼을 N/10번 누르고 MINO 버튼을 (-N)%10번 누르는 게 횟수가 더 적다.
				// 그렇지 않다면 MINT 버튼을 ((-N)/10+1)번 누르고 ADDO 버튼을 10-(-N)%10번 누르는 게 횟수가 더 적다.
				N = -N;
				if (N%10 <= 5) {
					sb.append(0).append(blank).append(N/10).append(blank).append(0).append(blank).append(N%10);
				} else {
					sb.append(0).append(blank).append(N/10+1).append(blank).append(10-N%10).append(blank).append(0);
				}
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
