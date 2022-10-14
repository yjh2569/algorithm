package tree;

import java.util.Arrays;

public class Solution_lv3_양과_늑대 {
	
	static int[][] tree;
    static boolean[] visited;
    static int max, N;
	
	public static void main(String[] args) {
		int result = solution(new int[] {0,0,1,1,1,0,1,0,1,0,1,1}, new int[][] {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}});
		System.out.println(result);
	}	
	
    public static int solution(int[] info, int[][] edges) {
        N = info.length;
        // 최대로 모을 수 있는 양의 마리 수
        max = 1;
        // 각 노드의 자식 노드를 저장하는 배열
        tree = new int[N][2];
        // DFS 과정 중 각 노드를 방문했는지를 나타내는 배열
        visited = new boolean[N];
        // 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(tree[i], -1);
        }
        // 부모와 자식 노드 관계를 바탕으로 tree를 채운다.
        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            if (tree[parent][0] == -1) {
                tree[parent][0] = child;
            } else {
                tree[parent][1] = child;
            }
        }
        // DFS를 통해 최대로 모을 수 있는 양의 마리 수를 계산
        dfs(info, 0, 1, 0);
        return max;
    }
    // DFS를 통해 최대로 모을 수 있는 양의 마리 수를 계산
    // loc는 현재 방문한 노드의 번호, sheep은 현재까지 모은 양의 마리 수, wolf는 현재까지 모은 늑대의 마리 수를 의미
    private static void dfs(int[] info, int loc, int sheep, int wolf) {
        max = Math.max(sheep, max);
        visited[loc] = true;
        // 현재 방문할 수 있는 노드를 나타내는 배열
        boolean[] canVisit = new boolean[N];
        // 모든 방문했던 노드의 자식 노드를 조사해 각 노드가 현재 방문 가능한지 조사한다.
        for (int i = 0; i < N; i++) {
            if (!visited[i]) continue;
            // 노드 i의 왼쪽 자식 노드가 존재하고, 해당 노드를 방문했을 때 여전히 양의 마리 수가 늑대의 마리 수보다 많은 경우
            if (tree[i][0] != -1) {
                if (info[tree[i][0]] == 0 || (info[tree[i][0]] == 1 && sheep > wolf+1)) {
                    canVisit[tree[i][0]] = true;
                }
            }
            // 노드 i의 오른쪽 자식 노드가 존재하고, 해당 노드를 방문했을 때 여전히 양의 마리 수가 늑대의 마리 수보다 많은 경우
            if (tree[i][1] != -1) {
                if (info[tree[i][1]] == 0 || (info[tree[i][1]] == 1 && sheep > wolf+1)) {
                    canVisit[tree[i][1]] = true;
                }
            }
        }
        // 모든 노드에 대해, 방문하지 않았으면서 방문할 수 있는 노드를 재귀적으로 방문한다.
        for (int i = 0; i < N; i++) {
            if (visited[i] || !canVisit[i]) continue;
            if (info[i] == 0) dfs(info, i, sheep+1, wolf);
            if (info[i] == 1) dfs(info, i, sheep, wolf+1);
        }
        // 백트래킹
        visited[loc] = false;
    }

}
