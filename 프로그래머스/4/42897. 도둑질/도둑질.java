import java.util.*;
class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        int[] sum1 = new int[money.length]; // 첫번째집 O
        int[] sum2 = new int[money.length]; // 첫번째집 X
        
        sum1[0] = money[0];
        sum1[1] = money[0];
        
        sum2[0] = 0;
        sum2[1] = money[1];
        
        for(int i=2; i<money.length; i++){
            sum1[i] = Math.max(sum1[i-2] + money[i], sum1[i-1]);
            sum2[i] = Math.max(sum2[i-2] + money[i], sum2[i-1]);
        }
        
        return Math.max(sum1[money.length-2], sum2[money.length-1]);
    }
}