package string;

import java.util.Arrays;

public class Solution_lv2_파일명_정렬 {

	public static void main(String[] args) {
		String[] result = solution(new String[] {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
		System.out.println(Arrays.toString(result));
	}
	// 파일의 이름을 분석한 결과를 저장하는 클래스
	static class FileName implements Comparable<FileName>{
		String head; // HEAD 부분
		int number; // NUMBER 부분
		int index; // files 내에서 해당 파일 이름의 인덱스
		public FileName(String head, int number, int index) {
			this.head = head;
			this.number = number;
			this.index = index;
		}
		// HEAD, NUMBER, 인덱스 우선순위로 정렬
		public int compareTo(FileName f) {
			return this.head.equals(f.head) ? 
					this.number == f.number ? Integer.compare(this.index, f.index) 
					: Integer.compare(this.number, f.number)
				: this.head.compareTo(f.head);
		}
	}
	
	public static String[] solution(String[] files) {
        int N = files.length;
        String[] answer = new String[N];
        FileName[] fileNames = new FileName[N];
        // files 내 각 파일의 이름을 분석해 fileNames에 저장
        for (int i = 0; i < N; i++) {
        	String file = files[i];
        	FileName fileName = analyzeFileName(file, i);
        	fileNames[i] = fileName;
        }
        // FileName의 정렬 기준에 따라 정렬
        Arrays.sort(fileNames);
        // 정렬 결과를 answer에 저장
        for (int i = 0; i < N; i++) {
        	int index = fileNames[i].index;
        	answer[i] = files[index];
        }
        return answer;
    }
	// 파일 이름을 분석하는 함수 
	private static FileName analyzeFileName(String file, int index) {
		String head = ""; // HEAD 부분을 소문자로 바꾼 결과 저장 변수
		int number = 0; // NUMBER 부분을 정수로 바꾼 결과 저장 변수
		// 파일 이름 분석 결과를 head와 number에 저장하기 전 임시로 문자열을 저장해두는 StringBuilder
		StringBuilder temp = new StringBuilder(); 
		// 파일 이름을 소문자로 변경
		String tempFile = file.toLowerCase();
		boolean isHead = true; // 현재 HEAD 부분을 분석 중인지를 나타내는 변수
		boolean isNumber = false; // 현재 NUMBER 부분을 분석 중인지를 나타내는 변수
		boolean completed = false; // 현재 HEAD와 NUMBER 부분을 분석 완료했는지를 나타내는 변수
		for (int i = 0; i < file.length(); i++) {
			char c = tempFile.charAt(i);
			// NUMBER 부분이 끝난 경우
			if ((c<'0' || '9'<c) && isNumber) {
				number = Integer.parseInt(temp.toString());
				completed = true;
				break;
			}
			// HEAD 부분이 끝난 경우
			if (('0'<=c && c<='9') && isHead) {
				isHead = false;
				isNumber = true;
				head = temp.toString();
				temp = new StringBuilder();
			}
			temp.append(c);
		}
		// NUMBER 뒤에 더 이상 문자가 없는 경우
		if (!completed) {
			number = Integer.parseInt(temp.toString());
		}
		return new FileName(head, number, index);
	}

}
