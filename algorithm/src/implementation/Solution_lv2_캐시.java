package implementation;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_lv2_캐시 {

	public static void main(String[] args) {
		int result = solution(3, new String[] {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
		System.out.println(result);

	}
	public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 큐를 사용해 캐시를 구현
        Queue<String> q = new LinkedList<>();
        // 현재 캐시 크기
        int curCacheSize = 0;
        // 캐시의 크기가 0이면 모든 데이터가 cache miss가 된다.
        if (cacheSize == 0) {
        	return cities.length*5;
        }
        for (int i = 0; i < cities.length; i++) {
        	// 대소문자 구분이 없다.
        	String city = cities[i].toLowerCase();
        	// cache hit
        	if (q.contains(city)) {
        		answer += 1;
        		// 큐에서 도시 이름을 새로 조회했기 때문에 기존 도시 이름은 삭제
        		q.remove(city);
        	// cache miss
        	} else {
        		// 캐시가 덜 찬 경우
        		if (curCacheSize < cacheSize) {
        			curCacheSize++;
        		// 캐시가 꽉 찬 경우
        		} else {
        			// LRU 방식에 따라 가장 나중에 조회된 도시이름을 제거
        			q.poll();
        		}
        		answer += 5;
        	}
        	// 조회한 도시 이름을 맨 뒤에 추가
        	q.offer(city);
        }
        return answer;
    }
}
