import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        
        int len = s.length();
        
        if(len%2 == 1){ // 홀수
            answer = s.substring(len/2, len/2+1);
        }else{ // 짝수
            answer = s.substring(len/2-1, len/2+1);
        }
        
        return answer;
    }
}