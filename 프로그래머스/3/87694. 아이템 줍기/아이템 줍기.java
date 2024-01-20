import java.util.*;
/*
사각형 좌표에 2배 해주기
*/
class Solution {
    int[][] map;
    int answer = 0;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[] ddx = {-1, 1, 0, 0, 1, 1, -1, -1};
    int[] ddy = {0, 0, -1, 1, -1, 1, -1, 1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        map = new int[102][102];
        
        for(int n=0; n<rectangle.length; n++){
            for(int i=rectangle[n][0]*2; i<=rectangle[n][2]*2; i++){
                for(int j=rectangle[n][1]*2;j<=rectangle[n][3]*2; j++){
                    map[i][j] = 1;
                }
            }
        }
        
        bfs(characterX*2, characterY*2, itemX*2, itemY*2);
        
        return answer;
    }
    
    void bfs(int cx, int cy, int ix, int iy){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[102][102];
        queue.offer(new Point(cx, cy, 1));
        visited[cx][cy] = true;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            if(p.x == ix && p.y == iy){
                answer = (p.dist-1) / 2;
                return;
            }
            
            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx < 0 || nx > 102 || ny < 0 || ny > 102) continue;
                if(visited[nx][ny] || map[nx][ny] == 0) continue;
                
                if(isEdge(nx, ny)){ // 테두리이면 이동
                    queue.offer(new Point(nx, ny, p.dist+1));
                    visited[nx][ny] = true;
                }
            }
        }
        
    }
    
    boolean isEdge(int x, int y){
        for(int d=0; d<8; d++){
            int nnx = x + ddx[d];
            int nny = y + ddy[d];
            
            if(nnx < 0 || nnx > 102 || nny < 0 || nny > 102) continue;
            if(map[nnx][nny] == 0){
                return true;
            }
        }
        return false;
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