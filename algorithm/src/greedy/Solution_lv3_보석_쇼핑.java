package greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv3_보석_쇼핑 {

	public static void main(String[] args) {
		int[] result = solution(new String[] {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        // 각 보석이 어떤 구간 내에 몇 개 존재하는지를 나타내는 Map
        Map<String, Integer> included = new HashMap<>();
        // 초기화
        for (String gem : gems) {
        	included.putIfAbsent(gem, 0);
        }
        int N = gems.length;
        int start = 0; // 현재 조사 중인 구간의 시작 번호
        int end = 0; // 현재 조사 중인 구간의 끝 번호
        int cnt = 1; // 구간 내 서로 다른 보석의 개수
        int totalCnt = included.size(); // gems 안에 있는 서로 다른 보석의 개수
        int minLen = Integer.MAX_VALUE; // 모든 보석을 포함하는 구간 중 가장 짧은 길이
        included.put(gems[0], 1); // 처음 구간을 [0, 0]으로 간주했을 때 0번째 보석은 한 개 포함한다.
        // [i, j] 구간에 있는 보석을 조사했을 때
        // 만약 구간 내에 모든 보석이 있지 않은 경우 끝 번호을 증가시켜 보석을 더 찾는다.
        // 구간 내에 모든 보석이 있는 경우 구간의 길이를 줄이기 위해 시작 번호를 증가시켜 구간 내 보석의 개수를 줄인다.
        // 이를 시작 번호가 N-1이 될 때까지 진행한다.
        // 이렇게 하면 시간복잡도를 O(N)까지 줄일 수 있다.
        while (start < N-1) {
        	// 모든 보석을 포함한 구간을 구한 상태에서, 기존에 구한 구간의 길이보다 더 짧은 길이의 구간을 찾은 경우
        	// 이때 start는 while문을 진행함에 따라 단조증가하므로 아래 조건을 만족하는 구간을 찾으면
        	// 짧은 구간이 여러 개일 때 시작 번호가 가장 작은 구간을 자동으로 구할 수 있다. 
        	if (cnt == totalCnt && minLen > end - start + 1) {
        		minLen = end - start + 1;
        		// 인덱스 값과 실제 번호는 1 차이가 나므로 이를 보정한다.
        		answer[0] = start+1;
        		answer[1] = end+1;
        	}
        	// 구간 내에 모든 보석이 있지 않으면서 끝 번호를 증가시킬 수 있는 경우
        	if (cnt < totalCnt && end < N-1) {
        		int count = included.get(gems[++end]);
        		// 현재 구간에 없는 보석인 경우
        		if (count == 0) {
        			cnt++;
        		}
        		included.put(gems[end], count+1);
        	} else {
        		int count = included.get(gems[start]);
        		// 현재 구간에 하나만 존재하는 보석인 경우
        		// 이를 제거하면 서로 다른 보석의 개수가 하나 줄어든다.
        		if (count == 1) {
        			cnt--;
        		}
        		included.put(gems[start++], count-1);
        	}
        }
        return answer;
    }
}
