package implementation;

public class Solution_lv1_신규_아이디_추천 {

	public static void main(String[] args) {
		String result = solution("...!@BaT#*..y.abcdefghijklm");
		System.out.println(result);
	}
	
	public static String solution(String new_id) {
		// 1단계 : 모든 문자 소문자로 변환
        new_id = new_id.toLowerCase();
        // 2단계 : 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자 제거
        StringBuilder sb = new StringBuilder();
        for (char c : new_id.toCharArray()) {
        	if (('a'<=c && c<='z') || ('0'<=c && c<='9') || c == '-' || c == '_' || c == '.' ) {
        		sb.append(c);
        	}
        }
        // 3단계 : 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환
        new_id = sb.toString();
        sb = new StringBuilder();
        boolean point = false;
        for (int i = 0; i < new_id.length(); i++) {
        	if (point && new_id.charAt(i) == '.') {
        		continue;
        	} else if (!point && new_id.charAt(i) == '.') {
        		point = true;
        	} else {
        		point = false;
        	}
        	sb.append(new_id.charAt(i));     	
        }
        new_id = sb.toString();
        // 4단계 : 마침표(.)가 처음이나 끝에 위치한다면 제거
        if (new_id.length() > 0 && new_id.charAt(0) == '.') new_id = new_id.substring(1);
        if (new_id.length() > 0 && new_id.charAt(new_id.length()-1) == '.') new_id = new_id.substring(0, new_id.length()-1);
        // 5단게 : 빈 문자열이라면, "a"를 대입
        if (new_id.length() == 0) new_id = "a";
        // 6단계 : 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거
        if (new_id.length() >= 16) new_id = new_id.substring(0, 15);
        // 마침표(.)가 끝에 위치한다면 제거
        if (new_id.length() > 0 && new_id.charAt(new_id.length()-1) == '.') new_id = new_id.substring(0, new_id.length()-1);
        // 7단계 : 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 추가
        if (new_id.length() <= 2) {
        	while (new_id.length() < 3) {
        		new_id += new_id.charAt(new_id.length()-1);
        	}
        }
        return new_id;
    }

}
