import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cnt, max, deleteWall;
    static int[] areas;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, -1, 0, 1}; // 서북동남
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        areas = new int[2500];

        cnt = 0;
        max = 0;
        deleteWall = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]) continue;
                bfs(i, j, cnt);
                cnt++;
            }
        }
        
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){

                for(int d=0; d<4; d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if(map[i][j] == map[nx][ny]) continue;

                    deleteWall = Math.max(deleteWall, areas[map[i][j]] + areas[map[nx][ny]]);
                }
            }
        }

        System.out.println(cnt);
        System.out.println(max);
        System.out.println(deleteWall);

    }

    public static void bfs(int x, int y, int num){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        int area = 0;

        while(!queue.isEmpty()){
            Point p = queue.poll();
            area++;

            int[] dir = getDirection(map[p.x][p.y]);

            map[p.x][p.y] = num;
            for(int d=0; d<4; d++){
                if(dir[d] != 0) continue;

                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;

                queue.offer(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        max = Math.max(max, area);
        areas[num] = area;
    }

    public static int[] getDirection(int num){
        int[] dir = new int[4];

        while(num > 0){
            if(num >= 8){
                dir[3] = 1;
                num -= 8;
            }else if(num >= 4){
                dir[2] = 1;
                num -= 4;
            }else if(num >= 2){
                dir[1] = 1;
                num -= 2;
            }else if(num >= 1){
                dir[0] = 1;
                num -= 1;
            }
        }

        return dir;
    }
}
