import java.util.*;
class Solution {
    List<int[]> minList = new ArrayList<>();
    public int[][] solution(int n) {
        
        hanoi(n, 1, 2, 3);
        
        int[][] answer = new int[minList.size()][2];
        
        for(int i=0; i<minList.size(); i++){
            answer[i] = minList.get(i);
            // System.out.println(Arrays.toString(minList.get(i))); 
        }
        
        return answer;
    }
    
    public void hanoi(int n, int start, int mid, int end){
        
        if(n == 1){
            minList.add(new int[]{start, end});
            return;
        }
        
        hanoi(n-1, start, end, mid);
        
        minList.add(new int[]{start, end});
        
        hanoi(n-1, mid, start, end);
    }
}