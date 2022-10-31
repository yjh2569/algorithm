package graph;

public class Solution_lv3_외벽_점검 {

	static int N, M, L;
	static int[] _weak;
	static int[] _dist;
	static int[] p;
	static boolean[] v;
	static boolean possible;
	static int answer;

	public static void main(String[] args) {
		int result = solution(12, new int[] { 1, 3, 4, 9, 10 }, new int[] { 3, 5, 7 });
		System.out.println(result);
	}

	public static int solution(int n, int[] weak, int[] dist) {
		// 취약 지역 점검을 위해 보내야 하는 친구 수의 최솟값
		// 불가능한 경우 Integer.MAX_VALUE
		answer = Integer.MAX_VALUE;
		// 전역변수 선언
		N = n;
		M = weak.length;
		L = dist.length;
		_weak = weak;
		_dist = dist;
		// 이동할 수 있는 거리를 순열을 통해 섞은 결과를 저장
		p = new int[L];
		// 순열에 사용할 방문 배열
		v = new boolean[L];
		perm(0);
		return answer == Integer.MAX_VALUE ? -1 : answer;
	}
	// 순열을 통해 이동할 수 있는 거리를 섞는다.
	private static void perm(int cnt) {
		if (cnt == L) {
			simulate();
			return;
		}
		for (int i = 0; i < L; i++) {
			if (v[i]) continue;
			v[i] = true;
			p[cnt] = _dist[i];
			perm(cnt+1);
			v[i] = false;
		}
		
	}
	// 섞은 결과를 가지고 앞에서부터 한 명씩 배정하면서 모든 취약 지점을 방문할 수 있는지 조사한다.
	private static void simulate() {
		// 시작 지점을 정한다.
		for (int l = 0; l < M; l++) {
			int cnt = 0; // p에서 뽑은 사람의 수
			int weak_cnt = 0; // 방문할 수 있는 취약 지점의 개수
			int k = l; // 현재 뽑은 사람이 방문을 시작하는 취약 지점
			int temp = k; // 현재 뽑은 사람이 방문할 수 있는 가장 먼 취약 지점
			// p에서 한 명씩 뽑아 방문할 수 있는 취약 지점을 조사한다.
			for (int i = 0; i < L; i++) {
				// 시계 방향으로 조사한다.
				while (p[i] >= (_weak[temp] - _weak[k] + N)%N && weak_cnt < M) {
					temp = (temp+1)%M;
					weak_cnt++;
				}
				cnt++;
				k = temp;
				// 모든 취약 지점을 방문한 경우
				if (weak_cnt >= M) {
					answer = Math.min(answer, cnt);
				}
			}
		}
		
	}
	
	

}
