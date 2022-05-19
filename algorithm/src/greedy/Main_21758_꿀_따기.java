package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_21758_꿀_따기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0; // 누적 합
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		int max = 0; // 꿀 획득량의 최댓값
		// 크게 세 가지 경우로 나눌 수 있다.
		// 1. 벌통이 오른쪽에 있고 벌 두 마리가 모두 왼쪽에 있는 경우
		// 꿀을 최대한 많이 얻으려면 벌통은 오른쪽 끝에 있어야 하고, 벌 한 마리는 왼쪽 끝에 있어야 한다. 
		// 나머지 한 마리를 어디 배치하느냐에 따라 얻을 수 있는 꿀의 양이 달라진다. 
		// 단, 나머지 벌이 왼쪽에서 2번째 자리에 위치해도 항상 최댓값을 얻을 수 없음에 유의해야 한다.
		int temp = sum - arr[0];
		for (int i = 1; i < N-1; i++) {
			temp -= arr[i];
			max = Math.max(max, sum - arr[0] - arr[i] + temp);
		}
		// 2. 벌통이 왼쪽에 있고 벌 두 마리가 모두 오른쪽에 있는 경우
		// 1번 경우와 마찬가지로 벌통이 왼쪽 끝에 있고 벌 한 마리가 오른쪽 끝에 있어야 한다.
		temp = sum - arr[N-1];
		for (int i = N-2; i >= 1; i--) {
			temp -= arr[i];
			max = Math.max(max, sum - arr[N-1] - arr[i] + temp);
		}
		// 3. 벌통이 벌 두 마리 사이에 있는 경우
		// 이때는 벌 두 마리가 각각 양끝에 있어야 한다. 벌통의 위치에 따라 꿀 획득량이 달라지므로, 벌통 위치를 조절한다.
		for (int i = 1; i < N-1; i++) {
			max = Math.max(max, sum - arr[0] - arr[N-1] + arr[i]);
		}
		System.out.println(max);
	}

}
