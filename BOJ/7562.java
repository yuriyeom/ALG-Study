import java.awt.*;
import java.util.*;

public class Main {

    static int[][] board;
    static int l, nowx, nowy, nextx, nexty;
    // 나이트가 이동가능한 범위 8가지
    static int[] dx = {2, 1, -2, -1, 2, 1, -1, -2};
    static int[] dy = {1, 2, 1, 2, -1, -2, -2, -1};
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while(t-- > 0){
            l = sc.nextInt();

            board = new int[l+1][l+1];

            nowx = sc.nextInt();
            nowy = sc.nextInt();
            nextx = sc.nextInt();
            nexty = sc.nextInt();

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < l; j++) {
                    bfs(nowx, nowy);
                }
            }
            System.out.println(board[nextx][nexty] - 1);
        }
    }

    static void bfs(int x, int y){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));
        board[x][y] = 1;

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for (int k = 0; k < 8; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= l || ny < 0 || ny >= l) continue;
                if(board[nx][ny] != 0) continue;

                queue.offer(new Point(nx, ny));
                board[nx][ny] = board[cur.x][cur.y] + 1;

                if(nx == nextx && ny == nexty){
                    return;
                }
            }
        }
    }
}
