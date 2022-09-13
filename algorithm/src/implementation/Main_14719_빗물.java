package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14719_빗물 {

    public static void main(String[] args) throws IOException {
    	// 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] heights = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        // 왼쪽부터 구한 높이의 최대값
        int[] lm = new int[W];
        // 오른쪽부터 구한 높이의 최대값
        int[] rm = new int[W];
        lm[0] = heights[0];
        rm[W-1] = heights[W-1];
        // 차례대로 최대값을 구한다.
        for (int i = 1; i < W; i++) {
            lm[i] = Math.max(lm[i-1], heights[i]);
            rm[W-i-1] = Math.max(rm[W-i], heights[W-i-1]);
        }
        // 고이는 빗물의 총량
        int res = 0;
        // 각 위치에 고이는 빗물은 왼쪽부터 구한 높이의 최대값과 오른쪽부터 구한 높이의 최대값 중
        // 작은 값에서 현재 높이를 뺀 결과와 같다.
        for (int i = 0; i < W; i++) {
            res += Math.min(lm[i], rm[i]) - heights[i];
        }
        System.out.println(res);
    }
}