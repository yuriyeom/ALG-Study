import java.util.*;
/*
direction을 돌려줘야 하나?
2차원 배열은 맞는것같고
*/
class Solution {
    int[][] map;
    int[] dx = {1, 0, -1};
    int[] dy = {0, 1, -1};
    public int[] solution(int n) {
        map = new int[n+1][n+1];
        
        int size = 0;
        for(int i=1; i<=n; i++){
            size += i;
        }
        
        int item = 1;
        int i=0, j=0;
        int dir = 0;
        while(item != size+1){
            // 값 넣기
            map[i][j] = item++;
            
            // 다음 칸 가보기
            i += dx[dir];
            j += dy[dir];
            
            // 끝 위치면 방향 바꾸기
            // 다음 칸을 이미 채웠으면 방향 바꾸기
            if(i == n || j == n || map[i][j] != 0){
                // 전 위치로 돌아오기
                i -= dx[dir];
                j -= dy[dir];
                
                // 방향 틀고
                dir = (dir + 1) % 3;
                
                // 새로 이동
                i += dx[dir];
                j += dy[dir];
            }
        }
        
        List<Integer> answerList = new ArrayList<>();
        for(int p=0; p<n; p++){
            for(int q=0; q<n; q++){
                if(map[p][q] == 0) continue;
                answerList.add(map[p][q]);    
            }
        }
        
        int[] answer = new int[size];
        for(int p=0; p<size; p++){
            answer[p] = answerList.get(p);
        }
        
        return answer;
    }
}