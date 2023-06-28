package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_20117_호반우_상인의_이상한_품질_계산법 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// greedy algorithm을 적용
		// 가장 많은 이익을 얻기 위해서는, i번째와 (N+1-i)번째 호반우(1<=i<=N/2)를 묶는다.
		// 그러면 해당 묶음의 중앙값이 (N+1-i)번째 호반우의 품질이 된다.
		// N이 홀수인 경우 가운데 있는 호반우를 하나의 묶음으로 간주한다.
		// 그렇게 하면 품질의 합을 최대로 할 수 있다.
		Arrays.sort(arr);
		int sum = 0;
		for (int i = (N+1)/2; i < N; i++) {
			sum += arr[i];
		}
		if (N%2 == 0) {
			System.out.println(sum*2);
		} else {
			System.out.println(sum*2 + arr[N/2]);
		}
	}

}
