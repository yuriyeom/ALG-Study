import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int len = arr.length;
        if(len == 1) return new int[]{-1};
        
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for(int i=0; i<len; i++){
            if(arr[i] < min){
                min = arr[i];
                minIdx = i;
            }
        }
        
        int[] answer = new int[len-1];
        for(int i=0; i<len; i++){
            if(i == minIdx){
                continue;
            }else if(i > minIdx){
                answer[i-1] = arr[i];
            }else{
                answer[i] = arr[i];
            }
        }
        
        return answer;
    }
}