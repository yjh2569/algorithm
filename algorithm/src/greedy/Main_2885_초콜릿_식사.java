package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2885_초콜릿_식사 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		// K보다 크거나 같은 가장 작은 2의 제곱수를 구한다.
		int size = 1; // 구매해야 하는 가장 작은 초콜릿의 크기
		int cnt = 0; // size가 2의 몇 제곱인지를 나타내는 수
		while (K > size) {
			size *= 2;
			cnt++;
		}
		// i번째 원소는 size/(2^i)를 의미한다.
		// 즉, size만한 초콜릿을, 메번 크기가 가장 작은 초콜릿마다 i번 쪼갰을 때 가장 작은 초콜릿의 크기를 말한다.
		int[] cnts = new int[cnt+1];
		cnts[0] = size;
		for (int i = 1; i <= cnt; i++) {
			cnts[i] = cnts[i-1]/2;
		}
		int idx = 0; // 초콜릿을 쪼개는 최소 횟수
		// K를 2의 제곱수를 이용해 표현한다 하면, 2진수로 변환한 뒤 
		// 해당 2진수 내 가장 오른쪽에 있는 1에 도달하기 위해서는 몇 번 초콜릿을 쪼개야 하는지를 구하면 된다.
		while (K > 0) {
			if (K < cnts[idx]) {
				idx++;
				continue;
			}
			K -= cnts[idx++];
		}
		System.out.println(size+" "+(idx-1));
	}

}
