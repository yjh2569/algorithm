package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1322_X와_K {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long X = Integer.parseInt(st.nextToken());
		long K = Integer.parseInt(st.nextToken());
		long res = 0; // 식을 만족하는 K번째로 작은 수
		// X + Y = X | Y를 만족하려면, X를 이진수로 표현했을 때 Y는 자릿수가 0인 자릿수에만 1이면 된다.
		// ex) X = 1011 라면, Y는 ...___0_00이면 된다.(X와 Y를 모두 이진수로 표현함)(_에는 0 아니면 1이 들어가면 된다.)
		// K번째로 작은 수를 구하려면 K를 이진수로 표현하고, _에 해당 이진수를 채워넣으면 된다.
		// 아래는 이와 같은 방법을 구현한 코드다.
		int n = -1; // 현재 자릿수
		while (K > 0) {
			n++;
			// X의 자릿수가 1인 경우
			if ((X & (1l << n)) != 0) continue;
			// 이진수로 표현한 K를 _에 넣는 코드
			if (K%2 == 1) {
				res += (1l << n);
				K -= 1;
			}
			K /= 2;
		}
		System.out.println(res);
	}	

}
