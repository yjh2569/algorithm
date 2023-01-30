package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2613_숫자구슬 {
	static int N, M;
	static int[] nums;
	static int min;
	static int[] cnts;
	static int maxVal;
	static int sum;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		sum = 0; // 구슬들에 적힌 숫자들의 합
		maxVal = 0; // 구슬들에 적힌 숫자 중 가장 큰 값
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			sum += nums[i];
			maxVal = Math.max(maxVal, nums[i]);
		}
		min = Integer.MAX_VALUE; // 각 그룹의 합의 최댓값을 최소로 만들었을 때 그 최댓값
		cnts = new int[M]; // 최댓값이 min이 될 때 각 그룹에 몇 개의 구슬이 있는지를 기록하는 배열
		// 매개변수 탐색을 통해 min과 cnts를 구한다. 
		binarySearch();
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(min).append("\n");
		for (int i = 0; i < M; i++) {
			sb.append(cnts[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 매개변수 탐색을 활용해 각 그룹의 합의 최댓값의 최소가 될 때를 구하는 함수 
	private static void binarySearch() {
		// 그룹의 합의 최댓값의 경계를 기준으로 이분 탐색을 진행한다.
		// 가능한 최댓값은 maxVal ~ sum까지다. 이때 아래 이분 탐색 특성상 r을 sum으로 잡으면 
		// mid가 r에 도달 불가능한 문제가 있어 r을 sum+1로 잡았다. 
		int l = maxVal, r = sum+1;
		while (l < r) {
			int mid = (l+r)/2;
			// 모든 그룹의 합이 mid를 넘지 않을 때 그룹을 나눈 결과를 계산한다.
			int[] result = divide(mid);
			// mid의 값이 충분히 커서 M개의 그룹으로 나눌 수 있는 경우
			if (result.length <= M) {
				// 최댓값의 최소를 갱신
				if (result.length == M && min > mid) {
					min = mid;
					cnts = result;
				}
				r = mid;
			// mid의 값이 작아서 M개의 그룹으로 나눌 수 없는 경우
			} else {
				l = mid+1;
			}
		}
	}
	// 합이 mid를 넘지 못할 때 각 그룹에 속한 구슬의 개수를 구하는 함수
	private static int[] divide(int mid) {
		// 그룹의 개수를 알 수 없어 ArrayList에 각 그룹에 속한 구슬의 개수를 저장
		ArrayList<Integer> counts = new ArrayList<>();
		int count = 0; // 현재 그룹에 속한 구슬의 개수
		int sum = 0; // 현재 그룹에 속한 구슬에 적힌 숫자들의 합
		for (int i = 0; i < N; i++) {
			// 숫자들의 합이 mid를 초과하거나, 그룹의 개수가 부족한 경우
			if (sum + nums[i] > mid || counts.size() + N - i < M) {
				counts.add(count);
				count = 0;
				sum = 0;
			}
			count++;
			sum += nums[i];
		}
		// 위 순회에서 마지막 그룹은 counts에 포함되지 않기 때문에 이를 포함시킨다.
		counts.add(count);
		// counts를 정수 배열로 바꾼다.
		int[] res = new int[counts.size()];
		for (int i = 0; i < counts.size(); i++) {
			res[i] = counts.get(i);
		}
		return res;
	}

}
