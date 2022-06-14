package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_13975_파일_합치기_3 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine());
			// 파일 합치는 비용을 최소화하려면 크기가 작은 파일들부터 합쳐나가야 한다.
			// 합쳐진 파일들의 각 크기가 나중에 그 파일을 꺼낼 때 계속해서 더해지기 때문이다.
			PriorityQueue<Long> pq = new PriorityQueue<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				pq.offer(Long.parseLong(st.nextToken()));
			}
			// 최소 비용
			long res = 0;
			// 두 개의 크기가 작은 파일을 꺼내 하나로 합치는 과정을 파일이 1개 남을 때까지 반복한다.
			while (pq.size() > 1) {
				long a = pq.poll();
				long b = pq.poll();
				pq.offer(a+b);
				res += a+b;
			}
			sb.append(res).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}

}
