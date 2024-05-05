import java.util.*;
class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        
        // 길이 잡고
        // 시작점 잡고
        // 같은 수이면 표시 10, 11
        
        int n = 0;
        for(int i=0; i<=10; i++){
            if(arr.length == Math.pow(2, i)){
                n = i;
                break;
            }
        }
        
        for(int l=n; l>0; l--){
            int len = (int)Math.pow(2, l);
            
            for(int i=0; i<arr.length; i+=len){
                for(int j=0; j<arr.length; j+=len){
                    
                    boolean same = true;
                    int first = arr[i][j];
                    
                    if(first != 0 && first != 1) continue;
                    
                    for(int p=i; p<i+len; p++){
                        for(int q=j; q<j+len; q++){
                            if(first != arr[p][q]){
                                same = false;
                                break;
                            }
                        }
                        if(!same) break;
                    }
                    
                    if(same){
                        answer[first]++;
                        for(int p=i; p<i+len; p++){
                            for(int q=j; q<j+len; q++){
                                arr[p][q] = first + 10;
                            }
                        }
                    }
                }
            }
        }
        
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                if(arr[i][j] == 0) answer[0]++;
                else if(arr[i][j] == 1) answer[1]++;
            }
        }
        
        return answer;
    }
}