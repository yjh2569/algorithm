package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1111_IQ_Test {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		// 다음 수를 구한다.
		String result = check(nums, N);
		System.out.println(result);
	}
	// 다음 수를 반환하는 함수
	// 다음 수가 여러 개인 경우 A, 다음 수가 없는 경우 B를 반환한다.
	private static String check(int[] nums, int N) {
		// 숫자가 한 개만 주어지면 다음 수를 구하는 것이 불가능하고,
		// 숫자가 두 개가 주어지면 두 숫자가 다른 경우 다음 수를 구하는 것이 불가능하다.
		// 두 숫자를 A, B라고 할 때 A*a + b = B는 부정방정식이 되기 떄문이다.
		if (N == 1 || (N == 2 && nums[0] != nums[1])) return "A";
		// 처음 두 숫자를 A, B라고 할 때 A*a + b = B인데,
		// 처음 두 숫자가 같으면 B*a + b = A*a + b = B = A가 된다.
		// 즉, 다음 수도 계속 첫 번쨰 수와 같은 값이 되어야 한다.
		if (nums[0] == nums[1]) {
			for (int i = 2; i < N; i++) {
				// 하나라도 첫 번째 수와 다른 수가 나오면 수열의 규칙을 만족하지 못하기에 다음 수를 구할 수 없다. 
				if (nums[i] != nums[0]) return "B";
			}
			return nums[0]+"";
		// 처음 두 숫자가 다르고 N >= 3인 경우
		// 처음 세 숫자를 A, B, C라고 할 때 A*a + b = B, B*a + b = C가 성립한다.
		// 이를 연립방정식으로 풀면 a = (B-C)/(A-B), b = B - A*a가 된다.
		// 이 두 값을 정수화한 뒤 수열 내 모든 수들에 대해 nums[i-1]*a + b = nums[i]가 되는지 확인한다.
		// 그러면 a 또는 b가 유리수가 되는 경우도 확인할 수 있다.
		} else {
			int a = (nums[1]-nums[2])/(nums[0]-nums[1]);
			int b = nums[1] - nums[0]*a;
			for (int i = 1; i < N; i++) {
				if (nums[i-1]*a+b != nums[i]) return "B";
			}
			return nums[N-1] * a + b + "";
		}
	}

}
