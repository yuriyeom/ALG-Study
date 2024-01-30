import java.util.*;
class Solution {
    public int[] solution(String s) {
        s = s.replace("{", "");
        s = s.replace("}", "");
        String[] splits = s.split(",");
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(String ss : splits)
            map.put(Integer.parseInt(ss), map.getOrDefault(Integer.parseInt(ss), 0) + 1);
        
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet()); 
        
        Collections.sort(list, new Comparator<>(){
           public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b){
               return -Integer.compare(a.getValue(), b.getValue());
           } 
        });
        
        int[] answer = new int[list.size()];
        
        int i = 0;
        for(Map.Entry<Integer, Integer> m : list){
            answer[i++] = m.getKey();
        }
        
        return answer;
    }
}