package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2477_참외밭 {
	// 변의 방향과 길이를 나타내는 클래스
	static class Edge {
		int d, dist;
		public Edge(int d, int dist) {
			this.d = d;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		Edge[] edges = new Edge[6];
		// 육각형을 큰 직사각형에서 작은 직사각형을 뺀 도형으로 간주한다.
		int W = 0; // 육각형에서 큰 직사각형의 가로 길이
		int H = 0; // 육각형에서 큰 직사각형의 세로 길이
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(d, dist);
			// 큰 직사각형의 가로 길이와 세로 길이를 구한다.
			if (d >= 3) H = Math.max(H, dist);
			else W = Math.max(W, dist);
		}
		int sW = 0; // 육각형에서 작은 직사각형의 가로 길이
		int sH = 0; // 육각형에서 작은 직사각형의 세로 길이
		// 도형의 형태를 추측해 작은 직사각형의 가로 길이와 세로 길이를 구한다.
		for (int i = 0; i < 6; i++) {
			Edge e = edges[i];
			// 이런 경우는 도형이 ㄴ, ㄱ 모양이고, 현재 큰 직사각형의 세로에 위치하므로,
			// (i+3)%6번째 변이 작은 직사각형의 가로, (i+4)%6번째 변이 작은 직사각형의 세로가 된다.
			if (e.d >= 3 && e.dist == H && edges[(i+1)%6].dist == W) {
				sW = edges[(i+3)%6].dist;
				sH = edges[(i+4)%6].dist;
			}
			// 이런 경우는 도형이 「, 」모양이고, 현재 큰 직사각형의 가로에 위치하므로,
			// (i+3)%6번째 변이 작은 직사각형의 세로, (i+4)%6번째 변이 작은 직사각형의 가로가 된다.
			if (e.d < 3 && e.dist == W && edges[(i+1)%6].dist == H) {
				sH = edges[(i+3)%6].dist;
				sW = edges[(i+4)%6].dist;
			}
		}
		System.out.println((W*H - sW*sH)*K);
	}
}
