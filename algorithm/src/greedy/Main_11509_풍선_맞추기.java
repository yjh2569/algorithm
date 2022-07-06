package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11509_풍선_맞추기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] balloons = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 풍선 높이의 최대값
		int max = 0;
		for (int i = 0; i < N; i++) {
			balloons[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, balloons[i]);
		}
		// 높이에 따른 화살의 개수
		int[] arrows = new int[max+1];
		// 필요한 화살의 개수의 최소값
		int cnt = 0;
		// greedy algorithm을 적용한다.
		// 앞에서부터 풍선의 높이를 조사한 뒤 해당 풍선의 높이와 같은 높이에 있는 화살이 존재하는지를 먼저 살핀다.
		// 만약 존재하면 그 화살을 이용해 풍선을 터뜨리고, 그렇지 않으면 해당 높이에 새로운 화살을 쏜다.
		// 그리고 그 화살의 높이를 1 낮춘 뒤 arrows 배열에 추가한다.
		for (int i = 0; i < N; i++) {
			int h = balloons[i];
			if (arrows[h] > 0) arrows[h]--;
			else cnt++;
			if (h > 0) arrows[h-1]++;
		}
		System.out.println(cnt);
	}

}
