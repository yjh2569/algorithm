package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_13019_A를_B로 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		int N = A.length();
		// 우선, A를 B로 바꿀 수 있는지 확인하기 위해 A와 B에 있는 알파벳의 개수를 비교한다.
		int[] count1 = new int[26];
		int[] count2 = new int[26];
		for (int i = 0; i < N; i++) {
			count1[A.charAt(i)-'A']++;
			count2[B.charAt(i)-'A']++;
		}
		// 만약 A와 B에 있는 각 알파벳의 개수가 다르다면, A를 B로 절대 바꿀 수 없다.
		for (int i = 0; i < 26; i++) {
			if (count1[i] != count2[i]) {
				System.out.println(-1);
				return;
			}
		}
		// A와 B에 있는 각 알파벳의 개수가 같으면, 항상 A를 B로 바꿀 수 있다.
		// greedy algorithm을 이용한다.
		// A와 B 각각 문자열 맨 끝에 포인터를 두고, A의 포인터에 있는 문자와 B의 포인터에 있는 문자를 비교한다.
		// 만약 두 문자가 같으면 굳이 A의 포인터에 있는 문자를 맨 앞으로 보낼 필요가 없으므로 두 포인터를 각각 1 감소시킨다.
		// 그렇지 않으면 어찌 되었던 간에 A의 포인터에 있는 문자를 맨 앞으로 보낼 필요가 있다.
		// 따라서 연산 횟수를 1 증가시키고, A의 포인터를 1 감소시킨다.
		// 그러나 이것이 A의 포인터에 있는 문자를 바로 맨 앞으로 보낸다는 의미는 아니다.
		// B의 문자열에 따라 앞에 있는 문자를 먼저 맨 앞으로 보낼 수 있다.
		// 그렇게 해도 A의 포인터에 있는 문자의 위치는 변하지 않는다.
		// 따라서 A의 문자열에 있는 문자 중 연산을 수행할 문자들만 뒤에서부터 찾아나가는 것이다.
		int cnt = 0; // 연산 횟수의 최솟값
		int b = N-1; // B의 포인터
		// A의 포인터
		// 항상 A의 포인터는 1씩 감소하므로 for문으로 표현
		for (int a = N-1; a >= 0; a--) {
			if (A.charAt(a) == B.charAt(b)) b--;
			else cnt++;
		}
		System.out.println(cnt);
	}

}
