package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1117_색칠_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long W = Long.parseLong(st.nextToken());
		long H = Long.parseLong(st.nextToken());
		long f = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());
		long x1 = Long.parseLong(st.nextToken());
		long y1 = Long.parseLong(st.nextToken());
		long x2 = Long.parseLong(st.nextToken());
		long y2 = Long.parseLong(st.nextToken());
		// 왼쪽 부분을 접었을 때, 왼쪽 끝 부분이 x좌표상으로 어느 부분에 위치하는지를 계산한다.
		// 만약 왼쪽 부분이 오른쪽 끝 부분을 넘지 않는다면 x좌표상으로 f가 될 것이고, 그렇지 않으면 W-f가 된다.
		long xf = W >= 2*f ? f : W-f;
		// 페인트칠한 직사각형이 왼쪽 부분에 모두 반영되는 경우
		if (x2 <= xf) {
			System.out.println(W*H - (x2-x1)*(y2-y1)*(c+1)*2);
		// 페인트칠한 직사각형이 왼쪽 부분에 전혀 반영되지 않는 경우
		} else if (xf <= x1) {
			System.out.println(W*H - (x2-x1)*(y2-y1)*(c+1));
		// 페인트칠한 직사각형이 왼쪽 부분에 일부 반영되는 경우
		} else {
			System.out.println(W*H - (xf-x1)*(y2-y1)*(c+1)*2 - (x2-xf)*(y2-y1)*(c+1));
		}
	}

}
