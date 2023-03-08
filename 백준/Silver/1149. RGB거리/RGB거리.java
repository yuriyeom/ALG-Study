import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] house = new int[N+1][3];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            house[i][0] = Integer.parseInt(st.nextToken());
            house[i][1] = Integer.parseInt(st.nextToken());
            house[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] d = new int[N+1][3];

        d[1][0] = house[1][0];
        d[1][1] = house[1][1];
        d[1][2] = house[1][2];
        for(int i=2; i<=N; i++){
            d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + house[i][0];
            d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + house[i][1];
            d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + house[i][2];
        }

        System.out.println(Math.min(Math.min(d[N][0], d[N][1]), d[N][2]));
    }
}
