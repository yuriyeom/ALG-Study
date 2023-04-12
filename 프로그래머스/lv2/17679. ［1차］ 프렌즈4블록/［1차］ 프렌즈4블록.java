import java.util.*;
import java.awt.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        char[][] map = new char[m][n];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                map[i][j] = board[i].charAt(j);
            }
        }
        // print(map, m, n);
        
        /*
        
        while(true)
            boolean fin = true;
            boolean[][] check;
            시작점 이중for문으로 돈다.
                2x2 블록을 돈다.
                    4개가 같은 블록이면 check[][] = true, fin = false
            
            if(fin) break   
            
            check true인거 다 지운다. answer++

            중력ㄱㄱ
        
        */
        
        while(true){
            // print(map, m, n);
            boolean fin = true;
            boolean[][] check = new boolean[m][n];
        
            // 시작점 찾는다.
            for(int i=0; i<m-1; i++){
                for(int j=0; j<n-1; j++){
                    
                    char start = map[i][j]; // 시작점 값
                    if(start == ' ') continue; // 시작부터 비어있으면 패스
                    int cnt = 0; // 시작점과 같은 값인 칸의 개수
                    
                    // 2x2 칸 돈다.
                    for(int a=i; a<i+2; a++){
                        for(int b=j; b<j+2; b++){
                            if(map[a][b] == start) cnt++; // 시작점과 같으면 개수+1
                        }
                    }
                    
                    // 4이면 없앨 애들 찾은거다.
                    if(cnt == 4){
                        for(int a=i; a<i+2; a++){
                            for(int b=j; b<j+2; b++){
                                check[a][b] = true; 
                            }
                        }
                    }
    
                    System.out.println();
                }
            }

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(check[i][j]){
                        answer++;
                        map[i][j] = ' ';
                        fin = false;
                    }
                }
            }
        
            if(fin) break;
            
            // 중력
            gravity(map, m, n);
        }
        
        return answer;
    }
    
    public void gravity(char[][] map, int m, int n){
        for(int j=0; j<n; j++){ // j번째 세로줄 검사
            for(int i=0; i<m; i++){
                
                for(int k=0; k<m-1; k++){
                    if(map[k+1][j] == ' '){
                        char temp = map[k][j];
                        map[k][j] = map[k+1][j];
                        map[k+1][j] = temp;
                    }
                }
                
            }
        }
    }
    
    public void print(char[][] map, int m, int n){
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}