class Solution {
    public int[] solution(int n, long left, long right) {
    
        /*
        	1 2 3 
            2 2 3 
            3 3 3 
            N:3 2~5
            (0,2) ~ (1,2)
        
        	1 2 3 4 
            2 2 3 4 
            3 3 3 4 
            4 4 4 4
            N:4 7~14
            (1,3) ~ (3,2)
        
        */
        // (left/N,left%N) ~ (right/N, right%N) 
        
        int[] answer = new int[(int)right-(int)left+1];
        long k=left%n;
        int idx = 0;
        for(long i=left/n; i<=right/n; i++){
            while(true){
                answer[idx++] = (int)Math.max(i, k) + 1;
                k++;
                
                if(i==right/n && k==right%n+1) break;
                if(k == n) break;
            }
            k=0;
        }
        return answer;
    }
}