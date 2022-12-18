package implementation;

import java.util.PriorityQueue;

public class Solution_lv3_징검다리_건너기 {

	public static void main(String[] args) {
		int result = solution(new int[] {2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3);
		System.out.println(result);
	}
	// 징검다리의 디딤돌을 나타내는 클래스
	static class Stone implements Comparable<Stone> {
		int idx, count; // 디딤돌의 인덱스, 밟을 수 있는 횟수를 나타내는 변수
		Stone prev; // 앞에 있는 디딤돌 중 밟을 수 있는 가장 가까운 디딤돌
		Stone next; // 뒤에 있는 디딤돌 중 밟을 수 있는 가장 가까운 디딤돌
		public Stone(int idx, int count) {
			this.idx = idx;
			this.count = count;
		}
		public void setPrev(Stone s) {
			this.prev = s;
		}
		public void setNext(Stone s) {
			this.next = s;
		}
		// 현재 돌을 제거하는 함수
		public void remove() {
			this.prev.setNext(this.next);
			this.next.setPrev(this.prev);
		}
		// next와 현재 돌 사이의 거리를 구하는 함수
		public int getDistance() {
			return next.idx - this.idx;
		}
		public int compareTo(Stone s) {
			return Integer.compare(this.count, s.count);
		}
	}
	
	public static int solution(int[] stones, int k) {
        int answer = 0;
        int N = stones.length;
        // stones 내 값들을 활용해 Stone 클래스로 바꾼 결과를 저장하는 배열
        Stone[] newStones = new Stone[N+2];
        // 시작 지점과 끝 지점을 밟을 수 있는 횟수가 매우 많은 디딤돌로 간주
        newStones[0] = new Stone(0, Integer.MAX_VALUE);
        newStones[N+1] = new Stone(N+1, Integer.MAX_VALUE);
        // 초기화
        for (int i = 1; i <= N; i++) {
        	newStones[i] = new Stone(i, stones[i-1]);
        }
        // 각 디딤돌의 prev와 next 설정
        newStones[0].setNext(newStones[1]);
        newStones[N+1].setPrev(newStones[N]);
        for (int i = 1; i <= N; i++) {
        	newStones[i].setPrev(newStones[i-1]);
        	newStones[i].setNext(newStones[i+1]);
        }
        // 디딤돌을 밟을 수 있는 횟수가 적은 순서대로 뽑기 위한 우선 순위 큐
        PriorityQueue<Stone> pq = new PriorityQueue<>();
        // 초기화
        for (int i = 0; i <= N+1; i++) {
        	pq.offer(newStones[i]);
        }
        // 1. 우선 순위 큐에 남아 있는 디딤돌 중 가장 밟을 수 있는 횟수가 적은 디딤돌을 꺼낸다.
        // 2. 해당 디딤돌을 밟을 수 있는 동안에는 니니즈 친구들은 징검다리를 통과할 수 있기 때문에, 
        //    밟을 수 있는 횟수가 곧 통과한 인원 수가 된다.
        // 3. 해당 디딤돌을 제거한다.
        // 4. 제거한 디딤돌 앞에 있는 가장 가까운 디딤돌을 찾는다.
        // 5. 그 디딤돌 기준 다음으로 밟을 수 있는 가장 가까운 디딤돌까지의 거리가 k보다 커지면 더 이상 징검다리를 통과할 수 없기에
        //    while 문을 종료한다.
        while (!pq.isEmpty()) {
        	Stone s = pq.poll();
        	answer = s.count;
        	s.remove();
        	Stone prev = s.prev;
        	if (prev.getDistance() > k) break;
        }
        return answer;
    }

}
