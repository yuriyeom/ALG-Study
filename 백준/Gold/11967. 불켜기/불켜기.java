import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] map;
    static ArrayList<Point>[][] adjList;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];

        adjList = new ArrayList[N+1][N+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                adjList[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[x][y].add(new Point(a, b));
        }
        ans = 0;

        bfs(1, 1);

        System.out.println(ans);
    }

    public static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] turnon = new boolean[N+1][N+1];
        boolean[][] visited = new boolean[N+1][N+1];
        queue.offer(new Point(x, y));
        visited[x][y] = true;
        turnon[x][y] = true;
        ans++;
        while(!queue.isEmpty()){

            Point p = queue.poll();

            // 불을 켠다.
            if(!adjList[p.x][p.y].isEmpty()){
                visited = new boolean[N+1][N+1];
                visited[p.x][p.y] = true;
                for(Point next : adjList[p.x][p.y]){
                    if(turnon[next.x][next.y]) continue;
                    turnon[next.x][next.y] = true;
                    ans++;
                }
                adjList[p.x][p.y].clear();
            }

            // 이동한다.
            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
                if(visited[nx][ny] || !turnon[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
            }
        }
    }
}
