package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_lv2_방금그곡 {

	public static void main(String[] args) {
		String result = solution("ABC", new String[] {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"});
		System.out.println(result);
	}
	// 음악 정보를 저장하는 클래스
	static class MusicInfo implements Comparable<MusicInfo> {
		int playtime; // 라디오 재생 시간
		int idx; // 입력 순서
		String title; // 제목
		String[] melody; // 악보 정보
		public MusicInfo(int playtime, int idx, String title, String[] melody) {
			this.playtime = playtime;
			this.idx = idx;
			this.title = title;
			this.melody = melody;
		}
		// 조건 일치하는 악보가 여러 개일 경우 라디오 재생 시간이 긴 순서대로, 같은 경우 입력 순서대로 정렬한 후
		// 순위가 가장 높은 악보를 꺼낸다.
		public int compareTo(MusicInfo m) {
			return this.playtime == m.playtime ? Integer.compare(this.idx, m.idx) : Integer.compare(m.playtime, this.playtime);
		}
	}
	
	public static String solution(String m, String[] musicinfos) {
        int N = musicinfos.length;
        // 문자열로 이루어진 곡 정보를 MusicInfo 클래스의 인스턴스로 바꾼다. 
        MusicInfo[] musicInfoArr = new MusicInfo[N];
        for (int i = 0; i < N; i++) {
        	String musicinfo = musicinfos[i];
        	musicInfoArr[i] = changeToMusicInfo(i, musicinfo);
        }
        // 조건에 부합하는 곡을 저장하는 ArrayList
        ArrayList<MusicInfo> matchedMusic = new ArrayList<>();
        // 기억하는 멜로디를 문자열의 배열로 바꾼다.
        String[] rememberedMelody = changeToMelodyArr(m);
        // 조건에 부합하는 곡을 찾는다.
        for (int i = 0; i < N; i++) {
        	MusicInfo musicInfo = musicInfoArr[i];
        	if (isMatched(rememberedMelody, musicInfo)) {
        		matchedMusic.add(musicInfo);
        	}
        }
        // 조건에 부합하는 곡이 여러 곡일 때를 대비해 정렬한 후 우선순위가 가장 높은 곡을 선택
        Collections.sort(matchedMusic);
        return matchedMusic.size() == 0 ? "(None)" : matchedMusic.get(0).title;
    }
	// 문자열로 이루어진 곡 정보를 분석해 MusicInfo 클래스의 인스턴스로 변환
	// idx는 입력 순서
	private static MusicInfo changeToMusicInfo(int idx, String musicinfo) {
		StringTokenizer st = new StringTokenizer(musicinfo, ",");
    	int startTime = changeToMin(st.nextToken());
    	int endTime = changeToMin(st.nextToken());
    	String title = st.nextToken();
    	String melody = st.nextToken(); 
    	return new MusicInfo(endTime - startTime, idx, title, changeToMelodyArr(melody));
	}
	// 문자열로 이루어진 멜로디 혹은 악보를 음의 배열로 바꾼다.
	private static String[] changeToMelodyArr(String melody) {
		ArrayList<String> melodyList = new ArrayList<>(); 
    	for (int i = 0; i < melody.length(); i++) {
    		// #이 포함된 음인 경우
    		if (i < melody.length()-1 && melody.charAt(i+1) == '#') {
    			melodyList.add(melody.substring(i, i+2));
    			i++;
    		} else {
    			melodyList.add(melody.charAt(i)+"");
    		}
    	}
    	return melodyList.toArray(new String[melodyList.size()]);
	}
	// 문자열로 주어진 시간을 분 단위로 바꾸는 함수
	private static int changeToMin(String time) {
		StringTokenizer st = new StringTokenizer(time, ":");
		int hours = Integer.parseInt(st.nextToken());
		int minutes = Integer.parseInt(st.nextToken());
		return hours*60+minutes;
	}
	// rememberedMelody라는 음들의 배열이 musicInfo의 악보 정보 안에 들어있는지를 확인하는 함수
	private static boolean isMatched(String[] rememberedMelody, MusicInfo musicInfo) {
		String[] melody = musicInfo.melody;
		int playtime = musicInfo.playtime;
		int l = melody.length;
		// 재생시간을 넘어가는 경우를 잘 처리해야 함에 유의
		outer: for (int i = 0; i < Math.min(playtime, l); i++) {
			if (!melody[i].equals(rememberedMelody[0])) continue;
			for (int j = 0; j < rememberedMelody.length; j++) {
				if (i+j >= playtime || !melody[(i+j)%l].equals(rememberedMelody[j])) continue outer;
			}
			return true;
		}
		return false;
	}
}
