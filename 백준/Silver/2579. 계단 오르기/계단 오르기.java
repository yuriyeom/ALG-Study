import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] stairs = new int[N+1];
        for(int i=1; i<=N; i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        int[] d = new int[N+1];

        d[1] = stairs[1];
        if(N >= 2)
            d[2] = stairs[1] + stairs[2];
        for(int i=3; i<=N; i++){
            // 2칸 전 기억해둔 값과 (3칸 전 기억해둔 값 + 1칸 전의 값) 중 큰 걸 선택
            d[i] = Math.max(d[i-2], d[i-3] + stairs[i-1]) + stairs[i];
        }

        System.out.println(d[N]);
    }
}
