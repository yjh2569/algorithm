package string;

import java.util.Arrays;

public class Solution_lv1_비밀지도 {

	public static void main(String[] args) {
		String[] result = solution(5,new int[] {9, 20, 28, 18, 11}, new int[] {30, 1, 21, 17, 28});
		System.out.println(Arrays.toString(result));

	}
	
	public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        // 두 비밀지도를 합친 결과를 암호화한 배열
        int[] map = new int[n];
        // 두 비밀지도를 or 연산을 통해 합친다.
        for (int i = 0; i < n; i++) {
        	map[i] = arr1[i] | arr2[i];
        }
        String wall = "#"; // 벽을 나타내는 문자
        String blank = " "; // 빈칸을 나타내는 문자
        // 암호화된 map을 해독해 원래 지도로 만든다.
        for (int i = 0; i < n; i++) {
        	StringBuilder sb = new StringBuilder();
        	int temp = 1;
        	for (int j = 0; j < n; j++) {
        		if ((map[i]/temp)%2 == 1) {
        			sb.append(wall);
        		} else {
        			sb.append(blank);
        		}
        		temp *= 2;
        	}
        	answer[i] = sb.reverse().toString();
        }
        return answer;
    }

}
