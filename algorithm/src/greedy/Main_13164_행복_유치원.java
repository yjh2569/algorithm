package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13164_행복_유치원 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int res = arr[N-1] - arr[0];
		int[] diff = new int[N-1];
		for (int i = 0; i < N-1; i++) {
			diff[i] = arr[i+1] - arr[i];
		}
		Arrays.sort(diff);
		for (int i = 0; i < K-1; i++) {
			res -= diff[N-2-i];
		}
		System.out.println(res);
		
	}

}
