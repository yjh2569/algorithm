package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2141_우체국 {
	// 마을의 위치와 인구 수를 저장하는 클래스
	static class Village implements Comparable<Village> {
		int idx; long people;
		public Village(int idx, long people) {
			this.idx = idx;
			this.people = people;
		}
		public int compareTo(Village c) {
			return Integer.compare(this.idx, c.idx);
		}		
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Village[] Villages = new Village[N];
		// 인구 수의 총합 * (-1)을 저장하기 위한 변수
		long sumGrad = 0l;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			long people = Long.parseLong(st.nextToken());
			Villages[i] = new Village(idx, people);
			sumGrad -= people;
		}
		// greedy algorithm을 적용한다.
		// 좌표의 배열 X와 인구 수의 배열 A가 주어졌을 떄(X가 정렬되어 있다고 가정), 
		// 특정 지점 x에서 각 사람들까지의 거리의 총합은
		// y = |x-X[0]|*A[0] + |x-X[1]|*A[1] + ... |x-X[N-1]|*A[N-1]이고,
		// 이를 함수로 간주해 x의 범위에 따라 나누어 절댓값을 풀면 
		// x가 X[0]보다 작을 때는 기울기가 -(A[0] + A[1] + ... + A[N-1])이다.(즉, sumGrad)
		// 이때 x가 X[0]보다 커지면 기울기가 A[0] - (A[1] + ... + A[N-1]) = sumGrad + 2*A[0]이 된다.
		// x가 x[1]보다 커지면 기울기가 A[0] + A[1] - (A[2] + ... + A[N-1]) = 
		// sumGrad + 2*(A[0] + A[1])이 된다.
		// 이 과정을 계속 반복하다 보면, 이 기울기가 처음에는 음수였다가 어느 순간 0 이상이 되는 순간이 있는데,
		// 그 순간이 바로 함수값가 최솟값이 되는 순간, 즉 사람들까지의 거리의 총합이 최소가 되는 순간이다.
		// 이때, 문제에서 주어지는 X는 정렬되어 있다는 보장이 없으므로, 
		// 우선 Village 클래스를 통해 입력을 받은 후, 좌표를 기준으로 정렬을 한 것이다.
		Arrays.sort(Villages);
		int idx = Villages[0].idx;
		for (int i = 0; i < N; i++) {
			sumGrad += Villages[i].people * 2;
			if (sumGrad >= 0) {
				idx = Villages[i].idx;
				break;
			}
		}
		System.out.println(idx);
	}

}
