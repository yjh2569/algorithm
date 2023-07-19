package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3078_좋은_친구 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 입력으로 들어온 이름들의 길이를 순서대로 꺼내기 위한 큐
		Queue<Integer> inputs = new LinkedList<>();
		// 등수가 i등~(i+K)등인 학생들의 이름 길이를 모아놓은 큐
		Queue<Integer> cur = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			inputs.offer(br.readLine().length());
		}
		// cur 안에 이름의 길이가 i인 학생의 수를 counts[i]에 저장하는 배열
		long[] counts = new long[21];
		// 1등부터 (K+1)등까지의 이름의 길이를 inputs에서 꺼내서 counts에 반영하고,
		// cur에 이름의 길이를 넣는다.
		for (int k = 0; k <= K; k++) {
			int l = inputs.poll();
			counts[l]++;
			cur.offer(l);
		}
		long sum = 0; // 좋은 친구 쌍의 수
		// 위 과정을 거치면 counts[n]에 이름의 길이가 n인 학생들 중, 1등~(K+1)등 사이인 학생 수가 저장된다.
		// 여기서 어떤 학생 쌍을 뽑아도 좋은 친구 쌍이 되므로,
		// 좋은 친구 쌍의 수는 counts[n]_C_2 = counts[n]*(counts[n]-1)/2가 된다.
		for (int n = 1; n <= 20; n++) {
			if (counts[n] == 0) continue;
			sum += counts[n]*(counts[n]-1)/2l;
		}
		// cur에서 등수가 가장 높은 학생을 빼고, 등수가 가장 낮은 학생보다 한 등수 낮은 학생을 넣는다.
		// 그러면 새롭게 생길 수 있는 좋은 친구 쌍은 새롭게 cur에 들어온 학생과 이름의 길이가 같은 학생 수와 같다.
		for (int k = K+1; k < N; k++) {
			int l = inputs.poll();
			int out = cur.poll();
			counts[out]--;
			sum += counts[l]++;
			cur.offer(l);
		}
		System.out.println(sum);
	}

}
