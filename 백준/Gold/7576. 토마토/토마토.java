import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int M, N;
    static int[][] box;
    static Queue<Point> queue;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        queue = new LinkedList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1) queue.offer(new Point(i, j));
            }
        }

        bfs();

        int ans = 0;
        for(int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                if(box[i][j] == 0){
                    System.out.println(-1);
                    return;
                }
                ans = Math.max(ans, box[i][j]);
            }
        }

        System.out.println(ans-1);
    }

    private static void bfs() {

        boolean[][] visited = new boolean[N][M];

        while(!queue.isEmpty()){

            Point p = queue.poll();

            for(int d=0; d<4; d++){
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(visited[nx][ny]) continue;
                if(box[nx][ny] != 0) continue;

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
                box[nx][ny] = box[p.x][p.y] + 1;
            }
        }
    }
}
