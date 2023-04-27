package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25758_유전자_조합 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] strs = new String[N]; // 1세대 유전자를 저장하는 배열
		int[] starts = new int[26]; // 각 알파벳에 대해 1세대 유전자의 첫 번째 형질로 몇 번 나왔는지를 저장하는 배열 
		int[] ends = new int[26]; // 각 알파벳에 대해 1세대 유전자의 두 번째 형질로 몇 번 나왔는지를 저장하는 배열
		for (int i = 0; i < N; i++) {
			String s = st.nextToken();
			strs[i] = s;
			starts[s.charAt(0)-'A']++;
			ends[s.charAt(1)-'A']++;
		}
		// 각 알파벳에 대해 2세대 유전자의 표현형으로 가능한 알파벳인지를 나타내는 배열
		boolean[] check = new boolean[26];
		// 각 1세대 유전자에 대해, 다음과 같은 프로세스를 진행한다.
		// 유전자의 첫 번째 형질보다 사전순으로 앞에 있는 문자가 유전자의 두 번째 형질에서 나타난 적이 있는지를 조사한다.
		// 이때, 자기 자신의 두 번째 형질은 무시해야 하므로 두 번째 형질과 같은 문자에 대해서는 두 번 이상 나타난 적이 있는지 조사한다.
		// 만약 그렇다면, 해당 유전자는 사전순으로 앞에 있는 문자가 두 번째 형질에서 나타나는 다른 유전자와 조합함으로써 
		// 유전자의 첫 번째 형질이 2세대 유전자의 표현형이 될 수 있다.
		// 유전자의 두 번째 형질에 대해서도 같은 방식으로 유전자의 첫 번째 형질에서 사전순으로 앞에 있는 문자가 나타난 적이 있는지 조사한다.
		for (int i = 0; i < N; i++) {
			String str = strs[i];
			int start = str.charAt(0)-'A';
			int end = str.charAt(1)-'A';
			for (int j = 0; j <= start; j++) {
				if ((end == j && ends[j] > 1) || (end != j && ends[j] > 0)) check[start] = true;
			}
			for (int j = 0; j <= end; j++) {
				if ((start == j && starts[j] > 1) || (start != j && starts[j] > 0)) check[end] = true;
			}
		}
		int cnt = 0; // 2세대 유전자의 표현형으로 가능한 문자의 개수
		for (int i = 0; i < 26; i++) {
			if (check[i]) cnt++;
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for (int i = 0; i < 26; i++) {
			if (check[i]) sb.append((char)(i+'A')).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
