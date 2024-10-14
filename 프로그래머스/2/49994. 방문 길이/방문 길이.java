import java.util.*;
class Solution {
    int[] dx = {-1, 1, 0, 0}; // UDRL
    int[] dy = {0, 0, 1, -1};
    Set<String> set;
    public int solution(String dirs) {
        int answer = 0;
        
        set = new HashSet<>();
        
        int x = 5;
        int y = 5;
        for(int i=0; i<dirs.length(); i++){
            int dir = -1;
            if(dirs.charAt(i) == 'U'){
                dir = 0;   
            }else if(dirs.charAt(i) == 'D'){
                dir = 1;
            }else if(dirs.charAt(i) == 'R'){
                dir = 2;
            }else{
                dir = 3;
            } 
            
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if(nx < 0 || nx > 10 || ny < 0 || ny > 10)
                continue;
            
            String line = x + "" + y + "" + nx + "" + ny;
            String reverse = nx + "" + ny + "" + x + "" + y;
            x = nx;
            y = ny;   
            if(set.contains(line) || set.contains(reverse)){
                continue;
            }else{
                set.add(line);
                answer++;
            }
        }
        
        return answer;
    }
}