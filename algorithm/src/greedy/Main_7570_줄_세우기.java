package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_7570_줄_세우기 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 만약 양끝이 아닌 줄 중간에 끼워넣을 수 있었다면, 해당 수열에서 LIS의 길이를 찾아 N에서 빼면 된다.
		// 이러한 아이디어에 착안하여, LIS를 찾되 연속된 숫자로 이루어진 LIS를 찾는다.
		// 연속된 숫자로 이루어진 LIS 내 숫자들은 양끝으로 이동시킬 필요가 없고, 나머지 숫자들은 반드시 움직여야 한다.
		// 이는 일반적인 LIS 문제보다 좀더 효율적으로 해결할 수 있는데, 핵심은 입력을 받을 때 인덱스와 값을 반대로 받는 것이다.
		// 그러면 번호가 인덱스가 되고, 그 번호를 가진 어린이의 현재 위치가 값이 된다.
		for (int i = 1; i <= N; i++) {
			arr[Integer.parseInt(st.nextToken())] = i;
		}
		int max = 1; // 연속된 숫자로 이루어진 LIS의 길이
		int l = 1; // 현재 고려중인, 연속된 숫자로 이루어진 수열의 길이
		// 번호 순서대로 연속적으로 순회한다.
		// 만약 현재 번호의 어린이가 이전 번호의 어린이보다 뒤에 있으면 연속적인 수열을 만들 수 있으므로 길이를 1 증가시킨다. 
		// 그렇지 않으면 지금까지 고려한 수열은 더이상 진행할 수 없으므로 max 값을 갱신해본 뒤 l을 1로 초기화한다.
		for (int i = 2; i <= N; i++) {
			if (arr[i] > arr[i-1]) l++;
			else {
				max = Math.max(l, max);
				l = 1;
			}
		}
		// 마지막 수의 경우 else 부분을 거치지 않을 수도 있으므로 max 값을 한 번 더 갱신해준다.
		max = Math.max(l, max);
		// 지금까지 구한 것은 연속적인 LIS의 길이, 즉 양끝으로 보낼 필요가 없는 어린이 수의 최댓값이므로 N에서 max를 빼서
		// 양끝으로 보내야 하는 어린이 수의 최솟값을 구한다.
		System.out.println(N - max);
	}

}
