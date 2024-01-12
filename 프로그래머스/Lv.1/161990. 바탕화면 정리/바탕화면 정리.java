import java.util.*;
class Solution {
    int n,m;
    public int[] solution(String[] wallpaper) {
        
        n = wallpaper.length;
        m = wallpaper[0].length();
        
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxx = 0;
        int maxy = 0;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char item = wallpaper[i].charAt(j);
                if(item == '#'){
                    minx = Math.min(minx, i);
                    miny = Math.min(miny, j);
                    maxx = Math.max(maxx, i+1);
                    maxy = Math.max(maxy, j+1);
                }
            }
        }
        
        return new int[]{minx, miny, maxx, maxy};
    }
}