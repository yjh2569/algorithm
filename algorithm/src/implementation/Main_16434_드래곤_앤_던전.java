package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16434_드래곤_앤_던전 {
	// 각 방에 있는 것이 무엇인지를 나타내는 enum
	static enum Type {
		POTION, MONSTER;
	}
	// 각 방의 정보를 저장하는 클래스
	static class Room {
		long a, h;		
		Type type;
		public Room(int typeNum, int a, int h) {
			type = typeNum == 1 ? Type.MONSTER : Type.POTION;
			this.a = a;
			this.h = h;
		}
		// 방에 들어갔을 때 감소 혹은 증가하는 생명력을 구하는 함수
		public long enter() {
			long HP = 0;
			// 몬스터를 만난 경우 h/ATK번 공격해야 몬스터를 무찌를 수 있다.
			// 이때 용사의 감소하는 생명력은 a*(h-1)/ATK로 구한다.
			// 이는 h%ATK=0 & h/ATK=k인 경우와, h%ATK!=0 & h/ATK=k-1인 경우가
			// 동일하게 k-1번 생명력이 감소하기 때문이다.
			if (type == Type.MONSTER) {
				long count = (h-1)/ATK;
				HP -= count*a;
			// 포션을 만난 경우 공격력과 생명력을 증가시킨다.
			} else {
				ATK += a;
				HP += h;
			}
			return HP;
		}
	}
	static long ATK;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		ATK = Long.parseLong(st.nextToken());
		// N번 방에 있는 용을 쓰러뜨리기 위한 최소 생명력을 구하기 위해,
		// 각 방에 들어설 때 생명력의 변화를 기록한다.
		// 단, 처음 던전에 입장할 때 생명력이 최대 생명력인 상태에서 입장하므로 생명력의 변화가 양수가 될 수는 없다.
		long curHP = 0; // 각 방에 들어설 때 생명력의 변화
		long minHP = 0; // 던전 탐험 중 가장 생명력이 낮을 때의 생명력의 변화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			Room room = new Room(t, a, h);
			// 생명력의 변화가 양수가 될 수 없음에 유의한다.
			curHP = Math.min(curHP + room.enter(), 0);
			minHP = Math.min(curHP, minHP);
		}
		// N번 방에 도달해 용을 쓰러뜨리기 위해서는 (최대 생명력) + minHP >= 1이어야 한다.
		// 따라서 최소의 최대 생명력은 (-minHP + 1)이다.
		System.out.println(-minHP+1);
	}
}
