package implementation;

import java.util.HashMap;
import java.util.Map;

public class Solution_lv1_키패드_누르기 {

	public static void main(String[] args) {
		String result = solution(new int[] { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 }, "right");
		System.out.println(result);
	}
	// 각 버튼 및 손가락의 위치를 나타내는 클래스
	static class Location {
		int r, c;
		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
		// 두 버튼 사이의 거리를 구하는 메소드
		public int getDistance(Location b) {
			return Math.abs(this.r - b.r) + Math.abs(this.c - b.c);
		}
		// 버튼이 왼쪽에 있는지를 확인하는 함수
		public boolean isLeft() {
			return c == 0;
		}
		// 버튼이 중앙에 있는지를 확인하는 함수
		public boolean isCenter() {
			return c == 1;
		}
		// 버튼이 오른쪽에 있는지를 확인하는 함수
		public boolean isRight() {
			return c == 2;
		}
	}

	public static String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder();
		// 각 버튼에 적힌 숫자를 키로, 버튼의 위치 값으로 하는 Map 
		Map<Integer, Location> map = new HashMap<>();
		// 버튼 추가
		for (int i = 1; i <= 9; i++) {
			map.put(i, new Location((i - 1) / 3, (i - 1) % 3));
		}
		map.put(0, new Location(3, 1));
		// 왼손 엄지의 위치
		Location leftHand = new Location(3, 0);
		// 오른손 엄지의 위치
		Location rightHand = new Location(3, 2);
		// 각 숫자에 대해 조건에 따라 엄지를 이동시키며 버튼을 누른다.
		for (int number : numbers) {
			Location loc = map.get(number);
			if (loc.isLeft()) changeHand(leftHand, loc, answer, false);
			else if (loc.isRight()) changeHand(rightHand, loc, answer, true);
			else if (loc.isCenter()) {
				if (leftHand.getDistance(loc) < rightHand.getDistance(loc)) changeHand(leftHand, loc, answer, false);
				else if (leftHand.getDistance(loc) > rightHand.getDistance(loc)) changeHand(rightHand, loc, answer, true);
				else if (hand.equals("left")) changeHand(leftHand, loc, answer, false);
				else changeHand(rightHand, loc, answer, true);
			}
		}
		return answer.toString();
	}
	// hand의 위치를 loc으로 이동시키고, isRight의 값에 따라 answer에 이동한 엄지를 기록하는 함수
	private static void changeHand(Location hand, Location loc, StringBuilder answer, boolean isRight) {
		if (isRight) answer.append("R");
		else answer.append("L");
		hand.r = loc.r;
		hand.c = loc.c;
	}

}
