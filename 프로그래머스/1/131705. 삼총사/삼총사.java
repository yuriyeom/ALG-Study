import java.util.*;
class Solution {
    int answer;
    public int solution(int[] number) {
        answer = 0;
        
        pickStudent(0, 0, new int[3], number);
        
        return answer;
    }
    
    public void pickStudent(int depth, int idx, int[] output, int[] number){
        if(depth == 3){
            int sum = 0;
            for(int o : output){
                sum += number[o];
            }
            if(sum == 0) answer++;
            return;
        }
        
        for(int i=idx; i<number.length; i++){
            output[depth] = i;
            pickStudent(depth+1, i+1, output, number);
        }
    }
}