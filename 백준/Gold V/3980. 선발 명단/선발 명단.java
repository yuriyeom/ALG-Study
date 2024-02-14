import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int C, ans;
    static int[][] players;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());

        while(C-- > 0){
            players = new int[11][11];

            StringTokenizer st;
            for(int i=0; i<11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++){
                    players[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0;
            pickPlayer(0, new boolean[11], 0);
            System.out.println(ans);
        }
    }

    public static void pickPlayer(int depth, boolean[] visited, int sum){
        if(depth == 11){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i=0; i<11; i++){
            if(visited[i] || players[depth][i] == 0) continue;
            visited[i] = true;
            pickPlayer(depth+1, visited, sum + players[depth][i]);
            visited[i] = false;
        }
    }
}
