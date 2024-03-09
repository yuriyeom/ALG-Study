import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] d = new int[N+1];
        d[1] = 1;
        int now = 1;
        for(int i=2; i<=N; i++){ // N
            if(Math.sqrt(i) % 1 > 0){
                int min = Integer.MAX_VALUE;
                for(int j=now; j>=1; j--){
                    min = Math.min(d[j] + d[i-j], min);
                }
                d[i] = min;
            }else{
                d[i] = 1;
                now = i;
            }
        }

        System.out.println(d[N]);
    }
}
