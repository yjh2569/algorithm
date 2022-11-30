package implementation;

import java.util.ArrayList;

public class Solution_lv1_다트_게임 {

	public static void main(String[] args) {
		int result = solution("1S2D*3T");
		System.out.println(result);
	}
	
	public static int solution(String dartResult) {
        int answer = 0;
        // 보너스 및 옵션을 적용한 점수들을 모아두는 ArrayList
        ArrayList<Integer> scores = new ArrayList<>();
        int l = dartResult.length();
        // dartResult 내 문자를 하나씩 보면서 scores에 기록한다.
        for (int i = 0; i < l; i++) {
        	char c = dartResult.charAt(i);
        	// 점수 부분인 경우
        	if ('0'<=c && c<='9') {
        		// 점수가 10점인 경우
        		if (c == '1' && i < l-1 && dartResult.charAt(i+1) == '0') {
        			scores.add(10);
        			i++;
        		// 점수가 1자리 수인 경우
        		} else {
        			scores.add(c-'0');
        		}
        	// Double 보너스
        	} else if (c == 'D') {
        		int n = scores.get(scores.size()-1);
        		scores.remove(scores.size()-1);
        		scores.add(n*n);
        	// Triple 보너스
        	} else if (c == 'T') {
        		int n = scores.get(scores.size()-1);
        		scores.remove(scores.size()-1);
        		scores.add(n*n*n);
        	// 스타상
        	} else if (c == '*') {
        		int n = scores.get(scores.size()-1);
        		scores.remove(scores.size()-1);
        		if (scores.size() > 0) {
        			int m = scores.get(scores.size()-1);
        			scores.remove(scores.size()-1);
        			scores.add(2*m);
        		}
        		scores.add(2*n);
        	// 아차상
        	} else if (c == '#') {
        		int n = scores.get(scores.size()-1);
        		scores.remove(scores.size()-1);
        		scores.add(-n);
        	}
        }
        // 점수 집계
        for (int score : scores) {
        	answer += score;
        }
        return answer;
    }

}
