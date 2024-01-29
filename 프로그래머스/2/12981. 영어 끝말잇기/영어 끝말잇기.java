import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        Set<String> set = new HashSet<>();
        set.add(words[0]);
        
        for(int i=1; i<words.length; i++){
            String front = words[i-1];
            String word = words[i];
            if(set.contains(word) || front.charAt(front.length()-1) != word.charAt(0)){
                answer[0] = i % n +1; // 사람  
                answer[1] = i / n +1; // 단어 
                break;
            }
            set.add(word);
        }
        
        return answer;
    }
}