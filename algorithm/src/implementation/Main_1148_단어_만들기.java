package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1148_단어_만들기 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 각 단어마다 각 알파벳이 몇 번 쓰였는지를 나타내는 배열을 저장하는 ArrayList
		ArrayList<int[]> numsOfAlphabets = new ArrayList<>();
		while (true) {
			String s = br.readLine();
			if ("-".equals(s)) break; // 사전 입력이 끝나는 경우
			int[] numOfAlphabets = new int[26]; // 현재 단어에서 각 알파벳이 몇 번 쓰였는지를 나타내는 배열
			for (char c : s.toCharArray()) {
				numOfAlphabets[c-'A']++;
			}
			numsOfAlphabets.add(numOfAlphabets);
		}
		StringBuilder result = new StringBuilder(); // 최종 결과를 출력하기 위한 StringBuilder
		while (true) {
			String s = br.readLine();
			if ("#".equals(s)) break; // 입력의 맨 끝
			int[] numOfAlphabets = new int[26]; // 퍼즐판에서 각 알파벳이 몇 개 있는지를 나타내는 배열
			for (char c : s.toCharArray()) {
				numOfAlphabets[c-'A']++;
			}
			// 사전에 있는 단어 중, 퍼즐판에서 만들 수 있는 단어들만 추렸을 때
			// 각 알파벳이 포함된 단어가 몇 개인지를 나타내는 배열
			int[] alphabets = new int[26];
			// 사전에 있는 단어에 대해 우선 퍼즐판에서 만들 수 있는 단어인지 확인한 후,
			// 해당 단어에 있는 알파벳을 골라 alphabets에 갱신한다.
			for (int[] nums : numsOfAlphabets) {
				boolean check = true;
				for (int i = 0; i < 26; i++) {
					if (nums[i] > numOfAlphabets[i]) {
						check = false;
						break;
					}
				}
				if (!check) continue; // 퍼즐판에서 만들 수 없는 단어인 경우
				// 단어와 퍼즐판에 있는 알파벳이면 해당 알파벳을 정중앙에 둠으로써 그 단어를 만들 수 있다. 
				for (int i = 0; i < 26; i++) {
					if (nums[i] > 0 && numOfAlphabets[i] > 0) {
						alphabets[i]++;
					}
				}
			}
			int min = Integer.MAX_VALUE; // 만들 수 있는 단어의 개수의 최솟값
			int max = 0; // 만들 수 있는 단어의 개수의 최댓값
			// 만들 수 있는 단어의 개수가 최소일 때 정중앙에 있는 알파벳
			// 여러 개일 수 있으므로 StringBuilder로 정의
			StringBuilder minAlphabets = new StringBuilder();
			// 만들 수 있는 단어의 개수가 최대일 때 정중앙에 있는 알파벳
			StringBuilder maxAlphabets = new StringBuilder();
			// 각 알파벳에 대한 alphabets 배열의 값을 확인하면서 최소/최대를 갱신
			for (int i = 0; i < 26; i++) {
				if (numOfAlphabets[i] == 0) continue; // 퍼즐판에 나오지 않은 문자인 경우
				if (min > alphabets[i]) {
					min = alphabets[i];
					minAlphabets = new StringBuilder();
					minAlphabets.append((char)(i+'A'));
				} else if (min == alphabets[i]) {
					minAlphabets.append((char)(i+'A'));					
				}
				if (max < alphabets[i]) {
					max = alphabets[i];
					maxAlphabets = new StringBuilder();
					maxAlphabets.append((char)(i+'A'));
				} else if (max == alphabets[i]) {
					maxAlphabets.append((char)(i+'A'));
				}
			}
			String blank = " ";
			result.append(minAlphabets).append(blank).append(min).append(blank)
				.append(maxAlphabets).append(blank).append(max).append("\n");
		}
		if (result.length() > 0) result.setLength(result.length()-1);
		System.out.println(result.toString());
	}

}
