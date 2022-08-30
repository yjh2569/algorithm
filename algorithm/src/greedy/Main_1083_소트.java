package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1083_소트 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int S = Integer.parseInt(br.readLine());
		// 스왑 시작 인덱스와 스왑 끝 인덱스
		int fromIdx = 0, toIdx = 0;
		// 최댓값 저장용 변수
		int max = 0;
		// 스왑을 위한 변수
		int temp = 0;
		// N = 1일 때는 스왑이 불가능하다.
		// 스왑 횟수를 다 쓸 때까지 반복
		while (N > 1 && S > 0) {
			// greedy algorithm을 적용한다.
			// 우선 i와 j의 차이가 S보다 작거나 같고(스왑 횟수가 충분하면서)
			// i가 최대한 작으면서, arr[j]가 최대한 큰 경우를 찾는다.
			for (int i = 0; i < N-1; i++) {
				// 매변 max, fromIdx, toIdx를 초기화한다.
				max = arr[i]; fromIdx = i; toIdx = -1;
				// i보다 큰 j 중 arr[j]의 값이 arr[i]보다 큰 값을 가지면서 
				// j-i가 S보다 작거나 같아 스왑이 가능한 경우가 있는지 확인
				for (int j = i+1; j < N; j++) {
					if (max < arr[j] && j-i <= S) {
						toIdx = j;
						max = arr[j];
					}
				}
				// 스왑이 가능한 경우를 찾았을 때
				if (toIdx > 0) break;
			}
			// 내림차순으로 정렬이 완료된 경우
			if (toIdx == -1) break;
			// 큰 수(arr[toIdx])를 최대한 앞으로(arr[fromIdx]로) 보낸다.
			for (int i = toIdx; i > fromIdx; i--) {
				// 스왑 과정
				temp = arr[i];
				arr[i] = arr[i-1];
				arr[i-1] = temp;
				S--;
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
