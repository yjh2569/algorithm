package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1262_알파벳_다이아몬드 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R1 = Integer.parseInt(st.nextToken());
		int C1 = Integer.parseInt(st.nextToken());
		int R2 = Integer.parseInt(st.nextToken());
		int C2 = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		// 전체 방 바닥을 구하지 않고, 규칙을 활용해 일부분만 구한다.
		// 우선, 알파벳 다이아몬드는 (2*N-1)*(2*N-1) 크기로 반복된다.
		// 그리고 맨 왼쪽 위 알파벳 다이아몬드에서 (N-1, N-1) 점에서 a로 시작해 거리가 N-1 이하인 모든 점들은
		// (거리)%26+'a'에 해당하는 알파벳을 값으로 가진다.
		// 거리가 N-1보다 크면 해당 지점은 .을 값으로 가진다.
		for (int r = R1; r <= R2; r++) {
			for (int c = C1; c <= C2; c++) {
				int R = r%(2*N-1), C = c%(2*N-1);
				int dist = Math.abs(N-1-R) + Math.abs(N-1-C);
				if (dist <= N-1) {
					sb.append((char)(dist%26+'a'));
				} else {
					sb.append('.');
				}
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
