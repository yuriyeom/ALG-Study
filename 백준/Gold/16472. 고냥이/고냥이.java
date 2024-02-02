import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String catWord = br.readLine();

        int left = 0;
        int right = 0;
        int len = 0;
        int max = 0;
        int[] charCnt = new int[123]; // 'a' ~ 'z'의 인덱스 위치를 사용하기 위해
        while(right < catWord.length()){
            charCnt[catWord.charAt(right)]++;

            // 종류 추가
            if(charCnt[catWord.charAt(right)] == 1) 
                len++;

            // 종류가 초과되면 앞부터 줄인다
            while(len > N){
                charCnt[catWord.charAt(left)]--;

                // 종류 제거
                if(charCnt[catWord.charAt(left)] == 0) 
                    len--;

                left++;
            }
            max = Math.max(max, right-left+1);
            
            right++;
        }

        System.out.println(max);
    }
}
