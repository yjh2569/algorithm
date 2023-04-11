package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21775_가희와_자원_놀이 {
	// 연산 카드의 id, 연산을 나타내는 클래스
	static class OperationCard {
		int id, n;
		String operation;
		public OperationCard(int id, String operation, int n) {
			this.id = id;
			this.operation = operation;
			this.n = n;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[] turns = new int[T];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < T; i++) {
			turns[i] = Integer.parseInt(st.nextToken());
		}
		// 연산 카드들을 저장하는 큐
		Queue<OperationCard> opCards = new LinkedList<>();
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			String operation = st.nextToken();
			// next 연산인 경우 n이 존재하지 않으므로, n을 0으로 한다.
			int n = 0;
			if (!"next".equals(operation)) n = Integer.parseInt(st.nextToken());
			opCards.offer(new OperationCard(id, operation, n));
		}
		// 공용 공간에 자연수 n이 적힌 자원 카드가 존재하는지를 나타내는 HashMap
		// key로 자연수 n을 가지고 있지 않거나, 자연수 n에 대응하는 값이 true인 경우 공용 공간에 자연수 n이 적힌 자원 카드가 존재한다.
		Map<Integer, Boolean> publicArea = new HashMap<>();
		// 개인이 가지고 있는 연산 카드들을 저장하는 배열
		// 개인이 가지고 있는 연산 카드가 없으면 null 값을 가진다.
		OperationCard[] privateCards = new OperationCard[N+1];
		// 출력 저장용 StringBuilder
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			int turn = turns[i];
			OperationCard privateCard = privateCards[turn]; // 개인이 가지고 있는 연산 카드
			// 연산 카드를 가지고 있는 경우
			if (privateCard != null) {
				// 해당 연산 카드의 연산을 수행한다.
				// 다만, 연산 카드 중 개인이 가질 수 있는 연산 카드는 acquire 연산 카드뿐이므로 acquire 연산을 수행한다.
				sb.append(privateCard.id).append("\n");
				// 공용 공간에 자연수 n이 적힌 자원 카드가 있으면 해당 자원 카드를 가져오고,
				// 그렇지 않으면 개인 공간에 연산 카드를 그대로 둔 채 다음 턴으로 넘어간다.
				if (!publicArea.containsKey(privateCard.n) || publicArea.get(privateCard.n)) {
					publicArea.put(privateCard.n, false);
					privateCards[turn] = null; // 개인이 가지고 있던 연산 카드를 버린다.
				}
			// 연산 카드를 가지고 있지 않은 경우
			} else {
				// 새로운 연산 카드를 하나 뽑아 연산 카드의 연산을 수행한다.
				OperationCard newCard = opCards.poll();
				sb.append(newCard.id).append("\n");
				// 연산 카드의 연산이 next면 별도의 작업이 필요없으므로 바로 다음 턴으로 넘어간다.
				// 연산 카드의 연산이 acquire이면 공용 공간에 자연수 n이 적힌 자원 카드가 있으면 가져오고, 
				// 그렇지 않으면 개인 공간에 해당 연산 카드를 넣는다.
				if ("acquire".equals(newCard.operation)) {
					if (!publicArea.containsKey(newCard.n) || publicArea.get(newCard.n)) {
						publicArea.put(newCard.n, false);
					} else {
						privateCards[turn] = newCard;
					}
				// 연산 카드의 연산이 release이면 개인 공간에 있는 자연수 n이 적힌 자원 카드를 공용 공간에 넣는다.
				// 입력의 조건에 따라 release 연산 수행 시 항상 개인 공간에 자연수 n이 적힌 자원 카드가 있다.
				} else if ("release".equals(newCard.operation)) {
					publicArea.put(newCard.n, true);
				}
			}
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
