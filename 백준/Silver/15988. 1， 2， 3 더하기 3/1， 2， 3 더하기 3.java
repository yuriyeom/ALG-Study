import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine());

            long[] d = new long[1000001];

            d[1] = 1; d[2] = 2; d[3] = 4;
            for(int i=4; i<=n; i++){
                d[i] = (d[i-3] + d[i-2] + d[i-1]) % 1000000009;
            }

            System.out.println(d[n] % 1000000009);
        }
    }
}
