import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        int k = 0;
        while(true){
            k++;
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            int[][] map = new int[N][3];

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<3; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] d = new int[N][3];

            d[0][0] = Integer.MAX_VALUE;
            d[0][1] = map[0][1];
            d[0][2] = Math.min(Integer.MAX_VALUE, map[0][2] + map[0][1]);
            for(int i=1; i<N; i++){
                d[i][0] = Math.min(d[i-1][0], d[i-1][1]) + map[i][0];
                d[i][1] = Math.min(Math.min(Math.min(d[i-1][0], d[i-1][1]), d[i-1][2]), d[i][0]) + map[i][1];
                d[i][2] = Math.min(Math.min(d[i-1][1], d[i-1][2]), d[i][1]) + map[i][2];
            }

            System.out.println(k + ". " + d[N-1][1]);
        }
    }
}
