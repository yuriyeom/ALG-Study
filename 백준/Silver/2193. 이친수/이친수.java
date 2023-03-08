import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[][] d = new long[91][2];

        d[1][0] = 0; d[1][1] = 1;
        for (int i = 2; i <= n; i++) {
            d[i][0] = d[i-1][0] + d[i-1][1];
            d[i][1] = d[i-1][0];
        }

        bw.write(String.valueOf(d[n][0] + d[n][1]));

        br.close();
        bw.close();
    }
}