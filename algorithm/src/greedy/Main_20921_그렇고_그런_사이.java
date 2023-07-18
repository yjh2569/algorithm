package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_20921_그렇고_그런_사이 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] res = new int[N]; // 결과를 저장하는 배열
		ArrayList<Integer> nums = new ArrayList<>(); // 남은 쪽지들을 저장하는 ArrayList
		for (int i = 1; i <= N; i++) {
			nums.add(i);
		}
		// greedy algorithm 적용
		// 첫번째 사람부터, K를 넘지 않는 가장 큰 수(M)가 적힌 쪽지를 준다.
		// 그러면 해당 사람과 그렇고 그런 사이는 무조건 M-1개가 생긴다.
		// 이를 K가 (남은 쪽지에 적힌 숫자 중 최댓값)보다 작을 때까지 반복한다.
		// 만약 K가 위 조건을 만족하면, 쪽지 중 (K+1)번쨰 쪽지를 가져가 그렇고 그런 사이가 딱 K개만 생기도록 한다.
		// 이후에는 그렇고 그런 사이가 생기면 안 되므로 작은 숫자가 적힌 쪽지부터 가져간다.
		for (int i = 0; i < N; i++) {
			if (K >= nums.size()-1) {
				res[i] = nums.remove(nums.size()-1);
				K -= nums.size();
			} else if (K != 0) {
				res[i] = nums.remove(K);
				K = 0;
			} else {
				res[i] = nums.remove(0);
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(res[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
