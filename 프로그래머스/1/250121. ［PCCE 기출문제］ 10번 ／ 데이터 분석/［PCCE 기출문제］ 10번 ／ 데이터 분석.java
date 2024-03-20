import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        int extIdx = getIdx(ext);
        
        ArrayList<int[]> list = new ArrayList<>();

        for(int[] d : data){
            if(d[extIdx] < val_ext){
                list.add(d);
            }
        }
        
        int sortIdx = getIdx(sort_by);
        
        Collections.sort(list, new Comparator<>(){
           public int compare(int[] a, int[] b){
               return Integer.compare(a[sortIdx], b[sortIdx]);
           } 
        });
        
        int[][] answer = new int[list.size()][4];
        
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
    
        return answer;
    }
    
    public int getIdx(String str){
        if(str.equals("code")) return 0;
        else if(str.equals("date")) return 1;
        else if(str.equals("maximum")) return 2;
        else return 3;
    }
}