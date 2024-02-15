import java.util.*;
class Solution {
    int max = 0;
    int[] answer;
    boolean find;
    public int[] solution(int n, int[] info) {
        answer = new int[info.length];
        find = false;
        
        shot(n, n, 0, 0, info, new int[info.length]);
        
        return find ? answer : new int[]{-1};
    }
    
    public void shot(int n, int rest, int depth, int score, int[] info, int[] lion){
        if(rest < 0) return;
        if(depth == 11 || rest == 0){
            
            // 어피치, 라이언 점수 계산
            int apeachScore = 0;
            int lionScore = 0;
            for(int i=0; i<10; i++){
                if(info[i] > lion[i]){
                    apeachScore += 10 - i;
                }else if(info[i] < lion[i]){
                    lionScore += 10 - i;
                }
            }
            
            // 라이언이 이기는 경우
            if(lionScore > apeachScore){
                find = true;
                // 최대 점수 차 일때 새로 lion 배열 저장
                if(max < Math.abs(apeachScore-lionScore)){
                    max = Math.abs(apeachScore-lionScore);
                    
                    // 화살 남아있으면 0점 맞히기
                    if(rest > 0) 
                        lion[depth-1] = rest;
                    
                    answer = Arrays.copyOf(lion, lion.length);  
                }else if(max == Math.abs(apeachScore-lionScore)){
                    // 화살 남아있으면 0점 맞히기
                    if(rest > 0) 
                        lion[depth-1] = rest;
                    
                    // 점수 차이가 같으면 가장 낮은 점수를 더 많이 맞힌 경우로
                    for(int i=lion.length-2; i>=0; i--){
                        if(lion[i] > answer[i]){
                            answer = Arrays.copyOf(lion, lion.length);
                            break;
                        }
                        if(lion[i] < answer[i]){
                            break;
                        }
                    }
                }
            }
            
            return;
        }
        
        // 쏘기
        if(rest >= info[depth] + 1){
            rest -= info[depth] + 1; // 남은 화살
            score += 10 - depth; // 점수
            lion[depth] = info[depth] + 1; // 라이언 화살 개수
            
            shot(n, rest, depth+1, score, info, lion);
            
            rest += info[depth] + 1;
            score -= 10 - depth;
            lion[depth] = 0;
            
        }
        
        // 안 쏘기
        shot(n, rest, depth+1, score, info, lion);
        
    }
}