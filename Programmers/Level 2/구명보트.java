import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int idx = 0;
        
        Arrays.sort(people);
        
        for(int l=people.length-1; l>=idx; l--){
            if(people[idx] + people[l] <= limit){
                idx++;
                answer++;
            }else{
               answer++; 
            }
        }
        
        return answer;
    }
}
