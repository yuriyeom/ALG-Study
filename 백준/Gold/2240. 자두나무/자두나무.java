import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] d = new int[W+1][T+1];

        int[] tree = new int[T+1];
        for(int i=1; i<=T; i++){
            tree[i] = Integer.parseInt(br.readLine());
            if(tree[i] == 1){
                d[0][i] = d[0][i-1] + 1;
            }else{
                d[0][i] = d[0][i-1];
            }

        }

        for(int i=1; i<=W; i++){
            for(int j=1; j<=T; j++){
                if(i>j) {
                    d[i][j] = d[i-1][j];
                    continue;
                }

                if(i%2 == 0 && tree[j] == 1 || i%2 == 1 && tree[j] == 2){
                    d[i][j] = Math.max(d[i][j-1], d[i-1][j-1]) + 1;
                }else{
                    d[i][j] = d[i][j-1];
                }
            }
        }

        int ans = 0;
        for(int i=0; i<=W; i++)
            ans = Math.max(ans, d[i][T]);

        System.out.println(ans);
    }
}
