package math;

import java.util.StringTokenizer;

public class Solution_lv3_추석_트래픽 {

	public static void main(String[] args) {
		int result = solution(new String[] {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"});
		System.out.println(result);
	}
	
	public static int solution(String[] lines) {
        int answer = 0;
        // 24시간을 ms로 나타낸 정수
        int time = 24*60*60*1000;
        // 각 ms마다 처리량을 나타내는 배열
        int[] secs = new int[time];
        int N = lines.length;
        for (int i = 0; i < N; i++) {
        	// 주어진 문자열을 분석해 응답완료시간과 처리 시간을 구한다.
            String[] s = lines[i].split(" ");
            StringTokenizer st = new StringTokenizer(s[1], ":");
            int h = Integer.parseInt(st.nextToken())*60*60*1000;
            int m = Integer.parseInt(st.nextToken())*60*1000;
            StringTokenizer st2 = new StringTokenizer(st.nextToken(), ".");
            int sec = Integer.parseInt(st2.nextToken())*1000;
            int ms = Integer.parseInt(st2.nextToken());
            // 응답완료시간
            int end = h+m+sec+ms;
            // 처리시간
            String duration = s[2].substring(0, s[2].length() - 1);
            // 응답완료시간과 처리시간으로부터 응답시작시간을 구한다.
            int start = Math.max(end-(int)(Double.parseDouble(duration)*1000), 0);
            // 효율적인 계산을 위해 응답시작시간에만 처리량을 1 증가시킨다.
            secs[start]++;
            // 2016년 9월 16일 이후의 시간은 고려하지 않는다.
            if (end+1000-1 < time) secs[end+1000-1]--;
        }
        // 누적합을 통해 각 ms마다 처리량을 효율적으로 계산한다.
        for (int i = 1; i < time; i++) {
            secs[i] += secs[i-1];
        }
        // 초당 최대 처리량을 계산한다.
        for (int i = 0; i < time; i++) {
            answer = Math.max(answer, secs[i]);
        }
        return answer;
    }
}
