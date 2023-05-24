import java.util.*;

class Solution {
    
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int n,m;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        return bfs(maps);
    }
    
    public int bfs(int[][] maps){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] check = new boolean[n][m];
        
        queue.offer(new Point(0, 0, 1));
        check[0][0] = true;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            
            if(p.x == n-1 && p.y == m-1){
                return p.dist;
            }
            
            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(maps[nx][ny] == 0 || check[nx][ny]) continue;
                
                check[nx][ny] = true;
                queue.offer(new Point(nx, ny, p.dist+1));
            }
        }
        return -1;
    }
}

class Point{
    int x, y, dist;
    
    Point(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}