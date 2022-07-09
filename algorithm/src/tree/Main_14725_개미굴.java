package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_14725_개미굴 {
	static ArrayList<String> foods;
	static StringBuilder res;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 개미굴에 있는 먹이 정보를 저장하는 ArrayList
		// 저장할 때 만약 먹이를 찾은 과정이 A -> B -> C라면 A-B-C로 저장
		// 이때 가장 끝에 있는 먹이만 저장하지 않고, 중간 과정에 있는 모든 먹이를 위와 같이 저장한다.
		foods = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			StringBuilder sb = new StringBuilder();
			for (int k = 0; k < K; k++) {
				sb.append(st.nextToken());
				if (!foods.contains(sb.toString())) foods.add(sb.toString());
				sb.append("-");
			}
		}
		// 위와 같이 받은 입력을 정렬하면 원하는 출력 결과에 대응하는 순서로 정렬할 수 있다.
		Collections.sort(foods);
		// 출력 결과를 저장
		res = new StringBuilder();
		// 각 먹이 정보를 수정하여 res에 저장
		for (String f : foods) {
			print(f);
		}
		if (res.length() > 0) res.setLength(res.length()-1);
		System.out.println(res.toString());
	}
	private static void print(String f) {
		// 경로를 파악하기 위해 "-"를 이용해 먹이 정보를 나눈다.
		String[] path = f.split("-");
		// 먹이가 있는 개미굴의 깊이는 path의 length를 통해 파악할 수 있다.
		for (int i = 0; i < (path.length-1)*2; i++) {
			res.append("-");
		}
		// 먹이 이름은 path의 맨 마지막 원소를 통해 알 수 있다.
		res.append(path[path.length-1]).append("\n");
	}

}
