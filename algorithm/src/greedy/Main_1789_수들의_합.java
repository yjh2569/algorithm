package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1789_수들의_합 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		// 합이 S가 되는 서로 다른 자연수들의 개수를 최대화하려면 1부터 차례대로 더해야 한다.
		// 따라서 1부터 차례대로 더하다가 S를 넘어가기 바로 직전에 중단하는 방식으로 N을 결정한다.
		// 예를 들어, S=200인 경우 1+2+...+19=190인데, 여기서 10을 추가할 수는 없으므로 19에 10을 더하는 방식으로
		// 중복을 제거한다. 
		// 즉, n(n+1)/2 <= S < (n+1)(n+2)/2를 만족하는 n을 찾는 문제와 같다.
		// 왼쪽 부등식을 풀면 n^2+n-2*S <= 0이므로 n >= (-1+Math.sqrt(1+8*S))/2라는 결론을 얻는다.
		// 만약 오른쪽 식의 값이 정수로 나온다면 그 값을 N이라 하고, 
		// 그렇지 않으면 중복을 제거하기 위해 오른쪽 식의 값을 내림한 결과를 N으로 한다.
		int N = (int)((-1+Math.sqrt(1+8*S))/2);
		System.out.println(N);
	}

}
