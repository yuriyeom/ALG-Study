import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        
        int wmax = 0, hmax = 0;
        
        for(int[] size : sizes){
            int tempMax = Math.max(size[0], size[1]);
            int tempMin = Math.min(size[0], size[1]);
            
            wmax = Math.max(wmax, tempMax);
            hmax = Math.max(hmax, tempMin);
        }
        return wmax * hmax;
    }
}