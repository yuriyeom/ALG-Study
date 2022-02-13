import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] field;
    static boolean[][] visited;
    static int M, N, K, num;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        while(T-- > 0){
            M = sc.nextInt();
            N = sc.nextInt();
            K = sc.nextInt();

            field = new int[N][M];
            visited = new boolean[N][M];
            num = 0;

            for(int i=0; i<K; i++){
                int y = sc.nextInt();
                int x = sc.nextInt();

                field[x][y] = 1;
            }

            for(int i=0; i<N; i++) {
                for (int j = 0; j < M; j++) {
                    if(field[i][j] == 0 || visited[i][j]) continue;
                    bfs(i, j);
                    num++;
                }
            }
            System.out.println(num);
        }
    }

    public static void bfs(int startX, int startY){
        Queue<Point> queue = new LinkedList<>();;
        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int k=0; k<4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(field[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
            }
        }
    }
}
