package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2643_색종이_올려_놓기 {
	// 색종이의 가로와 세로의 길이를 저장하는 클래스
	static class Paper {
		int r, c;
		public Paper(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Paper[] papers = new Paper[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			papers[i] = new Paper(r, c);
		}
		// 색종이의 넓이 순서대로 정렬
		Arrays.sort(papers, (x, y) -> Integer.compare(y.r*y.c, x.r*x.c));
		// dynamic programming 활용
		// dp[i]는 i번째 색종이가 맨 위에 올 때 가장 많이 쌓을 수 있는 색종이 수를 의미한다.
		int[] dp = new int[N];
		Arrays.fill(dp, 1); // 초기화
		int max = 1; // 결과값
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// 색종이를 다른 색종이 위에 올리려면 가로와 세로 길이 모두 이전 색종이보다 작아야 한다.
				// 단, 90도 회전이 가능하므로 가로와 세로가 바뀐 경우도 고려
				if ((papers[i].r <= papers[j].r && papers[i].c <= papers[j].c) 
						|| (papers[i].r <= papers[j].c && papers[i].c <= papers[j].r)) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}

}
