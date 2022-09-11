package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10986_나머지_합 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			// 본래 수 그대로 저장하지 않고 M으로 나눈 나머지를 저장
			nums[i] = Integer.parseInt(st.nextToken())%M;
		}
		// A_1부터의 누적 합을 M으로 나눈 나머지가 k인 경우의 수를 counts[k]에 저장한다.
		int[] counts = new int[M];
		// 아무것도 더하지 않으면 나머지가 0
		counts[0] = 1;
		// 연속된 부분 구간의 합이 M으로 나누어 떨어지는 경우의 수
		long cnt = 0;
		// A_1부터의 누적 합을 M으로 나눈 나머지
		int sum = 0;
		// A_1부터 A_i까지의 누적 합을 M으로 나눈 나머지를 구하고,
		// 현재까지 나온 누적 합 리스트 중 M으로 나머지가 k인 경우에 대해서만 
		// 해당 구간을 골라 빼면 새로운 연속된 부분 구간을 얻을 수 있다.
		// ex) A_1 + ... + A_10을 10으로 나눈 나머지가 5였다고 할 때
		// counts[5]의 값을 통해 A_9까지의 누적합 계산 중 나온, 
		// 누적 합을 10으로 나눈 나머지가 5인 경우의 수를 구함으로써
		// 해당 경우의 부분 구간을 A_1 + ... + A_10에서 빼면
		// 원하는 부분 구간을 얻을 수 있다.
		for (int i = 0; i < N; i++) {
			sum = (sum + nums[i])%M;
			cnt += counts[sum];
			// 나머지가 sum이 되는 경우의 수 갱신
			counts[sum]++;
		}
		System.out.println(cnt);
	}

}
