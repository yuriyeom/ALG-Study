import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] wine = new int[n+1];
        for(int i=1; i<=n; i++){
            wine[i] = Integer.parseInt(br.readLine());
        }

        // 1. 마시기
        // 2. 연속 3잔 마실 수 없다.

        // 6 10 13 9 8 1
        // o  o  x o o x
        // 1-2-4-5 = 33

        int[] d = new int[n+1];
        d[1] = wine[1];
        if(n >= 2)
            d[2] = wine[1] + wine[2];

        for(int i=3; i<=n; i++){
            d[i] = Math.max(Math.max(d[i-2], d[i-3] + wine[i-1]) + wine[i], d[i-1]);
        }
        
        System.out.println(d[n]);
    }
}
