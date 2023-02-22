import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // NxN 도시
    // 1 : 집, 2 : 치킨집
    // 치킨집 개수 : 최대 M개
    // 모든 집의 치킨 거리의 합이 최소

    static int N, M, ans;
    static int[][] map;
    static ArrayList<Point> chickens;
    static ArrayList<Point> homes;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        chickens = new ArrayList<>();
        homes = new ArrayList<>();
        ans = Integer.MAX_VALUE;

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    homes.add(new Point(i, j));
                }else if(map[i][j] == 2){
                    chickens.add(new Point(i, j));
                }
            }
        }

        comb(chickens.size(), new int[M], 0, 0);

        System.out.println(ans);
    }

    private static void comb(int size, int[] output, int idx, int k) {

        if (k == M) {
//            System.out.println(Arrays.toString(output));

            // M개 치킨집 골랐다.

            // 이제 각 집의 치킨거리를 구한다.
            int tempans = 0;

            // 집을 돌면서
            for(Point home : homes){
                // 이 집의 최소 치킨거리를 찾기 위해 비교할 값을 담는 변수
                int temp = Integer.MAX_VALUE;

                // 고른 치킨집을 돌면서
                for(int o : output){
                    temp = Math.min(temp, calDist(home, chickens.get(o)));
                }

                tempans += temp;
            }

            ans = Math.min(ans, tempans);

            return;
        }

        for(int i=idx; i<size; i++){
            output[k] = i;
            comb(size, output, i+1, k+1);
        }
    }

    private static int calDist(Point a, Point b){
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

}
