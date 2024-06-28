import java.util.*;
class Solution {
    public int solution(String[] board) {
        
        int cntO = 0;
        int cntX = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i].charAt(j) == 'O') cntO++;
                else if(board[i].charAt(j) == 'X') cntX++;
            }
        }
        
        if(cntO < cntX) return 0;
        if(cntO - cntX > 1) return 0;
        
        int lineO = 0;
        int lineX = 0;
        for(int i=0; i<3; i++){
            String row = "";
            String col = "";
            for(int j=0; j<3; j++){
                row += board[i].charAt(j);
                col += board[j].charAt(i);
            }
            if(row.equals("OOO") || col.equals("OOO"))
                lineO++;
            if(row.equals("XXX") || col.equals("XXX"))
                lineX++;
        }
        
        if(board[0].charAt(0) == 'O' && board[1].charAt(1) == 'O' && board[2].charAt(2) == 'O')
                lineO++;
        if(board[0].charAt(0) == 'X' && board[1].charAt(1) == 'X' && board[2].charAt(2) == 'X')
                lineX++;
        if(board[2].charAt(0) == 'O' && board[1].charAt(1) == 'O' && board[0].charAt(2) == 'O')
                lineO++;
        if(board[2].charAt(0) == 'X' && board[1].charAt(1) == 'X' && board[0].charAt(2) == 'X')
                lineX++;
        
        if(lineO > 0 && lineX > 0)
            return 0;
        
        if(lineX > 0 && cntO > cntX){
            return 0;
        }
        
        if(lineO > 0 && cntX >= cntO){
            return 0;
        }
        
        return 1;
    }
}