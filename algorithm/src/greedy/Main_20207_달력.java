package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_20207_달력 {
	// 일정의 시작날짜, 종료날짜, 기간을 저장하는 클래스
	// 일정을 시작날짜가 앞이고, 기간이 긴 순서대로 정렬할 수 있도록 한다.
	static class Plan implements Comparable<Plan>{
		int s, e, d;
		public Plan(int s, int e, int d) {
			this.s = s;
			this.e = e;
			this.d = d;
		}
		public int compareTo(Plan p) {
			return this.s == p.s ? Integer.compare(p.d, this.d) : Integer.compare(this.s, p.s);
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 일정을 받을 때 미리 시작날짜가 앞인 순서로, 시작날짜가 같은 경우 기간이 긴 순서로 정렬해둔다.
		PriorityQueue<Plan> plans = new PriorityQueue<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			plans.offer(new Plan(s, e, e-s+1));
		}
		// 일정을 기록하는 달력을 배열 형태로 구현
		// N이 1000 이하이므로 일정이 최대 1000번 겹칠 수 있다.
		int[][] calendar = new int[1001][366];
		// 조건에 맞게 일정을 달력에 기록
		for (int i = 0; i < N; i++) {
			Plan p = plans.poll();
			// 일정이 앞에서 기록한 일정과 겹치는 경우 아래에 기록한다.
			for (int j = 1; j <= 1000; j++) {
				// 일정이 겹치는지를 나타내는 변수
				boolean isFilled = false;
				// 일정이 겹치는지를 조사
				for (int k = p.s; k <= p.e; k++) {
					if (calendar[j][k] == 1) {
						isFilled = true;
						break;
					}
				}
				// 일정이 겹치면 달력에서 한 칸 아래로 이동한다.
				if (isFilled) continue;
				// 일정이 겹치지 않으면 해당 행에 일정을 기록한다.
				for (int k = p.s; k <= p.e; k++) {
					calendar[j][k] = 1;
				}
				break;
			}
		}
		// 코팅지의 직사각형의 넓이를 구하기 위해 필요한 변수들
		int h = 0; // 직사각형의 높이
		int w = 0; // 직사각형의 너비
		int res = 0; // 코팅지의 넓이의 합
		// 각 날짜마다 일정이 있는지 확인한다.
		// 일정이 있으면 직사각형의 너비를 1 증가시키고, 높이의 최대값읋 업데이트한다.
		// 일정이 없으면 지금까지 만든 직사각형의 넓이를 res에 더해주고, 직사각형의 높이와 너비를 초기화한다.
		for (int j = 1; j <= 365; j++) {
			// 일정이 없는지를 나타내는 변수
			boolean isEmpty = true;
			// 해당 날짜에 일정이 있는지를 확인
			// 일정이 있다면 높이의 최대값을 빠르게 구하기 위해 달력의 아래서부터 조사한다.
			for (int i = 1000; i >= 1; i--) {
				if (calendar[i][j] == 1) {
					isEmpty = false;
					h = Math.max(h, i);
					w++;
					break;
				}
			}
			// 일정이 없는 경우
			if (isEmpty) {
				res += h*w;
				h = 0;
				w = 0;
			}
		}
		// 직사각형이 마지막 날짜를 포함하는 경우 위 코드로는 해당 직사각형의 넓이를 반영할 수 없다.
		// 따라서 지금까지 만든 직사각형의 넓이를 별도로 구해 더해준다.
		res += h*w;
		System.out.println(res);
	}

}
