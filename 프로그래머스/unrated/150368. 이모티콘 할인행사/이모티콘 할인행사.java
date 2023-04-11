import java.util.*;
/*

    1순위. 플러스 가입
    2순위. 판매액 최대
    
    사용자 n명, 이모티콘 m개
    할인율 : 10, 20, 30, 40%
    
    이모티콘 m개의 할인율을 각각 정한다. 순열
    사용자를 돌면서
        이모티콘을 돌면서
            임티 할인율 >= 사용자 할인율 이면 구매
            그러다 구매비용이 가격 이상이면 플러스 ㄱㄱ
    

*/
class Solution {
    
    static int n, m;
    static int[] discount = {10, 20, 30, 40};
    static int[] answer;
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        
        n = users.length; // 사람 수
        m = emoticons.length; // 이모티콘 수
        
        perm(discount.length, new int[m], new boolean[m], 0, users, emoticons);
        
        return answer;
    }
    
    // m개 이모티콘의 할인율을 정한다.
    public void perm(int dlen, int[] output, boolean[] visited, int idx, int[][] users, int[] emoticons){
        
        if(idx == m){
            // System.out.println(Arrays.toString(output));
            /*
                [0, 0]
                [0, 1]
                [0, 2]
                [0, 3]
            */
            int plusCnt = 0;
            int maxProfit = 0;
            for(int user=0; user<n; user++){
                // System.out.println("유저 : " + user);
                boolean plus = false;
                int sum = 0;
                int dis = users[user][0];
                int price = users[user][1];
                for(int emo=0; emo<m; emo++){ // 임티 할인율 >= 사용자 할인율 이면 구매
                    
                    if(discount[output[emo]] >= dis){
                        // 구매
                        sum += emoticons[emo] * (100 - discount[output[emo]]) / 100;
                    }
                    if(sum >= price){
                        // System.out.println("안되겠다 플러스 가입!!");
                        plusCnt++;
                        sum = 0;
                        break;
                    }
                    
                    // System.out.println("구매했더니 이 가격 " + sum);
                    // if(sum >= price){
                    //     // System.out.println("안되겠다 플러스 가입!!");
                    //     // plus = true;
                    //     sum = 0;
                    //     plusCnt++;
                    //     break;
                    // }
                }
                // if(plus) break;
                maxProfit += sum;
            }
            // System.out.println("플러스 가입자 " + plusCnt);
            // System.out.println("구매비용 : " + maxProfit);
            if(plusCnt >= answer[0]){
                if(plusCnt == answer[0]){
                    answer[1] = Math.max(answer[1], maxProfit);
                }else{
                    answer[1] = maxProfit;
                }
                answer[0] = plusCnt;
            }
            
            return;
        }
        
        for(int i=0; i<dlen; i++){
            output[idx] = i;
            perm(dlen, output, visited, idx+1, users, emoticons);
        }
        
    }
}