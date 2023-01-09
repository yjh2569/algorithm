package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1713_후보_추천하기 {
	// 학생의 번호, 추천 횟수, 후보 등록 시간을 나타내는 클래스
	static class Candidate implements Comparable<Candidate> {
		int idx, cnt, time;
		public Candidate(int idx, int cnt, int time) {
			this.idx = idx;
			this.cnt = cnt;
			this.time = time;
		}
		// 후보 목록에서 제거할 때 추천 횟수와 후보 등록 시간을 이용해 정렬
		public int compareTo(Candidate c) {
			return this.cnt == c.cnt ? Integer.compare(this.time, c.time) : Integer.compare(this.cnt, c.cnt);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 후보들을 저장하는 ArrayList
		ArrayList<Candidate> candidates = new ArrayList<>();
		outer: for (int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			// 학생이 이미 후보 명단에 있는지 확인
			for (int j = 0; j < candidates.size(); j++) {
				if (candidates.get(j).idx == n) {
					candidates.get(j).cnt++;
					continue outer;
				}
			}
			// 후보 명단이 가득 찬 경우
			if (candidates.size() == N) {
				Collections.sort(candidates);
				candidates.remove(0);
			}
			candidates.add(new Candidate(n, 1, i));
		}
		// 번호 오름차순으로 후보 명단을 출력하기 위해 별도의 기준을 가지고 정렬
		Collections.sort(candidates, (x, y) -> Integer.compare(x.idx, y.idx));
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < candidates.size(); i++) {
			sb.append(candidates.get(i).idx).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
