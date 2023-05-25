import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int len = elements.length;
        HashSet<Integer> set = new HashSet<>();     
        
        int[][] d = new int[len+1][len+1];
        
        d[0] = Arrays.copyOf(elements, len);
        d[1] = Arrays.copyOf(elements, len);
        
        for(int i=0; i<len; i++){
            set.add(elements[i]);
        }
        
        for(int i=2; i<len+1; i++){ // 자리 수
            for(int j=0; j<len; j++){
                d[i][j] = d[i-1][j-1 < 0 ? len-1 : j-1] + elements[j];
                set.add(d[i][j]);
            }
        }
        return set.size();
    }
}