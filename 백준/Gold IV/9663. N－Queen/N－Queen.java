import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, ans;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        ans = 0;

        NQueen(0);

        System.out.println(ans);
    }

    public static void NQueen(int x){
        if(x == N){
            ans++;
            return;
        }

        for(int i=0; i<N; i++){
            if(possibleQueen(x, i)){
                map[x][i] = 1;
                NQueen(x+1);
                map[x][i] = 0;
            }
        }

    }

    public static boolean possibleQueen(int x, int y){
        for(int i=0; i<N; i++){
            if(map[x][i] == 1 || map[i][y] == 1) {
                return false;
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(Math.abs(x-i) == Math.abs(y-j) && map[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }
}
