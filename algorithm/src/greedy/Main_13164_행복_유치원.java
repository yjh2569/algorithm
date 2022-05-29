package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13164_행복_유치원 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 조의 개수가 하나일 때는 비용이 오른쪽 끝 값에서 왼쪽 끝 값을 뺀 결과이고,
		// 이후 조의 개수가 하나씩 늘어날 때마다 드는 비용을 최소화하려면 인접한 두 학생의 키 차이가 최대인 경우에 대해 분리해주면 된다.
		// 따라서, i+1번째 학생과 i번째 학생의 키 차이를 i번째 원소로 가지는 배열을 diff라 하고,
		// 이를 정렬한 뒤 가장 오른쪽, 즉 가장 큰 값부터 K-1개를 res에서 빼주면 비용의 최소값을 효율적으로 얻을 수 있다.
		int res = arr[N-1] - arr[0];
		int[] diff = new int[N-1];
		for (int i = 0; i < N-1; i++) {
			diff[i] = arr[i+1] - arr[i];
		}
		Arrays.sort(diff);
		for (int i = 0; i < K-1; i++) {
			res -= diff[N-2-i];
		}
		System.out.println(res);
		
	}

}
