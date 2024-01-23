import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, L, R, peopleCnt, countryCnt;
    static boolean end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        end = true;
        while(true) {

            end = true;
            visited = new boolean[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(visited[i][j]) continue;

                    bfs(i, j);
                }
            }

            if(end) break;
            day++;
        }

        System.out.println(day);
    }
    public static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        boolean[][] move = new boolean[N][N];
        move[x][y] = true;

        peopleCnt = map[x][y];
        countryCnt = 1;
        while(!queue.isEmpty()){
            Point p = queue.poll();

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny] || move[nx][ny]) continue;

                if(Math.abs(map[p.x][p.y] - map[nx][ny]) <= R
                        && Math.abs(map[p.x][p.y] - map[nx][ny]) >= L){
                    queue.offer(new Point(nx, ny));
                    visited[nx][ny] = true;
                    end = false;
                    move[nx][ny] = true;
                    peopleCnt += map[nx][ny];
                    countryCnt++;
                }
            }
        }

        // move true인 곳 => peopleCnt / countryCnt
        if(countryCnt <= 1) return;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (move[i][j]){
                    map[i][j] = peopleCnt / countryCnt;
                }
            }
        }
    }
}
