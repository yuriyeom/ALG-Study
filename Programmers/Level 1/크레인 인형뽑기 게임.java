import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int N = board.length;
        
        for(int move : moves){
            for(int i = 0; i < board.length; i++){
                int item = board[i][move - 1];
                if(item != 0){
                    board[i][move - 1] = 0;
                    if(!stack.isEmpty()){
                        if(stack.peek() == item){
                            stack.pop();
                            answer += 2;
                        }else{
                            stack.push(item);
                        }            
                        break;
                    }else{
                        stack.push(item);
                        break;
                    }
                    
                }
            }
        }
        return answer;
    }
}
