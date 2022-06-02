package dp;

import java.util.Scanner;

public class Solution_4311_오래된_스마트폰 {

	public static void main(String[] args) {
		// 라이브러리 제한으로 인해 Scanner를 통해 입력을 받는다.
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		outer: for (int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			int O = sc.nextInt();
			int M = sc.nextInt();
			// 0~9에 대해 계산기에서 터치가 가능한지를 나타내는 배열
			boolean[] nums = new boolean[10]; 
			// +(1), -(2), *(3), /(4)가 계산기에서 터치가 가능한지를 나타내는 배열
			boolean[] operators = new boolean[5];
			for (int i = 0; i < N; i++) {
				int x = sc.nextInt();
				nums[x] = true;
			}
			for (int i = 0; i < O; i++) {
				int x = sc.nextInt();
				operators[x] = true;
			}
			int W = sc.nextInt();
			// dijkstra's algorithm을 적용해 해결한다.
			// 0~999 사이의 숫자에 대한 방문 여부를 나타내는 배열
			boolean[] visited = new boolean[1000];
			// 0~999 사이의 숫자를 사칙연산 없이 나타내는데 필요한 최소 터치 횟수
			// 불가능한 경우 Integer.MAX_VALUE 값을 가진다.
			int[] cnts = new int[1000];
			// 0~999 사이의 숫자를 사칙연산을 포함하여 나타내는데 필요한 최소 터치 횟수
			// 불가능한 경우 Integer.MAX_VALUE 값을 가진다.
			int[] dp = new int[1000];
			// 초기화
			for (int i = 0; i < 1000; i++) {
				cnts[i] = Integer.MAX_VALUE;
				dp[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < 10; i++) {
				if (nums[i]) {
					cnts[i] = 1;
					dp[i] = 1;
				}
			}
			// 사칙연산 없이 나타낼 수 있는 모든 수를 탐색
			while (true) {
				// 현재 최소 터치 횟수를 알고 있는 수들 중 가장 터치 횟수가 작으면서 방문한 적 없는 수를 고른다.
				// 원래는 우선 순위 큐를 이용해 구현하였으나, 라이브러리를 사용할 수 없어 일반 배열로 대체했다.
				int[] u = new int[2];
				u[1] = Integer.MAX_VALUE;
				for (int i = 0; i < 1000; i++) {
					if (u[1] > cnts[i] && !visited[i]) {
						u[0] = i;
						u[1] = cnts[i];
					}
				}
				// 터치 최대 횟수를 넘어가거나 더 이상 방문할 수 있는 수가 없으면 while문을 끝낸다.
				if (u[1] > M) break;
				if (u[1] == Integer.MAX_VALUE) break;
				// 사칙연산 없이 목표로 하는 수를 만들 수 있으면 지금까지 터치한 횟수를 그대로 출력한다.
				if (u[0] == W) {
					System.out.println("#"+t+" "+cnts[W]);
					continue outer;
				}
				// 방문 여부 처리
				if (visited[u[0]]) continue;
				visited[u[0]] = true;
				// 계산기의 숫자 버튼을 눌러서 현재 수의 뒷자리에 한 자리의 수를 추가함으로써 새로운 수를 만든다.
				for (int i = 0; i < 10; i++) {
					if (!nums[i]) continue;
					int next = u[0]*10 + i;
					// 999를 넘으면 안 된다는 점에 유의
					if (next < 1000 && cnts[next] > u[1] + 1) {
						cnts[next] = u[1] + 1;
						dp[next] = u[1] + 1;
					}
				}
			}
			// 방문 배열 초기화
			visited = new boolean[1000];
			// 사칙연산을 포함해 나타낼 수 있는 모든 수를 탐색
			while (true) {
				// 현재 최소 터치 횟수를 알고 있는 수들 중 가장 터치 횟수가 작으면서 방문한 적 없는 수를 고른다.
				// 원래는 우선 순위 큐를 이용해 구현하였으나, 라이브러리를 사용할 수 없어 일반 배열로 대체했다.
				int[] u = new int[2];
				u[1] = Integer.MAX_VALUE;
				for (int i = 0; i < 1000; i++) {
					if (u[1] > dp[i] && !visited[i]) {
						u[0] = i;
						u[1] = dp[i];
					}
				}
				// 터치 최대 횟수를 넘어가거나 더 이상 방문할 수 있는 수가 없으면 while문을 끝낸다.
				if (u[1] > M) break;
				if (u[1] == Integer.MAX_VALUE) break;
				// 사칙연산을 포함하여 목표로 하는 수를 만들 수 있으면 지금까지 터치한 횟수에 1을 더해(등호 입력) 출력한다.
				// 이때 이 횟수에 1을 더했을 때 M을 넘어갈 수도 있으니 지금까지 터치한 횟수가 M보다 작은지 확인해야 한다.
				if (u[0] == W && dp[W] < M) {
					System.out.println("#"+t+" "+(dp[W]+1));
					continue outer;
				}
				// 방문 여부 처리
				if (visited[u[0]]) continue;
				visited[u[0]] = true;
				// 사칙연산 없이 만들 수 있는 수와 사칙연산을 해서 새로운 수를 만든다.
				for (int i = 1; i < 1000; i++) {
					// i가 사칙연산 없이 만들 수 없는 수인 경우
					if (cnts[i] == Integer.MAX_VALUE) continue;
					// 사칙연산을 수행한 뒤 만든 수의 터치 횟수가 이전에 시도했었던 다른 방법의 터치 횟수보다 작으면 update
					// 덧셈
					if (operators[1]) {
						int next = u[0] + i;
						if (next < 1000 && dp[next] > u[1] + 1 + cnts[i]) {
							dp[next] = u[1] + 1 + cnts[i];
						}
					}
					// 뺄셈
					if (operators[2]) {
						int next = u[0] - i;
						if (next >= 0 && dp[next] > u[1] + 1 + cnts[i]) {
							dp[next] = u[1] + 1 + cnts[i];
						}
					}
					// 곱셈
					if (operators[3]) {
						int next = u[0] * i;
						if (next < 1000 && dp[next] > u[1] + 1 + cnts[i]) {
							dp[next] = u[1] + 1 + cnts[i];
						}
					}
					// 나눗셈
					if (operators[4]) {
						int next = u[0] / i;
						if (next < 1000 && dp[next] > u[1] + 1 + cnts[i]) {
							dp[next] = u[1] + 1 + cnts[i];
						}
					}
				}
			}
			// 터치 횟수가 M을 넘어가거나, 더 이상 만들 수 있는 수가 존재하지 않는 경우
			System.out.println("#"+t+" "+(-1));
			sc.close();
		}
	}

}
