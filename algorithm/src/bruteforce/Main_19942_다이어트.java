package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_19942_다이어트 {
	// 식재료의 단백질, 지방, 탄수화물, 비타민의 양, 가격을 저장하는 클래스
	static class Ingredient {
		int p, f, s, v, c;
		public Ingredient(int p, int f, int s, int v, int c) {
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
	}
	static int N, mp, mf, ms, mv;
	static int minC;
	static ArrayList<String> results;
	static boolean[] v;
	static Ingredient[] ingredients;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		ingredients = new Ingredient[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			ingredients[i] = new Ingredient(p, f, s, v, c);
		}
		minC = Integer.MAX_VALUE; // 최저 영양소 기준을 만족하는 식재료 집합의 최소 비용
		v = new boolean[N]; // 각 식재료 사용 여부
		results = new ArrayList<>(); // 최소 비용의 식재료 집합을 모아두는 ArrayList
		// 모든 식재료 집합 중 최저 영양소 기준을 만족하는 식재료 집합의 최소 비용을 구한다.
		subset(0, 0, 0, 0, 0, 0);
		// 출력
		StringBuilder sb = new StringBuilder();
		// 최저 영양소 기준을 만족하는 식재료 집합이 없는 경우
		if (minC == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		sb.append(minC).append("\n");
		// 최소 비용의 식재료 집합이 여러 개인 경우 사전 순으로 가장 앞서는 식재료 집합을 찾는다.
		Collections.sort(results);
		String ascResult = results.get(0);
		for (char c : ascResult.toCharArray()) {
			sb.append((int)(c-'A'+1)).append(" ");
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// 부분집합을 활용해 모든 식재료 집합 중 최저 영양소 기준을 만족하는 식재료 집합의 최소 비용을 구하는 함수
	private static void subset(int cnt, int curP, int curF, int curS, int curV, int curC) {
		// 모든 식재료를 고려한 경우
		if (cnt == N) {
			// 최저 영양소 기준치를 만족하지 못한 경우
			if (curP < mp || curF < mf || curS < ms || curV < mv) return;
			// 최저 영양소 기준치를 만족하면서 비용이 최소 비용 이하인 경우
			if (curC <= minC) {
				// 비용이 최소 비용보다 작으면 최소 비용을 갱신한다.
				if (curC < minC) {
					minC = curC;
					results = new ArrayList<>();
				}
				// 식재료 집합에 있는 식재료의 번호를 알파벳으로 변환해 문자열로 results에 저장한다.
				// 이렇게 하면 사전 순으로 가장 앞서는 식재료 집합을 쉽게 구할 수 있다.
				String s = "";
				for (int i = 0; i < N; i++) {
					if (v[i]) s += (char)(i+'A');
				}
				results.add(s);
			}
			return;
		}
		Ingredient ing = ingredients[cnt];
		// 해당 식재료를 사용하는 경우
		// 최저 비용보다 더 많은 비용을 사용하는 것은 무의미하므로 넘어간다.
		if (curC + ing.c <= minC) {
			v[cnt] = true;
			subset(cnt+1, curP + ing.p, curF + ing.f, curS + ing.s, curV + ing.v, curC + ing.c);
			v[cnt] = false;
		}
		// 해당 식재료를 사용하지 않는 경우
		subset(cnt+1, curP, curF, curS, curV, curC);
	}

}
