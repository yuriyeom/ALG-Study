import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] seats = new int[41];

        /*
        N = 1
        1

        N = 2
        12
        21

        N = 3
        123
        132
        213

        N = 4
        1234
        1243
        1324
        2134
        2143
        */

        seats[0] = 1;
        seats[1] = 1;
        seats[2] = 2;
        for(int i=3; i<=40; i++){
            seats[i] = seats[i-1] + seats[i-2];
        }

        // VIP 좌석을 기준으로 나눠서 경우의 수 곱하기
        // 예제 기준 3 * 2 * 2
        int M = Integer.parseInt(br.readLine());
        int ans = 1;
        int idx = 0;
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(br.readLine());
            ans *= seats[num - idx - 1];
            idx = num;
        }

        // 마지막 VIP부터 끝까지의 경우의 수
        ans *= seats[N - idx];

        System.out.println(ans);
    }
}
