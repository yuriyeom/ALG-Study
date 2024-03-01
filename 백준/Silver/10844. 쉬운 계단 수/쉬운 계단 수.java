import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[][] d = new long[N+1][10];
        for(int i=1; i<=9; i++){
            d[1][i] = 1;
        }

        // N=2 17
        for(int i=2; i<=N; i++){ // 자릿수
            for(int j=0; j<=9; j++){
                if(j==0) {
                    d[i][j] = d[i-1][1];
                }
                else if(j==9) {
                    d[i][j] = d[i-1][8];
                }
                else{
                    d[i][j] = d[i-1][j-1] + d[i-1][j+1];
                }

                d[i][j] %= 1000000000;
            }
        }

        long ans = 0;
        for(int i=0; i<=9; i++)
            ans += d[N][i];

        System.out.println(ans % 1000000000);
    }
}
