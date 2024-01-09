import java.util.*;
class Solution {
    int max = 0;
    public int solution(int k, int[][] dungeons) {
        dfs(dungeons, new boolean[dungeons.length], k, 0);
        return max;
    }
    
    public void dfs(int[][] dungeons, boolean[] visited, int nowk, int i){
        
        max = Math.max(max, i);
        
        for(int t=0; t<dungeons.length; t++){
            if(visited[t] || nowk < dungeons[t][0]) continue;
            visited[t] = true; 
            dfs(dungeons, visited, nowk-dungeons[t][1], i+1);     
            visited[t] = false;
        }
    }
}