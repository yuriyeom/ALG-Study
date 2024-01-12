import java.util.*;
class Solution {
    int[][] map;
    int n, m;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int answer = 0;
    public int solution(String[] maps) {
        
        n = maps.length;
        m = maps[0].length();
        
        map = new int[n][m];
        
        int sx=0,sy=0;
        int lx=0,ly=0;
        int ex=0,ey=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char item = maps[i].charAt(j);
                if(item == 'X'){ // 통로
                    map[i][j] = 1;
                }else{
                    map[i][j] = 0;
                    if(item == 'S'){
                        sx = i; sy = j;
                    }else if(item == 'E'){
                        ex = i; ey = j;
                    }else if(item == 'L'){
                        lx = i; ly = j;
                    }
                }
            }
        }
        
        bfs(sx, sy, lx, ly);
        if(answer == 0) return -1;
        int num1 = answer;
        bfs(lx, ly, ex, ey);
        if(answer == 0 || num1 == answer) return -1;
        
        return answer;
    }
    
    public void bfs(int sx, int sy, int gx, int gy){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new Point(sx, sy, 0));
        visited[sx][sy] = true;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            
            if(p.x == gx && p.y == gy){
                answer += p.time;
                return;
            }
            
            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || map[nx][ny] == 1) continue;
                
                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny, p.time + 1));
            }
        }
    }
    
    class Point{
        int x, y, time;
        
        Point(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}