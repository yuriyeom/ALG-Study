import java.util.*;
class Solution {
    
    public String solution(String[] survey, int[] choices) {
        
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<survey.length; i++){
            
            if(choices[i] <= 3){ // 양수면 왼쪽이, 
                // System.out.println(survey[i].charAt(0));
                map.put(survey[i].charAt(0), map.getOrDefault(survey[i].charAt(0), 0) + score[choices[i]]);
            }else if(choices[i] > 3){ // 음수면 오른쪽이
                // System.out.println(survey[i].charAt(1));
                map.put(survey[i].charAt(1), map.getOrDefault(survey[i].charAt(1), 0) + score[choices[i]]);
            }
            
        }
        
        String answer = "";
        System.out.println(map.getOrDefault('R', 0) + " vs " + map.getOrDefault('T', 0));
        
        if(map.getOrDefault('R', 0) < map.getOrDefault('T', 0)) answer += 'T';
        else answer += 'R';
        
        if(map.getOrDefault('C', 0) < map.getOrDefault('F', 0)) answer += 'F';
        else answer += 'C';
        
        if(map.getOrDefault('J', 0) < map.getOrDefault('M', 0)) answer += 'M';
        else answer += 'J';
        
        if(map.getOrDefault('A', 0) < map.getOrDefault('N', 0)) answer += 'N';
        else answer += 'A';
        
        return answer;
    }
}