import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,H,W,sx,sy,fx,fy;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        fx = Integer.parseInt(st.nextToken())-1;
        fy = Integer.parseInt(st.nextToken())-1;

        bfs(sx, sy);
    }

    public static void bfs(int x, int y){
        Queue<Rect> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        queue.offer(new Rect(x, y, 0));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Rect p = queue.poll();

            if(p.x == fx && p.y == fy){
                System.out.println(p.dist);
                return;
            }

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny] || map[nx][ny] == 1) continue;
                if(!possible(nx, ny)) continue;

                queue.offer(new Rect(nx, ny, p.dist+1));
                visited[nx][ny] = true;

            }
        }
        System.out.println(-1);
    }

    public static boolean possible(int x, int y){
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(x+i < 0 || x+i >= N || y+j < 0 || y+j >= M || map[x+i][y+j] == 1){
                    return false;
                }
            }
        }
        return true;
    }
}
class Rect{
    int x, y, dist;

    public Rect(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}