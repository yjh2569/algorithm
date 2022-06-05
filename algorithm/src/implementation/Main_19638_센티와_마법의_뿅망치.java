package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_19638_센티와_마법의_뿅망치 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		// 우선 순위 큐에 거인들의 키를 넣어 가장 키가 큰 거인들을 빠르게 찾는다.
		PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		StringBuilder sb = new StringBuilder();
		// 뿅망치 사용 횟수에 따라 순회
		for (int t = 0; t <= T; t++) {
			// 가장 키가 큰 거인을 찾는다.
			int cur = pq.poll();
			// 가장 키가 큰 거인의 키가 센티보다 작으면 YES와 t 출력
			if (cur < H) {
				sb.append("YES\n").append(t);
				System.out.println(sb.toString());
				System.exit(0);
			}
			// 뿅망치 사용 횟수가 T면 break
			if (t == T) {
				pq.offer(cur);
				break;
			}
			// 뿅망치 사용
			// 단, 가장 키가 큰 거인의 키가 1이면 더 이상 뿅망치 사용이 무의미하므로 break
			if (cur > 1) pq.offer(cur/2);
			else {
				pq.offer(1);
				break;
			}
		}
		// 뿅망치를 T번 사용해도 가장 키가 큰 거인의 키가 센티보다 크거나 같은 경우
		sb.append("NO\n").append(pq.poll());
		System.out.println(sb.toString());
	}

}
