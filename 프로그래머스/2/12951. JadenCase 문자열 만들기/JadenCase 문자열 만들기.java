import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(s, " ", true);
        while (st.hasMoreTokens()) {
            String next = st.nextToken();
            if(next.equals(" ")){
                sb.append(" ");
            }else{
                String first = next.substring(0,1);
                String rest = next.substring(1);

                if(first.charAt(0) - '0' >= 49 && first.charAt(0) - '0' <= 74){
                    first = first.toUpperCase();
                }
                rest = rest.toLowerCase();
                
                sb.append(first + rest);
            }
         }
        
        return sb.toString();
    }
}