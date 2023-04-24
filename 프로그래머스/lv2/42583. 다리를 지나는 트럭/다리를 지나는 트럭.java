import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<Integer> queue = new LinkedList<>();
        
        for(int i=0; i<truck_weights.length; i++){
            queue.offer(truck_weights[i]);
        }
        
        Queue<Integer> bridge = new LinkedList<>();
        
        for(int l=0; l<bridge_length; l++){
            bridge.offer(0);
        }
        
        int sum = 0;
        int answer = 1;
        
        while(true){
            // 맨 앞 트럭
            int front = bridge.poll();
            sum -= front;
            
            if(!queue.isEmpty() && sum + queue.peek() <= weight){
                int newTruck = queue.poll();
                bridge.offer(newTruck);
                sum += newTruck;
            }else{
                bridge.offer(0);
            }
            
            if(sum == 0) break;
            
            // 시간++
            answer++; 
        }
        
        return answer;
    }
}