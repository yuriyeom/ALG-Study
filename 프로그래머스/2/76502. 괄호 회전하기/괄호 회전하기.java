import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        ArrayList<Character> list = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            list.add(s.charAt(i));
        }
        
        for(int i=0; i<s.length(); i++){
            char first = list.get(0);
            list.remove(0);
            list.add(first);
            stack.clear();
            for(Character bracket : list){
                if(!stack.isEmpty()){
                    char peek = stack.peek();
                    if((bracket == ']' && peek == '[') 
                      || (bracket == '}' && peek == '{')
                      || (bracket == ')' && peek == '(')){
                        stack.pop();
                    }else{
                        stack.push(bracket);
                    }
                }else stack.push(bracket);
            }
            if(stack.isEmpty()) answer++; 
        }
        
        return answer;
    }
}