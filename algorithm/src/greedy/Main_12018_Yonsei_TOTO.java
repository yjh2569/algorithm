package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12018_Yonsei_TOTO {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] mins = new int[n]; // 각 강의를 수강하기 위한 최소 마일리지를 저장하는 배열
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int[] points = new int[p];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < p; j++) {
				points[j] = Integer.parseInt(st.nextToken());
			}
			// 현재 수강신청 인원이 수강 인원보다 적으면 1마일리지를 내고 수강 신청을 하면 된다.
			if (p < l) {
				mins[i] = 1;
			// 그렇지 않으면 p-l번째로 마일리지를 적게 낸 학생과 같은 마일리지를 내면 된다. 
			} else {
				Arrays.sort(points);
				mins[i] = points[p-l];
			}
		}
		// 최대한 많은 강의를 수강하려면, 마일리지를 적게 내는 강의 순으로 수강 신청하면 된다.
		Arrays.sort(mins);
		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			if (sum + mins[i] > m) break;
			sum += mins[i];
			cnt++;
		}
		System.out.println(cnt);
	}

}
