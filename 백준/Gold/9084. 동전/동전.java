import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());

            int[] coins = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] d = new int[M+1];

            for(int j=0; j<N; j++){ // 사용하는 동전
                for(int i=1; i<=M; i++){ // 만들 금액
                    if(i-coins[j] > 0){
                        d[i] += d[i-coins[j]];
                    }else if(i-coins[j] == 0){
                        d[i]++;
                    }
                }
            }

            System.out.println(d[M]);
        }
    }
}
