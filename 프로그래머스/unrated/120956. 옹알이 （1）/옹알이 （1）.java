import java.util.*;
class Solution {
    /*
    
    babbling을 잘라서 자른 조각들이 words에 있는지 확인
    
    1) length로 돌려서 substring으로 2개로만 나누려고 했다가 3개도 되는거 깨달음zz
    2) 
    for babbling
        for words
            replace 다 해버림
        남은게 ""이면 answer++
        
    해봤더니 "wyeoo" 가 통과되어서 실패
    사이에 껴있는걸 어케 거르지
    replace할때 그 단어 왼쪽이나 오른쪽이 비어있어야 가능하다
    
    */
    public int solution(String[] babbling) {
        int answer = 0;
        
        ArrayList<String> words = new ArrayList<>(Arrays.asList("aya", "ye", "woo", "ma"));
        
        for(String ba : babbling){
            
            for(String word : words){
                ba = ba.replace(word, "*");
            }
            ba = ba.replace("*", "");
            if(ba.equals("")) {
                answer++;
            }
        }
        
        return answer;
    }
}