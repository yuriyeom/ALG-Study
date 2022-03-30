import java.awt.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static Queue<Point> queue;
    public static int[][] toma;
    public static int[][] dist;
    public static int n, m;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        m = sc.nextInt(); // 열
        n = sc.nextInt(); // 행

        toma = new int[n+1][m+1];
        dist = new int[n+1][m+1];
        queue = new LinkedList<>();

        // 1:익은 0:안익은 -1:없는
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                toma[i][j] = sc.nextInt();

                if(toma[i][j] == 1) queue.offer(new Point(i, j));
                if(toma[i][j] == 0) dist[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(toma[i][j] != 1) continue;
                bfs(i, j);
            }
        }

        int day = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                day = Math.max(day, dist[i][j]);
                if(dist[i][j] == -1){
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(day);
    }

    public static void bfs(int x, int y){

        while(!queue.isEmpty()){
            Point p = queue.poll();
            for(int k = 0; k<4; k++){
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];

                if(nx < 0 || nx > n || ny < 0 || ny > m) continue;

                if(toma[nx][ny] == 0 && dist[nx][ny] == -1){
                    queue.offer(new Point(nx, ny));
                    dist[nx][ny] = dist[p.x][p.y] + 1;
                }
            }
        }
    }
}
