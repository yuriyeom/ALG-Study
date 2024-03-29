import java.util.*;
class Solution {
    int[][] tired = {{1,1,1}, {5,1,1}, {25,5,1}};
    int answer;
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        
        backtracking(0, new boolean[minerals.length], picks, minerals, 0);
        
        return answer;
    }
    
    public void backtracking(int mineral, boolean[] visited, int[] picks, String[] minerals, int sum){
        if(isFinish(picks, visited)){
            answer = Math.min(answer, sum);
            return;
        }  
        
        for(int i=0; i<3; i++){ // 곡괭이
            if(picks[i] == 0) continue;
            
            picks[i]--;
            
            int temp = 0;
            for(int j=0; j<5; j++){
                if(mineral+j < minerals.length){
                    temp += tired[i][getMineral(minerals[mineral+j])];
                    visited[mineral+j] = true;
                }
            }
            
            backtracking(mineral+5, visited, picks, minerals, sum + temp);
            
            picks[i]++;
            for(int j=0; j<5; j++){
                if(mineral+j < minerals.length){
                    visited[mineral+j] = false;
                }
            }
            
        }
      
        
    }
    
    public boolean isFinish(int[] picks, boolean[] visited){
        boolean flag = true;
        for(int i=0; i<3; i++){
            if(picks[i] > 0) flag = false;
        }
        if(flag) {
            return true;
        }
        flag = true;
        for(int i=0; i<visited.length; i++){
            if(!visited[i]) flag = false;
        }
        if(flag) {
            return true;
        }
        
        return false;
    }
    
    public int getMineral(String str){
        if(str.equals("diamond")){
            return 0;
        }else if(str.equals("iron")){
            return 1;
        }else{
            return 2;
        }
    }
}