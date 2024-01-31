import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 날짜의 수
        int K = Integer.parseInt(st.nextToken()); // 며칠동안 연속인지

        int[] temps = new int[N+1];

        int answer = -100 * N;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            temps[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=2; i<=N; i++){
            temps[i] = temps[i] + temps[i-1];
        }

        for(int i=K; i<=N; i++){
            int sum = temps[i] - temps[i-K];
            answer = Math.max(sum, answer);
        }
        System.out.println(answer);
    }
}
