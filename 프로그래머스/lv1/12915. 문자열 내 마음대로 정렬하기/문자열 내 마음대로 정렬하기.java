import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        String[] answer = {};

        Arrays.sort(strings, new Comparator<>(){
            
            public int compare(String a, String b){
                if(a.charAt(n) > b.charAt(n)){
                    return 1;
                }else if(a.charAt(n) < b.charAt(n)){
                    return -1;
                }else{
                    return a.compareTo(b);
                }
            }
            
        });
        
        return strings;
    }
}