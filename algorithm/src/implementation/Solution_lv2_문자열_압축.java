package implementation;

public class Solution_lv2_문자열_압축 {

	public static void main(String[] args) {
		int result = solution("aabbaccc");
		System.out.println(result);

	}
	
	public static int solution(String s) {
        int answer = s.length();
        // 문자열을 나눴을 때 마지막 문자열의 길이
        int lastLen = 0;
        // 반복되는 문자열의 개수
    	int numLen = 0;
    	// 문자열을 자르는 단위를 결정한다.
        for (int l = 1; l <= s.length()/2; l++) {
        	// 이전 문자열
        	String prev = s.substring(0, l);
        	// 문자열이 반복되는 횟수
        	int cnt = 1;
        	// 입력으로 주어진 문자열을 변환한 결과의 길이
        	int length = 0;
        	// 문자열을 길이 l로 자르면서 변환한다.
        	for (int i = l; i < s.length(); i+=l) {
        		String cur = s.substring(i, Math.min(s.length(), i+l));
        		lastLen = cur.length();
        		if (prev.equals(cur)) cnt++;
        		else {
        			if (cnt > 1) {
        				numLen = (cnt+"").length();
        				length += l+numLen;
        			} else {
        				length += l;
        			}
        			cnt = 1;
        		}
        		prev = cur;
        	}
        	// 마지막 문자열
        	if (cnt > 1) {
        		numLen = (cnt+"").length();
				length += l+numLen;
        	} else {
        		length += lastLen;
        	}
        	answer = Math.min(answer, length);
        }
        return answer;
    }
	
}
