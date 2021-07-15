import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        
        for(int j=0; j<lost.length; j++){
            for(int i=0; i<reserve.length; i++){
                if(lost[j] == reserve[i]) {
                    lost[j] = n + 2;
                    reserve[i] = n + 2;  
                    answer += 1;
                    break;
              }
            }
        }
        
        for(int l : lost){
            for(int i=0; i<reserve.length; i++){
                if(reserve[i] == l+1 | reserve[i] == l-1 ){
                    answer += 1;
                    reserve[i] = n + 2;
                    break;
                }
            }
        }
        return answer;
    }
}
