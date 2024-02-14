import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] d = new int[triangle.length][triangle[triangle.length-1].length];
        d[0][0] = triangle[0][0];
        for(int i=1; i<triangle.length; i++){
            for(int j=0; j<triangle[i].length; j++){
                if(j == 0){
                    d[i][j] = d[i-1][j] + triangle[i][j];
                }else{
                    d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]) + triangle[i][j];
                }
            }
        }
        
        for(int i=0; i<triangle[triangle.length-1].length; i++){
            answer = Math.max(answer, d[triangle.length-1][i]);
        }
        
        return answer;
    }
}