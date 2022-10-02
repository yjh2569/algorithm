package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1135_뉴스_전하기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 각 직원의 직속상사 번호를 저장하는 배열
		int[] boss = new int[N];
		// 각 직원의 직속부하 수를 저장하는 배열
		int[] nums = new int[N];
		// 각 직원의 부하들이 모두 소식을 듣는데 걸리는 시간을 저장하는 배열
		int[] dp = new int[N];
		// 초기화
		Arrays.fill(dp, Integer.MAX_VALUE);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			boss[i] = Integer.parseInt(st.nextToken());
		}
		// 가장 말단 사원부터 해당 직원의 부하들이 모두 소식을 듣는데 걸리는 시간을 차례대로 구해 민식이까지 그 값을 구한다.
		// 번호가 작을수록 직급이 높기 때문에, 뒤에서부터 접근하면 항상 말단 사원부터 확인할 수 있다.
		for (int i = N-1; i >= 0; i--) {
			// 직속부하 수를 센다.
			if (i != 0) nums[boss[i]]++;
			// 직속부하가 없는 경우, 즉 말단 사원인 경우
			if (nums[i] == 0) {
				dp[i] = 0;
				continue;
			}
			// 직원의 직속부하들이 자신의 모든 부하들에게 소식을 전달하는데 걸리는 시간을 모은다.
			ArrayList<Integer> arr = new ArrayList<>();
			for (int j = N-1; j > i; j--) {
				if (boss[j] == i) arr.add(dp[j]);
			}
			// 자신의 모든 부하들에게 소식을 전달하는데 걸리는 시간이 긴 순서대로 정렬한 뒤,
			// 앞에서부터 전화를 하면 소식 전달 시간을 최소한으로 줄일 수 있다.
			Collections.sort(arr, Collections.reverseOrder());
			int max = 0; // 모든 부하에게 소식을 전달하는데 걸리는 시간의 최소값
			for (int j = 0; j < arr.size(); j++) {
				max = Math.max(max, arr.get(j)+j+1);
			}
			dp[i] = Math.min(dp[i], max);
		}
		// 민식이가 모든 부하들에게 소식을 전달하는데 걸리는 시간 출력
		System.out.println(dp[0]);
	}
}
