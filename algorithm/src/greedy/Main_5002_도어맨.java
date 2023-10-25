package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5002_도어맨 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		int m = 0; // 클럽 내에 있는 남성의 수
		int w = 0; // 클럽 내에 있는 여성의 수
		for (int i = 0; i < s.length(); i++) {
			// i번째 사람이 남성인 경우
			if (s.charAt(i) == 'M') {
				// i번째 사람을 들여보냈을 때 문제가 없는 경우
				if (Math.abs(m+1-w) <= N) m++;
				// i번째 사람을 들여보냈을 때 이성 간의 사람 수 차이가 N보다 크면, 
				// i+1번째 사람이 여성인 경우 i+1번째 사람을 먼져 들여보내고 i번째 사람을 들여보낸다.
				else if (i < s.length()-1 && s.charAt(i+1) == 'W') {
					m++; w++; i++;
				// 그렇지 않은 경우 더 이상 사람들을 클럽에 들여보낼 수 없다.
				} else break;
			// i번째 사람이 여성인 경우
			} else {
				if (Math.abs(w+1-m) <= N) w++;
				// i번째 사람을 들여보냈을 때 이성 간의 사람 수 차이가 N보다 크면, 
				// i+1번째 사람이 남성인 경우 i+1번째 사람을 먼져 들여보내고 i번째 사람을 들여보낸다.
				else if (i < s.length()-1 && s.charAt(i+1) == 'M') {
					m++; w++; i++;
				} else break;
			}
		}
		System.out.println(m+w);
	}

}
