package implementation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_lv3_매칭_점수 {

	public static void main(String[] args) {
		int result = solution("Muzi", new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"});
		System.out.println(result);
	}
	
	public static int solution(String word, String[] pages) {
        int N = pages.length;
        // 단어 검색은 대소문자를 무시하고 검색하기 때문에 소문자로 변환한다.
        word = word.toLowerCase();
        // 각 page의 url을 저장하는 배열
        String[] urls = new String[N];
        // 각 page의 기본 점수를 저장하는 배열
        int[] basicScore = new int[N];
        // 각 page의 외부 링크 수를 저장하는 배열
        int[] externalLinks = new int[N];
        // 각 page의 url, 인덱스를 키와 값으로 가지는 Map
        Map<String, Integer> idxs = new HashMap<>();
        // 각 page의 링크 점수를 저장하는 배열
        double[] linkScore = new double[N];
        for (int i = 0; i < N; i++) {
        	String page = pages[i];
        	// 소문자로 변환
        	page = page.toLowerCase();
        	// page의 url 찾기
        	// tag 형태가 항상 <meta property="og:url" content="URL"... 임을 이용
        	String temp = page.split("<meta property=\"og:url\" content=\"")[1];
        	String url = temp.split("\"")[0];
        	urls[i] = url;
        	idxs.put(urls[i], i);
        	// 단어 검색을 위해 word를 이용해 split한다.
        	String[] splittedByWord = page.split(word);
        	// page 내 word가 단어로 나오는 경우의 수
        	int cnt = 0;
        	for (int j = 0; j < splittedByWord.length-1; j++) {
        		// word가 연속해서 나오지 않으면서 온전한 단어로 나오는 경우에만 cnt를 1 증가시킨다.
        		if (splittedByWord[j].length() == 0 || splittedByWord[j+1].length() == 0 || 
        				isAlphabet(splittedByWord[j].charAt(splittedByWord[j].length()-1)) ||
        				isAlphabet(splittedByWord[j+1].charAt(0))) continue;
        		cnt++;
        	}
        	// 외부 링크 수는 <a href="가 몇 번 나오는지를 통해 계산
        	String[] splittedByATag = page.split("<a href=\"");
        	externalLinks[i] = splittedByATag.length-1;
        	basicScore[i] = cnt;
        }
        // 링크 점수 계산
        for (int i = 0; i < N; i++) {
        	String page = pages[i];
        	String[] splittedByATag = page.split("<a href=\"");
        	// 각 링크에 대해 해당 링크를 URL로 가지는 page의 링크 점수를 계산
        	for (int j = 1; j < splittedByATag.length; j++) {
        		String link = splittedByATag[j].split("\"")[0];
        		// 아무 관계없는 링크인 경우
        		if (idxs.get(link) == null) continue;
        		int linkIdx = idxs.get(link);
        		linkScore[linkIdx] += basicScore[i]*1.0/externalLinks[i];
        	}
        }
        // 매칭 점수가 최대가 되는 인덱스를 구한다.
        int maxIdx = 0;
        double max = 0;
        for (int i = 0; i < N; i++) {
        	if (max < basicScore[i] + linkScore[i]) {
        		max = basicScore[i] + linkScore[i];
        		maxIdx = i;
        	}
        }
        return maxIdx;
    }
	// c가 알파벳 소문자인지를 확인하는 함수
	private static boolean isAlphabet(char c) {
		return 'a'<=c && c<='z';
	}

}
