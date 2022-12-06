package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv2_압축 {

	public static void main(String[] args) {
		int[] result = solution("KAKAO");
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(String msg) {
        ArrayList<Integer> answerList = new ArrayList<>();
        // 압축에 사용할 사전
        Map<String, Integer> dic = new HashMap<>();
        // 사전 초기화
        for (int i = 1; i <= 26; i++) {
        	dic.put((char)('A'+i-1)+"", i);
        }
        // 사전에 사용된 마지막 인덱스 번호
        int lastIdx = 26;
        // 단어에 남은 문자가 없을 때까지 반복
        while (msg.length() > 0) {
        	// 사전에서 현재 입력과 일치하는 가장 긴 문자열의 길이를 찾는다. 
        	int l = 1;
        	while (l <= msg.length() && dic.containsKey(msg.substring(0, l))) l++;
        	l--;
        	// 사전에서 현재 입력과 일치하는 가장 긴 문자열
        	String substr = msg.substring(0, l);
        	// substr에 대한 인덱스 번호를 저장
        	answerList.add(dic.get(substr));
        	// 입력에서 처리되지 않은 다음 글자가 남아있다면 그 다음 글자를 포함하는 단어를 사전에 등록
        	if (l < msg.length()) {
        		dic.put(msg.substring(0, l + 1), ++lastIdx);
        	}
        	// 입력에서 substr을 제거
        	msg = msg.substring(l, msg.length());
        }
        // 결과를 정수 배열로 변경
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
        	answer[i] = answerList.get(i);
        }
        return answer;
    }

}
