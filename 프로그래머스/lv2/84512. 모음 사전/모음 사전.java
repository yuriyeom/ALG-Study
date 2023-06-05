import java.util.*;
class Solution {
    
    String[] dict = {"A", "E", "I", "O", "U"};
    int idx;
    boolean find;
    
    public int solution(String word) {
        idx = 0;
        find = false;
        
        dfs("", 0, word);
        
        return idx;
    }
    
    public void dfs(String str, int len, String target){
        if(find || len > 5) return;
        
        if(target.equals(str)){
            find = true;
            return;
        }
        
        idx++;
        
        for(int i=0; i<5; i++){
            dfs(str + dict[i], len+1, target);
        }
        
    }
}