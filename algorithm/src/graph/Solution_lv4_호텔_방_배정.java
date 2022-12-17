package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv4_호텔_방_배정 {

	public static void main(String[] args) {
		long[] result = solution(10, new long[] {1,3,4,1,3,1});
		System.out.println(Arrays.toString(result));
	}
	static Map<Long, Long> parents;
	public static long[] solution(long k, long[] room_number) {
		int N = room_number.length;
		// 고객이 원하는 방 번호를 키로, 실제로 예약되는 방 번호를 값으로 하는 Map
        parents = new HashMap<>();
        // 초기화
        for (long n : room_number) {
        	parents.put(n, n);
        }
        // 예약 결과를 저장하는 배열
        long[] answer = new long[N];
        // 유니온-파인드 알고리즘을 통해 실제 예약되는 방 번호를 찾는다.
        for (int i = 0; i < N; i++) {
        	// 실제 예약되는 방 번호를 찾는다.
        	long m = find(room_number[i]);
        	answer[i] = m;
        	// m+1을 번호로 하는 방을 parents에 없는 경우 추가
        	parents.putIfAbsent(m+1, m+1);
        	// m번 방을 원하는 경우를 m+1번 방을 원하는 경우와 동일하게 간주하도록 유니온 작업을 진행
        	union(m, m+1);
        }
        return answer;
    }
	// u번 방을 원하는 경우 실제 예약하는 방 번호를 찾는 함수
	private static long find(long u) {
		if (parents.get(u) == u) return u;
		parents.put(u, find(parents.get(u)));
		return parents.get(u);
	}
	// u번 방을 원하는 경우를 v번 방을 원하는 경우와 동일하게 간주하도록 설정
	private static void union(long u, long v) {
		u = find(u);
		v = find(v);
		if (u != v) parents.put(u, v);
	}
}
