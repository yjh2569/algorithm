package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main_1793_타일링 {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		// 큰 수 연산이므로 BigInteger를 사용한다.
		BigInteger[] arr = new BigInteger[251];
		arr[1] = new BigInteger("1");
		arr[0] = new BigInteger("1");
		// 결과
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 개수가 명확히 주어지지 않아 입력이 끝날 때까지 입력을 받는다.
		while ((line = br.readLine()) != null) {
			int n = 0;
			try {
				n = Integer.parseInt(line);
			} catch (Exception e) {
				break;
			}
			// 이미 구해놓은 값이 있으면 그 값을 출력한다.
			if (arr[n] != null && arr[n].compareTo(new BigInteger("0")) > 0) sb.append(arr[n]);
			else {
				// 적절한 점화식을 통해 답을 구한다.
				for (int i = 2; i <= n; i++) {
					arr[i] = arr[i-2].multiply(new BigInteger("2")).add(arr[i-1]);
				}
				sb.append(arr[n]);
			}
			sb.append("\n");						
		}
		// 출력
		if (sb.length() > 0) sb.setLength(sb.length() - 1);
		System.out.println(sb.toString());
	}

}
