package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1099_알_수_없는_문장 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String sentence = br.readLine();
		int L = sentence.length();
		int N = Integer.parseInt(br.readLine());
		// 각 단어 저장을 위한 배열
		String[] words = new String[N];
		// 각 단어에 있는 알파벳 개수를 세기 위한 배열
		int[][] cnts = new int[N][26];
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			words[i] = word;
			for (int j = 0; j < word.length(); j++) {
				char c = word.charAt(j);
				cnts[i][c - 'a']++;
			}
		}
		// dynamic programming을 적용한다.
		// dp[l]은 l번째 문자까지로 이루어진 문장의 비용의 최솟값을 의미한다.
		int[] dp = new int[L+1];
		// 초기화
		Arrays.fill(dp, 10000);
		dp[0] = 0;
		// dp[1]부터 dp[L]까지 bottom-up으로 구한다.
		for (int l = 1; l <= L; l++) {
			// l번째 문자까지의 문장에 있는 알파벳 개수를 센다.
			int[] cs = new int[26];
			for (int j = 0; j < l; j++) {
				cs[sentence.charAt(j) - 'a']++;
			}
			// (j+1)번째 문자부터 l번째 문자까지의 문자열이 주어진 단어 중 하나를 순서를 바꾼 결과인지를 확인하고,
			// 만약 그렇다면 원래 단어의 위치와 다른 위치에 있는 문자의 개수를 세어 비용을 계산한다.
			for (int j = 0; j < l; j++) {
				outer: for (int k = 0; k < N; k++) {
					// 각 단어에 대해 알파벳 개수를 통해 문자열이 해당 단어를 만들 수 있는지를 확인한다.
					for (int c = 0; c < 26; c++) {
						if (cnts[k][c] != cs[c]) continue outer;
					}
					// 원래 단어의 위치와 다른 위치에 있는 문자의 개수
					int cnt = 0;
					// 원래 단어
					String word = words[k];
					// 원래 단어의 위치와 다른 위치에 있는 문자의 개수를 센다.
					for (int m = 0; m < word.length(); m++) {
						if (sentence.charAt(j+m) != word.charAt(m)) cnt++;
					}
					// j번째 문자까지 문장의 최소 비용에 새 단어의 비용을 더한 결과와 기존의 최소 비용을 비교해 수정한다.
					dp[l] = Math.min(dp[l], dp[j]+cnt);
				}
				// 문자열의 앞에 있는 문자부터 하나씩 제거
				cs[sentence.charAt(j) - 'a']--;
			}
		}
		// dp[L]이 10000인 경우 문장을 해석할 수 없음을 의미한다.
		System.out.println(dp[L] == 10000 ? -1 : dp[L]);
	}

}
