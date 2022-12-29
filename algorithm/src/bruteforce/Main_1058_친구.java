package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1058_친구 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] isFriend = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				isFriend[i][j] = s.charAt(j) == 'Y';
			}
		}
		// 가장 유명한 사람의 2-친구 수
		int max = 0;
		for (int i = 0; i < N; i++) {
			// 현재 사람이 각 사람과 2-친구 관계인지를 나타내는 배열
			boolean[] is2Friend = new boolean[N];
			// 직접 친구 관계인 경우를 기록
			for (int j = 0; j < N; j++) {
				is2Friend[j] = isFriend[i][j];
			}
			// 2-친구 관계인지를 확인
			for (int j = 0; j < N; j++) {
				// j번째 사람과 직접 친구 관계인 경우
				if (is2Friend[j]) {
					// k번째 사람과 2-친구 관계를 맺을 수 있는지 확인
					for (int k = 0; k < N; k++) {
						// i, j, k가 모두 달라야 한다.
						if (i == k || j == k) continue;
						// i번째 사람과 j번째 사람이 친구고, k번째 사람과 j번째 사람이 친구여야 
						// k번째 사람이 i번째 사람의 2-친구가 된다. 
						is2Friend[k] |= isFriend[i][j] && isFriend[k][j];
					}
				}
			}
			// 2-친구 관계의 수를 센다.
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if (is2Friend[j]) cnt++;
			}
			max = Math.max(cnt, max);
		}
		System.out.println(max);
	}
}
