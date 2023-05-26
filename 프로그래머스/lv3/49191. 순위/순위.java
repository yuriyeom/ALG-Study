import java.util.*;
class Solution {
    
    /*
        n번까지 선수가 있음
        
        내가 이긴 선수 목록
        5 :
        4 : 3 2
        3 : 2
        2 : 5
        1 : 2
        
        5 : 2
        4 :
        3 : 4
        2 : 4 3 1
        1 : 
        
        2번의 in 개수가 3 out 개수가 1
    
    */
    
    List<Integer>[] adjList;
    boolean[] visited;
    
    int answer = 0;
    int[][] inout;
    public int solution(int n, int[][] results) {
        adjList = new ArrayList[n+1];
        
        for(int i=1; i<=n; i++){
            adjList[i] = new ArrayList<Integer>();
        }
        
        for(int i=0; i<results.length; i++){
            adjList[results[i][0]].add(results[i][1]);
        }
        
        inout = new int[n+1][2];
        
        
        for(int i=1; i<=n; i++){
            bfs(i, n);
        }
        
        for(int i=1; i<=n; i++){
            if(inout[i][0] + inout[i][1] == n-1) answer++;
        }
        
        return answer;
    }
    
    public void bfs(int start, int n){
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[n+1];
        
        queue.offer(start);
        visited[start] = true;
        
        while(!queue.isEmpty()){
            int p = queue.poll();
            // System.out.println(p);
            
            for(int next : adjList[p]){
                if(visited[next]) continue;
                inout[next][0]++; // in++
                inout[start][1]++; // out++
                visited[next] = true;
                queue.offer(next);
            }
        }
        
    }
}