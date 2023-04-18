package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_27888_가희와_지하철역_저장_시스템_1 {
	static Map<String, Integer> stations;
	static int[] counts;
	static Map<String, Integer> featureToBit;
	static int maxBit;
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 각 역의 이름을 키로, 해당 역의 특징을 비트마스킹으로 표현한 결과를 값으로 갖는 HashMap
		stations = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String name = br.readLine();
			stations.put(name, 0);
		}
		// 특징들을 비트마스킹으로 표현했을 때 해당 특징들을 가지는 역의 수를 나타내는 배열
		counts = new int[1 << 10];
		// 각 특징들과, 특징에 대응되는 비트를 키와 값으로 가지는 HashMap
		featureToBit = new HashMap<>();
		// (지금까지 추가한 특징의 수)-1
		// 새로운 특징을 찾았을 때 해당 특징에 대응하는 비트를 추가하기 위해 사용
		maxBit = -1;
		int r = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		outer: for (int i = 0; i < r; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char request = st.nextToken().charAt(0);
			// 역의 특징을 업데이트
			if (request == 'U') {
				String name = st.nextToken();
				// 기존에 가지고 있던 특징들을 제거한다.
				// 이에 따라 counts에서 해당 특징들의 부분집합에 대응하는 역 수를 1씩 감소시킨다.
				if (stations.get(name) != 0) {
					int stationFeatureBit = stations.get(name);
					changeFeatureCount(stationFeatureBit, 0, 0, -1);
				}
				// 역의 새 특징들
				String[] features = st.nextToken().split(",");
				int bits = 0; // 역의 새 특징들을 비트마스킹으로 표현하는 변수
				for (int j = 0; j < features.length; j++) {
					String feature = features[j];
					// 지금까지 다루지 않았던 특징이 나오는 경우 featureToBit에 해당 특징을 추가하고,
					// 그 특징에 비트 값을 부여한다.
					if (!featureToBit.containsKey(feature)) {
						featureToBit.put(feature, 1 << (++maxBit));
					}
					bits += featureToBit.get(feature);
				}
				// 역의 새 특징들의 비트마스킹을 갱신하고 이에 따라 counts에서
				// 해당 특징들의 부분집합에 대응하는 역 수를 1씩 증가시킨다.
				stations.put(name, bits);
				changeFeatureCount(bits, 0, 0, 1);
			// 특징들을 모두 가지는 역의 개수를 출력
			} else if (request == 'G') {
				String[] features = st.nextToken().split(",");
				int bits = 0;
				// 특징들을 비트로 표현
				for (int j = 0; j < features.length; j++) {
					String feature = features[j];
					// 갱신 과정에서 다루지 않았던 특징이 나오는 경우
					// 무조건 0을 출력하고 다음으로 넘어간다.
					if (!featureToBit.containsKey(feature)) {
						sb.append(0).append("\n");
						continue outer;
					}
					bits += featureToBit.get(feature);
				}
				// 특징들을 가지는 역의 수를 counts에서 찾아 출력한다.
				sb.append(counts[bits]).append("\n");
			}
		}
		if (sb.length() > 0) sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}
	// stationFeatureBit로 표현한 특징들의 부분집합인 특징들을 가지는 역의 수를 m만큼 변경하는 함수
	// cnt는 현재 다루고 있는 비트 자리, bits는 지금까지 고려한 특징들을 의미한다.
	// 부분집합을 구하는 재귀 함수와 동일한 방식으로 진행
	private static void changeFeatureCount(int stationFeatureBit, int cnt, int bits, int m) {
		// 모든 특징들을 고려한 경우
		if (cnt == maxBit+1) {
			// bits에 대응하는 역의 수를 m만큼 변경한다.
			if (bits != 0) counts[bits] += m;
			return;
		}
		// stationFeatureBit에 포함된 특징인 경우
		// 해당 특징을 bits에 포함시키고 다음으로 넘어간다.
		if ((stationFeatureBit & (1 << cnt)) != 0) {
			changeFeatureCount(stationFeatureBit, cnt+1, bits | (1 << cnt), m);
		}
		// 해당 특징을 bits에 포함시키지 않고 다음으로 넘어간다.
		changeFeatureCount(stationFeatureBit, cnt+1, bits, m);
	}

}
