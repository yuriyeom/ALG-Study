import java.util.*;
class Solution {
    
    List<Integer>[] adjList;
    int max;
    int maxdepth;
    public int solution(int n, int[][] edge) {
        
        max = 0;
        maxdepth = 0;
        
        adjList = new ArrayList[n+1];
        for(int i=1; i<n+1; i++){
            adjList[i] = new ArrayList<Integer>();
        }
        
        for(int[] ed : edge){
            adjList[ed[0]].add(ed[1]);
            adjList[ed[1]].add(ed[0]);
        }
        
        bfs(n);
        
        return max;
    }
    
    public void bfs(int n){
        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        
        queue.offer(new Point(1, 0));
        visited[1] = true;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            
            if(maxdepth < p.depth){
                maxdepth = p.depth;
                max = 1;
            }else if(maxdepth == p.depth){
                max++;
            }
            
            for(int next : adjList[p.node]){
                if(visited[next]) continue;
                
                visited[next] = true;
                queue.offer(new Point(next, p.depth+1));
            }
        }
    }
    
    class Point{
        int node, depth;
        
        Point(int node, int depth){
            this.node = node;
            this.depth = depth;
        }
    }
}