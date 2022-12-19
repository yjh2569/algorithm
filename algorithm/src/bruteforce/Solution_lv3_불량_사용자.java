package bruteforce;

import java.util.HashSet;
import java.util.Set;

public class Solution_lv3_불량_사용자 {

	public static void main(String[] args) {
		int result = solution(new String[] {"frodo", "fradi", "crodo", "abc123", "frodoc"}, new String[] {"*rodo", "*rodo", "******"});
		System.out.println(result);
	}
	static int N, M;
	static boolean[] v;
	static Set<Integer> answerSet;
	public static int solution(String[] user_id, String[] banned_id) {
        answerSet = new HashSet<>(); // 가능한 제재 아이디 명단을 비트로 표현한 결과를 저장하는 Set 
        N = user_id.length;
        M = banned_id.length;
        v = new boolean[M]; // 불량 사용자 아이디 각각에 대해 제재한 아이디가 있는지를 나타내는 방문 배열
        // 조합을 이용해 user_id 내에서 가능한 제재 아이디 목록의 경우의 수를 구한다.
        combi(0, 0, user_id, banned_id, 0);
        return answerSet.size();
    }
	// 조합을 통해 사용자 아이디 중 제재할 아이디를 고르는 함수
	// bit는 비트마스킹을 통해 user_id 중 현재 제재한 아이디 목록을 표현한 정수
	private static void combi(int cnt, int start, String[] user_id, String[] banned_id, int bit) {
		// 제재 목록을 다 채운 경우
		if (cnt == M) {
			// set에 bit를 집어넣는 형식을 통해 중복되는 경우를 제외한다.
			answerSet.add(bit);
			return;
		}
		// 각 사용자 아이디에 대해 순회
		for (int i = start; i < N; i++) {
			// 제재 아이디 목록을 순회
			for (int j = 0; j < M; j++) {
				// 이미 제재 아이디 목록에서 참고했거나 제재할 수 있는 아이디가 아닌 경우
				if (v[j] || !checkIfBanned(user_id[i], banned_id[j])) continue;
				v[j] = true;
				combi(cnt+1, i+1, user_id, banned_id, bit + 1 << i);
				v[j] = false;
			}
		}
	}
	// userId가 bannedId 형식에 맞는 아이디인지 확인하는 함수
	private static boolean checkIfBanned(String userId, String bannedId) {
		if (userId.length() != bannedId.length()) return false;
		for (int i = 0; i < userId.length(); i++) {
			char b = bannedId.charAt(i);
			char u = userId.charAt(i);
			if (b != '*' && b != u) return false;
		}
		return true;
	}

}
