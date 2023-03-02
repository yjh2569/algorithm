package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1360_되돌리기 {
	// 각 명령을 수행했을 때 텍스트와 명령을 수행한 시간을 저장하는 클래스
	static class Result {
		String text;
		int time;
		public Result(String text, int time) {
			this.text = text;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 각 명령에 대한 결과를 저장하는 배열
		Result[] results = new Result[N+1];
		// 초기화
		results[0] = new Result("", 0);
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			// 명령이 type인 경우
			if ("type".equals(command)) {
				char c = st.nextToken().charAt(0);
				int time = Integer.parseInt(st.nextToken());
				// 주어진 문자를 이전 명령의 결과의 텍스트에 추가한다.
				results[i] = new Result(results[i-1].text + c, time);
			// 명령이 undo인 경우
			} else if ("undo".equals(command)) {
				int l = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				int to = i-1; // 되돌아갈 텍스트가 results 배열 내 어디에 있는지를 찾기 위한 인덱스
				// time초때 l초만큼 되돌리기를 하면 (time - l)초 이전의 문자열로 되돌아간다.
				while (to > 0 && time - l <= results[to].time) to--;
				results[i] = new Result(results[to].text, time);
			}
		}
		// N초 때 문자열을 출력한다.
		System.out.println(results[N].text.toString());
	}

}
