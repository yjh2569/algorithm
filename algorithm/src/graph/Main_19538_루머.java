package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19538_루머 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int u = 1; u <= N; u++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = 0;
			while ((v = Integer.parseInt(st.nextToken())) != 0) {
				graph.get(u).add(v);
			}
		}
		int M = Integer.parseInt(br.readLine());
		boolean[] trust = new boolean[N+1]; // 현재 루머를 믿고 있는지를 나타내는 배열
		int[] res = new int[N+1]; // 루머를 믿은 시각
		Arrays.fill(res, -1);
		Queue<Integer> q = new LinkedList<>(); // BFS를 위한 큐
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 루머 최초 유포자를 입력으로 받는다.
		for (int i = 0; i < M; i++) {
			int u = Integer.parseInt(st.nextToken());
			trust[u] = true;
			res[u] = 0;
			q.offer(u);
		}
		int time = 1; // 현재 시각
		// 루머를 새롭게 믿은 사람들을 저장하는 큐
		// 바로 루머를 믿었다고 trust 배열에 반영하면 같은 시각의 다른 사람의 결과에 영향을 끼칠 수 있다.
		Queue<Integer> newTrust = new LinkedList<>();
		// BFS 로직 수행
		while (!q.isEmpty()) {
			int qLen = q.size();
			while (qLen > 0) {
				int u = q.poll();
				for (int v : graph.get(u)) {
					if (trust[v]) continue; // 이미 루머를 믿은 사람이면 루머를 퍼뜨릴 필요가 없다.
					int cnt = 0; // v의 주변인 중 루머를 믿는 사람
					int K = graph.get(v).size(); // v의 주변인의 수
					for (int w : graph.get(v)) {
						if (trust[w]) cnt++;
						if (cnt >= (K+1)/2) break; // v의 주변인 중 과반수가 루머를 믿는 경우
					}
					// v가 루머를 믿게 되는 경우
					if (cnt >= (K+1)/2) {
						newTrust.offer(v);
						q.offer(v);
						res[v] = time;
					}
				}
				qLen--;
			}
			// 새롭게 루머를 믿은 사람들을 trust 배열에 반영
			while (!newTrust.isEmpty()) {
				trust[newTrust.poll()] = true;
			}
			time++;
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(res[i]).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
