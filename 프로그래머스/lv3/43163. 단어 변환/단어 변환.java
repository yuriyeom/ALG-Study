import java.util.*;

class Solution {
    
    boolean[] used;
    int answer = 0;
    public int solution(String begin, String target, String[] words) {
        
        used = new boolean[words.length];
        
        dfs(begin, target, 0, words);
        
        return answer;
    }
    
    public void dfs(String now, String target, int idx, String[] words){
        if(now.equals(target)){
            answer = idx;
            return;
        }        
    
        for(int i=0; i<words.length; i++){
            if(used[i] || !possibleChange(now, words[i])) continue;
            used[i] = true;
            dfs(words[i], target, idx+1, words);
            used[i] = false;
        }
        
    }
    
    public boolean possibleChange(String str1, String str2){
        int diff = 0;
        
        for(int i=0; i<str1.length(); i++){
            if(str1.charAt(i) != str2.charAt(i)) diff++;
        }
        
        if(diff > 1) return false;
        return true;
    }
}