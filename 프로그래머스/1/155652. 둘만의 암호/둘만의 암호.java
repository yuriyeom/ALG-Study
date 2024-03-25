import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<s.length(); i++){
            char next = s.charAt(i);
            for(int j=0; j<index; j++){
                if(next == 'z') {
                    next = 'a';
                    if(skip.contains(next + "")) {
                        j--;
                    }
                    continue;
                }
                
                next = (char)(next + 1);
                if(skip.contains(next + "")) {
                    j--;
                }
            }
        
            sb.append(next);
        }
        
        return sb.toString();
    }
}