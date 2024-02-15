class Solution {
    int[][] map;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        map = new int[rows][columns];
        int num = 1;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                map[i][j] = num++;
            }
        }
        
        for(int i=0; i<queries.length; i++){
            int min = rotate(queries[i][0]-1, queries[i][1]-1, queries[i][2]-1, queries[i][3]-1,rows,columns,queries);
            answer[i] = min;
        }
        
        return answer;
    }
    
    public int rotate(int x1, int y1, int x2, int y2, int rows, int columns, int[][] queries){
        int min = Integer.MAX_VALUE;        
        int x = x1, y = y1;
        int d = 0;
        int temp1 = map[x][y];
        int temp2;
        
        while(true){
            min = Math.min(min, temp1);
            
            x += dx[d];
            y += dy[d];
            
            temp2 = map[x][y];
            map[x][y] = temp1;
            temp1 = temp2;
            
            if(d == 0 && y == y2){
                d++;
            }else if(d == 1 && x == x2){
                d++;
            }else if(d == 2 && y == y1){
                d++;
            }
            
            if(x == x1 && y == y1) break;
        }
        
        return min;
    }
}