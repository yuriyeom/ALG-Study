import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, k, ans;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[2][N];

        for(int i=0; i<2; i++){
            String line = br.readLine();
            for(int j=0; j<N; j++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        ans = 0;

        bfs();

        System.out.println(ans);
    }

    public static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[2][N];
        queue.offer(new Point(0, 0, 0));
        visited[0][0] = true;
        int time = 0;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            if(time != p.time){
                map[0][time] = 0;
                map[1][time] = 0;
                time++;
            }
            if(p.y == time-1) continue;

            int[] dx = {p.x, p.x, p.x == 1 ? 0 : 1};
            int[] dy = {1, -1, k};

            for(int d=0; d<3; d++){
                int nx = dx[d];
                int ny = p.y + dy[d];

                if(ny < 0) continue;
                if(ny >= N){
                    ans = 1;
                    return;
                }
                if(map[nx][ny] == 0 || visited[nx][ny]) continue;

                queue.offer(new Point(nx, ny, p.time+1));
                visited[nx][ny] = true;
            }
        }

    }

    static class Point{
        int x, y, time;

        public Point(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
