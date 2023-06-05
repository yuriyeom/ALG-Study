import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        
        int[] d = new int[prices.length];
        
        for(int i=0; i<prices.length; i++){
            for(int k=i+1; k<prices.length; k++){
                d[i]++;
                if(prices[i] > prices[k]) break;
            }
        }
        
        return d;
    }
}