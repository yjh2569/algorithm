package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1068_트리 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] parents = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			parents[i] = Integer.parseInt(st.nextToken());
		}
		int remove = Integer.parseInt(br.readLine());
		// 각 노드의 자식 노드들을 저장하는 ArrayList
		ArrayList<ArrayList<Integer>> children = new ArrayList<>();
		// 초기화
		for (int i = 0; i < N; i++) {
			children.add(new ArrayList<>());
		}
		// 각 노드의 자식 노드들을 찾는다.
		for (int i = 0; i < N; i++) {
			if (parents[i] == -1) continue;
			children.get(parents[i]).add(i);
		}
		// 각 노드가 제거할 노드를 제거한 후에도 남아있는지 나타내는 배열
		boolean[] exists = new boolean[N];
		Arrays.fill(exists, true);
		// BFS를 통해 노드를 제거했을 때 해당 노드가 루트 노드인 트리에 있는 노드 모두를 제거한다.
		Queue<Integer> q = new LinkedList<>();
		q.offer(remove);
		exists[remove] = false;
		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : children.get(u)) {
				exists[v] = false;
				q.offer(v);
			}
		}
		int res = 0; // 남아 있는 리프 노드의 개수
		// 각 노드가 리프 노드인지 확인한다.
		for (int i = 0; i < N; i++) {
			if (!exists[i]) continue; // 해당 노드가 있어야 한다.
			int cnt = 0; // 자식 노드의 수
			// 자식 노드 중 남아 있는 자식 노드를 센다.
			for (int v : children.get(i)) {
				if (exists[v]) cnt++;
			}
			// 자식 노드 중 남아 있는 자식 노드가 없으면 리프 노드다.
			if (cnt != 0) continue;
			res++;
		}
		System.out.println(res);
	}

}
