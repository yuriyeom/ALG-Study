import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H+1][N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[a][b+1] = -1;
        }

        for(int i=0; i<=3; i++){
            dfs(0, i);
        }

        System.out.println(-1);
    }

    public static void dfs(int depth, int m){

        if (depth == m) {
            if(down()){
                System.out.println(m);
                System.exit(0);
            }

            return;
        }

        for(int i=1; i<=H; i++){
            for(int j=1; j<N; j++){
                if(map[i][j] != 0 ||  map[i][j+1] != 0) continue;

                map[i][j] = 1;
                map[i][j+1] = -1;
                dfs(depth+1, m);
                map[i][j] = 0;
                map[i][j+1] = 0;
            }
        }

    }

    public static boolean down(){
        for(int i=1; i<=N; i++){ // i가 i로 도착하는가
            int now = 1;
            int col = i;
            while(now <= H){
                if(map[now][col] == 1){
                    col++;
                }else if(map[now][col] == -1){
                    col--;
                }
                now++;
            }
            if(i != col) {
                return false;
            }
        }
        return true;
    }
}
