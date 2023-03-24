package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1461_도서관 {
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] books = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			books[i] = Integer.parseInt(st.nextToken());
		}
		// 책의 위치를 좌표 순서대로 정렬한다.
		// 움직임을 최소화하기 위해, 마지막 목적지를 원점 기준 가장 멀리 떨어져 있는 곳으로 지정한다.
		// 여기서 해당 지점까지의 거리를 추가하고, 마지막 지점까지 움직이면서 마지막 지점과 가까운 책들을 미리 반납해둔다.
		// 이외의 나머지 책들은 반납 지점까지 갔다가 원점으로 복귀해야 하므로, 반납 지점까지의 거리*2를 거리에 추가한다.
		// 반납 지점을 구할 때는 아직 책을 반납하지 않은 양끝 지점 중 절댓값이 큰 지점을 우선적으로 반납 지점으로 지정한다.
		// 마지막 지점과 마찬가지로 반납 지점과 가까운 책들을 같이 반납한다.
		Arrays.sort(books);
		int dist = 0; // 움직여야 하는 거리의 최솟값
		int l = 0; // 왼쪽 끝 지점
		int r = N-1; // 오른쪽 끝 지점
		// 마지막 지점을 지정하고, 해당 지점과 가까운 책들을 반납한다.
		if (Math.abs(books[l]) > Math.abs(books[r])) {
			dist += Math.abs(books[l]);
			int capacity = M;
			while (l <= r && books[l] < 0 && capacity > 0) {
				l++;
				capacity--;
			}
		} else {
			dist += Math.abs(books[N-1]);
			int capacity = M;
			while (l <= r && books[r] > 0 && capacity > 0) {
				r--;
				capacity--;
			}
		}
		// 마지막 지점을 방문하면서 반납한 책들을 제외한 나머지 책들을 반납 지점을 정해가며 반납한다.
		while (l <= r) {
			if (Math.abs(books[l]) > Math.abs(books[r]) || books[r] < 0) {
				dist += 2*Math.abs(books[l]);
				int capacity = M;
				while (l <= r && books[l] < 0 && capacity > 0) {
					l++;
					capacity--;
				}
			} else if (Math.abs(books[l]) < Math.abs(books[r]) || books[l] > 0){
				dist += 2*Math.abs(books[r]);
				int capacity = M;
				while (l <= r && books[r] > 0 && capacity > 0) {
					r--;
					capacity--;
				}
			}
		}		
		System.out.println(dist);
	}

}
