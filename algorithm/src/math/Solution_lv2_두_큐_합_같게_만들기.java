package math;

public class Solution_lv2_두_큐_합_같게_만들기 {

	public static void main(String[] args) {
		int result = solution(new int[] {3, 2, 7, 2}, new int[] {4, 6, 5, 1});
		System.out.println(result);
	}
	
	public static int solution(int[] queue1, int[] queue2) {
		// 두 큐의 길이
        int N = queue1.length;
        // 투 포인터를 활용하여 해결한다.
        // 두 큐 내 원소를 모두 모은 배열을 하나 만들고, queue1에서 숫자를 뽑고 넣는 것을 
        // 해당 배열에서 시작과 끝 지점을 옮기는 방식으로 진행한다.
        // 숫자를 뽑아서 queue2에 넣는 경우는 시작 지점을 오른쪽으로 한 칸 움직이는 경우에 대응시키고,
        // queue2에서 뽑은 숫자를 넣는 경우는 끝 지점을 오른쪽으로 한 칸 움직이는 경우에 대응시킨다. 
        int[] q = new int[2*N];
        // 두 큐 내 있는 수들의 총합
        long sum = 0;
        // 현재 queue1 내 있는 수들의 총합
        long cur = 0;
        // 초기화를 진행
        // q에 두 큐에 있는 숫자를 차례대로 넣고, sum과 cur을 구한다.
        for (int i = 0; i < N; i++) {
        	q[i] = queue1[i];
        	q[i+N] = queue2[i];
        	sum += (long)q[i] + (long)q[i+N];
        	cur += (long)q[i];
        }
        // queue1의 현재 시작 지점(원소가 추출되는 지점)
        int start = 0;
        // queue1의 현재 끝 지점(원소가 삽입되는 지점)
        int end = N;
        // 작업 최소 횟수
        int cnt = 0;
        // 합이 홀수인 경우는 절대 두 큐의 합을 같게 만들 수 없다.
        if (sum%2 == 1) return -1;
        // 시작 지점과 끝 지점은 절대 2N을 넘어서면 안 되고, start는 end보다 작거나 같아야 한다. 
        while (start < 2*N && end < 2*N && start <= end) {
        	// 두 큐의 합이 같아지는 경우
        	if (cur == sum/2) {
        		return cnt;
        	// queue1에 수가 더 필요한 경우
        	} else if (cur < sum/2) {
        		cur += q[end++];
        	// queue1에 수가 너무 많은 경우
        	} else {
        		cur -= q[start++];
        	}
        	cnt++;
        }
        // 두 큐의 합을 같게 만들 수 없는 경우
        return -1;
    }

}
