import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            int n = Integer.parseInt(br.readLine());

            int[] d = new int[11];

            d[1] = 1; d[2] = 2; d[3] = 4;
            for(int i=4; i<=n; i++){
                if(d[i-1] > 0)
                    d[i] += d[i-1];
                if(d[i-2] > 0)
                    d[i] += d[i-2];
                if(d[i-3] > 0)
                    d[i] += d[i-3];
            }
            
            System.out.println(d[n]);
        }
    }
}
