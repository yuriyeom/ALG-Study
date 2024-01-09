import java.util.*;
class Solution {
    int max = 0;
    public int solution(int k, int[][] dungeons) {
        for(int i=0; i<dungeons.length; i++)
            dfs(dungeons, new boolean[dungeons.length], k, i, 1);
        return max;
    }
    
    public void dfs(int[][] dungeons, boolean[] visited, int nowk, int i, int depth){
        if(nowk < dungeons[i][0]){
            return;
        }
        if(max == dungeons.length) return;
        
        visited[i] = true;
        nowk -= dungeons[i][1];
        max = Math.max(max, depth);
        
        for(int t=0; t<dungeons.length; t++){
            if(visited[t] || i == t) continue;
            dfs(dungeons, visited, nowk, t, depth+1);     
        }
        visited[i] = false;
    }
}