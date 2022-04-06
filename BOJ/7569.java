import java.util.*;

class Point{
    int x;
    int y;
    int z;

    public Point(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

public class Main {

    static int m, n, h;
    static int[][][] toma;
    static int[][][] dist;
    static Queue<Point> queue;
    static int[] dx= {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        m = sc.nextInt();
        n = sc.nextInt();
        h = sc.nextInt();

        toma = new int[n+1][m+1][h+1];
        dist = new int[n+1][m+1][h+1];
        queue = new LinkedList<>();

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    toma[i][j][k] = sc.nextInt();
                    if(toma[i][j][k] == 1)  queue.offer(new Point(i, j, k));
                    if(toma[i][j][k] == 0) dist[i][j][k] = -1;
                }
            }
        }

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(toma[i][j][k] != 1 || dist[i][j][k] > 0) continue;
                    bfs(i, j, k);
                }
            }
        }

        int max = 0;
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    max = Math.max(max, dist[i][j][k]);
                    if(dist[i][j][k] == -1) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(max);
    }

    static void bfs(int x, int y, int z){
        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for (int p = 0; p < 6; p++) {
                int nx = cur.x + dx[p];
                int ny = cur.y + dy[p];
                int nz = cur.z + dz[p];

                if(nx < 0 || nx >= n || ny < 0 || ny >=m || nz < 0 || nz >= h) continue;
                if(dist[nx][ny][nz] >= 0) continue;

                queue.offer(new Point(nx, ny, nz));
                dist[nx][ny][nz] = dist[cur.x][cur.y][cur.z] + 1;
            }
        }
    }
}
