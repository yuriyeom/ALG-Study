import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,A,B,K,sx,sy,ex,ey,ans;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
        }

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;

        ans = -1;
        bfs();
        System.out.println(ans);
    }

    public static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new Point(sx, sy, 0));
        visited[sx][sy] = true;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            if(p.x == ex && p.y == ey){
                if(isPossible(p.x, p.y)){
                    ans = p.dist;
                }
                return;
            }

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(!isInside(nx, ny) || visited[nx][ny]) continue;
                if(!isPossible(nx, ny)) continue;

                queue.offer(new Point(nx, ny, p.dist+1));
                visited[nx][ny] = true;
            }
        }
    }

    public static boolean isPossible(int x, int y){
        for(int i=0; i<A; i++){
            for(int j=0; j<B; j++){
                if(!isInside(x+i, y+j) || map[x+i][y+j] == 1){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isInside(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Point{
        int x, y, dist;

        public Point(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
