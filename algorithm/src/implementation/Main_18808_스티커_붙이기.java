package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18808_스티커_붙이기 {
	static int N, M, K, R, C;
	static int[][] notebook;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 스티커를 붙일 노트북
		notebook = new int[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					sticker[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력으로 밭은 스티커를 노트북에 붙이는 것을 시도한다.
			tryToPatch(sticker);
		}
		System.out.println(countStickerArea());
	}
	// 스티커를 노트북에 붙이는 것을 시도하는 함수
	private static void tryToPatch(int[][] sticker) {
		// 4방향으로 돌려가면서 스티커를 붙여본다.
		for (int d = 0; d < 4; d++) {
			// 스티커를 붙이는데 성공한 경우
			if (patchedSticker(sticker)) break;
			// 스티커를 붙이는데 실패한 경우 스티커를 시계 방향으로 90도 회전한다.
			sticker = rotateSticker(sticker);
		}		
	}
	// 스티커를 현재 모양 그대로 노트북에 붙일 수 있는지 확인하는 함수
	private static boolean patchedSticker(int[][] sticker) {
		// 가능한 스티커의 왼쪽 위 좌표를 조절하면서 스티커를 붙일 수 있는지 확인한다.
		for (int i = 0; i < N-R+1; i++) {
			for (int j = 0; j < M-C+1; j++) {
				// 해당 위치에 스티커를 붙일 수 있다면 스티커를 붙인다.
				if (checkPlace(sticker, i, j)) {
					patchSticker(sticker, i, j);
					return true;
				}
			}
		}
		// 스티커를 노트북에 붙일 수 없는 경우
		return false;
	}
	// sticker를 노트북에, 왼쪽 위 좌표가 (i, j)일 때 붙일 수 있는지 확인하는 함수
	private static boolean checkPlace(int[][] sticker, int i, int j) {
		for (int k = 0; k < R; k++) {
			for (int l = 0; l < C; l++) {
				// 노트북의 (k+i, l+j) 지점에 스티커가 겹치는 경우
				if (sticker[k][l] == 1 && notebook[k+i][l+j] == 1) return false;
			}
		}
		return true;
	}
	// 스티커를 노트북에 붙이는 함수
	// 노트북의 (i, j) 지점이 스티커의 (0, 0) 지점이 되도록 붙인다.
	private static void patchSticker(int[][] sticker, int i, int j) {
		for (int k = 0; k < R; k++) {
			for (int l = 0; l < C; l++) {
				notebook[k+i][l+j] += sticker[k][l];
			}
		}
	}
	// 스티커를 시계 방향으로 90도 회전하는 함수
	private static int[][] rotateSticker(int[][] sticker) {
		// 90도 회전된 스티커를 지정하는 배열
		int[][] temp = new int[C][R];
		// 스티커를 회전시킨다.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				temp[j][R-1-i] = sticker[i][j];
			}
		}
		// 회전 후 R과 C가 뒤바뀐다.
		int tempC = C;
		C = R;
		R = tempC;
		return temp;
	}
	// 노트북에서 스티커가 붙은 칸의 개수를 세는 함수
	private static int countStickerArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (notebook[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}
}
