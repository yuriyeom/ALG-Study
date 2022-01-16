import java.util.*;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        List<Integer> rule = new ArrayList<>(Arrays.asList(6, 5, 4, 3, 2)); // 2개 맞힘 rule[i]==2 indexOf i찾아 i=4 1 2 3 4 5등 여기없으면 6등
        // lottos의 0의 개수를 구함
        // lottos와 win_nums의 일치 개수를 구함        
        int zero_cnt = 0;
        int correct = 0;
        for(int l : lottos){
            if(l == 0) zero_cnt++;
            for(int w : win_nums){
                if(l == w) correct++; 
            }
        }
        // 최고는 일치 개수 + 0의 개수 
        int best = correct + zero_cnt;
        // 최저는 일치 개수
        int worst = correct;
        // rule에서 등수 찾기
        for(int r : rule){
            if(best == r) answer[0] = rule.indexOf(r) + 1;
            if(worst == r) answer[1] = rule.indexOf(r) + 1;
        }
        if(answer[0] == 0) answer[0] = 6;
        if(answer[1] == 0) answer[1] = 6;
        return answer;
    }
}
