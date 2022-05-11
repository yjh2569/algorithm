import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2250_트리의_높이와_너비 {
	// 노드
	static class Node {
		int level, col;
		Node(int level, int col) {
			this.level = level;
			this.col = col;
		}
	}
	// 입력으로 주어지는 N과 컬럼 번호 c
	static int N, c;
	// 이진 트리의 높이
	static int height;
	// 이진 트리의 그래프 형태
	static int[][] graph;
	// 각 노드의 레벨과 열을 저장
	static Node[] nodes;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int[N+1][2]; // 각 노드는 왼쪽 자식 노드와 오른쪽 자식 노드를 가질 수 있다.
		StringTokenizer st;
		int[] parents = new int[N+1]; // 이진 트리의 루트를 판별하기 위해 부모 노드를 기록한다.
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int root = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			graph[root][0] = left;
			graph[root][1] = right;
			if (left != -1) parents[left] = root;
			if (right != -1) parents[right] = root;
		}
		// 컬럼 번호는 1부터 시작, height는 inorder 탐색 시 알아본다.
		c = 1; height = -1;
		// 부모 노드가 없는 노드가 루트 노드가 된다.
		int root = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == 0) {
				root = i;
				break;
			}
		}
		nodes = new Node[N+1];
		// 중위 순회(inorder traversal)을 통해 각 노드의 열 번호를 알아낸다.
		inorder(root, 1);
		// 각 레벨마다 최소 열 번호와 최대 열 번호를 조사한다.
		int[] min_col = new int[height+1];
		Arrays.fill(min_col, Integer.MAX_VALUE);
		int[] max_col = new int[height+1];
		for (int i = 1; i <= N; i++) {
			Node node = nodes[i];
			if (node == null) continue;
			min_col[node.level] = Math.min(min_col[node.level], node.col);
			max_col[node.level] = Math.max(max_col[node.level], node.col);
		}
		// 각 레벨마다 너비를 구해 너비의 최댓값과 이에 대응하는 레벨을 구한다.
		int res_l = 1;
		int res_w = -1;
		for (int i = 1; i <= height; i++) {
			if (res_w < max_col[i] - min_col[i] + 1) {
				res_l = i;
				res_w = max_col[i] - min_col[i] + 1;
			}
		}
		System.out.println(res_l+" "+res_w);

	}
	// 중위 순회는 왼쪽 부트리 -> 루트 -> 오른쪽 부트리 순으로 탐색한다.
	// 왼쪽 부트리의 모든 열 번호는 루트보다 작아야 하고, 오른쪽 부트리의 모든 열 번호는 루트보다 커야 하므로,
	// 만약 왼쪽 부트리를 먼저 순회하면서 열 번호를 붙이고, 루트에 열 번호를 붙인 후, 오른쪽 부트리를 순회하면서 열 번호를 붙이면
	// 문제의 조건을 만족하는 열 번호를 붙여줄 수 있다.
	private static void inorder(int n, int level) {
		if (graph[n][0] != -1) inorder(graph[n][0], level+1);
		height = Math.max(height, level);
		nodes[n] = new Node(level, c++);
		if (graph[n][1] != -1) inorder(graph[n][1], level+1);
	}

}
