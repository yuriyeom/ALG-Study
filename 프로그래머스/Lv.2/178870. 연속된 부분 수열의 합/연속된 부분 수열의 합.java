import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int[] sum = new int[sequence.length+1];
        for(int i=0; i<sequence.length; i++){
            sum[i+1] = sum[i] + sequence[i];
        }
        
        int i=0; int j=0;
        int len = Integer.MAX_VALUE;
        while(i <= j){
            if(sum[j] - sum[i] < k){
                j++;
            }else if(sum[j] - sum[i] == k){
                if(len > j-i){ // 제일 짧은건지 비교
                    len = j-i;
                    answer = new int[]{i, j-1};
                }
                j++;
            }else if(sum[j] - sum[i] > k){
                i++;
            }
            
            if(j == sum.length) break;
        }
        
        return answer;
    }
}