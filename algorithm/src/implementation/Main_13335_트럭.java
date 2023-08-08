package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13335_트럭 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] weights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		// 큐를 활용해 해결한다.
		// 다리를 큐로 간주하고, 트럭과 빈 공간에 대한 무게를 큐에 넣는다.
		// 빈 공간의 무게(0)을 넣는 것은, 트럭이 다리를 건너는데 걸리는 시간을 고려하기 위함이다.
		// 처음에는 0 W개를 넣어, 후에 트럭이 다리에 진입하면 해당 트럭이 W초 후에 다리를 지날 수 있도록 한다.
		// 매 초가 지날 때마다 큐에서 무게를 넣은 순서대로 꺼내 트럭이 지나가는 과정을 구현한다.
		// 그 후 각 트럭에 대해 트럭이 다리에 진입했을 때 다리 하중을 넘지 않는 경우 트럭을 다리에 진입시키고,(트럭의 무게를 큐에 넣고)
		// 다리 하중을 넘는 경우 트럭을 진입시키지 않는다(빈 공간의 무게를 큐에 넣는다).
		// 위 과정을 모든 트럭이 다리에 진입한 뒤면서, 다리에 가해지는 하중이 0일 때까지 반복한다.(즉, 모든 트럭이 다리를 지난 후)
		Queue<Integer> q = new LinkedList<>();
		int curWeight = 0; // 현재 다리에 가해지는 하중
		// 초기화
		for (int i = 0; i < W; i++) {
			q.offer(0);
		}
		int curIdx = 0; // 현재 다리에 진입할 트럭 번호
		int time = 0; // 현재 시간
		while (!q.isEmpty()) {
			curWeight -= q.poll(); // 다리 내 모든 트럭 이동
			if (curIdx != N) { // 다리를 지나갈 트럭이 있는 경우
				// 현재 다리를 지나갈 트럭이 다리에 진입했을 때 하중 제한에 안 걸리는 경우
				if (curWeight + weights[curIdx] <= L) {
					// 트럭을 다리에 진입시킨다.
					curWeight += weights[curIdx]; 
					q.offer(weights[curIdx++]);
				}
				// 하중 제한에 걸리면 트럭을 새로 다리에 진입시키지 않는다.
				else q.offer(0);
			}
			time++;
			// 모든 트럭이 다리를 지난 경우
			if (curIdx == N && curWeight == 0) break;
		}
		System.out.println(time);	
	}
}
