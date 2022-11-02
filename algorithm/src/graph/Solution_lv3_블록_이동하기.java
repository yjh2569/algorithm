package graph;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_lv3_블록_이동하기 {
	
	static int N;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) {
		int result = solution(new int[][] {{0, 0, 0, 1, 1},{0, 0, 0, 1, 0},{0, 1, 0, 1, 1},{1, 1, 0, 0, 1},{0, 0, 0, 0, 0}});
		System.out.println(result);
	}
	
	public static int solution(int[][] board) {
        int answer = 0;
        // board의 행 또는 열의 길이를 전역변수로 지정
        N = board.length;
        // BFS를 이용해 최단거리를 구한다.
        Queue<int[]> q = new LinkedList<>();
        // 2*1 크기의 로봇을 움직이기 때문에, 방문 배열 내 각 칸에 현재 로봇이 현재 칸 기준 어떤 방향으로 놓여져 있는지도 기록한다.
        // 예를 들어, visited[0][0][1]이 참이라는 것은 (0, 0) 칸에 로봇이 1번째 방향(동쪽 방향)으로 놓여져 있음을 의미한다.
        // 동쪽 방향으로 놓여져 있다는 것은 현재 칸의 동쪽에 로봇의 다른 부분이 놓여져 있음을 의미한다.
        boolean[][][] visited = new boolean[N][N][4];
        // 초기화
        // q에는 로봇의 한쪽 부분만 넣는다.
        q.offer(new int[] {0, 0, 1});
        // 방문 배열에는 로봇의 양쪽 부분의 위치와, 각 부분이 어떤 방향으로 놓여져 있는지를 넣는다.
        visited[0][0][1] = true;
        visited[0][1][3] = true;
        // (N-1, N-1)까지 가는데 걸리는 최소 시간
        int time = 0;
        while (!q.isEmpty()) {
        	int qLen = q.size();
        	while (qLen > 0) {
        		int[] u = q.poll();
        		// 현재 로봇이 (N-1, N-1)에 도착했으면 time을 반환
        		if (checkArrived(u)) {
        			return time;
        		}
        		// u로부터 로봇의 한쪽 부분의 정보를 받아온다.
        		int r = u[0], c = u[1], d = u[2];
        		// 1. 로봇의 한쪽 부분이 놓여있는 방향 쪽으로 이동하는 경우
        		if (check(r + dr[d]*2, c + dc[d]*2) && !visited[r + dr[d]][c + dc[d]][d] 
        				&& board[r + dr[d]*2][c + dc[d]*2] == 0) {
        			visited[r + dr[d]][c + dc[d]][d] = true;
        			visited[r + dr[d]*2][c + dc[d]*2][(d+2)%4] = true;
        			q.offer(new int[] {r + dr[d], c + dc[d], d});
        		}
        		// 2. 로봇의 한쪽 부분이 놓여있는 방향과 반대 방향으로 이동하는 경우
        		if (check(r - dr[d], c - dc[d]) && !visited[r - dr[d]][c - dc[d]][d]
        				&& board[r - dr[d]][c - dc[d]] == 0) {
        			visited[r - dr[d]][c - dc[d]][d] = true;
        			visited[r][c][(d+2)%4] = true;
        			q.offer(new int[] {r - dr[d], c - dc[d], d});
        		}
        		// 로봇의 다른 부분의 정보를 받아온다.
        		int next_r = u[0] + dr[d], next_c = u[1] + dc[d];
        		// 3. 로봇이 놓여있는 방향의 수직 방향으로 이동하는 경우
        		// 예를 들어 로봇이 가로로 놓여있었다면 위아래로 움직이는 경우를 말한다.
        		for (int dir = 1; dir <= 3; dir += 2) {
        			if (check(r + dr[(d+dir)%4], c + dc[(d+dir)%4]) && check(next_r + dr[(d+dir)%4], next_c + dc[(d+dir)%4])
        					&& !visited[r + dr[(d+dir)%4]][c + dc[(d+dir)%4]][d] 
        					&& board[r + dr[(d+dir)%4]][c + dc[(d+dir)%4]] == 0
        					&& board[next_r + dr[(d+dir)%4]][next_c + dc[(d+dir)%4]] == 0) {
        				visited[r + dr[(d+dir)%4]][c + dc[(d+dir)%4]][d] = true;
        				visited[next_r + dr[(d+dir)%4]][next_c + dc[(d+dir)%4]][(d+2)%4] = true;
        				q.offer(new int[] {r + dr[(d+dir)%4], c + dc[(d+dir)%4], d});
        			}
        		}
        		for (int dir = 1; dir <= 3; dir += 2) {
        			// 4. 로봇의 한쪽 부분을 기준으로 회전하는 경우
        			if (check(r + dr[(d+dir)%4], c + dc[(d+dir)%4]) && check(next_r + dr[(d+dir)%4], next_c + dc[(d+dir)%4])
        					&& !visited[r][c][(d+dir)%4] 
        					&& board[r + dr[(d+dir)%4]][c + dc[(d+dir)%4]] == 0
        					&& board[next_r + dr[(d+dir)%4]][next_c + dc[(d+dir)%4]] == 0) {
        				visited[r][c][(d+dir)%4] = true;
        				visited[r + dr[(d+dir)%4]][c + dc[(d+dir)%4]][(d+dir+2)%4] = true;
        				q.offer(new int[] {r, c, (d+dir)%4});
        			}
        			// 5. 로봇의 다른 부분을 기준으로 회전하는 경우
        			if (check(r + dr[(d+dir)%4], c + dc[(d+dir)%4]) && check(next_r + dr[(d+dir)%4], next_c + dc[(d+dir)%4])
        					&& !visited[next_r][next_c][(d+dir)%4] 
        					&& board[r + dr[(d+dir)%4]][c + dc[(d+dir)%4]] == 0
        					&& board[next_r + dr[(d+dir)%4]][next_c + dc[(d+dir)%4]] == 0) {
        				visited[next_r][next_c][(d+dir)%4] = true;
        				visited[next_r + dr[(d+dir)%4]][next_c + dc[(d+dir)%4]][(d+dir+2)%4] = true;
        				q.offer(new int[] {next_r, next_c, (d+dir)%4});
        			}
        		}
        		qLen--;
        	}
        	time++;
        }
        return answer;
    }
	// 경계 확인을 위한 함수
	private static boolean check(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	// 현재 로봇이 (N-1, N-1)에 도착했는지 조사하는 함수
	// 입력으로 받은 위치뿐만 아니라, 로봇의 다른 부분이 (N-1, N-1)에 도달했는지도 조사한다.
	private static boolean checkArrived(int[] u) {
		return (u[0] == N-1 && u[1] == N-1) || (u[0] + dr[u[2]] == N-1 && u[1] + dc[u[2]] == N-1);
	}

}
