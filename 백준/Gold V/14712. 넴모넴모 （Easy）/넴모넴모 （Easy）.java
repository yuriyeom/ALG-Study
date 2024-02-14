import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        ans = 0;
        
        nemo(0, 0, new boolean[N][M]);
        
        System.out.println(ans);
    }

    public static void nemo(int x, int y, boolean[][] visited){
        if(y == M){ // 마지막 열이 끝났으면 종료
            if(stopGame()){
                ans++;
            }
            return;
        }

        if(x == N){
            nemo(0, y+1, visited);
            return;
        }
        
        if(visited[x][y]){
            nemo(x+1, y, visited);
            return;
        }

        visited[x][y] = true;

        // 놓기
        map[x][y] = 1;
        nemo(x+1, y, visited);
        map[x][y] = 0;

        // 안 놓기
        nemo(x+1, y, visited);

        visited[x][y] = false;
    }

    public static boolean stopGame(){
        for(int i=0; i<N-1; i++){
            for(int j=0; j<M-1; j++){
                if(map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1){
                    return false;
                }
            }
        }

        return true;
    }
}
