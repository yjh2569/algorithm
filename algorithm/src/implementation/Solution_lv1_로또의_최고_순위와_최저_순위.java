package implementation;

import java.util.Arrays;

public class Solution_lv1_로또의_최고_순위와_최저_순위 {

	public static void main(String[] args) {
		int[] result = solution(new int[] {44, 1, 0, 0, 31, 25}, new int[] {31, 10, 45, 1, 6, 19});
		System.out.println(Arrays.toString(result));

	}
	
	public static int[] solution(int[] lottos, int[] win_nums) {
        int success = 0; // 현재 복권에 있는, 알아볼 수 있는 숫자 중 당첨 번호의 개수
        int fail = 0; // 현재 복권에 있는, 알아볼 수 있는 숫자 중 당첨 번호가 아닌 번호의 개수
        for (int lotto : lottos) {
        	// 알아볼 수 없는 숫자는 건너뛴다.
        	if (lotto == 0) continue;
        	boolean included = false; // 현재 숫자가 당첨 번호에 포함되어 있는지를 나타내는 변수
        	for (int win_num : win_nums) {
        		if (lotto == win_num) {
        			included = true;
        			break;
        		}
        	}
        	// 현재 숫자가 당첨 번호에 포함되어 있는지를 조사
        	if (included) {
        		success++;
        	} else {
        		fail++;
        	}
        }
        // 당첨 번호를 맞춘 개수에 따른 순위
        int[] grade = new int[] {6, 6, 5, 4, 3, 2, 1};
        // 가장 많이 맞출 수 있는 경우는 알아볼 수 있는 숫자 중 당첨되지 않은 번호를 제외하고 전부 당첨 번호인 경우이고,
        // 가장 적게 맞출 수 있는 경우는 알아볼 수 있는 숫자 중 당첨된 번호를 제외하고 전부 당첨 번호가 아닌 경우이다.
        int[] answer = new int[] {grade[6-fail], grade[success]};
        return answer;
    }

}
