import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String,Integer> map = new HashMap<>();
        
//         for(String p : participant){
//             if(map.containsKey(p)){
//                 int cnt = map.get(p);
//                 map.put(p, cnt+1);
//             }else{
//                 map.put(p, 1);
//             }
//         }
//         for(String c:completion){
//             if(map.containsKey(c)){
//                 int cnt = map.get(c);
//                 if(cnt > 1) map.put(c, cnt-1);
//                 else map.remove(c);
//             }         
//         }
        
//         for (String key : map.keySet()) {
//             answer += key;
//         }
      
      // getOrDefault(Object key, Object defaultValue) : 지정된 키의 값을 반환한다. 키가 없을 경우, default Value로 지정된 데이터를 반환한다. 
      // value를 불러와서 -1해서 다시 넣어주거나 remove하는 대신 value가 0이 아닌 key를 골라내도록 할 수 있다.
        for(String p:participant){
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        for(String c:completion){
            map.put(c, map.get(c)-1);
        }
        
        for(String key:map.keySet()){
            if(map.get(key) != 0){
                answer = key;
            }
        }
        
        return answer;
    }
}
