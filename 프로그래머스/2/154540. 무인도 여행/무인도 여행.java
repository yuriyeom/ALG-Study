import java.util.*;
import java.awt.*;
class Solution {
    int r,c;
    int[][] map;
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int[] solution(String[] maps) {
        r = maps.length;
        c = maps[0].length();
        
        map = new int[r][c];
        
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(maps[i].charAt(j) == 'X') map[i][j] = 0;
                else map[i][j] = maps[i].charAt(j) - '0';
            }
        }
        
        ArrayList<Integer> answerList = new ArrayList<>();
        
        visited = new boolean[r][c];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(visited[i][j] || map[i][j] == 0) continue;
                
                int sum = bfs(i, j);
                
                if(sum > 0) answerList.add(sum);
            }
        }
        
        if(answerList.size() == 0){
            return new int[]{-1};
        }
        
        Collections.sort(answerList);
        
        int[] answer = new int[answerList.size()];
        for(int i=0; i<answer.length; i++)
            answer[i] = answerList.get(i);
        
        return answer;
    }
    
    public int bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        
        int sum = 0;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            sum += map[p.x][p.y];
            
            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                
                if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
                if(visited[nx][ny] || map[nx][ny] == 0) continue;
                
                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                    
            }
        }
        
        return sum;
    }
}