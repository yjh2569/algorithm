package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12956_퍼레이드 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 초기 도로 상태
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE/10);
			map[i][i] = 0;
		}
		// 도로들의 출발지과 목적지를 저장하는 배열
		int[][] roads = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			map[u][v] = w;
			map[v][u] = w;
			roads[i][0] = u;
			roads[i][1] = v;
		}
		// 퍼레이드가 열리지 않는다고 가정했을 때 임의의 모든 X에서 Y까지 가는 최단 경로의 비용을 저장하는 배열
		int[][] initial = new int[N][N];
		// 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				initial[i][j] = map[i][j];
			}
		}
		// 플로이드-와샬 알고리즘을 이용한다.
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) continue;
				for (int j = 0; j < N; j++) {
					if (i == j || j == k) continue;
					if (initial[i][j] > initial[i][k] + initial[k][j]) {
						initial[i][j] = initial[i][k] + initial[k][j];
					}
				}
			}
		}
		// 각 도로가 퍼레이드로 인해 막힌 경우 임의의 모든 X에서 Y까지 가는 최단 경로의 비용을 저장하는 배열
		int[][] blocked = new int[N][N];
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			int[] road = roads[m];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					blocked[i][j] = map[i][j];
				}
			}
			// 해당 도로가 퍼레이드로 막혔다고 가정
			blocked[road[0]][road[1]] = Integer.MAX_VALUE/10;
			blocked[road[1]][road[0]] = Integer.MAX_VALUE/10;
			// 플로이드-와샬 알고리즘 적용
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k) continue;
					for (int j = 0; j < N; j++) {
						if (i == j || j == k) continue;
						if (blocked[i][j] > blocked[i][k] + blocked[k][j]) {
							blocked[i][j] = blocked[i][k] + blocked[k][j];
						}
					}
				}
			}
			// 퍼레이드에 영향을 받는 버스 노선의 개수
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					if (blocked[i][j] > initial[i][j]) {
						cnt++;
					}
				}
			}
			sb.append(cnt).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
