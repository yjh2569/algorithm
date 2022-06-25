package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2594_놀이공원 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		// 오전 10시 ~ 오후 10시 사이의 각 분마다 쉴 수 있는지를 나타내는 배열
		// 오전 10시 정각을 0번째 인덱스로 시작해 1분마다 인덱스 값이 1 증가한다.
		boolean[] rest = new boolean[721];
		Arrays.fill(rest, true);
		// 놀이기구 작동 시간을 이용해 쉴 수 있는 시간을 조사한다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 오전 10시를 0, 오후 10시를 720(=60*12)으로 바꾼다.
			int startMin = (start/100-10)*60 + start%100;
			int endMin = (end/100-10)*60 + end%100;
			// 놀이기구 운영 10분 전 시각부터 놀이기구 종료 9분 후까지 쉴 수 없도록 지정
			// 놀이기구 종료 10분 후가 되자마자 쉴 수 있음에 유의
			// 또한, 오전 10시 이전과 오후 10시 이후는 놀이공원 개장 시간이 아님에 유의
			for (int min = Math.max(startMin-10, 0); min < Math.min(endMin+10, 720); min++) {
				rest[min] = false;
			}
		}
		int max = 0; // 쉬는 시간의 최댓값
		int cur = 0; // 현재까지 구한 쉬는 시간
		// 각 분에 대해 쉴 수 있는 경우에는 cur을 증가시키다가
		// 쉴 수 없는 경우가 나오면 쉬는 시간의 최댓값이 될 수 있는지 시도
		for (int m = 0; m < 720; m++) {
			if (rest[m]) cur++;
			else {
				max = Math.max(cur, max);
				cur = 0; // 쉬는 시간 초기화
			}
		}
		// 마지막 쉬는 시간이 최댓값이 될 수 있음에 유의
		max = Math.max(max, cur);
		System.out.println(max);
	}

}
