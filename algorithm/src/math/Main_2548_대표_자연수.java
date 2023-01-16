package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2548_대표_자연수 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		// nums를 정렬한 후, diff라는 크기가 N-1인 배열을 다음과 같이 정의한다.
		// diff[i] = nums[i+1] - nums[i]
		// 이때 nums[0]이 다른 모든 자연수들과의 차이의 합을 계산하면 다음과 같이 표현할 수 있다.
		// (N-1)*diff[0] + (N-2)*diff[1] + ... + 1*diff[N-2]
		// nums[1]의 경우
		// 1*diff[0] + (N-2)*diff[1] + ... + 1*diff[N-2]
		// nums[2]의 경우
		// 1*diff[0] + 2*diff[1] + (N-3)*diff[2] + ... + 1*diff[N-2]
		// 이를 통해 n의 값이 커질수록 모든 자연수들과의 차이의 합이 작아진다는 것을 알 수 있고, 
		// 반대로 n이 N-1일 때부터 이를 diff를 이용해 나타내면
		// 반대로 n의 값이 작아질수록 모든 자연수들과의 차이의 합이 작아진다.
		// 즉, 중앙에 있을 때 그 값이 최소가 됨을 알 수 있다.
		Arrays.sort(nums);
		System.out.println(nums[(N-1)/2]);
	}

}
