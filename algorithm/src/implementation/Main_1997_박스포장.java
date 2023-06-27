package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1997_박스포장 {
	static int n, w, b, h;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		// 각 박스 안에 쌓은 높이를 저장하는 ArrayList
		ArrayList<Integer> heights = new ArrayList<>();
		// 현재 박스의 모습
		int[][] curBox = new int[b][w];
		int prevH = 0; // 현재 박스 내 장식 판의 높이
		// 박스 안에 장식 판을 순서대로 쌓는다.
		for (int i = 0; i < n; i++) {
			h = Integer.parseInt(br.readLine());
			// 쌓을 장식 판
			int[][] block = new int[h][w];
			for (int r = 0; r < h; r++) {
				String s = br.readLine();
				for (int c = 0; c < w; c++) {
					block[r][c] = s.charAt(c) == '.' ? 0 : 1;
				}
			}
			// 장식 판이 내려간 깊이
			int curH = 0;
			// 장식 판이 내려갈 수 있는 가장 깊은 곳까지 내려간다.
			while (curH <= b-h && checkIfCanPut(curBox, block, curH)) {
				curH++;
			}
			// 장식 판을 박스에 넣을 수 없는 경우
			if (curH == 0) {
				heights.add(prevH); // 박스 내 장식 판의 높이를 heights에 저장
				// 박스 초기화
				for (int r = 0; r < b; r++) {
					Arrays.fill(curBox[r], 0);
				}
				// 박스를 비우면 현재 장식 판은 박스 가장 안쪽까지 넣을 수 있다.
				curH = b-h+1;
			}
			// 장식 판이 내려간 깊이를 활용해 현재 박스 내 장식 판의 높이를 구한다.
			prevH = b - (--curH);
			// 장식 판을 박스 안에 넣는다.
			for (int r = curH; r < curH + h; r++) {
				for (int c = 0; c < w; c++) {
					curBox[r][c] += block[r - curH][c];
				}
			}
		}
		heights.add(prevH); // 마지막 장식 판을 넣은 후 박스 내 장식판의 높이를 구한다.
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int h : heights) {
			sb.append(h).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// block을 curBox에 curH만큼의 깊이로 넣을 수 있는지를 확인하는 함수
	private static boolean checkIfCanPut(int[][] curBox, int[][] block, int curH) {
		for (int r = curH; r < curH + h; r++) {
			for (int c = 0; c < w; c++) {
				// block을 curBox에 넣을 수 없는 경우
				if (curBox[r][c] == 1 && block[r - curH][c] == 1) return false;
			}
		}
		return true;
	}

}
