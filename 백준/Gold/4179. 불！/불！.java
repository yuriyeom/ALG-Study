import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static Queue<Point> fires;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fires = new LinkedList<>();

        int jx=-1, jy=-1;
        int[][] dist = new int[R][C];
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'J'){
                    jx = i; jy = j;
                }else if(map[i][j] == 'F'){
                    fires.add(new Point(i, j));
                    dist[i][j] = 1;
                }
            }
        }

        int[][] fire = fire(fires, dist);
        jihun(jx, jy, fire);

        System.out.println("IMPOSSIBLE");
    }

    public static int[][] fire(Queue<Point> fires, int[][] dist){

        while(!fires.isEmpty()){
            Point p = fires.poll();

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                if(map[nx][ny] == '#') continue;
                if(dist[nx][ny] != 0 && dist[nx][ny] <= dist[p.x][p.y] + 1) continue;

                fires.offer(new Point(nx, ny));
                dist[nx][ny] = dist[p.x][p.y] + 1;
            }
        }

        return dist;
    }

    public static void jihun(int jx, int jy, int[][] fire){
        Queue<Point> queue = new LinkedList<>();
        int[][] dist = new int[R][C];
        queue.offer(new Point(jx, jy));
        dist[jx][jy] = 1;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    System.out.println(dist[p.x][p.y]);
                    System.exit(0);
                }
                if(map[nx][ny] == '#') continue;
                if(dist[nx][ny] != 0 && dist[nx][ny] <= dist[p.x][p.y] + 1) continue;
                if(fire[nx][ny] != 0 && fire[nx][ny] <= dist[p.x][p.y] + 1) continue;

                queue.offer(new Point(nx, ny));
                dist[nx][ny] = dist[p.x][p.y] + 1;
            }
        }


    }
}
