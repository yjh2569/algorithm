package math;

import java.util.StringTokenizer;

public class Solution_lv2_k진수에서_소수_개수_구하기 {

	public static void main(String[] args) {
		int result = solution(437674, 3);
		System.out.println(result);
	}
	
	public static int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        // n을 k진수로 바꾼다.
        while (n > 0) {
            sb.append(Integer.toString(n%k));
            n = n/k;
        }
        // 결과가 빈 문자열일 경우
        if (sb.length() == 0) return 0;
        // 현재 sb는 n을 k진수로 바꾼 결과를 역순으로 나타낸 것이므로, 이를 뒤집고 0을 기준으로 나눈다.
        StringTokenizer st = new StringTokenizer(sb.reverse().toString(), "0");
        // 소수의 개수
        int answer = 0;
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            Long m = Long.parseLong(s);
            // 변환 결과가 1인 경우 소수가 아니므로 다음 수로 진행
            if (m <= 1) continue;
            // 해당 수가 소수인지 에라토스테네스의 체를 통해 판단
            boolean isPrime = true;
            for (int i = 2; i <= (long) Math.sqrt(m); i++) {
                if (m%i == 0) {
                    isPrime = false;
                    break;
                }
            }
            // 해당 수가 소수면 소수의 개수를 1 증가시킨다.
            if (isPrime) answer++;
        }
        return answer;
    }

}
