import java.util.*;
/*
map
S:시작, O:가능, X:장애물

이동하다가 조건 걸리면 초기화하고 돌아가야함

*/
class Solution {
    int[][] map;
    int w, h;
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        w = park.length;
        h = park[0].length();
        
        map = new int[w][h];
        
        int sx = -1; int sy = -1;
        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
                int item = park[i].charAt(j);
                if(item == 'S') {
                    sx = i; sy = j;
                    map[i][j] = 0;
                }else if(item == 'O'){
                    map[i][j] = 0;
                }else if(item == 'X'){
                    map[i][j] = 1;
                }
            }
        }
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int d = -1;
        int x = sx;
        int y = sy;
        for(String route : routes){
            int td=d; int tx=x; int ty=y; // 임시 변수
            boolean success = true;
            
            String[] splits = route.split(" ");
            
            // 방향 결정
            if(splits[0].equals("N")){
                td = 0;
            }else if(splits[0].equals("S")){
                td = 1;
            }else if(splits[0].equals("W")){
                td = 2;
            }else if(splits[0].equals("E")){
                td = 3;
            }
            
            // 주어진 칸만큼 이동
            for(int i=0; i<Integer.parseInt(splits[1]); i++){
                tx += dx[td];
                ty += dy[td];
                
                if(tx < 0 || tx >= w || ty < 0 || ty >= h || map[tx][ty] == 1) {
                    success = false;
                    break;
                }
            }
            if(success){
                d = td;
                x = tx;
                y = ty;
            }
        }
        
        return new int[]{x, y};
    }
}