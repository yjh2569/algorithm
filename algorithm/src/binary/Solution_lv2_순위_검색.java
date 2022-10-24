package binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv2_순위_검색 {
	static Map<String, Integer> lang;
	static Map<String, Integer> job;
	static Map<String, Integer> worktime;
	static Map<String, Integer> food;
	static ArrayList<Integer>[][][][] list;
	static String[] ss;
	public static void main(String[] args) {
		int[] result = solution(new String[] {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"}, new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"});
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        // 코딩테스트 참여 개발언어 항목
        lang = new HashMap<>();
        lang.put("-", 0); lang.put("cpp", 1); lang.put("java", 2); lang.put("python", 3);
        // 지원 직군
        job = new HashMap<>();
        job.put("-", 0); job.put("backend", 1); job.put("frontend", 2);
        // 경력
        worktime = new HashMap<>();
        worktime.put("-", 0); worktime.put("junior", 1); worktime.put("senior", 2);
        // 소울 푸드
        food = new HashMap<>();
        food.put("-", 0); food.put("chicken", 1); food.put("pizza", 2);
        // 각 항목들의 내용을 가지고 분류하기 위해 ArrayList의 배열을 만든다.
        list = new ArrayList[4][3][3][3];
        // 초기화
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 3; j++) {
        		for (int k = 0; k < 3; k++) {
        			for (int l = 0; l < 3; l++) {
        				list[i][j][k][l] = new ArrayList<>();
        			}
        		}
        	}
        }
        // 각 응시자의 정보를 바탕으로 list에 코딩테스트 점수를 넣는다.
        for (String s : info) {
        	ss = s.split(" ");
        	for (int i : new int[] {0, lang.get(ss[0])}) {
        		for (int j : new int[] {0, job.get(ss[1])}) {
        			for (int k : new int[] {0, worktime.get(ss[2])}) {
        				for (int l : new int[] {0, food.get(ss[3])}) {
        					list[i][j][k][l].add(Integer.parseInt(ss[4]));
        				}
        			}
        		}
        	}
        }
        // 이분 탐색을 통해 효율적으로 특정 점수 이상의 응시자 수를 찾는다.
        // 이를 위해 우선 각 ArrayList를 정렬한다.
        for (int i = 0; i < 4; i++) {
        	for (int j = 0; j < 3; j++) {
        		for (int k = 0; k < 3; k++) {
        			for (int l = 0; l < 3; l++) {
        				Collections.sort(list[i][j][k][l]);
        			}
        		}
        	}
        }
        int idx = 0;
        // 각 query에 대해 해당 query에 대한 답을 찾을 수 있는 ArrayList를 찾고 여기서 이분 탐색을 통해
        // query를 만족하는 응시자 수를 찾는다.
        for (String s : query) { 
        	ss = s.split(" ");
        	ArrayList<Integer> arr = list[lang.get(ss[0])][job.get(ss[2])][worktime.get(ss[4])][food.get(ss[6])];
        	int l = binarySearch(arr, Integer.parseInt(ss[7]));
        	answer[idx++] = arr.size() - l;
        }
        return answer;
    }
	// 이분 탐색을 통해 arr 내에서 score 이상의 값을 가진 수의 개수를 센다.
	private static int binarySearch(ArrayList<Integer> arr, int score) {
		int l = 0; int r = arr.size();
		while (l < r) {
			int mid = (l+r)/2;
			if (arr.get(mid) < score) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return l;
	}
}
