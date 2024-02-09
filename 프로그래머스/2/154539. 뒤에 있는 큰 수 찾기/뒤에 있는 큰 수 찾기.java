import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        
        int max = numbers[numbers.length-1];
        answer[numbers.length-1] = -1;
        for(int i=numbers.length-2; i>=0; i--){
            if(max <= numbers[i]) {
                answer[i] = -1;
                max = numbers[i];
            }
            if(answer[i] == -1) continue;
            
            if(answer[i+1] == -1){
                answer[i] = max;
            }else if(numbers[i] < numbers[i+1]){
                answer[i] = numbers[i+1];
            }else if(numbers[i] < answer[i+1]){
                answer[i] = answer[i+1];
            }
            
            if(answer[i] != 0) continue;
            for(int j=i+1; j<numbers.length; j++){
                if(numbers[i] < answer[j]){
                    answer[i] = answer[j];
                    break;
                }
            }
            
        }
        
        // for(int i=0; i<numbers.length; i++){
        //     if(answer[i] == -1) continue;
        //     for(int j=i+1; j<numbers.length; j++){
        //         if(numbers[i] < numbers[j]){
        //             answer[i] = numbers[j];
        //             break;
        //         }
        //         if(numbers[i] < answer[j]){
        //             answer[i] = answer[j];
        //             break;
        //         }
        //     }
        // }
        
        return answer;
    }
}