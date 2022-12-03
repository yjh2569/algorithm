package string;

import java.util.ArrayList;

public class Solution_lv4_자동완성 {

	public static void main(String[] args) {
		int result = solution(new String[] {"go", "gone", "guild"});
		System.out.println(result);
	}
	// Trie 자료구조
	static class Trie {
		char c; // 문자
		ArrayList<Trie> childs; // 자식 노드들
		int cntOfDesc; // 현재까지의 노드까지 문자를 입력했을 때 나오는 자동완성되는 문자열의 개수
		public Trie(char c) {
			this.c = c;
			this.childs = new ArrayList<>();
			this.cntOfDesc = 1;
		}
		// 자식 노드 추가 메소드
		public void addTrie(Trie t) {
			childs.add(t);
		}
		// 노드 간 비교를 위해 equals 메소드 override
		@Override
		public boolean equals(Object t) {
			if (t instanceof Trie) {
				Trie trie = (Trie) t;
				return this.c == trie.c;
			}
			return false;
		}
	}
	static int answer;
	
	public static int solution(String[] words) {
        answer = 0;
        // 루트 Trie 정의
        Trie root = new Trie('0');
        // 각 word를 이용해 Trie를 만든다.
        for (String word : words) {
        	makeTrie(root, word, 0);
        }
        // 각 word에 대해 최소 몇 개의 문자를 입력해야 자동완성이 되는지 계산
        for (String word : words) {
        	countCharacters(root, word, 0, 0);
        }
        return answer;
    }	
	// word를 자동완성시키기 위해 최소 몇 개의 문자를 입력해야 하는지 계산하는 재귀함수
	// t는 현재 조사 중인 노드, idx는 word 내 조사 중인 문자 인덱스, depth는 Trie 내 노드의 깊이
	private static void countCharacters(Trie t, String word, int idx, int depth) {
		// word 내 모든 문자를 입력해야만 검색이 가능한 경우
		if (word.length() == idx) {
			answer += word.length();
			return;
		}
		// 자동 완성이 가능한 경우
		if (t.c != '0' && t.cntOfDesc == 1) {
			answer += depth;
			return;
		}
		// word의 idx번째 문자를 이용해 임시 노드를 만들어 자식 노드를 찾는다.
		Trie temp = new Trie(word.charAt(idx));
		int childIdx = t.childs.indexOf(temp);
		Trie child = t.childs.get(childIdx);
		// 다음 자식 노드를 재귀적으로 탐색
		countCharacters(child, word, idx+1, depth+1);
	}
	// Trie를 재귀적으로 만드는 함수
	// t라는 현재 노드에 word의 idx번째 문자를 이용해 자식 노드를 만들거나 자동완성 개수를 늘린다.
	private static void makeTrie(Trie t, String word, int idx) {
		// base condition
		if (word.length() == idx) return;
		Trie child = new Trie(word.charAt(idx));
		// t의 자식 노드들 중 word의 idx번째 문자를 가진 노드가 없는 경우
		if (!t.childs.contains(child)) {
			t.addTrie(child);
		// t의 자식 노드들 중 word의 idx번째 문자를 가진 노드가 이미 있는 경우
		} else {
			// 해당 노드를 찾는다.
			child = t.childs.get(t.childs.indexOf(child));
			// 그 노드의 자동완성 개수를 1 증가시킨다.
			child.cntOfDesc++;
		}
		// 위 과정을 재귀적으로 수행
		makeTrie(child, word, idx+1);
	}
}
