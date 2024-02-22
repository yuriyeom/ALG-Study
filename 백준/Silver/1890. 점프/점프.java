import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] d = new long[N][N];
        d[0][0] = 1;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i == N-1 && j == N-1) continue;

                int dist = map[i][j];

                if(i+dist < N)
                    d[i+dist][j] += d[i][j];

                if(j+dist < N)
                    d[i][j+dist] += d[i][j];

            }
        }

        System.out.println(d[N-1][N-1]);
    }
}
