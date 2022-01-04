import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        for(int row=3; row<(brown+yellow)/2; row++){
            int col = (brown + yellow) / row;            
            int yblock = (row - 2)*(col - 2);
            if(yblock == yellow && row >= col){
                answer[0] = row;
                answer[1] = col;
            }
        }
        return answer;
    }
}
