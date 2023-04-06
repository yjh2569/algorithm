package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23349_졸업_사진 {
	// 예약 정보의 위치, 시작 시간, 종료 시간을 저장하는 클래스
	static class Reservation {
		String location;
		int startTime, endTime;
		public Reservation(String location, int startTime, int endTime) {
			this.location = location;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Reservation> reservations = new LinkedList<>(); // 예약을 저장하는 큐
		ArrayList<String> checkedNames = new ArrayList<>(); // 예약한 사람의 이름을 저장하는 ArrayList
		ArrayList<String> checkedLocations = new ArrayList<>(); // 예약한 장소를 저장하는 ArrayList
		int maxTime = 0; // 종료 시간 중 가장 늦은 시간
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String location = st.nextToken();
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			// 이미 예약한 사람이면 예약을 더 이상 할 수 없으므로 넘어간다.
			if (checkedNames.contains(name)) continue;
			checkedNames.add(name);
			// checkedLocations에는 장소가 중복되지 않도록 저장한다.
			if (!checkedLocations.contains(location)) checkedLocations.add(location);
			reservations.offer(new Reservation(location, startTime, endTime));
			maxTime = Math.max(maxTime, endTime);
		}
		// 장소를 사전 순으로 정렬한다.
		Collections.sort(checkedLocations);
		int numOfLocations = checkedLocations.size(); // 장소의 총 개수
		// 각 장소에 대해 특정 시간에 몇 명의 인원이 예약했는지를 나타내는 배열
		// numsOfReservations[i][j]는 i번째 장소에 j 시각에 몇 명이 예약했는지를 의미한다.
		int[][] numsOfReservations = new int[numOfLocations][maxTime+1];
		// 예약을 하나씩 꺼내 numsOfReservations에 반영한다.
		while (!reservations.isEmpty()) {
			Reservation r = reservations.poll();
			int idxOfLocation = checkedLocations.indexOf(r.location);
			// 시작 시간과 종료 시간에 표시만 해둔다.
			numsOfReservations[idxOfLocation][r.startTime]++;
			numsOfReservations[idxOfLocation][r.endTime]--;
		}
		// 누적합을 이용해 한 번에 numsOfReservations의 값을 구한다.
		for (int i = 0; i < numOfLocations; i++) {
			for (int j = 1; j <= maxTime; j++) {
				numsOfReservations[i][j] += numsOfReservations[i][j-1];
			}
		}
		int maxLocationIdx = 0; // 예약한 인원이 가장 많은 시간대에 해당하는 장소가 몇 번째 장소인지를 나타내는 변수
		int maxStartTime = 0; // 예약한 인원이 가장 많은 시간대의 시작 시간
		int maxEndTime = 0; // 예약한 인원이 가장 많은 시간대의 종료 시간
		int maxNum = 0; // 예약한 인원이 가장 많은 시간대의 예약 인원
		// 예약한 인원이 가장 많은 시간대가 여러 개인 경우를 대비해 사전 순으로 정렬된 장소 순서대로, 
		// 그리고 시작 시간이 빠른 순서대로 확인한다.
		for (int i = 0; i < numOfLocations; i++) {
			for (int j = 0; j <= maxTime; j++) {
				// 예약한 인원이 더 많은 시간대를 발견한 경우 갱신한다.
				if (maxNum < numsOfReservations[i][j]) {
					maxLocationIdx = i;
					maxStartTime = j;
					maxNum = numsOfReservations[i][j];
					// 해당 시간대의 종료 시간을 확인한다.
					while (j <= maxTime && numsOfReservations[i][j] == numsOfReservations[i][maxStartTime]) j++;
					// 위 while 문으로 인해 j는 numsOfReservation[i][j]의 값이 변했을 때의 값일 가능성이 있다.
					// 따라서 j를 1 감소시켜 다음 step에서 j를 한 칸 건너뛰지 않도록 한다.
					maxEndTime = j--;  
				}
			}
		}
		// 예약한 인원이 가장 많은 시간대의 예약 장소
		String maxLocation = checkedLocations.get(maxLocationIdx);
		System.out.println(maxLocation+" "+maxStartTime+" "+maxEndTime);
	}

}
