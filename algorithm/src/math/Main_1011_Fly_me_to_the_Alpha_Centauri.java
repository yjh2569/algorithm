package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1011_Fly_me_to_the_Alpha_Centauri {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// x, y 간의 차이만 있으면 된다.
			int diff = y-x;
			// 2*n번 이동으로 갈 수 있는 최대 거리는 1+2+...+n+n+...+2+1=n^2+n
			// 2*n+1번 이동으로 갈 수 있는 최대 거리는 1+2+...+n+(n+1)+n+...+2+1=(n+1)^2
			// 따라서, (n^2+1) ~ (n^2+n)만큼 이동하려면 최소 2*n번 이동이 필요하고,
			// (n^2+n+1) ~ (n+1)^2만큼 이동하려면 최소 2*n+1번 이동이 필요하다.
			// 이를 정리하면, 우선 n을 구하기 위해 (이동 거리-1)의 양의 제곱근 값을 구한다.
			// 그 다음, (이동 거리)-n^2과 (n+1)^2-(이동 거리)의 값을 비교한다.
			// (이동 거리)-n^2가 (n+1)^2-(이동 거리)보다 작으면, 이동 거리가 (n^2+1) ~ (n^2+n)에 속하므로 최소 2*n번 이동이 필요하다.
			// 그렇지 않으면, 이동 거리가 (n^2+n+1) ~ (n+1)^2에 속하므로 최소 2*n+1번 이동이 필요하다.
			int sqrt = (int)Math.sqrt(diff-1);
			if (diff - sqrt*sqrt < (sqrt+1)*(sqrt+1) - diff) {
				sb.append(sqrt*2).append("\n");
			} else {
				sb.append(sqrt*2+1).append("\n");
			}
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
