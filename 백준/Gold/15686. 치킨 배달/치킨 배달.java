import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans=Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<Point> houseList, chickenList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // NxN map
        M = Integer.parseInt(st.nextToken()); // 유효한 치킨집 수

        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();

        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) houseList.add(new Point(i, j));
                else if(map[i][j] == 2) chickenList.add(new Point(i, j));
            }
        }

        // 각 집마다 가장 가까운 치킨집과의 거리
        pickChicken(0, 0, new boolean[chickenList.size()], new int[M]);

        System.out.println(ans);
    }

    public static void pickChicken(int idx, int depth, boolean[] visited, int[] output){
        if(depth == M){ // 치킨집 M개 선택했으면
            calDistance(output);
            return;
        }

        for(int i=idx; i<chickenList.size(); i++){
            if(visited[i]) continue;
            visited[i] = true;
            output[depth] = i;
            pickChicken(i+1, depth+1, visited, output);
            visited[i] = false;
        }
    }

    public static void calDistance(int[] chickenIdxs){
        int cityDist = 0; // 도시의 치킨 거리
        for(int i=0; i<houseList.size(); i++){ // 집을 돌면서
            Point house = houseList.get(i);
            int houseDist = Integer.MAX_VALUE; // 해당 집의 치킨 거리
            for(int j=0; j<M; j++){ // 선택한 M개의 치킨집 돌면서
                Point chicken = chickenList.get(chickenIdxs[j]);

                int dist = Math.abs(house.x-chicken.x) + Math.abs(house.y-chicken.y);

                houseDist = Math.min(dist, houseDist);
            }
            cityDist += houseDist;
        }
        ans = Math.min(cityDist, ans);
    }
}