package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6987_월드컵 {
	static boolean possible;
	static int[][] results;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 각 테스트 케이스에 대해 진행
		outer: for (int t = 1; t <= 4; t++) {
			possible = false; // 해당 결과가 가능한 결과인지를 나타내는 변수
			StringTokenizer st = new StringTokenizer(br.readLine());
			results = new int[6][3]; // 경기 결과를 저장하는 배열
			for (int i = 0; i < 6; i++) {
				int sum = 0; // 각 팀이 진행한 경기 수의 합
				for (int j = 0; j < 3; j++) {
					results[i][j] = Integer.parseInt(st.nextToken());
					sum += results[i][j];
				}
				// 각 팀이 진행한 경기 수의 합이 5가 아니면 불가능한 경기 결과다.
				if (sum != 5) {
					sb.append(0).append(" ");
					continue outer;
				}
			}
			// 각 팀은 다른 팀과 항상 한 번씩 경기를 진행하므로, 각 경기에 대한 가짓수를 나눠서 재귀적으로 진행한다.
			check(0, 1);
			// 경기 결과가 가능하면 1, 그렇지 않으면 0을 출력
			sb.append(possible ? 1 : 0).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// team 팀과 other 팀의 경기 결과를 가짓수에 따라 재귀적으로 진행해 가능한 경기 결과인지를 확인하는 함수
	private static void check(int team, int other) {
		// 모든 경기 결과를 탐색한 결과 가능한 경우
		// 이미 가능한 경기 결과를 찾은 경우에도 바로 리턴한다.
		if (team == 6 || possible) {
			possible = true;
			return;
		}
		// team 팀에 대한 경기 결과를 모두 고려한 경우
		if (other == 6) {
			// 다음 team 팀을 고려한다.
			check(team+1, team+2);
			return;
		}
		// team 팀이 이길 때, 비길 때, 질 때로 나눠서 재귀적으로 진행한다.
		checkIfPossibleGame(team, other, 0);
		checkIfPossibleGame(team, other, 1);
		checkIfPossibleGame(team, other, 2);
	}
	// team 팀이 other 팀에 대해 teamWon의 결과를 얻을 수 있는지 확인하고, 그럴 경우 다음 단계로 진행하는 함수
	// teamWon은 team 팀이 이길 경우 0, 비길 경우 1, 질 경우 2의 값을 가진다.
	private static void checkIfPossibleGame(int team, int other, int teamWon) {
		// other 팀은 team 팀과 반대 값을 가지도록 설정
		int otherTeamWon = teamWon == 0 ? 2 : (teamWon == 1 ? 1 : 0);
		// 가능한 경기인 경우
		if (results[team][teamWon] > 0 && results[other][otherTeamWon] > 0) {
			results[team][teamWon]--; results[other][otherTeamWon]--; // 해당 경기를 승패 수에서 제거
			check(team, other+1); // 다음 단계로 진행
			results[team][teamWon]++; results[other][otherTeamWon]++; // 해당 경기를 다시 복구(백트래킹)
		}
	}
}
