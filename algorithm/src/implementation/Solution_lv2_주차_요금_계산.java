package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_lv2_주차_요금_계산 {

	public static void main(String[] args) {
		int[] result = solution(new int[] {180, 5000, 10, 600}, new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
		System.out.println(Arrays.toString(result));
	}
	
	public static int[] solution(int[] fees, String[] records) {
		// 각 차량이 입차한 시각을 저장하는 map
        Map<Integer, Integer> parking_times = new HashMap<>();
        // 각 차량이 주차장에 주차한 총 시간을 저장하는 map
        Map<Integer, Integer> parking_minutes = new HashMap<>();
        for (String s : records) {
            StringTokenizer st = new StringTokenizer(s);
            StringTokenizer time = new StringTokenizer(st.nextToken(), ":");
            // 시간을 분 단위로 바꾼다.
            int minute = Integer.parseInt(time.nextToken())*60+Integer.parseInt(time.nextToken());
            int car_num = Integer.parseInt(st.nextToken());
            String status = st.nextToken();
            // 해당 차가 입차한 경우
            if (status.equals("IN")) {
                parking_times.put(car_num, minute);
            // 해당 차가 출차한 경우
            } else {
            	// 주차장에 주차한 총 시간을 갱신
                int parking_min = minute - parking_times.get(car_num);
                if (parking_minutes.get(car_num) == null) parking_minutes.put(car_num, parking_min);
                else parking_minutes.put(car_num, parking_min + parking_minutes.get(car_num));
                // 해당 차의 입차 기록을 제거
                parking_times.put(car_num, -1);
            }
        }
        for (Integer car_num : parking_times.keySet()) {
        	// 만약 입차 기록이 남아있다면, 해당 차량은 출차 기록이 별도로 없다는 의미이므로 23시 59분까지의 주차 기록을 저장
            if (parking_times.get(car_num) != -1) {
                int parking_min = 23*60+59 - parking_times.get(car_num);
                if (parking_minutes.get(car_num) == null) parking_minutes.put(car_num, parking_min);
                else parking_minutes.put(car_num, parking_min + parking_minutes.get(car_num));
                parking_times.put(car_num, -1);
            }
        }
        // 각 차량의 주차 비용을 저장하는 배열
        ArrayList<int[]> car_fees = new ArrayList<>();
        for (Integer car_num : parking_minutes.keySet()) {
            int minute = parking_minutes.get(car_num);
            int fee = 0;
            // 주차 요금 계산 공식에 맞게 계산
            if (minute <= fees[0]) fee += fees[1];
            else {
                fee += fees[1] + ((minute - fees[0] + fees[2] - 1) / fees[2]) * fees[3];
            }
            car_fees.add(new int[] {car_num, fee});
        }
        // 차량 번호 순서대로 정렬
        Collections.sort(car_fees, (x, y) -> Integer.compare(x[0], y[0]));
        int[] answer = new int[car_fees.size()];
        for (int i = 0; i < car_fees.size(); i++) {
            answer[i] = car_fees.get(i)[1];
        }
        return answer;
    }

}
