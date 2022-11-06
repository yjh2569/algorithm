package greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution_lv4_무지의_먹방_라이브 {
	// 음식을 먹는데 걸리는 시간과 food_times 내에서 음식의 인덱스를 저장하는 클래스
	static class Food implements Comparable<Food> {
	    int time;
	    int idx;
	    public Food(int time, int idx) {
	        this.time = time;
	        this.idx = idx;
	    }
	    public int compareTo(Food f) {
	        return Integer.compare(this.time, f.time);
	    }
	}
	
	public static void main(String[] args) {
		int result = solution(new int[] {3, 1, 2}, 5);
		System.out.println(result);
	}
	
	public static int solution(int[] food_times, long k) {
		// k초 후에 먹을 음식이 남아있는지 확인
        long sumFood = 0;
        int N = food_times.length;
        for (int i = 0; i < N; i++) {
        	sumFood += food_times[i];
        }
        // k초 후에 남은 음식이 없는 경우
        if (sumFood <= k) return -1;
        // 주어진 음식들을 먹는데 걸리는 시간 순서대로 저장하는 우선순위 큐
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
        	pq.offer(new Food(food_times[i], i+1));
        }
        long totalTime = 0; // 현재까지 소요된 총 시간
        long cycle = 0; // 회전판의 회전 수
        long cnt = N; // 현재 남아있는 음식의 수
        // 현재까지 소요된 총 시간에,
        // (음식을 다 먹는데 걸리는 시간이 가장 적은 음식을 먹는데 걸리는 시간) * (현재 남아있는 음식의 수)를 더한 값이
        // 네트워크 장애가 발생하는데 걸리는 시간보다 커질 때까지,
        // 가장 시간이 적게 걸리는 음식을 꺼내 그 음식을 포함한 모든 남아있는 음식을
        // 가장 시간이 적게 걸리는 음식을 먹는 시간만큼 먹는다.
        while (totalTime + (pq.peek().time - cycle) * cnt <= k) {
        	int curTime = pq.poll().time;
        	totalTime += (curTime - cycle) * cnt;
        	cycle = curTime;
        	cnt--;
        }
        // 남아있는 음식들을 저장하는 ArrayList
        ArrayList<Food> remainedFood = new ArrayList<>();
        while (!pq.isEmpty()) {
        	remainedFood.add(pq.poll());
        }
        // food_time 내 음식의 인덱스 순서대로 정렬
        Collections.sort(remainedFood, (x, y) -> Integer.compare(x.idx, y.idx));
        // 현재 남은 시간을 남아있는 음식의 수로 나눈 나머지를 통해 k초 후에 먹을 음식의 인덱스를 구한다.
        return remainedFood.get((int) ((k - totalTime) % cnt)).idx;
    }

}
