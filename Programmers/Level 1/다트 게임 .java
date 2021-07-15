import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Solution {
    public int solution(String dartResult) {
        int sum = 0;
        
        List<Integer> scores = new ArrayList<>();
        int i = 0;
        
        Pattern pattern = Pattern.compile("([0-9]+)([SDT])([*#]?)");
        Matcher matcher = pattern.matcher(dartResult);
        
        while(matcher.find()){
            int score = Integer.parseInt(matcher.group(1));
            
            String bonus = matcher.group(2);
            if(bonus.equals("D")){
                score = (int)Math.pow(score, 2);
            }else if(bonus.equals("T")){
                score = (int)Math.pow(score, 3);
            }
            
            String opt = matcher.group(3);
            if(opt != null){
                if(opt.equals("*")){
                    if(i > 0){
                        score += scores.get(i-1);
                        scores.remove(scores.get(i-1));
                    }
                    score *= 2;
                }else if(opt.equals("#")){
                    score *= -1;
                }                
            }
            scores.add(score);
            i++;
        }        
        
        for(int s:scores){
            sum += s;
        }
        
        return sum;
    }
}
