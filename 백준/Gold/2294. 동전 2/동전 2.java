import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] d = new int[k+1];
        Arrays.fill(d, k+1);
        
        int[] coins = new int[n];
        for(int i=0; i<n; i++){
            coins[i] = Integer.parseInt(br.readLine());
            if(coins[i] <= k)
                d[coins[i]] = 1;
        }

        for(int i=1; i<=k; i++){ // 금액
            for(int j=0; j<n; j++){ // 동전
                if(i-coins[j] > 0){
                    d[i] = Math.min(d[i], d[i-coins[j]] + 1);
                }
            }
        }

        System.out.println(d[k] == k+1 ? -1 : d[k]);

    }
}
