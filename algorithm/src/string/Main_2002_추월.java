package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_2002_추월 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 각 차량이 몇 번째로 터널에 들어갔는지를 Map 형태로 표현
		Map<String, Integer> ins = new HashMap<>();
		for (int i = 0; i < N; i++) {
			ins.put(br.readLine(), i);
		}
		// 터널을 나간 차량을 먼저 나간 순서대로 저장하는 배열
		String[] outs = new String[N];
		for (int i = 0; i < N; i++) {
			outs[i] = br.readLine();
		}
		// 반드시 추월한 차량의 수
		int cnt = 0;
		// 터널을 나온 각 차량이 터널에 들어간 순서보다, 
		// 그 차량 뒤에 나온 차량이 들어간 순서가 더 빠른 경우가 하나라도 있다면
		// 해당 차량은 반드시 추월한 차량이라 할 수 있다.
		for (int i = 0; i < N; i++) {
			int idx = ins.get(outs[i]);
			for (int j = i+1; j < N; j++) {
				if (idx > ins.get(outs[j])) {
					cnt++;
					break;
				}
			}
		}
		System.out.println(cnt);
	}

}
