package bruteforce;

import java.util.*;

public class Solution_lv2_메뉴_리뉴얼 {
	
	static ArrayList<String> menu;
	static Map<String, Integer> cnts;
	
	public static void main(String[] args) {
		String[] result = solution(new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[] {2,3,4});
		System.out.println(Arrays.toString(result));
	}
	
	public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        // 가능한 음식 메뉴 조합에 대해 해당 음식 조합을 먹은 사람의 수
        cnts = new HashMap<>();
        // 추가할 음식 메뉴 조합을 모아둔 ArrayList
        menu = new ArrayList<>();
        // course 내 각 음식 조합에 포함되는 음식 수에 대해 해당 조합을 먹은 사람 수의 최대값
        int[] maxs = new int[course.length];
        // 가능한 음식 메뉴 조합을 모두 찾는다.
        for (int n : course) {
        	for (String s : orders) {
        		combi(0, 0, n, s, new StringBuilder());
        	}
        }
        // course 내 각 수에 대해 해당 수만큼의 음식을 가진 음식 조합 중 가장 많이 찾는 음식 조합을 menu에 넣는다.
        for (int i = 0; i < course.length; i++) {
        	int l = course[i];
        	for (String s : cnts.keySet()) {
        		if (s.length() != l) continue;
        		maxs[i] = Math.max(maxs[i], cnts.get(s));
        	}
        }
        for (int i = 0; i < course.length; i++) {
        	int l = course[i];
        	for (String s : cnts.keySet()) {
        		if (s.length() == l && maxs[i] == cnts.get(s)) {
        			menu.add(s);
        		}
        	}
        }
        // 사전 순으로 정렬
        Collections.sort(menu);
        answer = menu.toArray(new String[menu.size()]);
        return answer;
    }
	// 조합을 통해 가능한 음식 조합을 찾는다.
	private static void combi(int cnt, int start, int n, String s, StringBuilder sb) {
		if (cnt == n) {
			String res = sb.toString();
			char[] cs = res.toCharArray();
			Arrays.sort(cs);
			res = new String(cs);
			cnts.putIfAbsent(res, 0);
			cnts.put(res, cnts.get(res)+1);
			return;
		}
		for (int i = start; i < s.length(); i++) {
			sb.append(s.charAt(i));
			combi(cnt+1, i+1, n, s, sb);
			sb.setLength(sb.length()-1);
		}
				
	}
	
	

}
