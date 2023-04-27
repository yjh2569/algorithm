package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14233_악덕_사장 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		// 이 문제의 마감기한의 단위를 시간으로 간주한다.
		// 어떤 일을 처리하면서, 시간이 흐른다는 것을 주의하면 된다.
		// 예를 들어, 7 5 10 이라는 세 일이 주어진 경우, 만약 첫 번째 일을 6시간 일을 하게 하면 첫 번째 일을 하긴 했지만
		// 그만큼 시간이 지났기 때문에 마감 기한이 5인 일은 수행할 수 없다.
		// 일을 하는 시간을 k라고 하면, 마감 기한인 7인 일을 하면 마감 기한이 5인 일은 5-k로 줄어들고, 
		// 또 일을 하면 마감 기한이 10인 일은 10-2*k로 줄어든다.
		// 따라서, 7-k >= 0, (5-k)-k >= 0, (10-k-k)-k >= 0을 모두 만족하는 가장 큰 k를 찾는 문제와 같다.
		// 이때, 일을 처리하는 순서는 상관없기 때문에, k를 최대로 하려면 마감 기한이 짧은 일을 우선 처리하는 것이 좋다.
		// 위 과정을 정리하면, A를 정렬한 뒤 앞에서부터 차례대로 A[i]/(i+1)의 최솟값을 구해나가면 된다.
		Arrays.sort(A);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			min = Math.min(min, A[i]/(i+1));
		}
		System.out.println(min);
	}

}
