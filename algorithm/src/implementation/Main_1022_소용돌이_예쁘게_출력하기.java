package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1022_소용돌이_예쁘게_출력하기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		// 출력할 수들을 저장할 배열
		int[][] map = new int[r2-r1+1][c2-c1+1];
		// 출력할 수들 중 가장 큰 수
		int max = 0;
		// 소용돌이를 직접 만드는 것이 아니라, 소용돌이가 완성되었을 때 각 칸에 어떤 수가 있을지를 규칙을 통해 알아낸다.
		for (int r = r1; r <= r2; r++) {
			for (int c = c1; c <= c2; c++) {
				// 어떤 수가 있을지를 알아낸 후 이를 map의 (r-r1, c-c1)에 저장한다.
				// 음수는 배열의 인덱스가 될 수 없기 때문이다.
				map[r-r1][c-c1] = findNum(r, c);
				max = Math.max(max, map[r-r1][c-c1]);
			}
		}
		// 가장 큰 수의 길이를 구한다.
		int max_l = findLength(max);
		StringBuilder sb = new StringBuilder(); // 출력 저장용 변수
		String space = " "; // 빈 공간을 미리 정의해두고 나중에 추가할 때 사용한다.
		// map에 저장해놓은 수들을 출력 형식에 맞게 sb에 넣는다.
		for (int r = 0; r <= r2-r1; r++) {
			for (int c = 0; c <= c2-c1; c++) {
				// 각 수의 길이를 알아내고 가장 큰 수와의 길이 차만큼 빈 공간을 추가한다.
				int l = findLength(map[r][c]);
				for (int i = 0; i < max_l-l; i++) {
					sb.append(space);
				}
				// map에 저장해놓은 수를 추가한다.
				sb.append(map[r][c]).append(space);
			}
			sb.append("\n");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 소용돌이를 만들었을 때 (r, c)에 어떤 수가 들어갈지를 규칙을 통해 알아낸다.
	private static int findNum(int r, int c) {
		// (n, n)에는 항상 (2*n+1)^2이 들어감을 이용한다.
		// 우선 r과 c의 절대값 중 큰 수를 찾는다.
		int abs_r = Math.abs(r);
		int abs_c = Math.abs(c);
		int m = Math.max(abs_r, abs_c); // r과 c의 절대값 중 큰 수
		// 그 다음, (m, m)에 들어가는 수를 미리 구한다.
		int start = (2*m+1)*(2*m+1);
		// 마지막으로, 소용돌이 내 (m, m)을 꼭짓점으로 하는 정사각형에서 
		// (r, c)가 어느 부분에 있는지를 알아내 (r, c)에 어떤 수가 들어갈지를 추측한다.
		if (m == r) { // 정사각형의 아래쪽 변에 있는 경우
			return start-(m-c);
		} else if (m == -c) { // 정사각형의 왼쪽 변에 있는 경우
			return start-2*m-(m-r);
		} else if (m == -r) { // 정사각형의 위쪽 변에 있는 경우
			return start-4*m-(m+c);
		} else { // 정사각형의 오른쪽 변에 있는 경우
			return start-6*m-(m+r);
		}
	}
	// 양의 정수의 길이를 구하는 함수
	private static int findLength(int n) {
		int l = 0;
		int temp = n;
		while (temp > 0) {
			temp /= 10;
			l++;
		}
		return l;
	}

}
