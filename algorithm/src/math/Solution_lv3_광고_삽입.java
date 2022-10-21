package math;

import java.util.StringTokenizer;

public class Solution_lv3_광고_삽입 {

	public static void main(String[] args) {
		String result = solution("02:03:55", "00:14:15", new String[] {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
		System.out.println(result);
	}
	
	public static String solution(String play_time, String adv_time, String[] logs) {
		// 입력으로 주어지는 시간을 초 단위로 바꾼다.
        int playTimeSec = changeToSec(play_time);
        int advTimeSec = changeToSec(adv_time);
        // 재생 시간 내 각 초마다 존재하는 재생 기록 구간 수를 저장하는 배열
        // 모든 시간은 끝 시간을 미만으로 간주하므로 가장 끝에 있는 시간은 포함하지 않는다.
        int[] cnts = new int[playTimeSec];
        // logs 내 각 재생 기록의 시작 시간과 끝 시간을 초 단위로 바꾸고 시작 시간과 끝 시간에 표시한다.
        for (String log : logs) {
        	StringTokenizer st = new StringTokenizer(log, "-");
        	int startTimeSec = changeToSec(st.nextToken());
        	int endTimeSec = changeToSec(st.nextToken());
        	cnts[startTimeSec]++;
        	// 끝 시간이 playTimeSec인 경우를 제외한다.
        	if (endTimeSec < playTimeSec) cnts[endTimeSec]--;
        }
        // 누적 합을 통해 각 초마다 존재하는 재생 기록 구간 수를 구한다.
        for (int i = 1; i < playTimeSec; i++) {
        	cnts[i] += cnts[i-1];
        }
        // 특정 시간에 광고 재생 시 광고의 누적 재생 시간
        // 입력에 따라 int 범위를 넘어설 수 있음에 유의
        long curSum = 0;
        // 0초에 시작했을 때로 초기화
        for (int i = 0; i < advTimeSec; i++) {
        	curSum += cnts[i];
        }
        // 누적 재생 시간이 가장 길 때 광고 재생 시작 시간
        int maxTimeSec = 0;
        // 가장 긴 누적 재생 시간
        long maxSum = curSum;
        // 광고 재생 시작 시간을 변화시키면서 누적 재생 시간이 가장 긴 경우를 찾는다.
        for (int t = 0; t + advTimeSec < playTimeSec; t++) {
        	// t초일 때 재생 기록 구간 수를 빼고 (t+advTimeSec)초일 때 재생 기록 구간 수를 더하면 
        	// 광고 재생 시작 시간이 (t+1)초일 때 누적 재생 시간이 나온다.
        	curSum += cnts[t+advTimeSec] - cnts[t];
        	if (curSum > maxSum) {
        		maxTimeSec = t+1;
        		maxSum = curSum;
        	}
        }
        // 위에서 구한 maxTimeSec을 반환값에 맞게 바꾼다.
        StringBuilder sb = new StringBuilder();
        int hour = maxTimeSec/3600;
        sb.append(hour >= 10 ? hour : "0"+hour).append(":");
        int minute = (maxTimeSec/60)%60;
        sb.append(minute >= 10 ? minute : "0"+minute).append(":");
        int second = maxTimeSec%60;
        sb.append(second >= 10 ? second : "0"+second);
        return sb.toString();
    }
	// String으로 주어지는 시간을 초 단위로 바꾼다.
	private static int changeToSec(String time) {
		StringTokenizer st = new StringTokenizer(time, ":");
		int hour = Integer.parseInt(st.nextToken());
		int minute = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());
		return hour*3600 + minute*60 + second;
	}
	

}
