import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        /*
        N = 1
        0,1,2,3,4,5,6,7,8,9
        = 10

        N = 2
        0 + 0~9
        1 + 1~9
        2 + 2~9
        3 + 3~9
        9 + 9

        = 10+9+8+...+1

        N=1 | 1 1 1 1 1 1 1 1 1
        N=2 | 1 2 3 4 5 6 7 8 9

        */

        int[][] d = new int[N+1][10];

        Arrays.fill(d[1], 1);
        for(int i=1; i<=N; i++){
            d[i][0] = 1;
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=9; j++){
                d[i][j] = (d[i][j-1] + d[i-1][j]) % 10007;
            }
        }

        int ans = 0;
        for(int j=0; j<=9; j++)
            ans += d[N][j];

        System.out.println(ans % 10007);
    }
}