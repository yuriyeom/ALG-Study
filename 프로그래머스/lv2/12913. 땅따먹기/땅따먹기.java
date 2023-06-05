import java.util.*;
class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        int[][] d = new int[land.length][4];
        
        d[0] = Arrays.copyOf(land[0], 4);
        for(int i=1; i<land.length; i++){
            d[i][0] = land[i][0] + Math.max(Math.max(d[i-1][1], d[i-1][2]), d[i-1][3]);
            d[i][1] = land[i][1] + Math.max(Math.max(d[i-1][0], d[i-1][2]), d[i-1][3]);
            d[i][2] = land[i][2] + Math.max(Math.max(d[i-1][0], d[i-1][1]), d[i-1][3]);
            d[i][3] = land[i][3] + Math.max(Math.max(d[i-1][0], d[i-1][1]), d[i-1][2]);
        }
        
        for(int i=0; i<4; i++){
            answer = Math.max(answer, d[land.length-1][i]);
        }
        
        return answer;
    }
}