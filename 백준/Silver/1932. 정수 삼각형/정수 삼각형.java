import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] d = new int[n+1][n+1];

        StringTokenizer st;
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=i; j++){
                d[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=2; i<=n; i++){
            for(int j=1; j<=i; j++){
                d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]) + d[i][j];
            }
        }

        int max = 0;
        for(int i=1; i<=n; i++){
            max = Math.max(d[n][i], max);
        }

        System.out.println(max);
    }
}
