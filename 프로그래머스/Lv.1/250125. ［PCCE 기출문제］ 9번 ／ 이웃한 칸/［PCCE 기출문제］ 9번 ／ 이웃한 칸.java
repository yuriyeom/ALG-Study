class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        int n = board.length;
        
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        
        for(int d=0; d<4; d++){
            int nx = h + dx[d];
            int ny = w + dy[d];
            
            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if(board[h][w].equals(board[nx][ny])) answer++;
        }
        
        return answer;
    }
}