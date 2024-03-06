import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] d = new int[1000001];
        d[1] = 1;
        d[2] = 2;
        // 00 11
        // 001 100 111
        // 0011 0000 1001 1100 1111
        for(int i=3; i<=N; i++){
            d[i] = (d[i-1] + d[i-2]) % 15746;
        }

        System.out.println(d[N] % 15746);
    }
}
