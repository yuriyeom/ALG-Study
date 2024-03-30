import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        HashMap<Integer, Integer> left = new HashMap<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        
        for(int top : topping){
            right.put(top, right.getOrDefault(top, 0) + 1);
        }
        
        for(int top : topping){
            left.put(top, right.getOrDefault(top, 0) + 1);
            right.put(top, right.get(top)-1);
            if(right.get(top) == 0)
                right.remove(top);
            
            if(left.size() == right.size()){
                answer++;
            }
        }
        
        return answer;
    }
}