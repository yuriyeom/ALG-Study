import java.util.*;

// 앞의 수가 클수록 큰 수이므로 다음 수를 선택할 수 있는 범위 내에서 가장 큰 수를 선택
// 10번 테스트케이스에서 시간초과 -> 남은 정답수와 number에서 남은 자릿수가 같으면 더 돌 것 없이 그대로 넣어줘야함
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        int numlen = number.length();
        int anslen = numlen - k;
        char[] arr = number.toCharArray();
       
        int p = 0; // 찾은 max의 index에 +1한 위치. 다음으로 따질 범위의 시작 위치
        while(anslen > 0){ // 채워야할 정답 수
            if(p == numlen - anslen){ // 남은 number의 숫자 개수와 채워야할 정답 자릿수가 같으면 남은 number를 그대로 정답에 넣어준다.
                answer += number.substring(p, numlen); 
                break;
            }
            int max=0;
            for(int i=p; i<=numlen-anslen; i++){ 
                // max, max위치 p 찾기
                if(max < arr[i] - '0') {
                    if(max == arr[i]) continue; // 숫자가 같으면 먼저 나온 수를 선택하도록.
                    max = arr[i] - '0'; // 7
                    p = i+1; // 찾은 maxindex+1 를 다음 시작 위치로.
                }
            }
            answer += max;
            anslen--;        
        }
        return answer;
    }
}
