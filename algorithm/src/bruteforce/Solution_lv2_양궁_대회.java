package bruteforce;

import java.util.Arrays;

public class Solution_lv2_양궁_대회 {
	static int max;
    static int[] answer;
	
	public static void main(String[] args) {
		int[] result = solution(5, new int[] {2,1,1,1,0,0,0,0,0,0,0});
		System.out.println(Arrays.toString(result));
	}
	
    public static int[] solution(int n, int[] info) {
        answer = new int[] {-1};
        // (라이언의 점수) - (어피치의 점수)의 최댓값
        max = 0;
        // 조합을 통해 모든 경우를 고려한다.
        combi(0, n, new int[11], n, info);
        return answer;
    }
    // 중복조합을 통해 각 과녁에 맞출 화살의 개수를 정한다.
    static void combi(int cnt, int k, int[] cur, int n, int[] info) {
    	// 모든 영역을 고려한 경우
        if (cnt == 11) {
        	// 라이언의 점수
            int sum1 = 0;
            // 어피치의 점수
            int sum2 = 0;
            // 규칙에 따라 라이언과 어피치의 점수를 계산
            for (int i = 0; i <= 10; i++) {
                if (cur[i] > info[i] && cur[i] > 0) {
                    sum1 += 10-i;
                } else if (info[i] >= cur[i] && info[i] > 0) {
                    sum2 += 10-i;
                }
            }
            // max를 갱신
            if (sum1 > sum2 && max < sum1 - sum2) {
                max = Math.max(max, sum1 - sum2);
                answer = Arrays.copyOf(cur, 11);
            // 최대값이 같을 경우 가장 낮은 점수를 더 많이 맞춘 경우를 반환해야 한다.
            } else if (sum1 > sum2 && max == sum1 - sum2) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] > cur[i]) return;
                    else if (answer[i] < cur[i]) {
                        answer = Arrays.copyOf(cur, 11);
                        return;
                    }
                }
            }
            return;
        }
        // 현재 cnt점에 맞출 화살의 개수를 결정한 뒤 다음 영역을 고려한다.
        for (int i = 0; i <= k; i++) {
            cur[cnt] = i;
            combi(cnt+1, k-i, cur, n, info);
        }
    }

}
