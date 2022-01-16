import java.util.*;
class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();

        int left = 10;
        int right = 12;

        for(int i=0; i<numbers.length; i++){
            if(numbers[i]==1 ||numbers[i]==4 || numbers[i]==7) {
                sb.append('L');
                left = numbers[i];
            }else if(numbers[i]==3 ||numbers[i]==6 || numbers[i]==9){ 
                sb.append('R');
                right = numbers[i];
            }else{
                if(numbers[i] == 0) numbers[i] = 11; 
                int ldist = Math.abs(numbers[i] - left) / 3 + Math.abs(numbers[i] - left) % 3;
                int rdist = Math.abs(right - numbers[i]) / 3 + Math.abs(right - numbers[i]) % 3;

                if(ldist == rdist){
                    if(hand.equals("left")){
                        sb.append('L');
                        left = numbers[i];                  
                    }else{
                        sb.append('R');
                        right = numbers[i];                        
                    }            
                }
                else if(ldist < rdist){
                    sb.append('L');
                    left = numbers[i];                         
                }else if(ldist > rdist){
                    sb.append('R');
                    right = numbers[i];                        
                }
            }

        }

        return sb.toString();
    }
}
