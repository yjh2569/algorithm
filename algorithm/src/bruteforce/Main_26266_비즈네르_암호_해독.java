package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_26266_비즈네르_암호_해독 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String original = br.readLine();
		String encrypt = br.readLine();
		int N = original.length(); // 문자열 길이
		int[] arr = new int[N]; // 암호화된 문자열과 기존 문자열 내 각 문자의 격차
		for (int i = 0; i < N; i++) {
			arr[i] = (encrypt.charAt(i) - original.charAt(i) + 26)%26;
		}
		// N의 약수를 길이로 하는 키 중 가장 짧은 키를 찾는다.
		outer: for (int l = 1; l <= N; l++) {
			if (N%l != 0) continue; // N의 약수가 길이가 되는 경우만 고려
			// 키 후보가 실제로 키가 될 수 있는지 확인한다.
			// i번째 문자 격차가 j*l+i번째 문자 격차와 동일한지 확인한다.
			for (int i = 0; i < l; i++) {
				int compare = arr[i];
				for (int j = 1; j < N/l; j++) {
					if (compare != arr[j*l+i]) continue outer;
				}
			}
			// 키 후보가 실제로 키가 되는 경우
			// l이 작을 때부터 진행하므로 여기에 오면 가장 짧은 키를 찾은 것이나 마찬가지다.
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < l; i++) {
				sb.append((char)((arr[i]+25)%26+'A'));
			}
			System.out.println(sb.toString());
			return;
		}
	}

}
