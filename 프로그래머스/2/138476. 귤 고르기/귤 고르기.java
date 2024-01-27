import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        int[] cnts = new int[10000001];
        for(int i=0; i<tangerine.length; i++){
            cnts[tangerine[i]]++;
        }
        
        Arrays.sort(cnts);
        for(int i=cnts.length-1; i>0; i--){
            if(k <= 0) break;
            k -= cnts[i];
            answer++;
        }
        
        return answer;
    }
}