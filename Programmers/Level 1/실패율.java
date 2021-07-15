import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        
        int[] fail = new int[N];
        int[] arrival = new int[N];
        for(int i = 0; i < stages.length; i++){
            for(int j=0; j<N; j++){
                if(j+1 <= stages[i]) arrival[j]++; 
            }            

            if(stages[i] > N) continue;
            fail[stages[i]-1]++;
            
        }
        
        HashMap<Integer, Double> map = new HashMap<>();
        
        for(int i=0; i<N; i++){
            if(fail[i] == 0 | arrival[i] == 0) {
                map.put(i+1, (double)0); continue;
            }
            map.put(i+1, fail[i] / (double)arrival[i]); 
        }
        
        List<Integer> keySetList = new ArrayList<>(map.keySet());		
        
	Collections.sort(keySetList, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));
        
        for(int i=0; i<keySetList.size(); i++){
            answer[i] = keySetList.get(i);
        }
        
        return answer;
    }
}
