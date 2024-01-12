import java.util.*;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        ArrayList<String> nameList = new ArrayList(Arrays.asList(name));
        
        for(int i=0; i<photo.length; i++){
            int score = 0;
            for(String p : photo[i]){
                if(nameList.contains(p)){
                    int idx = nameList.indexOf(p);
                    score += yearning[idx];
                }
            }
            answer[i] = score;
        }
        
        return answer;
    }
}