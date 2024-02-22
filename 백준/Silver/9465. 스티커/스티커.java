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
            int n = Integer.parseInt(br.readLine());

            int[][] score = new int[2][n];
            for(int i=0; i<2; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] d = new int[2][n];

            // 스티커떼면 좌우상하도 쓸수없다
            // 점수 합 최대로 스티커떼기
            d[0][0] = score[0][0];
            d[1][0] = score[1][0];
            if(n > 1){
                d[0][1] = score[1][0] + score[0][1];
                d[1][1] = score[0][0] + score[1][1];
            }
            for(int j=2; j<n; j++){
                d[0][j] = Math.max(d[1][j-1], d[1][j-2]) + score[0][j];
                d[1][j] = Math.max(d[0][j-1], d[0][j-2]) + score[1][j];
            }

            System.out.println(Math.max(d[0][n-1], d[1][n-1]));
        }
    }
}
