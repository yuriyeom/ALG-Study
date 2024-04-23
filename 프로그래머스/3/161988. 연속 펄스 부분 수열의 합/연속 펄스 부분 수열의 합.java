import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        
        int[] arr = new int[sequence.length+1];
        arr[0] = 1;
        for(int i=1; i<arr.length; i++){
            arr[i] = arr[i-1] * -1;
        }
        
        long[][] d = new long[2][sequence.length];
        d[0][0] = sequence[0];
        d[1][0] = sequence[0] * -1;
        for(int i=0; i<2; i++){
            for(int j=1; j<sequence.length; j++){
                d[i][j] = sequence[j] * arr[j+i] + d[i][j-1];
            }
        }
        
        long ans = 0;
        for(int i=0; i<2; i++){
            long min = Long.MAX_VALUE;
            int minIdx = -1;    
            for(int j=0; j<sequence.length; j++){
                if(d[i][j] < min){
                    min = d[i][j];
                    minIdx = j;
                }
            }
            
            for(int j=minIdx+1; j<sequence.length; j++){
                ans = Math.max(d[i][j] - min, ans);
            }
        }
        
        for(int i=0; i<2; i++){
            for(int j=0; j<sequence.length; j++)
                ans = Math.max(d[i][j], ans);
        }
        
        return ans;
    }
}