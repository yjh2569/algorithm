package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_lv2_오픈채팅방 {

	public static void main(String[] args) {
		String[] result = solution(new String[] {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"});
		System.out.println(Arrays.toString(result));
	}
	
	public static String[] solution(String[] record) {
        int N = record.length;
        // 유저 ID마다 최종 변경된 닉네임을 저장하는 Map
        Map<String, String> nicknames = new HashMap<>();
        // 각 유저 ID마다 가장 마지막 닉네임이 무엇인지를 조사한다.
        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(record[i]);
        	String action = st.nextToken();
        	if (action.equals("Enter") || action.equals("Change")) {
        		nicknames.put(st.nextToken(), st.nextToken());
        	}
        }
        // 들어오거나 나가는 기록을 저장하기 위한 ArrayList
        ArrayList<String> answer = new ArrayList<>();
        // 마지막 닉네임을 이용해 문구를 만들어 저장한다.
        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(record[i]);
        	String action = st.nextToken();
        	if (action.equals("Enter")) {
        		answer.add(nicknames.get(st.nextToken())+"님이 들어왔습니다.");
        	} else if (action.equals("Leave")) {
        		answer.add(nicknames.get(st.nextToken())+"님이 나갔습니다.");
        	}
        }
        // Array로 변환해서 반환
        return answer.toArray(new String[answer.size()]);
    }

}
