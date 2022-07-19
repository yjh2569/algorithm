package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1525_퍼즐 {
	// 정리된 상태를 문자열로 나타낸 결과
	static String correct = "123456780";
	// 각 문자열마다 해당 문자열에 도달하기까지 최단 거리를 저장
	static Map<String, Integer> visited = new HashMap<>();
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 초기 상태를 문자열로 저장
		String init = "";
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				int num = Integer.parseInt(st.nextToken());
				init += num;
			}
		}
		visited.put(init, 0);
		System.out.println(bfs(init));
	}
	// BFS를 통해 정리된 상태에 도달하기 위한 최소 이동 횟수를 구한다.
	static int bfs(String init) {
		Queue<String> q = new LinkedList<>();
		q.add(init);
		while(!q.isEmpty()) {
			String pos = q.poll();
			int move = visited.get(pos);
			// 0의 위치 파악
			int zero = pos.indexOf('0');
			int r = zero/3;
			int c = zero%3;
			// 정리된 상태에 도달하면 최소 이동 횟수 출력
			if(pos.equals(correct)) {
				return move;
			}
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(nr<0 || nc<0 || nr>2 || nc>2) continue;
				int nPos = nr*3 + nc;
				char ch = pos.charAt(nPos);
				String next = pos.replace(ch, 'c');
				next = next.replace('0', ch);
				next = next.replace('c', '0');
				if(!visited.containsKey(next)) {
					q.add(next);
					visited.put(next, move+1);
				}
			}
		}
		return -1;
	}
}
