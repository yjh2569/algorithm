package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17182_우주_탐사선 {
	static int N, min;
	static int[][] fw;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		fw = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				fw[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 플로이드-와샬 알고리즘을 이용해 모든 행성 쌍에 대해, 행성 간 이동에 필요한 최소 시간을 구한다. 
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (i == k) continue;
				for (int j = 0; j < N; j++) {
					if (j == i || j == k) continue;
					fw[i][j] = Math.min(fw[i][k] + fw[k][j], fw[i][j]);
				}
			}
		}
		min = Integer.MAX_VALUE; // 모든 행성을 탐사하는데 필요한 최소 시간
		// K번째 행성부터 시작해, 모든 행성을 탐사하기 위한 전체 경우의 수를 고려하고, 그 중 최단 탐사 시간을 구한다.
		perm(K, 1, 1 << K, 0);
		System.out.println(min);
	}
	// 순열을 활용해 전체 행성을 탐사하는데 걸리는 최단 시간을 구하는 함수
	// cur은 현재 위치한 행성, cnt는 지금까지 탐사한 행성의 개수, bit는 지금까지 탐사한 행성을 비트마스킹으로 표현한 것,
	// time은 지금까지 탐사를 했을 때 걸린 시간을 의미한다.
	private static void perm(int cur, int cnt, int bit, int time) {
		// 모든 행성의 탐사를 마친 경우
		if (cnt == N) {
			min = Math.min(min, time);
			return;
		}
		// 각 행성에 대해 탐사를 시도
		for (int i = 0; i < N; i++) {
			// 이미 탐사한 행성인 경우
			if ((bit & (1 << i)) != 0) continue;
			// i번째 행성으로 이동한다.
			perm(i, cnt+1, bit | (1 << i), time+fw[cur][i]);
		}
	}

}
