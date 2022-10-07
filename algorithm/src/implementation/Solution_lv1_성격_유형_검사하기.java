package implementation;

import java.util.HashMap;
import java.util.Map;

public class Solution_lv1_성격_유형_검사하기 {

	public static void main(String[] args) {
		String result = solution(new String[] {"AN", "CF", "MJ", "RT", "NA"}, new int[] {5, 3, 2, 7, 5});
		System.out.println(result);

	}
	
	public static String solution(String[] survey, int[] choices) {
        String answer = "";
        int N = survey.length;
        // 각 지표에 대한 유형을 점수로 나타낸 결과를 저장하는 배열
        // 한 유형을 음수로, 다른 유형을 양수로 표현한다.
        // 음수로 표현하는 유형이 사전순으로 빠른 알파벳이 되도록 한다.
        int[] score = new int[4];
        // 질문의 유형과 해당 질문이 묻는 지표 및 방향을 대응시키는 맵
        // 여기서 방향은, 음수로 표현하는 유형이 비동의 쪽으로, 양수로 표현하는 유형이 동의 쪽으로 되어있는 경우를 1로 표시한다.
        Map<String, int[]> map = new HashMap<>();
        map.put("RT", new int[] {0, 1}); map.put("TR", new int[] {0, -1});
        map.put("CF", new int[] {1, 1}); map.put("FC", new int[] {1, -1});
        map.put("JM", new int[] {2, 1}); map.put("MJ", new int[] {2, -1});
        map.put("AN", new int[] {3, 1}); map.put("NA", new int[] {3, -1});
        // 음수로 표현하는 유형을 모아둔 문자열
        String neg = "RCJA";
        // 양수로 표현하는 유형을 모아둔 문자열
        String pos = "TFMN";
        // 각 질문과 그에 대한 대답을 수치화한다.
        for (int i = 0; i < N; i++) {
        	String s = survey[i];
        	int n = choices[i];
        	int[] arr = map.get(s);
        	score[arr[0]] += (n-4)*arr[1];
        }
        // 검사 결과를 바탕으로 성격 유형을 알아낸다.
        for (int i = 0; i < 4; i++) {
        	// score[i]가 0인 경우 사전순으로 빠른, 음수로 표현하는 유형을 선택한다.
        	if (score[i] <= 0) {
        		answer += neg.charAt(i);
        	} else {
        		answer += pos.charAt(i);
        	}
        }
        return answer;
    }

}
