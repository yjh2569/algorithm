package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_15482_한글_LCS {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1 = br.readLine();
		String s2 = br.readLine();
		// dp[i][j]는 s1의 i번째 문자까지의 부분 문자열과, s2의 j번째 문자까지의 부분 문자열의 LCS 길이
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		// 일반적인 LCS를 구하는 방법을 사용한다.
		// java의 char 자료형은 2바이트로, 한글도 이에 포함되기 때문에 charAt 메소드를 활용하면 된다. 
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				// s1의 i번째 문자와 s2의 j번째 문자가 같은 경우
				// s1(0, i)와 s2(0, j)의 LCS에 해당 문자를 추가한다.
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
				// 그렇지 않다면
				// s1(0, i)와 s2(0, j+1)의 LCS, s1(0, i+1)와 s2(0, j)의 LCS 중 더 긴 것을 채택한다.
				} else {					
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.println(dp[s1.length()][s2.length()]);
	}
}
