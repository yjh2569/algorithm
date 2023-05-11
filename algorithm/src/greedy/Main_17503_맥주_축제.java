package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_17503_맥주_축제 {
	// 맥주의 선호도와 도수 레벨을 저장하는 클래스
	static class Beer implements Comparable<Beer>{
	    int v, c;
	    public Beer(int v, int c) {
	        this.v = v;
	        this.c = c;
	    }
	    public int compareTo(Beer b) {
	    	return this.c == b.c ? Integer.compare(b.v, this.v) : Integer.compare(this.c, b.c);
	    }
	}
    static int N, M, K;
    public static void main(String[] args) throws IOException {
    	// 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 마실 맥주를 저장하는 우선순위 큐
        Beer[] beers = new Beer[K]; // 맥주들을 저장하는 배열
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            beers[i] = new Beer(v, c);
        }
        // 맥주를 도수가 낮은 순서대로, 도수가 같으면 선호도가 높은 순서대로 정렬
        Arrays.sort(beers);
        int total = 0; // 선호도의 합
        int answer = -1; // 결과
        for (Beer beer : beers) {
        	// 일단 각 맥주를 마시려고 시도해본다.
            pq.add(beer.v);
            total += beer.v;
            // 마시는 맥주의 개수가 N개 초과인 경우
            if (pq.size() > N) {
                total -= pq.poll();
            }
            // 맥주의 개수가 N개가 되면서 선호도의 총합이 M보다 크거나 같은 경우
            if (pq.size() == N && total >= M) {
                answer = beer.c;
                break;
            }
        }
        System.out.print(answer);
    }
}