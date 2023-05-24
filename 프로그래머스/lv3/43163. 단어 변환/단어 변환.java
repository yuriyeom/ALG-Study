import java.util.*;

class Solution {
    
    List<String> wordList;
    int answer;
    boolean[] used;
    
    public int solution(String begin, String target, String[] words) {
        
        wordList = Arrays.asList(words);
        used = new boolean[wordList.size()];
        
        if(!wordList.contains(target)) return 0;
        
        dfs(begin, target, begin, 0);
        
        return answer;
    }
    
    public void dfs(String begin, String target, String now, int idx){
        if(now.equals(target)){
            answer = idx;
            return;
        }
        List<String> candidates = possibleChange(now);
        
        for(String newstr : candidates){
            int i = wordList.indexOf(newstr);
            used[i] = true;
            dfs(begin, target, newstr, idx+1);
            used[i] = false;
        }
        
    }
    
    public List<String> possibleChange(String str1){
        List<String> answers = new ArrayList<>();
        
        for(String str2 : wordList){
            if(used[wordList.indexOf(str2)]) continue;
            int diff = 0;
            for(int i=0; i<str1.length(); i++){
                if(str1.charAt(i) != str2.charAt(i)) diff++;
                if(diff > 1) break;
            }
            if(diff == 1) answers.add(str2);
        }
       
        return answers;
    }
}