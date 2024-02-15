import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum1=0, sum2=0;
        
        for(int i=0; i<queue1.length; i++){
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        
        while(true){
            if(sum1 > sum2){
                int n = q1.poll();
                q2.offer(n);
                sum1 -= n;
                sum2 += n;
            }else if(sum1 < sum2){
                int n = q2.poll();
                q1.offer(n);
                sum2 -= n;
                sum1 += n;
            }else{
                break;
            }
            
            if(answer > queue1.length * 2 + 1) {
                answer = -1;
                break;
            }
            
            answer++;
        }
        
        return answer;
    }
}