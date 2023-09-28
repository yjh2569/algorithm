package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_3178_코코스 {
	// 각 노드에 저장되는 문자와 이전 노드, 다음 노드들의 ArrayList를 저장하는 클래스
	static class Node {
		char c;
		Node prev;
		ArrayList<Node> next;
		public Node(char c) {
			this.c = c;
			this.next = new ArrayList<>();
		}
		public void setPrev(Node prev) {
			this.prev = prev;
		}
		public void addNext(Node next) {
			this.next.add(next);
		}
		// 두 노드가 같은지를 비교할 때는 해당 노드에 있는 문자와 이전 노드가 같은지를 확인한다.
		public boolean equals(Node n) {
			return this.c == n.c && this.prev == n.prev;
		}
	}
	static int cnt;
	static Node prev;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String[] strs = new String[N];
		for (int i = 0; i < N; i++) {
			strs[i] = br.readLine();
		}
		// 트라이를 앞쪽 K개 글자에 대해 구성하고, 뒤쪽 K개 글자에 대해 역방향으로 구성한 뒤
		// 그 결과 나오는 모든 정점의 개수를 센다.
		cnt = 0; // 정점의 수가 가장 작은 코코스의 정점 개수
		Node init = new Node('0'); // 앞쪽 트라이의 루트 노드
		Node reverseInit = new Node('0'); // 뒤쪽 트라이의 루트 노드
		// 각 문자열을 이용해 트라이를 구성한다.
		for (int i = 0; i < N; i++) {
			String str = strs[i];
			prev = init;
			// 0번째 문자부터 K-1번째 문자까지를 이용해 앞쪽 트라이를 만든다.
			for (int j = 0; j < K; j++) {
				makeTrie(str.charAt(j));
			}
			prev = reverseInit;
			// (2*K-1)번째 문자부터 K번째 문자까지를 이용해 뒤쪽 트라이를 만든다.
			for (int j = 2*K-1; j >= K; j--) {
				makeTrie(str.charAt(j));
			}
		}
		System.out.println(cnt);
	}
	// 트라이 내 문자가 c인 노드를 만드는 함수
	private static void makeTrie(char c) {
		// prev, c를 이용해 노드를 만든다.
		Node n = new Node(c);
		n.setPrev(prev);
		// 이전 노드의 다음 노드들 중 이미 문자가 c인 노드가 있으면 노드를 추가로 만들지 않아도 된다.
		for (Node m : prev.next) {
			if (m.equals(n)) {
				prev = m;
				return;
			}
		}
		// 이전 노드의 다음 노드들 중 문자가 c인 노드가 없으면 이전 노드의 다음 노드로 새로 만든 노드를 추가한다.
		prev.next.add(n);
		// 노드를 새로 만들었으므로 노드의 개수를 증가시킨다.
		cnt++;
		// 이전 노드를 새로 만든 노드로 갱신해 다음 문자에 해당하는 노드를 만들 준비를 한다.
		prev = n;
	}
}
