import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for(int i=0; i<=discount.length-10; i++){ // 시작점
            HashMap<String, Integer> map = new HashMap<>();
            for(int j=0; j<want.length; j++){
                map.put(want[j], number[j]);
            }
            
            int sum = 0;
            for(int n : number) sum += n;

            for(int j=i; j<i+10; j++){
                if(!map.containsKey(discount[j])) continue;
                
                if(map.get(discount[j]) > 0){
                    map.put(discount[j], map.get(discount[j])-1);
                    sum--;
                }
            }
            if(sum == 0) answer++;
        }
        
        return answer;
    }
}