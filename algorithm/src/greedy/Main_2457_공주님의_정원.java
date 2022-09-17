package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_2457_공주님의_정원 {
	// 꽃이 피고 지는 날짜를 기록하는 클래스
	static class Flower implements Comparable<Flower> {
		int start, end;
		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
		// 피는 날짜가 빠른 순서, 피는 날짜가 같으면 지는 날짜가 빠른 순서를 가지게 한다.
		public int compareTo(Flower f) {
			return this.start == f.start ? Integer.compare(this.end, f.end) : Integer.compare(this.start, f.start);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 각 월마다 며칠이 있는지를 배열로 나타낸다.
		int[] months = new int[] {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int N = Integer.parseInt(br.readLine());
		// 꽃들을 저장하는 ArrayList
		ArrayList<Flower> flowers = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start_m = Integer.parseInt(st.nextToken());
			int start_d = Integer.parseInt(st.nextToken());
			int end_m = Integer.parseInt(st.nextToken());
			int end_d = Integer.parseInt(st.nextToken());
			// 원활한 계산을 위해 각 꽃이 피고 지는 날짜를 1월 1일부터 해당 날짜까지의 일수로 바꾼다.
			for (int m = 1; m < start_m; m++) {
				start_d += months[m];
			}
			for (int m = 1; m < end_m; m++) {
				end_d += months[m];
			}
			flowers.add(new Flower(start_d, end_d));
		}
		// 꽃이 피는 날짜가 빠른 순서대로, 피는 날짜가 같으면 지는 날짜가 빠른 순서대로 정렬한다.
		Collections.sort(flowers);
		// greedy algorithm을 적용한다.
		// 1. 우선, 꽃이 피는 날짜가 3월 1일 이전(3월 1일 포함)이면서 꽃이 지는 날짜가 가장 늦는 꽃을 선택한다.
		// 2. 마지막으로 고른 꽃이 지는 날짜보다 꽃이 피는 날짜가 빠른 꽃 중에서 꽃이 지는 날짜가 가장 늦는 꽃을 선택한다.
		// 3. 2번을 마지막으로 고른 꽃이 지는 날짜가 11월 30일 이후(11월 30일 미포함)이 될 때까지 반복한다.
		int start_idx = -1; // 1번에서 고른 꽃의 인덱스
		int max_end = 0; // 1번에서 고른 꽃의 지는 날짜
		int d_3_1 = 31 + 28 + 1; // 3월 1일을 일수로 표현
		for (int i = 0; i < N; i++) {
			Flower f = flowers.get(i);
			// 3월 1일 이전에 피는 꽃이 없는 경우
			if (start_idx == -1 && f.start > d_3_1) {
				System.out.println(0);
				System.exit(0);
			// 3월 1일 이전에 피는 꽃을 찾았고, 현재 꽃은 3월 1일 이후에 피는 꽃인 경우
			// flowers는 꽃이 피는 날짜 순서대로 정렬했기 때문에, 이 이후의 꽃을 보는 것은 더 이상 무의미하다.
			} else if (f.start > d_3_1) {
				break;
			}
			// 3월 1일 이전에 피는 꽃이면서, 이전까지 본 꽃들보다 꽃이 지는 날짜가 늦는 경우
			// 해당 꽃을 시작 꽃으로 지정한다.
			if (max_end < f.end) {
				start_idx = i;
				max_end = f.end;
			}
		}
		int cnt = 1; // 선택한 꽃의 최소 개수
		int end_d = flowers.get(start_idx).end; // 마지막 꽃이 지는 날짜
		int max_end_d = end_d; // 순회 중 가장 마지막으로 꽃이 지는 날짜를 임시로 저장하기 위한 변수
		int max_idx = start_idx; // 순회 중 가장 마지막으로 지는 꽃의 인덱스를 임시로 저장하기 위한 변수
		int d_11_30 = 0; // 11월 30일을 일수로 표현
		for (int i = 1; i <= 11; i++) {
			d_11_30 += months[i];
		}
		// 마지막 꽃이 지는 날짜가 11월 30일 이후(11월 30일 미포함에 유의)가 될 때까지 순회
		while (end_d <= d_11_30) {
			// 여기서는 start_idx가 마지막으로 고른 꽃의 인덱스가 된다.
			// 마지막으로 고른 꽃 이후, 즉 마지막으로 고른 꽃이 피는 날짜보다 늦게 피는 꽃들만 탐색한다.
			for (int i = start_idx + 1; i < N; i++) {
				Flower f = flowers.get(i);
				// 마지막으로 고른 꽃이 지는 날짜보다 빨리 피는 꽃들 중 가장 늦게 지는 꽃을 찾는다.
				if (end_d >= f.start && max_end_d < f.end) {
					max_end_d = f.end;
					max_idx = i;
				// 마지막으로 고른 꽃이 지는 날짜보다 늦게 피는 꽃인 경우
				// 마찬가지로 flowers는 꽃이 피는 날짜 순서대로 정렬되어 있기 때문에 이 이후의 꽃을 보는 것은 무의미하다.
				} else if (end_d < f.start) {
					break;
				}
			}
			// 위의 for 문에서 다음 꽃을 찾지 못한 경우
			// 3월 1일부터 11월 30일까지 매일 한 개의 꽃이 피어 있도록 유지하는 것이 불가능하다는 의미다.
			if (max_idx == start_idx) {
				System.out.println(0);
				System.exit(0);
			}
			// 마지막으로 고른 꽃을 선택하고 관련 변수를 갱신한다.
			start_idx = max_idx;
			cnt++;
			end_d = max_end_d;
		}
		System.out.println(cnt);
	}

}
