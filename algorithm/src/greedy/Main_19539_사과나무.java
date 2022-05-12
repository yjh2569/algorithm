package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19539_사과나무 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			cnt += cur/2;
			sum += cur;
		}
		if (sum%3 == 0 && cnt >= sum/3) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
