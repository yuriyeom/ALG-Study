import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    static int N, taza, out, ans;
    static int[][] map;
    static boolean[] visited;
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        
        // N 이닝
        // 9명 경기
        
        // 경기
        // 3아웃 => 다음 이닝
        // 타순은 계속 유지
        // 1,2,3루 선수 이동 : 이닝마다 초기화
        // 1번선수는 4번 고정
        
        
        N = sc.nextInt();
        
        map = new int[N][10];
        
        for(int i=0; i<N; i++) {
            for(int j=1; j<=9; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        
        ans = 0;
        
        // 타순 정하고. 1번 고정 유의
        perm(9, new int[10], new boolean[10], 1);
        
        System.out.println(ans);
    }
    
    public static void perm(int n, int[] output, boolean[] visited, int idx) {
        
        if(idx == n+1) {
            if(output[4] == 1) {
//                System.out.println(Arrays.toString(output));
                int playScore = play(output);
//                System.out.println(playScore);
                ans = Math.max(ans, playScore);
            }
            return;
        }
        
        for(int i=1; i<=n; i++) {
            if(visited[i]) continue;
            
            output[idx] = i;
            visited[i] = true;
            perm(n, output, visited, idx+1);
            visited[i] = false;
            
        }
    }

    private static int play(int[] output) {
        int score = 0;
        taza = 0;
        out = 0;
        int[] loo = new int[4]; // 루
        
        for(int i=0; i<N; i++) { // 이닝
            loo = new int[4]; // 이닝이 시작될 때 루에 있는 선수는 없다.
            out = 0; // 아웃 초기화
            
            while(true) {
                taza++; // 타순 +1
                if(taza == 10) taza = 1; // 한바퀴 돌았으면 다시 1번타자
                
                // output[taza] : 타자
                if(map[i][output[taza]] == 0) {
                    // 아웃
                    out++;
                    if(out == 3) {
                        break; // 다음 이닝으로
                    }
                }else if(map[i][output[taza]] == 1) {
                    // 안타
                    if(loo[3] == 1) {
                        loo[3] = 0;
                        score++;
                    }
                    if(loo[2] == 1) {
                        loo[2] = 0;
                        loo[3] = 1;
                    }
                    if(loo[1] == 1) {
                        loo[1] = 0;
                        loo[2] = 1;
                    }
                    loo[1] = 1;
                }else if(map[i][output[taza]] == 2) {
                    // 2루타
                    if(loo[3] == 1) {
                        loo[3] = 0;
                        score++;
                    }
                    if(loo[2] == 1) {
                        loo[2] = 0;
                        score++;
                    }
                    if(loo[1] == 1) {
                        loo[1] = 0;
                        loo[3] = 1;
                    }
                    loo[2] = 1;
                }else if(map[i][output[taza]] == 3) {
                    // 3루타
                    if(loo[3] == 1) {
                        loo[3] = 0;
                        score++;
                    }
                    if(loo[2] == 1) {
                        loo[2] = 0;
                        score++;
                    }
                    if(loo[1] == 1) {
                        loo[1] = 0;
                        score++;
                    }
                    loo[3] = 1;
                }else if(map[i][output[taza]] == 4) {
                    // 홈런
                    score++;
                    for(int k=3; k>=1; k--) {
                        if(loo[k] == 1) score++;
                        loo[k] = 0;
                    }
                }
            }
            
        }
        return score;
        
    }

}