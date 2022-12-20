package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv1_튜플 {

	public static void main(String[] args) {
		int[] result = solution("{{4,2,3},{3},{2,3,4,1},{2,3}}");
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(String s) {
		// 문자열을 정수 집합들의 ArrayList로 바꾼다.
        ArrayList<int[]> sets = changeStringToSets(s);
        // 배열 크기 순서대로 정렬
        Collections.sort(sets, (x, y) -> Integer.compare(x.length, y.length));
        // sets로부터 튜플을 알아낸다.
        return findTuple(sets);
    }
	// 집합으로부터 튜플을 유추하는 함수
	private static int[] findTuple(ArrayList<int[]> sets) {
		// 집합 내 각 정수를 튜플에 포함시켰는지를 나타내는 방문 배열
		Map<Integer, Boolean> visited = new HashMap<>();
        int N = sets.size();
        // 튜플
        int[] answer = new int[N];
        // 크기가 작은 배열부터 조사하면서 튜플을 채워나간다.
        outer: for (int i = 0; i < N; i++) {
        	int[] set = sets.get(i);
        	for (int j = 0; j < set.length; j++) {
        		if (visited.get(set[j]) == null) {
        			visited.put(set[j], true);
        			answer[i] = set[j];
        			continue outer;
        		}
        	}
        }
        return answer;
	}
	// 집합 표현을 정수 배열의 ArrayList로 바꾸는 함수
	private static ArrayList<int[]> changeStringToSets(String s) {
		// 양쪽의 중괄호 표현 제거
		s = s.substring(2, s.length()-2);
		// "},{"을 이용해 분리
		String[] setList = s.split("\\}\\,\\{");
		int N = setList.length;
		// 집합 표현으로부터 만들어지는 정수 배열들을 저장
		ArrayList<int[]> sets = new ArrayList<>();
		// 숫자들만 뽑아내 정수 배열을 만들고 이를 sets에 저장
		for (int i = 0; i < N; i++) {
			String[] stringSet = setList[i].split(",");
			int l = stringSet.length;
			int[] set = new int[l];
			for (int j = 0; j < l; j++) {
				set[j] = Integer.parseInt(stringSet[j]);
			}
			sets.add(set);
		}
		return sets;
	}
}
