import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[][] d = new int[501][501];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                d[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= i; j++) {
                d[i][j] = Math.max(d[i-1][j-1], d[i-1][j]) + d[i][j];
            }
        }
        int max = d[n][1];
        for (int i = 2; i <= n; i++) {
            if(max < d[n][i]){
                max = d[n][i];
            }
        }
        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}
