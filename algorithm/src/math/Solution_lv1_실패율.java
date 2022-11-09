package math;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_lv1_실패율 {
	// 각 스테이지의 실패율과 스테이지 번호를 저장하는 클래스
	static class Stage implements Comparable<Stage> {
		double failureRate;
		int round;

		public Stage(double failureRate, int round) {
			this.failureRate = failureRate;
			this.round = round;
		}
		// 실패율에 대한 내림차순으로, 만약 실패율이 같다면 번호가 작은 순서대로 정렬
		@Override
		public int compareTo(Stage s) {
			return Double.compare(this.failureRate, s.failureRate) == 0 ? Integer.compare(this.round, s.round)
					: Double.compare(s.failureRate, this.failureRate);
		}
	}

	public static void main(String[] args) {
		int[] result = solution(4, new int[] {4,4,4,4,4});
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(int N, int[] stages) {
		// i번째 스테이지까지 도달한 인원 수를 failed[i]에 저장
        int[] failed = new int[N+2];
        // i번째 스테이지에 도달한 인원 수를 success[i]에 저장
        int[] success = new int[N+2];
        for (int stage : stages) {
        	failed[stage]++;
        	success[stage]++;
        }
        // 누적합을 통해 i번째 스테이지에 도달한 인원 수를 구한다.
        for (int i = N; i >= 0; i--) {
        	success[i] += success[i+1];
        }
        // 실패율이 높은 순서대로 정렬하기 위해 우선순위 큐에 넣고 하나씩 뽑아낸다.
        PriorityQueue<Stage> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
        	// i번째 스테이지에 도달한 인원 수가 없는 경우에도 실패율이 0임에 유의
        	if (success[i] == 0) pq.offer(new Stage(0, i));
        	else pq.offer(new Stage(failed[i]*1.0/success[i], i));
        }
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
        	answer[i] = pq.poll().round;
        }
        return answer;
    }
}
