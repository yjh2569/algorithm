package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_3098_소셜네트워크 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// i와 j가 친구 관계인지를 나타낸다.
		// 값이 2이면 현재 친구 관계임을, 1이면 곧 친구 관계가 됨을 의미한다.
		int[][] relationships = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			relationships[x][y] = 2;
			relationships[y][x] = 2;
		}
		int time = 0; // 모든 사람이 친구 관계를 맺을 때까지 걸리는 시간
		int cur = M; // 현재 친구 관계 수
		ArrayList<Integer> list = new ArrayList<>(); // 매 시간마다 생기는 친구 관계의 수를 저장하는 ArrayList
		// 모든 사람이 친구 관계를 맺을 때까지 반복
		while (cur < N*(N-1)/2) {
			int temp = 0; // 새로 생기는 친구 관계의 수
			// 새로 생기는 친구 관계를 찾는다.
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					// 이미 친구 관계거나, 곧 친구 관계가 되는 경우
					if (relationships[i][j] != 0) continue;
					// k라는 사람을 경유해서 i와 j가 친구가 될 수 있는지 조사한다.
					for (int k = 0; k < N; k++) {
						if (relationships[i][k] == 2 && relationships[k][j] == 2) {
							relationships[i][j] = relationships[j][i] = 1;
							temp++;
							break;
						}
					}
				}				
			}
			// 만들어진 친구 관계를 갱신한다.
			list.add(temp);
			cur += temp;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (relationships[i][j] == 1) relationships[i][j] = 2;
				}
			}
			time++;
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(time).append("\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
