import java.util.*;
import java.awt.*;
/*
[[1, 1, 1, 0], 
[1, 2, 2, 0], 
[1, 0, 0, 1], 
[0, 0, 0, 1], 
[0, 0, 0, 3], 
[0, 0, 0, 3]]

*/
class Solution {
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    boolean[][] visited;
    public int[] solution(int m, int n, int[][] picture) {
        visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(visited[i][j] || picture[i][j] == 0) continue;
                numberOfArea++;
                bfs(m, n, i, j, visited, picture);
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    void bfs(int m, int n, int i, int j, boolean[][] visited, int[][] picture){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i,j));
        visited[i][j] = true;
        int area = 1;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx< 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(visited[nx][ny] || picture[nx][ny] == 0) continue;
                if(picture[nx][ny] != picture[p.x][p.y]) continue;
                
                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                area++;
            }
        }
        
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, area);
    }
}