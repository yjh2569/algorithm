package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2128_마지막_조별_시합 {
	static int N, D, K;
	static int max;
	static ArrayList<int[]> problems;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 각 학생들이 풀 수 있는 문제들의 번호를 저장하는 ArrayList 
		problems = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int[] arr = new int[L];
			for (int j = 0; j < L; j++) {
				int problem = Integer.parseInt(st.nextToken());
				arr[j] = problem;
			}
			problems.add(arr);
		}
		// A조의 최대 인원수
		max = 0;
		// 알고리즘 문제 D개 중에 K개를 골라 K개의 문제만 풀 수 있는 학생들의 수를 센다.
		// K개의 문제 이외의 다른 문제를 푸는 학생은 제외한다.
		// 조합을 위한 방문 배열
		visited = new boolean[D+1];
		// A조의 최대 인원수를 구한다.
		combi(0, 1);
		System.out.println(max);
	}
	// 조합을 이용해 문제 D개 중 K개를 택하고 해당 K개의 문제만 풀 수 있는 학생 수를 센다.
	private static void combi(int cnt, int start) {
		if (cnt == K) {
			// 조합을 통해 고른 K개의 문제만 풀 수 있는 학생 수
			int count = 0;
			outer: for (int i = 0; i < N; i++) {
				int[] ps = problems.get(i);
				for (int p : ps) {
					// K개의 문제가 아닌 다른 문제를 풀 수 있는 경우
					if (!visited[p]) continue outer;
				}
				count++;
			}
			// 최대값 갱신
			max = Math.max(max, count);
		}
		for (int i = start; i <= D; i++) {
			visited[i] = true;
			combi(cnt+1, i+1);
			visited[i] = false;
		}
		
	}

}
