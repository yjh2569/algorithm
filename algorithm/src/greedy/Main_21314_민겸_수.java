package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_21314_민겸_수 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		// M이 연속으로 나타난 횟수
		int cnt = 0;
		// 민겸 수가 십진수로 변환되었을 때 가장 큰 값
		StringBuilder max = new StringBuilder();
		// 민겸 수가 십진수로 변환되었을 때 가장 작은 값
		StringBuilder min = new StringBuilder();
		// greedy algorithm을 적용한다.
		// 연속되는 M의 개수를 세다가, 만약 K를 만나면 가장 큰 값을 만들기 위해서는 
		// 연속되는 M과 K 한 개를 합쳐서 하나의 민겸 수로 간주해야 한다.
		// 그렇지 않으면 항상 위에서 나온 수보다 작은 결과를 얻는다.
		// 가장 작은 값을 만들기 위해서는 
		// 연속되는 M과 K를 분리해 다른 민겸 수로 간주해야 한다.
		// 그렇지 않으면 항상 위에서 나온 수보다 큰 결과를 얻는다.
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'M') cnt++;
			else {
				max.append(5);
				for (int j = 0; j < cnt; j++) max.append(0);
				if (cnt > 0) min.append(1);
				for (int j = 0; j < cnt-1; j++) min.append(0);
				min.append(5);
				cnt = 0;
			}
		}
		// K로 끝나지 않은 경우
		// M으로만 이루어진 민겸 수의 경우 가장 큰 값을 만들려면 M을 모두 1로 간주해야 하고,
		// 가장 작은 값을 만들려면 연속되는 M들을 모두 하나의 민겸 수로 간주해야 한다.
		if (cnt > 0) {
			for (int j = 0; j < cnt; j++) max.append(1);
			min.append(1);
			for (int j = 0; j < cnt-1; j++) min.append(0);
		}
		System.out.println(max.toString());
		System.out.println(min.toString());
	}

}
