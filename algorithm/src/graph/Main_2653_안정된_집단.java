package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_2653_안정된_집단 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] likes = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				likes[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 가능한 소집단을 저장하는 ArrayList
		ArrayList<ArrayList<Integer>> groups = new ArrayList<>();
		// 각 인원이 어떤 소집단에 포함되어 있는지를 나타내는 HashMap
		Map<Integer, Integer> idxs = new HashMap<>();
		int idx = 0; // 현재 소집단의 개수
		for (int i = 0; i < n; i++) {
			// 현재 (i+1)번 사람을 포함하는 소집단이 없는 경우
			if (!idxs.containsKey(i)) {
				// (i+1)번이 좋아하는 사람(자기 자신 포함) 모두를 소집단으로 묶어 임시 저장한다.
				ArrayList<Integer> group = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					if (likes[i][j] == 0) {
						group.add(j);
						idxs.put(j, idx); // (j+1)번 사람이 속한 소집단 번호 갱신
					}
				}
				groups.add(group);
				idx++; // 소집단 개수 증가
			// 현재 (i+1)번 사람을 포함하는 소집단이 있는 경우
			} else {
				// (i+1)번 사람을 포함하는 소집단
				ArrayList<Integer> group = groups.get(idxs.get(i));
				// 해당 소집단 안에 있는 사람들 모두를 좋아하고, 밖에 있는 사람들 모두를 싫어하는지 확인
				for (int j = 0; j < n; j++) {
					if ((likes[i][j] == 0 && !group.contains(j)) || (likes[i][j] == 1 && group.contains(j))) {
						System.out.println(0);
						return;
					}
				}
			}
		}
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(groups.size()).append("\n");
		for (ArrayList<Integer> group : groups) {
			// 각 소집단의 크기는 2 이상이어야 한다.
			if (group.size() <= 1) {
				System.out.println(0);
				return;
			}
			Collections.sort(group);
			for (int m : group) {
				sb.append(m+1).append(" ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
