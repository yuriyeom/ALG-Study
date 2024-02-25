import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        
        int[] dict = new int[26];
        Arrays.fill(dict, -1);
        
        for(int i=0; i<s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(dict[idx] == -1){
                answer[i] = -1;
            }else{
                answer[i] = i - dict[idx];
            }
            dict[idx] = i;
        }
        
        return answer;
    }
}