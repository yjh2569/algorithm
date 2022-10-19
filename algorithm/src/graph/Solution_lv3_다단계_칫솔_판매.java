package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv3_다단계_칫솔_판매 {

	public static void main(String[] args) {
		int[] result = solution(new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"}, new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[] {"young", "john", "tod", "emily", "mary"}, new int[] {12, 4, 2, 5, 10});
		System.out.println(Arrays.toString(result));

	}
	
	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
		int[] answer = new int[N];
		// 각 판매원이 enroll에서 몇 번째 원소인지를 map으로 표현
		Map<String, Integer> idxs = new HashMap<>();
		for (int i = 0; i < N; i++) {
			idxs.put(enroll[i], i);
		}
		// "-"의 경우 -1번째 원소로 간주
		idxs.put("-", -1);
		int M = seller.length;
		// 각 판매 내역에 대해 수익 분배
		for (int i = 0; i < M; i++) {
			String sellerName = seller[i];
			// 판매한 판매원의 인덱스
			int sellerIdx = idxs.get(sellerName);
			// 얻은 수익
			int benefit = amount[i] * 100;
			// 현재 남은 수익의 10%
			int cur = benefit;
			// 판매원부터 차례대로 올라가면서 수익을 분배한다.
			while (sellerIdx != -1 && cur > 0) {
				cur /= 10;
				answer[sellerIdx] += benefit - cur;
				sellerIdx = idxs.get(referral[sellerIdx]);
				benefit = cur;
			}
		}
        return answer;
    }

}
