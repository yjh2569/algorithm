package math;

public class Solution_lv2_n진수_게임 {

	public static void main(String[] args) {
		String result = solution(16, 16, 2, 2);
		System.out.println(result);
	}
	
	public static String solution(int n, int t, int m, int p) {
		// 결과를 기록하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        // 현재 말하고 있는 수
        int cur = 0;
        // 현재 cur을 말하기 시작하는 사람의 순서
        int start = 0;
        // 계산의 편의를 위해 순서가 0번째부터 시작한다고 가정
        p -= 1;
        // 미리 구할 숫자의 개수가 t개가 될 때까지 반복
        while (sb.length() < t) {
        	// cur을 n진수로 바꾼다.
        	String converted = convertToNaryNum(n, cur);
        	// cur을 말하는 도중 튜브가 말할 순서가 오는 경우
        	if ((p-start+m)%m < converted.length()) {
        		sb.append(converted.charAt((p-start+m)%m));
        	}
        	// 다음 숫자 말할 준비
        	start = (start + converted.length())%m;
        	cur++;
        }
        return sb.toString();
    }
	// cur을 n진수로 변환하는 함수
	private static String convertToNaryNum(int n, int cur) {
		// 변환 결과를 저장하는 StringBuilder
		StringBuilder num = new StringBuilder();
		// cur이 0인 경우 바로 0을 반환
		if (cur == 0) return "0";
		// 낮은 자리수부터 차례대로 구한다.
		while (cur > 0) {
			if (cur%n >= 10) {
				num.append((char)('A' + cur%n - 10));
			} else {
				num.append(cur%n);				
			}
			cur /= n;
		}
		return num.reverse().toString();
	}
}
