import java.util.*;
import java.awt.*;
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int solution(int[][] land) {
        int answer = 0;
        
        int n = land.length;
        int m = land[0].length;
        int[] areaArr = new int[n*m];   
        
        boolean[][] v = new boolean[n][m];
        int idx = 1;
        boolean[] visited = new boolean[n*m];;
        for(int j=0; j<m; j++){
            // visited = new boolean[n*m];
            Arrays.fill(visited, false);
            int sum = 0;
            for(int i=0; i<n; i++){
                if(!v[i][j] && land[i][j] != 0){
                    bfs(i, j, n, m, land, v, idx, areaArr);
                    idx++;
                }
                
                if(land[i][j] == 0 || visited[land[i][j]]) continue;
                
                visited[land[i][j]] = true;
                sum += areaArr[land[i][j]];
            }
            answer = Math.max(sum, answer);       
        }
        
        return answer;
    }
    
    public void bfs(int i, int j, int n, int m, int[][] land, boolean[][] visited, int idx, int[] areaArr){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));
        visited[i][j] = true;
        
        int area = 0;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            land[p.x][p.y] = idx;
            area++;
            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || land[nx][ny] == 0) continue;
                    
                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
            }
        }
        
        areaArr[idx] = area;
    }
}