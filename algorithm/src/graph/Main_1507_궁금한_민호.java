package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1507_궁금한_민호 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] times = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				times[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 원래 그래프
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = times[i][j];
			}
		}
		// 플로이드-와샬 알고리즘을 역이용한다.
		// 만약 times[i][j] > times[i][k] + times[k][j]라면 
		// 플로이드-와샬 알고리즘을 통해 제대로 최단 거리를 구한 것이 아니므로 불가능한 경우이고,
		// times[i][j] = times[i][k] + times[k][j]라면
		// i에서 j로 가기 위해 k를 거쳐야 함을 의미하므로 i에서 j로 가는 간선은 필요없기에 이를 제거한다.
		// times[i][j] < times[i][k] + times[k][j]라면
		// i에서 j로 가기 위해 k를 거치면 안 된다는 것을 의미한다. 
		// 만약 모든 k에 대해 위 식을 만족한다면 i에서 j로 가는 간선이 존재함을 의미한다.
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) continue;
				for (int j = 0; j < N; j++) {
					if (i == j || j == k) continue;
					// 불가능한 경우
					if (times[i][j] > times[i][k] + times[k][j]) {
						System.out.println(-1);
						System.exit(0);
					}
					if (times[i][j] == times[i][k] + times[k][j]) {
						graph[i][j] = 0;
					}
				}
			}
		}
		// 모든 도로의 시간의 합을 구한다.
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				sum += graph[i][j];
			}
		}
		System.out.println(sum);
	}

}
