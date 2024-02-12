import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        int[] d = new int[1000001];
        Arrays.fill(d, 1000001);
        d[x] = 0;
        if(x+n <= y) d[x+n] = 1;
        if(x*2 <= y) d[x*2] = 1;
        if(x*3 <= y) d[x*3] = 1;
        for(int i=x; i<=y; i++){
            
            if(i+n <= y) d[i+n] = Math.min(d[i]+1, d[i+n]);
            if(i*2 <= y) d[i*2] = Math.min(d[i]+1, d[i*2]);
            if(i*3 <= y) d[i*3] = Math.min(d[i]+1, d[i*3]);
        }
        
        return d[y] == 1000001 ? -1 : d[y];
    }
}