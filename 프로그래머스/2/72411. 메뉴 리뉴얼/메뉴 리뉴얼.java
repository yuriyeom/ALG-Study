import java.util.*;
class Solution {
    HashMap<String, Integer>[] map;
    public String[] solution(String[] orders, int[] course) {
        
        // 코스 개수별 map 생성
        map = new HashMap[11];
        for(int i=0; i<11; i++)
            map[i] = new HashMap<>();
        
        // 주문 문자열 먼저 정렬 
        for(int i=0; i<orders.length; i++){
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = new String(arr);
        }
        
        // 코스 선택 후 개수 세기 
        for(int cour : course){ // 코스 개수
            for(String order : orders){ // 주문
                pickCourse(order, 0, 0, cour, new int[cour], new boolean[order.length()]);
            }
        }
        
        // 각 map에서 2 이상 코스 고르기
        ArrayList<String> answerList = new ArrayList<>();
        for(HashMap<String,Integer> m : map){
            int maxCnt = 0;
            for(String key : m.keySet()){
                maxCnt = Math.max(maxCnt, m.get(key));
            }         
            
            if(maxCnt < 2) continue;
            
            for(String key : m.keySet())
                if(m.get(key) == maxCnt) answerList.add(key);
        }
        
        Collections.sort(answerList);
        
        return answerList.toArray(new String[answerList.size()]);
    }
    
    public void pickCourse(String order, int depth, int idx, int n, int[] output, boolean[] visited){
        if(depth == n){
            String str = "";
            for(int i=0; i<output.length; i++)
                str += order.charAt(output[i]);
            map[n].put(str, map[n].getOrDefault(str, 0) + 1);
            return;
        }
        
        
        for(int i=idx; i<order.length(); i++){
            if(visited[i]) continue;
            
            output[depth] = i;
            visited[i] = true;
            pickCourse(order, depth+1, i+1, n, output, visited);
            visited[i] = false;
        }
        
    }
}